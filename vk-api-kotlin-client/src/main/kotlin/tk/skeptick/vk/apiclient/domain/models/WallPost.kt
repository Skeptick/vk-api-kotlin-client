package tk.skeptick.vk.apiclient.domain.models

import kotlinx.serialization.*
import tk.skeptick.vk.apiclient.BooleanInt
import tk.skeptick.vk.apiclient.EnumStringSerializer
import tk.skeptick.vk.apiclient.SerializableEnum
import tk.skeptick.vk.apiclient.domain.*

@Serializable
data class WallPost(
    @SerialName("id") override val id: Int,
    @SerialName("owner_id") private val _ownerId: Int? = null,
    @SerialName("to_id") private val toId: Int? = null,
    @SerialName("from_id") val fromId: Int,
    @SerialName("date") val date: Int,
    @SerialName("text") val text: String,
    @SerialName("post_type") val type: Type,
    @SerialName("from") val from: Community? = null,
    @SerialName("postponed_id") val postponedId: Int? = null,
    @SerialName("created_by") val createdByUserId: Int? = null,
    @SerialName("reply_owner_id") val replyOwnerId: Int? = null,
    @SerialName("reply_post_id") val replyPostId: Int? = null,
    @SerialName("likes") val likes: Likes? = null,
    @SerialName("reposts") val reposts: Reposts? = null,
    @SerialName("comments") val comments: Comments? = null,
    @SerialName("views") val views: Views? = null,
    @SerialName("post_source") val postSource: Source? = null,
    @SerialName("attachments") val attachments: List<Attachment>? = null,
    @SerialName("geo") val geo: Geo? = null,
    @SerialName("activity") val activity: Activity? = null,
    @SerialName("signer_id") val signerUserId: Int? = null,
    @SerialName("copy_history") val copyHistory: List<WallPost>? = null,
    @SerialName("friends_only") val isFriendsOnly: BooleanInt? = null,
    @SerialName("is_pinned") val isPinned: BooleanInt = BooleanInt(false),
    @SerialName("marked_as_ads") val isMarkedAsAds: BooleanInt = BooleanInt(false),
    @SerialName("can_pin") val canPin: BooleanInt? = null,
    @SerialName("can_delete") val canDelete: BooleanInt? = null,
    @SerialName("can_edit") val canEdit: BooleanInt? = null,
    @SerialName("can_archive") val canArchive: Boolean? = null,
    @SerialName("is_archived") val isArchived: Boolean? = null,
    @SerialName("is_favorite") val isFavorite: Boolean = false,
    @SerialName("is_promoted_post_stealth") val isPromotedPostStealth: Boolean = false,
    @SerialName("access_key") override val accessKey: String? = null
) : MessageAttachment {

    override val typeAttachment: AttachmentType get() = AttachmentType.WALL

    override val ownerId: Int = _ownerId ?: toId!!

    @Serializable(with = Type.Companion::class)
    enum class Type(override val value: String) : SerializableEnum<String> {
        POST("post"),
        COPY("copy"),
        REPLY("reply"),
        POSTPONE("postpone"),
        SUGGEST("suggest"),
        PHOTO("photo"),
        POST_ADS("post_ads");

        companion object : EnumStringSerializer<Type>(Type::class)
    }

    @Serializable
    data class Comments(
        @SerialName("count") val count: Int,
        @SerialName("list") val list: List<WallComment>? = null,
        @SerialName("groups_can_post") val isGroupsCanComment: Boolean = false,
        @SerialName("can_post") val canPost: BooleanInt? = null,
        @SerialName("can_close") val canClose: BooleanInt? = null,
        @SerialName("can_open") val canOpen: BooleanInt? = null)

    @Serializable
    data class Likes(
        @SerialName("count") val count: Int,
        @SerialName("user_likes") val isUserLikes: BooleanInt? = null,
        @SerialName("can_like") val canLike: BooleanInt? = null,
        @SerialName("can_publish") val canPublish: BooleanInt? = null)

    @Serializable
    data class Reposts(
        @SerialName("count") val count: Int,
        @SerialName("user_reposted") val isUserReposted: BooleanInt? = null)

    @Serializable
    data class Views(
        @SerialName("count") val count: Int)

    @Serializable
    data class Source(
        @SerialName("type") val type: Type,
        @SerialName("link") val link: Link? = null,
        @SerialName("platform") val platform: Platform? = null,
        @SerialName("data") val data: DataType? = null) {

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
    data class Attachment(
        @SerialName("type") val type: AttachmentType,
        @SerialName("photo") val photo: Photo? = null,
        @SerialName("video") val video: Video? = null,
        @SerialName("audio") val audio: Audio? = null,
        @SerialName("doc") val document: Document? = null,
        @SerialName("graffiti") val graffiti: Graffiti? = null,
        @SerialName("link") val link: Link? = null,
        @SerialName("note") val note: Note? = null,
        @SerialName("poll") val poll: Poll? = null,
        @SerialName("page") val page: Page? = null,
        @SerialName("album") val album: Album? = null,
        @SerialName("photos_list") val photosList: List<Int>? = null,
        @SerialName("market") val market: Market? = null,
        @SerialName("market_album") val marketAlbum: Market.Album? = null,
        @SerialName("sticker") val sticker: Sticker? = null,
        @SerialName("pretty_cards") val prettyCards: PrettyCards? = null,
        @SerialName("event") val event: Event? = null,
        @SerialName("article") val article: Article? = null,
        @SerialName("audio_playlist") val audioPlaylist: Playlist? = null)

    @Serializable
    data class Activity(
        @SerialName("type") val type: Type,
        @SerialName("comments") val comments: List<WallComment>? = null) {

        @Serializable(with = Type.Companion::class)
        enum class Type(override val value: String) : SerializableEnum<String> {
            COMMENTS("comments");

            companion object : EnumStringSerializer<Type>(Type::class)
        }

    }

}







