package tk.skeptick.vk.apiclient.domain.models

import kotlinx.serialization.*
import tk.skeptick.vk.apiclient.BooleanInt
import tk.skeptick.vk.apiclient.domain.AttachmentType
import tk.skeptick.vk.apiclient.domain.CommentAttachment
import tk.skeptick.vk.apiclient.domain.MessageAttachment
import tk.skeptick.vk.apiclient.domain.WallAttachment

@Serializable
data class Photo(
    @SerialName("id") override val id: Int,
    @SerialName("album_id") val albumId: Int,
    @SerialName("owner_id") override val ownerId: Int,
    @SerialName("sizes") val sizes: List<Size>,
    @SerialName("text") val description: String,
    @SerialName("date") val date: Int,
    @SerialName("likes") val likes: Likes? = null,
    @SerialName("reposts") val reposts: Reposts? = null,
    @SerialName("comments") val comments: Comments? = null,
    @SerialName("can_comment") val canComment: BooleanInt? = null,
    @SerialName("tags") val tags: Tags? = null,
    @SerialName("access_key") override val accessKey: String? = null,
    @SerialName("user_id") val userId: Int? = null,
    @SerialName("post_id") val wallPostId: Int? = null,
    @SerialName("placer_id") val placerId: Int? = null,
    @SerialName("tag_created") val tagCreated: Int? = null,
    @SerialName("tag_id") val tagId: Int? = null
) : CommentAttachment, WallAttachment, MessageAttachment {

    override val typeAttachment: AttachmentType get() = AttachmentType.PHOTO

    val isUploadFromGroup get() = userId == 100

    @Serializable
    data class Size(
        @SerialName("url") val src: String,
        @SerialName("width") val width: Int,
        @SerialName("height") val height: Int,
        @SerialName("type") val type: String? = null)

    @Serializable
    data class Likes(
        @SerialName("count") val count: Int,
        @SerialName("user_likes") val isUserLikes: BooleanInt? = null)

    @Serializable
    data class Reposts(
        @SerialName("count") val count: Int)

    @Serializable
    data class Comments(
        @SerialName("count") val count: Int)

    @Serializable
    data class Tags(
        @SerialName("count") val count: Int)

}

