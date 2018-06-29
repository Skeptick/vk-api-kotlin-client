@file:Suppress("NOTHING_TO_INLINE", "UNUSED")

package tk.skeptick.vk.apiclient

import com.github.kittinunf.result.Result
import kotlinx.serialization.KSerializer
import kotlinx.serialization.internal.ListLikeSerializer
import kotlinx.serialization.json.JSON
import org.json.JSONArray
import org.json.JSONObject
import tk.skeptick.vk.apiclient.methods.DefaultListResponse
import tk.skeptick.vk.apiclient.methods.ExtendedListResponse
import tk.skeptick.vk.apiclient.domain.VkApiError
import tk.skeptick.vk.apiclient.methods.uploads.UploadFileError
import tk.skeptick.vk.apiclient.oauth.OAuth
import tk.skeptick.vk.apiclient.oauth.OAuthError
import tk.skeptick.vk.apiclient.oauth.OAuthResponse

internal val json = JSON.nonstrict

internal interface MethodsContext {

    val client: VkApiClient

    fun String.httpGet(
        vararg parameters: Pair<String, Any?>
    ): VkApiRequestBuilder = VkApiRequestBuilder(
        client = client,
        httpMethod = HttpMethod.GET,
        path = this,
        parameters = parameters.toList()
    )

    fun String.httpPost(
        vararg params: Pair<String, Any?>
    ): VkApiRequestBuilder = VkApiRequestBuilder(
        client = client,
        httpMethod = HttpMethod.POST,
        path = this,
        parameters = params.toList()
    )

}

internal class VkApiRequestBuilder(
    private val client: VkApiClient,
    private val httpMethod: HttpMethod,
    private val path: String,
    private val parameters: List<Pair<String, Any?>>) {

    fun <T : Any> withSerializer(
        serializer: KSerializer<T>
    ): VkApiRequest<T> = VkApiRequest(
        client = client,
        httpMethod = httpMethod,
        path = path,
        parameters = parameters,
        serializer = serializer
    )

}

internal inline fun <T : Any> list(nestedSerializer: KSerializer<T>) =
    DefaultListResponse.serializer(nestedSerializer)

internal inline fun <T : Any> extendedList(nestedSerializer: KSerializer<T>) =
    ExtendedListResponse.serializer(nestedSerializer)

internal inline fun Boolean.asInt(): Int? =
    if (this) 1 else 0

//--- Mappers to parameters list ---//

internal val OAuth.parameters: List<Pair<String, String>> get() = when (this) {
    is OAuth.User -> when (this) {
        is OAuth.User.CodeFlow -> listOf(
            "client_id" to clientId.toString(),
            "client_secret" to clientSecret,
            "code" to code,
            "redirect_uri" to redirectUri
        )
        is OAuth.User.PasswordFlow -> listOf(
            "grant_type" to "password",
            "client_id" to clientId.toString(),
            "client_secret" to clientSecret,
            "username" to username,
            "password" to password
        )
    }
    is OAuth.Community -> when (this) {
        is OAuth.Community.CodeFlow -> listOf(
            "client_id" to clientId.toString(),
            "client_secret" to clientSecret,
            "code" to code,
            "redirect_uri" to redirectUri
        )
    }
}

internal val CaptchaResponse.parameters get() = listOf(
    "captcha_sid" to sid,
    "captcha_key" to key
)

//--- Parsers ---//

internal fun <T : Any> parseMethodResponse(
    responseString: String,
    serializer: KSerializer<T>
): Result<T, Exception> =
    JSONObject(responseString).let {
        Result.of(getMethodResponse(it, serializer)) {
            getMethodError(it) ?: IllegalStateException("Response is null.")
        }
    }

internal fun <T : Any> parseUploadResponse(
    responseString: String,
    serializer: KSerializer<T>
): Result<T, Exception> =
    when (JSONObject(responseString).has("error")) {
        true -> Result.error(json.parse(UploadFileError.serializer(), responseString))
        false -> Result.of(json.parse(serializer, responseString))
    }

internal fun parseOAuthResponse(
    responseString: String
): Result<OAuthResponse, Exception> =
    when (JSONObject(responseString).has("access_token")) {
        true -> Result.of(json.parse(OAuthResponse.serializer(), responseString))
        false -> Result.error(json.parse(OAuthError.serializer(), responseString))
    }

// VK API makes me write some shitcode
private fun <T> getMethodResponse(jsonObject: JSONObject, serializer: KSerializer<T>): T? =
    jsonObject.opt("response").let {
        if (it != null && it !is JSONArray)
            json.parse(serializer, it.toString())
        else if (it is JSONArray && serializer is ListLikeSerializer<*, *, *>)
            json.parse(serializer, it.toString())
        else null
    }

private fun getMethodError(jsonObject: JSONObject): VkApiError? =
    jsonObject.optJSONObject("error")?.toString()?.let {
        json.parse(VkApiError.serializer(), it)
    }