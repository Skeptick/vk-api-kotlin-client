package tk.skeptick.vk.apiclient.oauth

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class OAuthError(
    @SerialName("error") val error: String,
    @SerialName("error_description") val errorDescription: String? = null,
    @SerialName("validation_type") val validationType: String? = null,
    @SerialName("validation_sid") val validationSid: String? = null,
    @SerialName("phone_mask") val phoneMask: String? = null,
    @SerialName("redirect_uri") val redirectUri: String? = null,
    @SerialName("captcha_sid") val captchaSid: String? = null,
    @SerialName("captcha_img") val captchaImg: String? = null
) : Exception()

@Serializable
data class OAuthResponse(
    @SerialName("access_token") val accessToken: String,
    @SerialName("expires_in") val expiresIn: Int,
    @SerialName("user_id") val userId: Int? = null)