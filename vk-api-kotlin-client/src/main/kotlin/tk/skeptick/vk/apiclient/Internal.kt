@file:Suppress("NOTHING_TO_INLINE", "UNUSED")

package tk.skeptick.vk.apiclient

import io.ktor.client.request.forms.*
import io.ktor.http.*
import io.ktor.util.date.GMTDate
import io.ktor.utils.io.core.ByteReadPacket
import kotlinx.serialization.KSerializer
import tk.skeptick.vk.apiclient.methods.VkApiResponse
import tk.skeptick.vk.apiclient.methods.uploads.UploadFileError
import tk.skeptick.vk.apiclient.oauth.OAuthError
import tk.skeptick.vk.apiclient.oauth.OAuthResponse

internal interface MethodsContext {

    val client: ApiClient

    fun <T : Any> String.httpGet(
        serializer: KSerializer<T>,
        block: ParametersBuilder.() -> Unit = {}
    ): VkApiRequest<T> = VkApiRequest(
        client = client,
        httpMethod = HttpMethod.Get,
        path = this,
        parameters = Parameters.build(block),
        serializer = serializer
    )

    fun <T : Any> String.httpPost(
        serializer: KSerializer<T>,
        block: ParametersBuilder.() -> Unit = {}
    ): VkApiRequest<T> = VkApiRequest(
        client = client,
        httpMethod = HttpMethod.Post,
        path = this,
        parameters = Parameters.build(block),
        serializer = serializer
    )

}

internal inline fun Boolean.asInt(): Int? = if (this) 1 else 0

internal inline val GMTDate.unixtime: Int
    get() = (GMTDate(seconds, minutes, hours, dayOfMonth, month, year).timestamp / 1000).toInt()

internal inline val Pair<String, String>.formPart: FormPart<String>
    get() = FormPart(first, second, Headers.Empty)

internal inline val UploadableFile.formPart: FormPart<ByteReadPacket>
    get() = FormPart(key, ByteReadPacket(content.bytes), content.filename.filenameHeader)

private inline val String.filenameHeader: Headers
    get() = headersOf(HttpHeaders.ContentDisposition, "filename=$this")

//--- Parsers ---//

internal suspend fun <T : Any> parseMethodResponse(
    responseString: String,
    serializer: KSerializer<T>
): VkResult<T, Exception> =
    VkResult.of<VkApiResponse<T>, Exception> {
        json.parse(VkApiResponse.serializer(serializer), responseString)
    }.flatMap { vkApiResponse ->
        when {
            vkApiResponse.error != null -> VkResult.error(vkApiResponse.error)
            vkApiResponse.response != null -> VkResult.of(vkApiResponse.response)
            else -> VkResult.error(IllegalStateException("Response is null"))
        }
    }

internal fun <T : Any> parseUploadResponse(
    responseString: String,
    serializer: KSerializer<T>
): VkResult<T, Exception> {
    val jsonObject = json.parseJson(responseString).jsonObject
    return when (jsonObject.contains("error")) {
        true -> VkResult.error(json.fromJson(UploadFileError.serializer(), jsonObject))
        false -> VkResult.of(json.fromJson(serializer, jsonObject))
    }
}

internal fun parseOAuthResponse(
    responseString: String
): VkResult<OAuthResponse, Exception> {
    val jsonObject = json.parseJson(responseString).jsonObject
    return when (jsonObject.contains("access_token")) {
        true -> VkResult.of(json.fromJson(OAuthResponse.serializer(), jsonObject))
        false -> VkResult.error(json.fromJson(OAuthError.serializer(), jsonObject))
    }
}