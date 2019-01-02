package tk.skeptick.vk.apiclient.domain.models

import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Note(
    @SerialName("id") val id: Int,
    @SerialName("owner_id") val ownerId: Int,
    @SerialName("title") val title: String,
    @SerialName("text") val text: String,
    @SerialName("date") val date: Int,
    @SerialName("view_url") val viewUrl: String,
    @Optional @SerialName("comments") val commentsCount: Int? = null,
    @Optional @SerialName("read_comments") val readCommentsCount: Int? = null)