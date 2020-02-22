package tk.skeptick.vk.apiclient.domain.models

import kotlinx.serialization.*
import tk.skeptick.vk.apiclient.BooleanInt
import tk.skeptick.vk.apiclient.EnumIntSerializer
import tk.skeptick.vk.apiclient.EnumStringSerializer
import tk.skeptick.vk.apiclient.SerializableEnum

@Serializable
data class Community(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("screen_name") val screenName: String,
    @SerialName("is_closed") val closeType: CloseType,
    @SerialName("type") val type: Type,
    @SerialName("deactivated") val deactivationType: DeactivationType? = null,
    @SerialName("is_admin") val isAdmin: BooleanInt? = null,
    @SerialName("admin_level") val adminLevel: AdminLevel? = null,
    @SerialName("is_member") val isMember: BooleanInt? = null,
    @SerialName("is_advertiser") val isAdvertiser: BooleanInt? = null,
    @SerialName("invited_by") val invitedBy: Int? = null,
    @SerialName("photo_50") val photo50: String? = null,
    @SerialName("photo_100") val photo100: String? = null,
    @SerialName("photo_200") val photo200: String? = null,
    @SerialName("activity") val activity: String? = null,
    @SerialName("age_limits") val ageLimits: AgeLimits? = null,
    @SerialName("ban_info") val banInfo: BanInfo? = null,
    @SerialName("can_create_topic") val canCreateTopic: BooleanInt? = null,
    @SerialName("can_message") val canMessage: BooleanInt? = null,
    @SerialName("can_post") val canPost: BooleanInt? = null,
    @SerialName("can_see_all_posts") val canSeeAllPosts: BooleanInt? = null,
    @SerialName("can_upload_doc") val canUploadDoc: BooleanInt? = null,
    @SerialName("can_upload_video") val canUploadVideo: BooleanInt? = null,
    @SerialName("city") val city: City? = null,
    @SerialName("contacts") val contacts: List<Contact>? = null,
    @SerialName("counters") val counters: Counters? = null,
    @SerialName("country") val country: Country? = null,
    @SerialName("cover") val cover: Cover? = null,
    @SerialName("crop_photo") val cropPhoto: CropPhoto? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("fixed_post") val fixedPostId: Int? = null,
    @SerialName("has_photo") val hasPhoto: BooleanInt? = null,
    @SerialName("is_favorite") val isFavorite: BooleanInt? = null,
    @SerialName("is_hidden_from_feed") val isHiddenFromFeed: BooleanInt? = null,
    @SerialName("is_messages_blocked") val isMessagesBlocked: BooleanInt? = null,
    @SerialName("links") val links: List<Link>? = null,
    @SerialName("main_album_id") val mainAlbumId: Int? = null,
    @SerialName("main_section") val mainSection: MainSectionType? = null,
    @SerialName("market") val market: Market? = null,
    @SerialName("member_status") val memberStatus: MemberStatus? = null,
    @SerialName("place") val place: Geo.Place? = null,
    @SerialName("public_date_label") val publicDateLabel: String? = null,
    @SerialName("site") val site: String? = null,
    @SerialName("start_date") private val startDate: String? = null,
    @SerialName("finish_date") val eventFinishDate: Int? = null,
    @SerialName("status") val status: String? = null,
    @SerialName("trending") val isTrending: BooleanInt? = null,
    @SerialName("verified") val isVerified: BooleanInt? = null,
    @SerialName("wall") val wallCloseType: WallCloseType? = null,
    @SerialName("wiki_page") val wikiPage: String? = null,
    @SerialName("action_button") val actionButton: ActionButton? = null,
    @SerialName("live_covers") val liveCovers: LiveCovers? = null) {

    val isOpened: Boolean get() = closeType == CloseType.OPEN
    val isClosed: Boolean get() = closeType == CloseType.CLOSED
    val isPrivate: Boolean get() = closeType == CloseType.PRIVATE
    val isDeactivated: Boolean get() = deactivationType != null
    val maxPhoto: String? get() = photo200 ?: photo100 ?: photo50

    val eventStartDate: Int? get() =
        if (type == Type.EVENT) startDate?.let(String::toInt)
        else null

    val foundationDate: String? get() =
        if (type == Type.PAGE) startDate
        else null

    @Serializable(with = CloseType.Companion::class)
    enum class CloseType(override val value: Int) : SerializableEnum<Int> {
        OPEN(0),
        CLOSED(1),
        PRIVATE(2);

        companion object : EnumIntSerializer<CloseType>(CloseType::class)
    }

    @Serializable(with = DeactivationType.Companion::class)
    enum class DeactivationType(override val value: String) : SerializableEnum<String> {
        DELETED("deleted"),
        BANNED("banned");

        companion object : EnumStringSerializer<DeactivationType>(DeactivationType::class)
    }

    @Serializable(with = AdminLevel.Companion::class)
    enum class AdminLevel(override val value: Int) : SerializableEnum<Int> {
        NO_ROLE(0),
        MODERATOR(1),
        EDITOR(2),
        ADMINISTRATOR(3);

        companion object : EnumIntSerializer<AdminLevel>(AdminLevel::class)
    }

    @Serializable(with = Type.Companion::class)
    enum class Type(override val value: String) : SerializableEnum<String> {
        GROUP("group"),
        PAGE("page"),
        EVENT("event");

        companion object : EnumStringSerializer<Type>(Type::class)
    }

    @Serializable(with = AgeLimits.Companion::class)
    enum class AgeLimits(override val value: Int) : SerializableEnum<Int> {
        NO_LIMITS(1),
        SIXTEEN_PLUS(2),
        EIGHTEEN_PLUS(3);

        companion object : EnumIntSerializer<AgeLimits>(AgeLimits::class)
    }

    @Serializable(with = MainSectionType.Companion::class)
    enum class MainSectionType(override val value: Int) : SerializableEnum<Int> {
        NO(0),
        PHOTOS(1),
        TOPICS(2),
        AUDIOS(3),
        VIDEOS(4),
        MARKET(5);

        companion object : EnumIntSerializer<MainSectionType>(MainSectionType::class)
    }

    @Serializable(with = MemberStatus.Companion::class)
    enum class MemberStatus(override val value: Int) : SerializableEnum<Int> {
        NOT_MEMBER(0),
        MEMBER(1),
        NOT_SURE(2),
        DECLINED_INVITATION(3),
        SENT_REQUEST(4),
        INVITED(5);

        companion object : EnumIntSerializer<MemberStatus>(MemberStatus::class)
    }

    @Serializable(with = WallCloseType.Companion::class)
    enum class WallCloseType(override val value: Int) : SerializableEnum<Int> {
        DISABLED(0),
        OPEN(1),
        LIMITED(2),
        CLOSED(3);

        companion object : EnumIntSerializer<WallCloseType>(WallCloseType::class)
    }

    @Serializable
    data class BanInfo(
        @SerialName("end_date") val endDate: Int,
        @SerialName("comment") val comment: String? = null) {

        val isPermanently: Boolean get() = endDate == 0

    }

    @Serializable
    data class City(
        @SerialName("id") val id: Int,
        @SerialName("title") val title: String)

    @Serializable
    data class Country(
        @SerialName("id") val id: Int,
        @SerialName("title") val title: String)

    @Serializable
    data class Contact(
        @SerialName("user_id") val userId: Int? = null,
        @SerialName("desc") val description: String? = null,
        @SerialName("phone") val phone: String? = null,
        @SerialName("email") val email: String? = null)

    @Serializable
    data class Counters(
        @SerialName("photos") val photos: Int? = null,
        @SerialName("albums") val albums: Int? = null,
        @SerialName("videos") val videos: Int? = null,
        @SerialName("audios") val audios: Int? = null,
        @SerialName("topics") val topics: Int? = null,
        @SerialName("docs") val docs: Int? = null)

    @Serializable
    data class Cover(
        @SerialName("enabled") val isEnabled: BooleanInt,
        @SerialName("images") val images: List<Image>? = null) {

        @Serializable
        data class Image(
            @SerialName("url") val url: String,
            @SerialName("width") val width: Int,
            @SerialName("height") val height: Int)

    }

    @Serializable
    data class Link(
        @SerialName("id") val id: Int,
        @SerialName("url") val url: String,
        @SerialName("name") val name: String,
        @SerialName("desc") val description: String,
        @SerialName("photo_50") val photo50: String? = null,
        @SerialName("photo_100") val photo100: String? = null)

    @Serializable
    data class Market(
        @SerialName("enabled") val enabled: BooleanInt,
        @SerialName("price_min") val priceMin: Int? = null,
        @SerialName("price_max") val priceMax: Int? = null,
        @SerialName("main_album_id") val mainAlbumId: Int? = null,
        @SerialName("contact_id") val contactId: Int? = null,
        @SerialName("currency") val currency: Currency? = null,
        @SerialName("currency_text") val currencyText: String? = null) {

        val isCommunityMessagesForContact: Boolean? get() = contactId?.let { it <= 0 }

        @Serializable
        data class Currency(
            @SerialName("id") val id: Int,
            @SerialName("name") val name: String)

    }

    @Serializable
    data class ActionButton(
        @SerialName("is_enabled") val isEnabled: Boolean? = null,
        @SerialName("action_type") val actionType: ActionType,
        @SerialName("target") val target: Target,
        @SerialName("title") val title: String) {

        @Serializable(with = ActionType.Companion::class)
        enum class ActionType(override val value: String) : SerializableEnum<String> {
            NONE(""),
            SEND_EMAIL("send_email"),
            CALL_PHONE("call_phone"),
            CALL_VK("call_vk"),
            OPEN_URL("open_url"),
            OPEN_APP("open_app"),
            OPEN_GROUP_APP("open_group_app");

            companion object : EnumStringSerializer<ActionType>(ActionType::class)
        }

        @Serializable
        data class Target(
            @SerialName("email") val email: String? = null,
            @SerialName("phone") val phone: String? = null,
            @SerialName("user_id") val userId: Int? = null,
            @SerialName("url") val url: String? = null,
            @SerialName("is_internal") val isInternal: Boolean? = null,
            @SerialName("google_store_url") val googleStoreUrl: String? = null,
            @SerialName("itunes_url") val itunesUrl: String? = null,
            @SerialName("app_id") val appId: Int? = null)

    }

    @Serializable
    data class LiveCovers(
        @SerialName("is_enabled") val isEnabled: Boolean,
        @SerialName("is_scalable") val isScalable: Boolean? = null,
        @SerialName("story_ids") val storyIds: List<String>? = null)

}