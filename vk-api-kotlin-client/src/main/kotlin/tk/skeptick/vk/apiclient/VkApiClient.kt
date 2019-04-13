@file:Suppress("NOTHING_TO_INLINE")

package tk.skeptick.vk.apiclient

import com.github.kittinunf.result.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.forms.*
import io.ktor.client.request.post
import io.ktor.client.request.request
import io.ktor.client.response.HttpResponse
import io.ktor.client.response.readText
import io.ktor.http.*
import tk.skeptick.vk.apiclient.domain.Language
import tk.skeptick.vk.apiclient.oauth.OAuth
import tk.skeptick.vk.apiclient.oauth.OAuthResponse

class VkApiClient(
    accessToken: String,
    val httpClient: HttpClient
) : ApiClient {

    init { httpClient.configure() }

    private var baseParams = Parameters.build {
        append("access_token", accessToken)
        append("v", DefaultApiParams.API_VERSION)
    }

    var language: Language? = null
        set(lang) {
            field = lang
            baseParams = Parameters.build {
                appendAll(baseParams)
                remove("lang")
                append("lang", lang?.value)
            }
        }

    override suspend fun <T : Any> executeMethod(
        request: VkApiRequest<T>,
        additionalParameters: Parameters?
    ): Result<T, Exception> = try {
        parseMethodResponse(request(additionalParameters).readText(), request.serializer)
    } catch (exception: Exception) {
        Result.error(exception)
    }

    override suspend fun <T : Any> uploadFile(
        request: UploadFilesRequest<T>
    ): Result<T, Exception> = try {
        parseUploadResponse(request().readText(), request.serializer)
    } catch (exception: Exception) {
        Result.error(exception)
    }

    private suspend inline operator fun <T : Any> VkApiRequest<T>.invoke(
        additionalParameters: Parameters? = null
    ): HttpResponse = httpClient.request(DefaultApiParams.API_URL + path) {
        method = httpMethod
        val parameters = baseParams + parameters + additionalParameters
        if (method == HttpMethod.Post) body = FormDataContent(parameters)
        else url.parameters.appendAll(parameters)
    }

    private suspend inline operator fun <T : Any> UploadFilesRequest<T>.invoke(): HttpResponse =
        httpClient.submitFormWithBinaryData(uploadUrl, formData {
            files.map(UploadableFile::formPart).forEach(::append)
        })

    companion object {

        suspend fun byOAuth(
            httpClient: HttpClient,
            authData: OAuth,
            captchaResponse: CaptchaResponse? = null
        ): VkApiClient = when (val response = authorize(httpClient, authData, captchaResponse)) {
            is Result.Success -> VkApiClient(response.value.accessToken, httpClient)
            is Result.Failure -> throw response.error
        }

        suspend fun authorize(
            httpClient: HttpClient,
            authData: OAuth,
            captchaResponse: CaptchaResponse? = null
        ): Result<OAuthResponse, Exception> = httpClient.configure().post<String>(DefaultApiParams.OAUTH_URL) {
            body = FormDataContent(authData.parameters + captchaResponse?.parameters)
        }.let(::parseOAuthResponse)

        private fun HttpClient.configure(): HttpClient = config {
            expectSuccess = false
        }

    }

}