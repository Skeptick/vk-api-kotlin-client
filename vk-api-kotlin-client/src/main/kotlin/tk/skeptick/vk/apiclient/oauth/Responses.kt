package tk.skeptick.vk.apiclient.oauth

import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class OAuthError(
    @SerialName("error") val error: String,
    @SerialName("error_description") val errorDescription: String
) : Exception()

@Serializable
data class OAuthResponse(
    @SerialName("access_token") val accessToken: String,
    @SerialName("expires_in") val expiresIn: Int,
    @Optional @SerialName("user_id") val userId: Int? = null)