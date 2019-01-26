package tk.skeptick.vk.apiclient

import com.github.kittinunf.result.Result
import io.ktor.http.HttpMethod
import io.ktor.http.Parameters
import kotlinx.serialization.KSerializer

class CaptchaResponse(
    val sid: String,
    val key: String
)

class FileContent(
    val filename: String,
    val bytes: ByteArray
)

class UploadableFile(
    val key: String,
    val content: FileContent
)

class VkApiRequest<T : Any>(
    val client: ApiClient,
    val httpMethod: HttpMethod,
    val path: String,
    val parameters: Parameters,
    var serializer: KSerializer<T>
)

class UploadFilesRequest<T : Any>(
    val client: ApiClient,
    val uploadUrl: String,
    val files: List<UploadableFile>,
    val serializer: KSerializer<T>
)

suspend fun <T : Any> VkApiRequest<T>.execute(captchaResponse: CaptchaResponse? = null): Result<T, Exception> {
    return client.executeMethod(this, captchaResponse?.parameters)
}

suspend fun <T : Any> UploadFilesRequest<T>.execute(): Result<T, Exception> {
    return client.uploadFile(this)
}