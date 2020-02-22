package tk.skeptick.vk.apiclient.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tk.skeptick.vk.apiclient.BooleanInt
import tk.skeptick.vk.apiclient.domain.AttachmentType
import tk.skeptick.vk.apiclient.domain.MessageAttachment

@Serializable
data class WallComment(
    @SerialName("id") override val id: Int,
    @SerialName("from_id") val fromId: Int,
    @SerialName("date") val date: Int,
    @SerialName("text") val text: String,
    @SerialName("post_id") val postId: Int? = null,
    @SerialName("owner_id") val wallOwnerId: Int? = null,
    @SerialName("reply_to_user") val replyToUserId: Int? = null,
    @SerialName("reply_to_comment") val replyToCommentId: Int? = null,
    @SerialName("attachments") val attachments: List<WallPost.Attachment>? = null,
    @SerialName("parents_stack") val parentsStack: List<Int>? = null,
    @SerialName("likes") val likes: Likes? = null,
    @SerialName("thread") val thread: Thread? = null,
    @SerialName("photo_id") val photoId: Int? = null,
    @SerialName("video_id") val videoId: Int? = null,
    @SerialName("item_id") val itemId: Int? = null,
    @SerialName("photo_owner_id") val photoOwnerId: Int? = null,
    @SerialName("video_owner_id") val videoOwnerId: Int? = null,
    @SerialName("post_owner_id") val postOwnerId: Int? = null,
    @SerialName("market_owner_id") val marketOwnerId: Int? = null
) : MessageAttachment {

    override val typeAttachment: AttachmentType get() = AttachmentType.WALL

    override val ownerId: Int = wallOwnerId ?: fromId


    @Serializable
    data class Likes(
        @SerialName("count") val count: Int,
        @SerialName("user_likes") val isUserLikes: BooleanInt? = null,
        @SerialName("can_like") val canLike: BooleanInt? = null)

    @Serializable
    data class Thread(
        @SerialName("count") val count: Int,
        @SerialName("items") val items: List<WallComment>? = null,
        @SerialName("can_post") val canPost: Boolean? = null,
        @SerialName("show_reply_button") val showReplyButton: Boolean? = null,
        @SerialName("groups_can_post") val groupsCanPost: Boolean? = null)

}