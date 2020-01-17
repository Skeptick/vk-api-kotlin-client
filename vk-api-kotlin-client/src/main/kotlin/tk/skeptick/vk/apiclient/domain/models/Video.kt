package tk.skeptick.vk.apiclient.domain.models

import kotlinx.serialization.*
import tk.skeptick.vk.apiclient.BooleanInt
import tk.skeptick.vk.apiclient.EnumStringSerializer
import tk.skeptick.vk.apiclient.SerializableEnum
import tk.skeptick.vk.apiclient.domain.AttachmentType
import tk.skeptick.vk.apiclient.domain.MessageAttachment

@Serializable
data class Video(
    @SerialName("id") override val id: Int,
    @SerialName("owner_id") override val ownerId: Int,
    @SerialName("title") val title: String,
    @SerialName("description") val description: String,
    @SerialName("duration") val duration: Int, // sec
    @SerialName("date") val date: Int,
    @SerialName("views") val viewsCount: Int,
    @SerialName("comments") val commentsCount: Int,
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
    @SerialName("privacy_view") val privacyView: Privacy? = null,
    @SerialName("privacy_comment") val privacyComment: Privacy? = null,
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
) : MessageAttachment {

    override val typeAttachment get() = AttachmentType.VIDEO.value

    @Serializable
    data class Privacy(
        @SerialName("category") val category: Category? = null,
        @SerialName("owners") val owners: Owners? = null) {

        @Serializable(with = Category.Companion::class)
        enum class Category(override val value: String) : SerializableEnum<String> {
            ALL("all"),
            FRIENDS("friends"),
            FRIENDS_OF_FRIENDS("friends_of_friends"),
            ONLY_ME("only_me");

            companion object : EnumStringSerializer<Category>(Category::class)
        }

        @Serializable
        data class Owners(
            @SerialName("excluded") val excluded: List<Int>? = null,
            @SerialName("allowed") val allowed: List<Int>? = null)

    }

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