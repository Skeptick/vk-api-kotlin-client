package tk.skeptick.vk.apiclient.methods.wall

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateCommentResponse(
    @SerialName("comment_id") val commentId: Int,
    @SerialName("parents_stack") val parentsStack: List<Int>)