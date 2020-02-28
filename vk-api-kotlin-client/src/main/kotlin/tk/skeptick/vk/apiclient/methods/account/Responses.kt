package tk.skeptick.vk.apiclient.methods.account

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tk.skeptick.vk.apiclient.*
import tk.skeptick.vk.apiclient.domain.Language
import tk.skeptick.vk.apiclient.domain.models.User
import tk.skeptick.vk.apiclient.methods.DefaultListResponse

@Serializable
data class ChangePasswordResponse(
    @SerialName("token") val token: String)

@Serializable
data class ActiveOffer(
    @SerialName("id") val id: String,
    @SerialName("title") val title: String,
    @SerialName("instruction") val instruction: String,
    @SerialName("price") val price: Int,
    @SerialName("instruction_html") val instructionHtml: String? = null,
    @SerialName("short_description") val shortDescription: String? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("img") val image: String? = null,
    @SerialName("tag") val tag: String? = null)

@Serializable
data class AccountCounters(
    @SerialName("friends") val friends: Int? = null,
    @SerialName("messages") val messages: Int? = null,
    @SerialName("photos") val photos: Int? = null,
    @SerialName("videos") val videos: Int? = null,
    @SerialName("notes") val notes: Int? = null,
    @SerialName("gifts") val gifts: Int? = null,
    @SerialName("events") val events: Int? = null,
    @SerialName("groups") val groups: Int? = null,
    @SerialName("notifications") val notifications: Int? = null,
    @SerialName("sdk") val sdk: Int? = null,
    @SerialName("app_requests") val appRequests: Int? = null)

@Serializable
data class AccountInfo(
    @SerialName("country") val countryCode: String? = null,
    @SerialName("https_required") val isHttpsRequired: BooleanInt? = null,
    @SerialName("2fa_required") val is2faRequired: BooleanInt? = null,
    @SerialName("own_posts_default") val isOwnPostsDefault: BooleanInt? = null,
    @SerialName("no_wall_replies") val isNoWallReplies: BooleanInt? = null,
    @SerialName("intro") val isIntroPassed: BooleanInt? = null,
    @SerialName("lang") val language: Language? = null)

@Serializable
data class AccountProfileInfo(
    @SerialName("first_name") val firstName: String,
    @SerialName("last_name") val lastName: String,
    @SerialName("maiden_name") val maidenName: String? = null,
    @SerialName("screen_name") val screenName: String? = null,
    @SerialName("sex") val sex: User.Sex? = null,
    @SerialName("relation") val relationStatus: User.RelationStatus? = null,
    @SerialName("relation_partner") val relationPartner: User.RelationPartner? = null,
    @SerialName("relation_pending") val isRelationPending: BooleanInt? = null,
    @SerialName("relation_requests") val relationRequests: List<User.RelationPartner>? = null,
    @SerialName("bdate") val birthDate: String? = null,
    @SerialName("bdate_visibility") val birthDateVisibility: BirthDateVisibility? = null,
    @SerialName("home_town") val homeTown: String? = null,
    @SerialName("country") val country: User.Country? = null,
    @SerialName("city") val city: User.City? = null,
    @SerialName("name_request") val nameRequest: NameRequest? = null,
    @SerialName("status") val status: String? = null,
    @SerialName("phone") val phone: String? = null) {

    @Serializable(with = BirthDateVisibility.Companion::class)
    enum class BirthDateVisibility(override val value: Int) : IntEnum {
        SHOW_FULL(1),
        SHOW_MONTH_AND_DAY(2),
        HIDE(0);

        companion object : EnumIntSerializer<BirthDateVisibility>(BirthDateVisibility::class, values())
    }

    @Serializable
    data class NameRequest(
        @SerialName("id") val id: Int,
        @SerialName("first_name") val firstName: String,
        @SerialName("last_name") val lastName: String,
        @SerialName("status") val status: Status) {

        @Serializable
        enum class Status(val value: String) {
            @SerialName("processing") PROCESSING("processing"),
            @SerialName("declined") DECLINED("declined")
        }

    }

}

@Serializable
data class PushSettings(
    @SerialName("disabled") val isDisabled: BooleanInt = BooleanInt(false),
    @SerialName("conversations") val conversations: DefaultListResponse<Conversation>,
    @SerialName("disabled_until") val disabledUntil: Int? = null) {

    @Serializable
    data class Conversation(
        @SerialName("peer_id") val peerId: Int,
        @SerialName("sound") val isSoundEnabled: BooleanInt,
        @SerialName("disabled_until") val disabledUntil: Int) {

        val isDisabledForever: Boolean get() = disabledUntil <= 0

    }

}

@Serializable
data class SaveAccountProfileInfoResponse(
    @SerialName("changed") val isChanged: BooleanInt,
    @SerialName("name_request") val nameRequest: NameRequest? = null) {

    @Serializable
    data class NameRequest(
        @SerialName("id") val id: Int,
        @SerialName("first_name") val firstName: String,
        @SerialName("last_name") val lastName: String,
        @SerialName("status") val status: Status,
        @SerialName("repeat_date") val repeatDate: Int? = null) {

        @Serializable
        enum class Status(val value: String) {
            @SerialName("success") SUCCESS("success"),
            @SerialName("processing") PROCESSING("processing"),
            @SerialName("declined") DECLINED("declined"),
            @SerialName("was_accepted") WAS_ACCEPTED("was_accepted"),
            @SerialName("was_declined") WAS_DECLINED("was_declined")
        }

    }

}