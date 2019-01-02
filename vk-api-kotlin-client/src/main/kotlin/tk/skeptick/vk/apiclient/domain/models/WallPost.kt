package tk.skeptick.vk.apiclient.domain.models

import kotlinx.serialization.*
import tk.skeptick.vk.apiclient.BooleanInt
import tk.skeptick.vk.apiclient.EnumStringSerializer
import tk.skeptick.vk.apiclient.SerializableEnum
import tk.skeptick.vk.apiclient.domain.*

@Serializable
data class WallPost(
    @SerialName("id") override val id: Int,
    @Optional @SerialName("owner_id") private val _ownerId: Int? = null,
    @Optional @SerialName("to_id") private val toId: Int? = null,
    @SerialName("from_id") val fromId: Int,
    @SerialName("date") val date: Int,
    @SerialName("text") val text: String,
    @SerialName("post_type") val type: Type,
    @Optional @SerialName("created_by") val createdByUserId: Int? = null,
    @Optional @SerialName("reply_owner_id") val replyOwnerId: Int? = null,
    @Optional @SerialName("reply_post_id") val replyPostId: Int? = null,
    @Optional @SerialName("likes") val likes: Likes? = null,
    @Optional @SerialName("reposts") val reposts: Reposts? = null,
    @Optional @SerialName("comments") val comments: Comments? = null,
    @Optional @SerialName("views") val views: Views? = null,
    @Optional @SerialName("post_source") val postSource: Source? = null,
    @Optional @SerialName("Attachments") val attachments: Attachments? = null,
    @Optional @SerialName("geo") val geo: Geo? = null,
    @Optional @SerialName("signer_id") val signerUserId: Int? = null,
    @Optional @SerialName("copy_history") val copyHistory: List<WallPost>? = null,
    @Optional @SerialName("friends_only") val isFriendsOnly: BooleanInt = BooleanInt(false),
    @Optional @SerialName("is_pinned") val isPinned: BooleanInt = BooleanInt(false),
    @Optional @SerialName("marked_as_ads") val isMarkedAsAds: BooleanInt = BooleanInt(false),
    @Optional @SerialName("can_pin") val canPin: BooleanInt? = null,
    @Optional @SerialName("can_delete") val canDelete: BooleanInt? = null,
    @Optional @SerialName("can_edit") val canEdit: BooleanInt? = null,
    @Optional @SerialName("is_favorite") val isFavorite: Boolean? = null,
    @Optional @SerialName("access_key") override val accessKey: String? = null
) : MessageAttachment {

    @Transient override val typeAttachment: String get() = AttachmentType.WALL.value
    @Transient override val ownerId: Int = _ownerId ?: toId!!

    @Serializable(with = Type.Companion::class)
    enum class Type(override val value: String) : SerializableEnum<String> {
        POST("post"),
        COPY("copy"),
        REPLY("reply"),
        POSTPONE("postpone"),
        SUGGEST("suggest");

        companion object : EnumStringSerializer<Type>(Type::class)
    }

    @Serializable
    data class Comments(
        @SerialName("count") val count: Int,
        @SerialName("can_post") val canPost: BooleanInt,
        @Optional @SerialName("groups_can_post") val isGroupsCanComment: Boolean = false,
        @Optional @SerialName("can_close") val canClose: BooleanInt = BooleanInt(false),
        @Optional @SerialName("can_open") val canOpen: BooleanInt = BooleanInt(false))

    @Serializable
    data class Likes(
        @SerialName("count") val count: Int,
        @SerialName("user_likes") val isUserLikes: BooleanInt,
        @SerialName("can_like") val canLike: BooleanInt,
        @SerialName("can_publish") val canPublish: BooleanInt)

    @Serializable
    data class Reposts(
        @SerialName("count") val count: Int,
        @SerialName("user_reposted") val isUserReposted: BooleanInt)

    @Serializable
    data class Views(
        @SerialName("count") val count: Int)

    @Serializable
    data class Source(
        @SerialName("type") val type: Type,
        @Optional @SerialName("link") val link: Link? = null,
        @Optional @SerialName("platform") val platform: Platform? = null,
        @Optional @SerialName("data") val data: DataType? = null) {

        @Serializable(with = Type.Companion::class)
        enum class Type(override val value: String) : SerializableEnum<String> {
            VK("vk"),
            WIDGET("widget"),
            API("api"),
            RSS("rss"),
            SMS("sms");

            companion object : EnumStringSerializer<Type>(Type::class)
        }

        @Serializable(with = Platform.Companion::class)
        enum class Platform(override val value: String) : SerializableEnum<String> {
            ANDROID("android"),
            IPHONE("iphone"),
            WPHONE("wphone");

            companion object : EnumStringSerializer<Platform>(Platform::class)
        }

        @Serializable(with = DataType.Companion::class)
        enum class DataType(override val value: String) : SerializableEnum<String> {
            PROFILE_ACTIVITY("profile_activity"),
            PROFILE_PHOTO("profile_photo"),
            COMMENTS("comments"),
            LIKE("like"),
            POLL("poll");

            companion object : EnumStringSerializer<DataType>(DataType::class)
        }

    }

    @Serializable
    data class Attachments(
        @SerialName("type") val type: AttachmentType,
        @Optional @SerialName("photo") val photo: Photo? = null,
        @Optional @SerialName("video") val video: Video? = null,
        @Optional @SerialName("audio") val audio: Audio? = null,
        @Optional @SerialName("doc") val document: Document? = null,
        @Optional @SerialName("link") val link: Link? = null,
        @Optional @SerialName("note") val note: Note? = null,
        @Optional @SerialName("poll") val poll: Poll? = null,
        @Optional @SerialName("page") val page: Page? = null,
        @Optional @SerialName("album") val album: Photo.Album? = null,
        @Optional @SerialName("photos_list") val photosList: List<Int>? = null,
        @Optional @SerialName("market") val market: Market? = null,
        @Optional @SerialName("market_album") val marketAlbum: Market.Album? = null,
        @Optional @SerialName("sticker") val sticker: Sticker? = null)

}







