package tk.skeptick.vk.apiclient

import com.github.kittinunf.result.Result
import com.github.kittinunf.result.flatMap
import kotlinx.coroutines.experimental.Deferred
import kotlinx.serialization.KSerializer
import tk.skeptick.vk.apiclient.oauth.OAuth
import tk.skeptick.vk.apiclient.oauth.OAuthResponse
import java.io.File

enum class HttpMethod {
    GET,
    POST
}

class CaptchaResponse(
    val sid: String,
    val key: String
)

class VkApiRequest<T : Any>(
    val client: VkApiClient,
    val httpMethod: HttpMethod,
    val path: String,
    val parameters: List<Pair<String, Any?>>,
    var serializer: KSerializer<T>
)

class UploadFilesRequest<T : Any>(
    val client: VkApiClient,
    val uploadUrl: String,
    val files: List<Triple<String, String, File>>,
    val serializer: KSerializer<T>
)

fun <T : Any> VkApiRequest<T>.async(
    captchaResponse: CaptchaResponse? = null
): Deferred<Result<T, Exception>> =
    client.executeMethod(this, captchaResponse?.parameters)

fun <T : Any> UploadFilesRequest<T>.async()
    : Deferred<Result<T, Exception>> = client.uploadFile(this)

suspend fun <T : Any> VkApiRequest<T>.awaitResult(
    captchaResponse: CaptchaResponse? = null
): Result<T, Exception> =
    async(captchaResponse).await()

suspend fun <T : Any> UploadFilesRequest<T>.awaitResult()
    : Result<T, Exception> = async().await()

fun TransportClient.authorize(
    authData: OAuth,
    captchaResponse: CaptchaResponse? = null
): Deferred<Result<OAuthResponse, Exception>> = execute(
    httpMethod = HttpMethod.POST,
    url = DefaultApiParams.OAUTH_URL,
    parameters = combineParameters(
        authData.parameters,
        captchaResponse?.parameters ?: emptyList()
    ),
    mapper = { parseOAuthResponse(it) }
)