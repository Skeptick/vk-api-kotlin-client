@file:Suppress("NOTHING_TO_INLINE", "UNUSED")

package tk.skeptick.vk.apiclient

import io.ktor.client.request.forms.*
import io.ktor.http.*
import io.ktor.util.date.GMTDate
import io.ktor.utils.io.core.ByteReadPacket
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import tk.skeptick.vk.apiclient.domain.*
import tk.skeptick.vk.apiclient.domain.models.Address
import tk.skeptick.vk.apiclient.methods.DefaultListResponse
import tk.skeptick.vk.apiclient.methods.ExtendedListResponse
import tk.skeptick.vk.apiclient.methods.VkApiResponse
import tk.skeptick.vk.apiclient.methods.uploads.UploadFileError
import tk.skeptick.vk.apiclient.oauth.OAuth
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

internal inline fun ParametersBuilder.append(first: String, second: Any?) {
    if (second != null) append(first, second.toString())
}

internal inline val GMTDate.unixtime: Int
    get() = (GMTDate(seconds, minutes, hours, dayOfMonth, month, year).timestamp / 1000).toInt()

internal inline val Pair<String, String>.formPart: FormPart<String>
    get() = FormPart(first, second, Headers.Empty)

internal inline val UploadableFile.formPart: FormPart<ByteReadPacket>
    get() = FormPart(key, ByteReadPacket(content.bytes), content.filename.filenameHeader)

private inline val String.filenameHeader: Headers
    get() = headersOf(HttpHeaders.ContentDisposition, "filename=$this")

internal inline fun <T : Any> list(nestedSerializer: KSerializer<T>) =
    DefaultListResponse.serializer(nestedSerializer)

internal inline fun <T : Any> extendedList(nestedSerializer: KSerializer<T>) =
    ExtendedListResponse.serializer(nestedSerializer)

internal inline fun Boolean.asInt(): Int? =
    if (this) 1 else 0

//--- Mappers to parameters list ---//

internal inline val OAuth.parameters: Parameters
    get() = when (this) {
        is OAuth.User -> when (this) {
            is OAuth.User.CodeFlow -> Parameters.build {
                append("client_id", clientId.toString())
                append("client_secret", clientSecret)
                append("code", code)
                append("redirect_uri", redirectUri)
            }
            is OAuth.User.PasswordFlow -> Parameters.build {
                append("grant_type", "password")
                append("client_id", clientId.toString())
                append("client_secret", clientSecret)
                append("username", username)
                append("password", password)
            }
        }
        is OAuth.Community -> when (this) {
            is OAuth.Community.CodeFlow -> Parameters.build {
                append("client_id", clientId.toString())
                append("client_secret", clientSecret)
                append("code", code)
                append("redirect_uri", redirectUri)
            }
        }
    }

internal inline val CaptchaResponse.parameters: Parameters
    get() = Parameters.build {
        append("captcha_sid", sid)
        append("captcha_key", key)
    }

internal inline val Media.media: String
    get() = buildString {
        append(ownerId)
        append("_").append(id)
        if (accessKey != null) append("_").append(accessKey!!)
    }

internal inline val Attachment.attachment: String
    get() = buildString {
        append(typeAttachment.value)
        append(media)
    }

//--- Parsers ---//

internal val json = Json(JsonConfiguration.Stable.copy(
    encodeDefaults = false,
    ignoreUnknownKeys = true,
    isLenient = false,
    serializeSpecialFloatingPointValues = false,
    allowStructuredMapKeys = true,
    prettyPrint = false,
    unquotedPrint = false,
    useArrayPolymorphism = false
))

internal suspend fun <T : Any> parseMethodResponse(
    responseString: String,
    serializer: KSerializer<T>
): Result<T, Exception> =
    Result.of<VkApiResponse<T>, Exception> {
        json.parse(VkApiResponse.serializer(serializer), responseString)
    }.flatMap { vkApiResponse ->
        vkApiResponse.asSuspendableResult()
    }

private inline fun <T : Any> VkApiResponse<T>.asSuspendableResult(): Result<T, Exception> =
    when {
        error != null -> Result.error(error)
        response != null -> Result.of(response)
        else -> Result.error(IllegalStateException("Response is null"))
    }


internal fun <T : Any> parseUploadResponse(
    responseString: String,
    serializer: KSerializer<T>
): Result<T, Exception> {
    val jsonObject = json.parseJson(responseString).jsonObject
    return when (jsonObject.contains("error")) {
        true -> Result.error(json.fromJson(UploadFileError.serializer(), jsonObject))
        false -> Result.of(json.fromJson(serializer, jsonObject))
    }
}

internal fun parseOAuthResponse(
    responseString: String
): Result<OAuthResponse, Exception> {
    val jsonObject = json.parseJson(responseString).jsonObject
    return when (jsonObject.contains("access_token")) {
        true -> Result.of(json.fromJson(OAuthResponse.serializer(), jsonObject))
        false -> Result.error(json.fromJson(OAuthError.serializer(), jsonObject))
    }
}


//--- Serializers ---//

internal fun Keyboard.serialize(): String = json.stringify(Keyboard.serializer(), this)

internal fun Address.Timetable.serialize(): String = json.stringify(Address.Timetable.serializer(), this)