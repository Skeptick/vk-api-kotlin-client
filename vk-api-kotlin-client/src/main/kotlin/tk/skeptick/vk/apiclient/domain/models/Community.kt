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
    @Optional @SerialName("deactivated") val deactivationType: DeactivationType? = null,
    @Optional @SerialName("is_admin") val isAdmin: BooleanInt? = null,
    @Optional @SerialName("admin_level") val adminLevel: AdminLevel? = null,
    @Optional @SerialName("is_member") val isMember: BooleanInt? = null,
    @Optional @SerialName("invited_by") val invitedBy: Int? = null,
    @Optional @SerialName("photo_50") val photo50: String? = null,
    @Optional @SerialName("photo_100") val photo100: String? = null,
    @Optional @SerialName("photo_200") val photo200: String? = null,
    @Optional @SerialName("activity") val activity: String? = null,
    @Optional @SerialName("age_limits") val ageLimits: AgeLimits? = null,
    @Optional @SerialName("ban_info") val banInfo: BanInfo? = null,
    @Optional @SerialName("can_create_topic") val canCreateTopic: BooleanInt? = null,
    @Optional @SerialName("can_message") val canMessage: BooleanInt? = null,
    @Optional @SerialName("can_post") val canPost: BooleanInt? = null,
    @Optional @SerialName("can_see_all_posts") val canSeeAllPosts: BooleanInt? = null,
    @Optional @SerialName("can_upload_doc") val canUploadDoc: BooleanInt? = null,
    @Optional @SerialName("can_upload_video") val canUploadVideo: BooleanInt? = null,
    @Optional @SerialName("city") val city: City? = null,
    @Optional @SerialName("contacts") val contacts: List<Contact>? = null,
    @Optional @SerialName("counters") val counters: Counters? = null,
    @Optional @SerialName("country") val country: Country? = null,
    @Optional @SerialName("cover") val cover: Cover? = null,
    @Optional @SerialName("crop_photo") val cropPhoto: CropPhoto? = null,
    @Optional @SerialName("description") val description: String? = null,
    @Optional @SerialName("fixed_post") val fixedPostId: Int? = null,
    @Optional @SerialName("has_photo") val hasPhoto: BooleanInt? = null,
    @Optional @SerialName("is_favorite") val isFavorite: BooleanInt? = null,
    @Optional @SerialName("is_hidden_from_feed") val isHiddenFromFeed: BooleanInt? = null,
    @Optional @SerialName("is_messages_blocked") val isMessagesBlocked: BooleanInt? = null,
    @Optional @SerialName("links") val links: List<Link>? = null,
    @Optional @SerialName("main_album_id") val mainAlbumId: Int? = null,
    @Optional @SerialName("main_section") val mainSection: MainSectionType? = null,
    @Optional @SerialName("market") val market: Market? = null,
    @Optional @SerialName("member_status") val memberStatus: MemberStatus? = null,
    @Optional @SerialName("place") val place: Geo.Place? = null,
    @Optional @SerialName("public_date_label") val publicDateLabel: String? = null,
    @Optional @SerialName("site") val site: String? = null,
    @Optional @SerialName("start_date") private val startDate: String? = null,
    @Optional @SerialName("finish_date") val eventFinishDate: Int? = null,
    @Optional @SerialName("status") val status: String? = null,
    @Optional @SerialName("trending") val isTrending: BooleanInt? = null,
    @Optional @SerialName("verified") val isVerified: BooleanInt? = null,
    @Optional @SerialName("wiki_page") val wikiPage: String? = null) {

    @Transient val isOpened: Boolean = closeType == CloseType.OPEN
    @Transient val isClosed: Boolean = closeType == CloseType.CLOSED
    @Transient val isPrivate: Boolean = closeType == CloseType.PRIVATE
    @Transient val isDeactivated: Boolean? get() = deactivationType != null
    @Transient val maxPhoto: String? = photo200 ?: photo100 ?: photo50

    @Transient val eventStartDate: Int? get() =
        if (type == Type.EVENT) startDate?.let(String::toInt)
        else null

    @Transient val foundationDate: String? get() =
        if (type == Type.PAGE) startDate
        else null

    @Serializable(with = CloseType.Companion::class)
    enum class CloseType(override val value: Int) : SerializableEnum<Int> {
        OPEN(0),
        CLOSED(1),
        PRIVATE(2);

        companion object : EnumIntSerializer<CloseType>(
            clazz = CloseType::class,
            values = enumValues()
        )
    }

    @Serializable(with = DeactivationType.Companion::class)
    enum class DeactivationType(override val value: String) : SerializableEnum<String> {
        DELETED("deleted"),
        BANNED("banned");

        companion object : EnumStringSerializer<DeactivationType>(
            clazz = DeactivationType::class,
            values = enumValues()
        )
    }

    @Serializable(with = AdminLevel.Companion::class)
    enum class AdminLevel(override val value: Int) : SerializableEnum<Int> {
        MODERATOR(1),
        EDITOR(2),
        ADMINISTRATOR(3);

        companion object : EnumIntSerializer<AdminLevel>(
            clazz = AdminLevel::class,
            values = enumValues()
        )
    }

    @Serializable(with = Type.Companion::class)
    enum class Type(override val value: String) : SerializableEnum<String> {
        GROUP("group"),
        PAGE("page"),
        EVENT("event");

        companion object : EnumStringSerializer<Type>(
            clazz = Type::class,
            values = enumValues()
        )
    }

    @Serializable(with = AgeLimits.Companion::class)
    enum class AgeLimits(override val value: Int) : SerializableEnum<Int> {
        NO_LIMITS(1),
        SIXTEEN_PLUS(2),
        EIGHTEEN_PLUS(3);

        companion object : EnumIntSerializer<AgeLimits>(
            clazz = AgeLimits::class,
            values = enumValues()
        )
    }

    @Serializable(with = MainSectionType.Companion::class)
    enum class MainSectionType(override val value: Int) : SerializableEnum<Int> {
        NO(0),
        PHOTOS(1),
        TOPICS(2),
        AUDIOS(3),
        VIDEOS(4),
        MARKET(5);

        companion object : EnumIntSerializer<MainSectionType>(
            clazz = MainSectionType::class,
            values = enumValues()
        )
    }

    @Serializable(with = MemberStatus.Companion::class)
    enum class MemberStatus(override val value: Int) : SerializableEnum<Int> {
        NOT_MEMBER(0),
        MEMBER(1),
        NOT_SURE(2),
        DECLINED_INVITATION(3),
        SENT_REQUEST(4),
        INVITED(5);

        companion object : EnumIntSerializer<MemberStatus>(
            clazz = MemberStatus::class,
            values = enumValues()
        )
    }

    @Serializable
    data class BanInfo(
        @SerialName("end_date") val endDate: Int,
        @Optional @SerialName("comment") val comment: String? = null) {

        @Transient val isPermanently: Boolean get() = endDate == 0

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
        @Optional @SerialName("user_id") val userId: Int? = null,
        @Optional @SerialName("desc") val description: String? = null,
        @Optional @SerialName("phone") val phone: String? = null,
        @Optional @SerialName("email") val email: String? = null)

    @Serializable
    data class Counters(
        @Optional @SerialName("photos") val photos: Int? = null,
        @Optional @SerialName("albums") val albums: Int? = null,
        @Optional @SerialName("videos") val videos: Int? = null,
        @Optional @SerialName("audios") val audios: Int? = null,
        @Optional @SerialName("topics") val topics: Int? = null,
        @Optional @SerialName("docs") val docs: Int? = null)

    @Serializable
    data class Cover(
        @SerialName("enabled") val isEnabled: BooleanInt,
        @Optional @SerialName("images") val images: List<Image>? = null) {

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
        @Optional @SerialName("photo_50") val photo50: String? = null,
        @Optional @SerialName("photo_100") val photo100: String? = null)

    @Serializable
    data class Market(
        @SerialName("enabled") val enabled: BooleanInt,
        @Optional @SerialName("price_min") val priceMin: Int? = null,
        @Optional @SerialName("price_max") val priceMax: Int? = null,
        @Optional @SerialName("main_album_id") val mainAlbumId: Int? = null,
        @Optional @SerialName("contact_id") val contactId: Int? = null,
        @Optional @SerialName("currency") val currency: Currency? = null,
        @Optional @SerialName("currency_text") val currencyText: String? = null) {

        @Transient val isCommunityMessagesForContact: Boolean?
            get() = contactId?.let { it <= 0 }

        @Serializable
        data class Currency(
            @SerialName("id") val id: Int,
            @SerialName("name") val name: String)

    }

}