package tk.skeptick.vk.apiclient.methods.wall

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tk.skeptick.vk.apiclient.BooleanInt
import tk.skeptick.vk.apiclient.domain.models.Community
import tk.skeptick.vk.apiclient.domain.models.User
import tk.skeptick.vk.apiclient.domain.models.WallComment
import tk.skeptick.vk.apiclient.methods.ListResponse

@Serializable
data class CreateCommentResponse(
    @SerialName("comment_id") val commentId: Int,
    @SerialName("parents_stack") val parentsStack: List<Int>)

@Serializable
data class CommentsListResponse(
    @SerialName("count") override val count: Int,
    @SerialName("items") override val items: List<WallComment>,
    @SerialName("current_level_count") val currentLevelCount: Int,
    @SerialName("can_post") val canPost: Boolean,
    @SerialName("show_reply_button") val showReplyButton: Boolean,
    @SerialName("groups_can_post") val groupsCanPost: Boolean = false,
    @SerialName("profiles") val profiles: List<User>? = null,
    @SerialName("groups") val groups: List<Community>? = null
) : ListResponse<WallComment>

@Serializable
data class PostResponse(
    @SerialName("post_id") val postId: Int)

@Serializable
data class RepostResponse(
    @SerialName("success") val isSuccess: BooleanInt,
    @SerialName("post_id") val postId: Int,
    @SerialName("reposts_count") val repostsCount: Int,
    @SerialName("likes_count") val likesCount: Int)