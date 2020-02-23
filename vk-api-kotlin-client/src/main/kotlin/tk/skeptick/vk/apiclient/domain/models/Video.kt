package tk.skeptick.vk.apiclient.domain.models

import kotlinx.serialization.*
import tk.skeptick.vk.apiclient.BooleanInt
import tk.skeptick.vk.apiclient.domain.*

@Serializable
data class Video(
    @SerialName("id") override val id: Int,
    @SerialName("owner_id") override val ownerId: Int,
    @SerialName("title") val title: String,
    @SerialName("description") val description: String,
    @SerialName("duration") val duration: Int, // sec
    @SerialName("date") val date: Int,
    @SerialName("comments") val commentsCount: Int,
    @SerialName("views") val viewsCount: Int? = null,
    @SerialName("image") val image: List<VideoImage>? = null,
    @SerialName("first_frame") val firstFrame: List<VideoImage>? = null,
    @SerialName("width") val width: Int? = null,
    @SerialName("height") val height: Int? = null,
    @SerialName("player") val playerUrl: String? = null,
    @SerialName("platform") val platform: String? = null,
    @SerialName("adding_date") val addingDate: Int? = null,
    @SerialName("files") val files: Files? = null,
    @SerialName("can_edit") val canEdit: BooleanInt? = null,
    @SerialName("can_add") val canAdd: BooleanInt? = null,
    @SerialName("can_like") val canLike: BooleanInt? = null,
    @SerialName("can_comment") val canComment: BooleanInt? = null,
    @SerialName("can_repost") val canRepost: BooleanInt? = null,
    @SerialName("privacy_view") val privacyView: PrivacySettings? = null,
    @SerialName("privacy_comment") val privacyComment: PrivacySettings? = null,
    @SerialName("likes") val likes: Likes? = null,
    @SerialName("reposts") val reposts: Reposts? = null,
    @SerialName("is_private") val isPrivate: BooleanInt = BooleanInt(false),
    @SerialName("converting") val isConverting: BooleanInt = BooleanInt(false),
    @SerialName("processing") val isInProcessing: BooleanInt = BooleanInt(false),
    @SerialName("live") val isLive: BooleanInt = BooleanInt(false),
    @SerialName("upcoming") val isUpcoming: BooleanInt = BooleanInt(false),
    @SerialName("repeat") val isRepeat: BooleanInt = BooleanInt(false),
    @SerialName("is_favorite") val isFavorite: Boolean = false,
    @SerialName("access_key") override val accessKey: String? = null
) : CommentAttachment, WallAttachment, MessageAttachment {

    override val typeAttachment: AttachmentType get() = AttachmentType.VIDEO

    @Serializable
    data class VideoImage(
        @SerialName("url") val url: String,
        @SerialName("width") val width: Int,
        @SerialName("height") val height: Int,
        @SerialName("with_padding") val withPadding: BooleanInt = BooleanInt(false))

    @Serializable
    data class Files(
        @SerialName("external") val external: String? = null,
        @SerialName("mp4_240") val mp240: String? = null,
        @SerialName("mp4_360") val mp360: String? = null,
        @SerialName("mp4_480") val mp480: String? = null,
        @SerialName("mp4_720") val mp720: String? = null,
        @SerialName("mp4_1080") val mp1080: String? = null)

    @Serializable
    data class Likes(
        @SerialName("count") val count: Int,
        @SerialName("user_likes") val isUserLikes: BooleanInt? = null)

    @Serializable
    data class Reposts(
        @SerialName("count") val count: Int,
        @SerialName("user_reposted") val isUserReposted: BooleanInt? = null)

}