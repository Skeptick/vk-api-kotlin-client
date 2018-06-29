package tk.skeptick.vk.apiclient.domain.models

import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WallComment(
    @SerialName("id") val id: Int,
    @SerialName("from_id") val fromId: Int,
    @SerialName("date") val date: Int,
    @SerialName("text") val text: String,
    @Optional @SerialName("reply_to_user") val replyToUserId: Int? = null,
    @Optional @SerialName("reply_to_comment") val replyToCommentId: Int? = null,
    @Optional @SerialName("attachments") val attachments: WallPost.Attachments? = null,

    // Only for reply in message
    @Optional @SerialName("post_id") val postId: Int? = null,
    @Optional @SerialName("owner_id") val wallOwnerId: Int? = null)