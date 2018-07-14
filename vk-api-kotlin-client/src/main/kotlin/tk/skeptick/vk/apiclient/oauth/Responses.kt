package tk.skeptick.vk.apiclient.oauth

import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class OAuthError(
    @SerialName("error") val error: String,
    @Optional @SerialName("error_description") val errorDescription: String? = null,
    @Optional @SerialName("validation_type") val validationType: String? = null,
    @Optional @SerialName("validation_sid") val validationSid: String? = null,
    @Optional @SerialName("phone_mask") val phoneMask: String? = null,
    @Optional @SerialName("redirect_uri") val redirectUri: String? = null,
    @Optional @SerialName("captcha_sid") val captchaSid: String? = null,
    @Optional @SerialName("captcha_img") val captchaImg: String? = null
) : Exception()

@Serializable
data class OAuthResponse(
    @SerialName("access_token") val accessToken: String,
    @SerialName("expires_in") val expiresIn: Int,
    @Optional @SerialName("user_id") val userId: Int? = null)