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
    @SerialName("photo_130") val photo130: String,
    @SerialName("photo_320") val photo320: String,
    @Optional @SerialName("photo_640") val photo640: String? = null,
    @Optional @SerialName("photo_800") val photo800: String? = null,
    @Optional @SerialName("first_frame_130") val firstFrame130: String? = null,
    @Optional @SerialName("first_frame_160") val firstFrame160: String? = null,
    @Optional @SerialName("first_frame_320") val firstFrame320: String? = null,
    @Optional @SerialName("first_frame_800") val firstFrame800: String? = null,
    @Optional @SerialName("first_frame_1280") val firstFrame1280: String? = null,
    @Optional @SerialName("width") val width: Int? = null,
    @Optional @SerialName("height") val height: Int? = null,
    @Optional @SerialName("player") val playerUrl: String? = null,
    @Optional @SerialName("platform") val platform: String? = null,
    @Optional @SerialName("adding_date") val addingDate: Int? = null,
    @Optional @SerialName("files") val files: Files? = null,
    @Optional @SerialName("can_edit") val canEdit: BooleanInt? = null,
    @Optional @SerialName("can_add") val canAdd: BooleanInt? = null,
    @Optional @SerialName("can_like") val canLike: BooleanInt? = null,
    @Optional @SerialName("can_comment") val canComment: BooleanInt? = null,
    @Optional @SerialName("can_repost") val canRepost: BooleanInt? = null,
    @Optional @SerialName("privacy_view") val privacyView: Privacy? = null,
    @Optional @SerialName("privacy_comment") val privacyComment: Privacy? = null,
    @Optional @SerialName("likes") val likes: Likes? = null,
    @Optional @SerialName("reposts") val reposts: Reposts? = null,
    @Optional @SerialName("is_private") val isPrivate: BooleanInt = BooleanInt(false),
    @Optional @SerialName("converting") val isConverting: BooleanInt = BooleanInt(false),
    @Optional @SerialName("processing") val isInProcessing: BooleanInt = BooleanInt(false),
    @Optional @SerialName("live") val isLive: BooleanInt = BooleanInt(false),
    @Optional @SerialName("upcoming") val isUpcoming: BooleanInt = BooleanInt(false),
    @Optional @SerialName("repeat") val isRepeat: BooleanInt = BooleanInt(false),
    @Optional @SerialName("is_favorite") val isFavorite: Boolean = false,
    @Optional @SerialName("access_key") override val accessKey: String? = null
) : MessageAttachment {

    @Transient override val typeAttachment get() = AttachmentType.VIDEO.value
    @Transient val photoMax: String get() = photo800 ?: photo640 ?: photo320

    @Serializable
    data class Privacy(
        @Optional @SerialName("category") val category: Category? = null,
        @Optional @SerialName("owners") val owners: Owners? = null) {

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
            @Optional @SerialName("excluded") val excluded: List<Int>? = null,
            @Optional @SerialName("allowed") val allowed: List<Int>? = null)

    }

    @Serializable
    data class Files(
        @Optional @SerialName("external") val external: String? = null,
        @Optional @SerialName("mp4_240") val mp240: String? = null,
        @Optional @SerialName("mp4_360") val mp360: String? = null,
        @Optional @SerialName("mp4_480") val mp480: String? = null,
        @Optional @SerialName("mp4_720") val mp720: String? = null,
        @Optional @SerialName("mp4_1080") val mp1080: String? = null)

    @Serializable
    data class Likes(
        @SerialName("count") val count: Int,
        @Optional @SerialName("user_likes") val isUserLikes: BooleanInt? = null)

    @Serializable
    data class Reposts(
        @SerialName("count") val count: Int,
        @Optional @SerialName("user_reposted") val isUserReposted: BooleanInt? = null)

}