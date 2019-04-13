package tk.skeptick.vk.apiclient.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VkApiError(
    @SerialName("error_code") val errorCode: Int,
    @SerialName("error_msg") val errorMessage: String,
    @SerialName("captcha_sid") val captchaSid: String? = null,
    @SerialName("captcha_img") val captchaImg: String? = null,
    @SerialName("redirect_uri") val redirectUri: String? = null,
    @SerialName("confirmation_text") val confirmationText: String? = null,
    @SerialName("request_params") val requestParams: List<RequestParameter> = emptyList()
) : Exception() {

    @Serializable
    data class RequestParameter(
        @SerialName("key") val key: String,
        @SerialName("value") val value: String)

}