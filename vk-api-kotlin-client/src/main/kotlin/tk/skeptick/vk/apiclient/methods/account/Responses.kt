package tk.skeptick.vk.apiclient.methods.account

import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import tk.skeptick.vk.apiclient.BooleanInt
import tk.skeptick.vk.apiclient.EnumIntSerializer
import tk.skeptick.vk.apiclient.EnumStringSerializer
import tk.skeptick.vk.apiclient.SerializableEnum
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
    @Optional @SerialName("instruction_html") val instructionHtml: String? = null,
    @Optional @SerialName("short_description") val shortDescription: String? = null,
    @Optional @SerialName("description") val description: String? = null,
    @Optional @SerialName("img") val image: String? = null,
    @Optional @SerialName("tag") val tag: String? = null)

@Serializable
data class AccountCounters(
    @Optional @SerialName("friends") val friends: Int? = null,
    @Optional @SerialName("messages") val messages: Int? = null,
    @Optional @SerialName("photos") val photos: Int? = null,
    @Optional @SerialName("videos") val videos: Int? = null,
    @Optional @SerialName("notes") val notes: Int? = null,
    @Optional @SerialName("gifts") val gifts: Int? = null,
    @Optional @SerialName("events") val events: Int? = null,
    @Optional @SerialName("groups") val groups: Int? = null,
    @Optional @SerialName("notifications") val notifications: Int? = null,
    @Optional @SerialName("sdk") val sdk: Int? = null,
    @Optional @SerialName("app_requests") val appRequests: Int? = null)

@Serializable
data class AccountInfo(
    @Optional @SerialName("country") val countryCode: String? = null,
    @Optional @SerialName("https_required") val isHttpsRequired: BooleanInt? = null,
    @Optional @SerialName("2fa_required") val is2faRequired: BooleanInt? = null,
    @Optional @SerialName("own_posts_default") val isOwnPostsDefault: BooleanInt? = null,
    @Optional @SerialName("no_wall_replies") val isNoWallReplies: BooleanInt? = null,
    @Optional @SerialName("intro") val isIntroPassed: BooleanInt? = null,
    @Optional @SerialName("lang") val language: Language? = null)

@Serializable
data class AccountProfileInfo(
    @SerialName("first_name") val firstName: String,
    @SerialName("last_name") val lastName: String,
    @Optional @SerialName("maiden_name") val maidenName: String? = null,
    @Optional @SerialName("screen_name") val screenName: String? = null,
    @Optional @SerialName("sex") val sex: User.Sex? = null,
    @Optional @SerialName("relation") val relationStatus: User.RelationStatus? = null,
    @Optional @SerialName("relation_partner") val relationPartner: User.RelationPartner? = null,
    @Optional @SerialName("relation_pending") val isRelationPending: BooleanInt? = null,
    @Optional @SerialName("relation_requests") val relationRequests: List<User.RelationPartner>? = null,
    @Optional @SerialName("bdate") val birthDate: String? = null,
    @Optional @SerialName("bdate_visibility") val birthDateVisibility: BirthDateVisibility? = null,
    @Optional @SerialName("home_town") val homeTown: String? = null,
    @Optional @SerialName("country") val country: User.Country? = null,
    @Optional @SerialName("city") val city: User.City? = null,
    @Optional @SerialName("name_request") val nameRequest: NameRequest? = null,
    @Optional @SerialName("status") val status: String? = null,
    @Optional @SerialName("phone") val phone: String? = null) {

    @Serializable(with = BirthDateVisibility.Companion::class)
    enum class BirthDateVisibility(override val value: Int) : SerializableEnum<Int> {
        SHOW_FULL(1),
        SHOW_MONTH_AND_DAY(2),
        HIDE(0);

        companion object : EnumIntSerializer<BirthDateVisibility>(
            clazz = BirthDateVisibility::class,
            values = enumValues()
        )
    }

    @Serializable
    data class NameRequest(
        @SerialName("id") val id: Int,
        @SerialName("first_name") val firstName: String,
        @SerialName("last_name") val lastName: String,
        @SerialName("status") val status: Status) {

        @Serializable(with = Status.Companion::class)
        enum class Status(override val value: String) : SerializableEnum<String> {
            PROCESSING("processing"),
            DECLINED("declined");

            companion object : EnumStringSerializer<Status>(
                clazz = Status::class,
                values = enumValues()
            )
        }

    }

}

@Serializable
data class PushSettings(
    @SerialName("disabled") val isDisabled: BooleanInt = BooleanInt(false),
    @SerialName("conversations") val conversations: DefaultListResponse<Conversation>,
    @Optional @SerialName("disabled_until") val disabledUntil: Int? = null) {

    @Serializable
    data class Conversation(
        @SerialName("peer_id") val peerId: Int,
        @SerialName("sound") val isSoundEnabled: BooleanInt,
        @SerialName("disabled_until") val disabledUntil: Int) {

        @Transient val isDisabledForever: Boolean get() = disabledUntil <= 0

    }

}

@Serializable
data class SaveAccountProfileInfoResponse(
    @SerialName("changed") val isChanged: BooleanInt,
    @Optional @SerialName("name_request") val nameRequest: NameRequest? = null) {

    @Serializable
    data class NameRequest(
        @SerialName("id") val id: Int,
        @SerialName("first_name") val firstName: String,
        @SerialName("last_name") val lastName: String,
        @SerialName("status") val status: Status,
        @Optional @SerialName("repeat_date") val repeatDate: Int? = null) {

        @Serializable(with = Status.Companion::class)
        enum class Status(override val value: String) : SerializableEnum<String> {
            SUCCESS("success"),
            PROCESSING("processing"),
            DECLINED("declined"),
            WAS_ACCEPTED("was_accepted"),
            WAS_DECLINED("was_declined");

            companion object : EnumStringSerializer<Status>(
                clazz = Status::class,
                values = enumValues()
            )
        }

    }

}