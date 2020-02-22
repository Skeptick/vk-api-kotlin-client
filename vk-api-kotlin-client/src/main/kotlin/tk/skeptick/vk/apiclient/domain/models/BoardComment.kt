package tk.skeptick.vk.apiclient.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BoardComment(
    @SerialName("id") val id: Int,
    @SerialName("from_id") val fromId: Int,
    @SerialName("date") val date: Int,
    @SerialName("text") val text: String,
    @SerialName("attachments") val attachments: List<WallPost.Attachment>? = null,
    @SerialName("likes") val likes: WallComment.Likes? = null,
    @SerialName("topic_id") val topicId: Int? = null,
    @SerialName("topic_owner_id") val topicOwnerId: Int? = null)