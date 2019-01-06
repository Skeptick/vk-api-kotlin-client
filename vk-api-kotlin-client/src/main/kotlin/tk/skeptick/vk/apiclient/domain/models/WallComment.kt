package tk.skeptick.vk.apiclient.domain.models

import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import tk.skeptick.vk.apiclient.BooleanInt
import tk.skeptick.vk.apiclient.domain.AttachmentType
import tk.skeptick.vk.apiclient.domain.MessageAttachment

@Serializable
data class WallComment(
    @SerialName("id") override val id: Int,
    @SerialName("from_id") val fromId: Int,
    @SerialName("date") val date: Int,
    @SerialName("text") val text: String,
    @Optional @SerialName("post_id") val postId: Int? = null,
    @Optional @SerialName("owner_id") val wallOwnerId: Int? = null,
    @Optional @SerialName("reply_to_user") val replyToUserId: Int? = null,
    @Optional @SerialName("reply_to_comment") val replyToCommentId: Int? = null,
    @Optional @SerialName("attachments") val attachments: WallPost.Attachments? = null,
    @Optional @SerialName("parents_stack") val parentsStack: List<Int>? = null,
    @Optional @SerialName("likes") val likes: Likes? = null,
    @Optional @SerialName("thread") val thread: Thread? = null
) : MessageAttachment {

    @Transient override val typeAttachment: String get() = AttachmentType.WALL.value
    @Transient override val ownerId: Int = wallOwnerId ?: fromId

    @Serializable
    data class Likes(
        @SerialName("count") val count: Int,
        @Optional @SerialName("user_likes") val isUserLikes: BooleanInt? = null,
        @Optional @SerialName("can_like") val canLike: BooleanInt? = null)

    @Serializable
    data class Thread(
        @SerialName("count") val count: Int,
        @Optional @SerialName("items") val items: List<WallComment>? = null,
        @Optional @SerialName("can_post") val canPost: Boolean? = null,
        @Optional @SerialName("show_reply_button") val showReplyButton: Boolean? = null,
        @Optional @SerialName("groups_can_post") val groupsCanPost: Boolean? = null)

}