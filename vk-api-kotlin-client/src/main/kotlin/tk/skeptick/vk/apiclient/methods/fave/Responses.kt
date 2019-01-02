package tk.skeptick.vk.apiclient.methods.fave

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FaveLink(
    @SerialName("id") val id: String,
    @SerialName("url") val url: String,
    @SerialName("title") val title: String,
    @SerialName("description") val description: String,
    @SerialName("photo_50") val photo50: String,
    @SerialName("photo_100") val photo100: String,
    @SerialName("photo_200") val photo200: String)