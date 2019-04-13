package tk.skeptick.vk.apiclient.domain.models

import kotlinx.serialization.*
import tk.skeptick.vk.apiclient.BooleanInt
import tk.skeptick.vk.apiclient.EnumIntSerializer
import tk.skeptick.vk.apiclient.EnumStringSerializer
import tk.skeptick.vk.apiclient.SerializableEnum
import tk.skeptick.vk.apiclient.methods.groups.CommunityManager

@Serializable
data class User(
    @SerialName("id") val id: Int,
    @SerialName("first_name") val firstName: String,
    @SerialName("last_name") val lastName: String,
    @SerialName("is_closed") val isClosed: Boolean? = null,
    @SerialName("can_access_closed") val canAccessClosed: Boolean? = null,
    @SerialName("first_name_nom") val firstNameNom: String? = null,
    @SerialName("first_name_gen") val firstNameGen: String? = null,
    @SerialName("first_name_dat") val firstNameDat: String? = null,
    @SerialName("first_name_acc") val firstNameAcc: String? = null,
    @SerialName("first_name_ins") val firstNameIns: String? = null,
    @SerialName("first_name_abl") val firstNameAbl: String? = null,
    @SerialName("last_name_nom") val lastNameNom: String? = null,
    @SerialName("last_name_gen") val lastNameGen: String? = null,
    @SerialName("last_name_dat") val lastNameDat: String? = null,
    @SerialName("last_name_acc") val lastNameAcc: String? = null,
    @SerialName("last_name_ins") val lastNameIns: String? = null,
    @SerialName("last_name_abl") val lastNameAbl: String? = null,
    @SerialName("maiden_name") val maidenName: String? = null,
    @SerialName("nickname") val nickname: String? = null,
    @SerialName("screen_name") val screenName: String? = null,
    @SerialName("sex") val sex: Sex? = null,
    @SerialName("domain") val domain: String? = null,
    @SerialName("site") val site: String? = null,
    @SerialName("status") val status: String? = null,
    @SerialName("status_audio") val statusAudio: Audio? = null,
    @SerialName("about") val about: String? = null,
    @SerialName("activities") val activities: String? = null,
    @SerialName("books") val books: String? = null,
    @SerialName("games") val games: String? = null,
    @SerialName("interests") val interests: String? = null,
    @SerialName("music") val music: String? = null,
    @SerialName("quotes") val quotes: String? = null,
    @SerialName("tv") val tv: String? = null,
    @SerialName("personal") val personal: Personal? = null,
    @SerialName("relation") val relationStatus: RelationStatus? = null,
    @SerialName("relatives") val relatives: List<Relatives>? = null,
    @SerialName("occupation") val occupation: Occupation? = null,
    @SerialName("schools") val schools: List<School>? = null,
    @SerialName("universities") val universities: List<University>? = null,
    @SerialName("career") val career: List<Career>? = null,
    @SerialName("military") val military: List<Military>? = null,
    @SerialName("city") val city: City? = null,
    @SerialName("home_town") val homeTown: String? = null,
    @SerialName("country") val country: Country? = null,
    @SerialName("bdate") val birthDate: String? = null,
    @SerialName("contacts") val contacts: Contacts? = null,
    @SerialName("connections") val socialConnections: Map<String, String>? = null,
    @SerialName("exports") val socialExports: Map<String, String>? = null,
    @SerialName("counters") val counters: Counters? = null,
    @SerialName("common_count") val commonFriendsCount: Int? = null,
    @SerialName("followers_count") val followersCount: Int? = null,
    @SerialName("crop_photo") val cropPhoto: CropPhoto? = null,
    @SerialName("last_seen") val lastSeenInfo: LastSeenInfo? = null,
    @SerialName("online_app") val onlineFromAppId: String? = null,
    @SerialName("relation_partner") val relationPartner: RelationPartner? = null,
    @SerialName("lists") val lists: List<Int>? = null,
    @SerialName("online") val isOnline: BooleanInt? = null,
    @SerialName("online_mobile") val isOnlineFromMobile: BooleanInt? = null,
    @SerialName("has_mobile") val hasMobile: BooleanInt? = null,
    @SerialName("has_photo") val hasPhoto: BooleanInt? = null,
    @SerialName("is_favorite") val isFavorite: BooleanInt? = null,
    @SerialName("is_friend") val isFriend: BooleanInt? = null,
    @SerialName("friend_status") val friendStatus: FriendStatus? = null,
    @SerialName("is_hidden_from_feed") val isHiddenFromFeed: BooleanInt? = null,
    @SerialName("can_post") val canPost: BooleanInt? = null,
    @SerialName("can_see_all_posts") val canSeeAllPosts: BooleanInt? = null,
    @SerialName("can_see_audio") val canSeeAudio: BooleanInt? = null,
    @SerialName("can_send_friend_request") val canSendFriendRequest: BooleanInt? = null,
    @SerialName("can_write_private_message") val canWriteMessage: BooleanInt? = null,
    @SerialName("blacklisted") val isBlacklisted: BooleanInt? = null,
    @SerialName("blacklisted_by_me") val isBlacklistedByMe: BooleanInt? = null,
    @SerialName("trending") val isTrending: BooleanInt? = null,
    @SerialName("verified") val isVerified: BooleanInt? = null,
    @SerialName("hidden") val isHidden: BooleanInt = BooleanInt(false),
    @SerialName("photo_id") val photo50: String? = null,
    @SerialName("photo_100") val photo100: String? = null,
    @SerialName("photo_200") val photo200: String? = null,
    @SerialName("photo_max") val photoMax: String? = null,
    @SerialName("photo_200_orig") val photo200Orig: String? = null,
    @SerialName("photo_400_orig") val photo400Orig: String? = null,
    @SerialName("photo_max_orig") val photoMaxOrig: String? = null,
    @SerialName("timezone") val timezone: Int? = null,
    @SerialName("deactivated") val deactivationType: DeactivationType? = null,
    @SerialName("invited_by") val invitedBy: Int? = null,
    @SerialName("role") val role: CommunityManager.Role? = null) {

    val isDeactivated: Boolean get() = deactivationType != null

    @Serializable(with = Sex.Companion::class)
    enum class Sex(override val value: Int) : SerializableEnum<Int> {
        FEMALE(1),
        MALE(2),
        NOT_SPECIFIED(0);

        companion object : EnumIntSerializer<Sex>(Sex::class)
    }

    @Serializable(with = RelationStatus.Companion::class)
    enum class RelationStatus(override val value: Int) : SerializableEnum<Int> {
        SINGLE(1),
        RELATIONSHIP(2),
        ENGAGED(3),
        MARRIED(4),
        COMPLICATED(5),
        ACTIVELY_SEARCHING(6),
        LOVE(7),
        CIVIL_UNION(8),
        NOT_SPECIFIED(0);

        companion object : EnumIntSerializer<RelationStatus>(RelationStatus::class)
    }

    @Serializable(with = FriendStatus.Companion::class)
    enum class FriendStatus(override val value: Int) : SerializableEnum<Int> {
        NOT_FRIEND(0),
        OUTGOING_REQUEST(1),
        INCOMING_REQUEST(2),
        FRIEND(3);

        companion object : EnumIntSerializer<FriendStatus>(FriendStatus::class)
    }

    @Serializable(with = DeactivationType.Companion::class)
    enum class DeactivationType(override val value: String) : SerializableEnum<String> {
        DELETED("deleted"),
        BANNED("banned");

        companion object : EnumStringSerializer<DeactivationType>(DeactivationType::class)
    }

    @Serializable
    data class Personal(
        @SerialName("political") val politicalView: PoliticalView? = null,
        @SerialName("langs") val languages: List<String>? = null,
        @SerialName("religion") val religion: String? = null,
        @SerialName("inspired_by") val inspiredBy: String? = null,
        @SerialName("people_main") val importantInOthersPeople: ImportantInOthers? = null,
        @SerialName("life_main") val personalPriority: PersonalPriority? = null,
        @SerialName("smoking") val viewOnSmoking: ViewOnBadHabit? = null,
        @SerialName("alcohol") val viewOnAlcohol: ViewOnBadHabit? = null) {

        @Serializable(with = PoliticalView.Companion::class)
        enum class PoliticalView(override val value: Int) : SerializableEnum<Int> {
            COMMUNIST(1),
            SOCIALIST(2),
            MODERATE(3),
            LIBERAL(4),
            CONSERVATIVE(5),
            MONARCHIST(6),
            ULTRACONSERVATIVE(7),
            APATHETIC(8),
            LIBERTARIAN(9);

            companion object : EnumIntSerializer<PoliticalView>(PoliticalView::class)
        }

        @Serializable(with = ImportantInOthers.Companion::class)
        enum class ImportantInOthers(override val value: Int) : SerializableEnum<Int> {
            INTELLECT_AND_CREATIVITY(1),
            KINDNESS_AND_HONESTY(2),
            HEALTH_AND_BEAUTY(3),
            WEALTH_AND_POWER(4),
            COURAGE_AND_PERSISTENCE(5),
            HUMOR_AND_LOVE_FOR_LIFE(6);

            companion object : EnumIntSerializer<ImportantInOthers>(ImportantInOthers::class)
        }

        @Serializable(with = PersonalPriority.Companion::class)
        enum class PersonalPriority(override val value: Int) : SerializableEnum<Int> {
            FAMILY_AND_CHILDREN(1),
            CAREER_AND_MONEY(2),
            ENTERTAINMENT_AND_LEISURE(3),
            SCIENCE_AND_RESEARCH(4),
            IMPROVING_THE_WORLD(5),
            PERSONAL_DEVELOPMENT(6),
            BEAUTY_AND_ART(7),
            FAME_AND_INFLUENCE(8);

            companion object : EnumIntSerializer<PersonalPriority>(PersonalPriority::class)
        }

        @Serializable(with = ViewOnBadHabit.Companion::class)
        enum class ViewOnBadHabit(override val value: Int) : SerializableEnum<Int> {
            VERY_NEGATIVE(1),
            NEGATIVE(2),
            NEUTRAL(3),
            COMPROMISABLE(4),
            POSITIVE(5);

            companion object : EnumIntSerializer<ViewOnBadHabit>(ViewOnBadHabit::class)
        }

    }

    @Serializable
    data class Relatives(
        @SerialName("type") val type: Type,
        @SerialName("id") val userId: Int? = null,
        @SerialName("name") val name: Int? = null) {

        @Serializable(with = Type.Companion::class)
        enum class Type(override val value: String) : SerializableEnum<String> {
            CHILD("child"),
            SIBLING("sibling"),
            PARENT("parent"),
            GRANDPARENT("grandparent"),
            GRANDCHILD("grandchild");

            companion object : EnumStringSerializer<Type>(Type::class)
        }

    }

    @Serializable
    data class Occupation(
        @SerialName("id") val id: Int? = null,
        @SerialName("name") val name: String? = null,
        @SerialName("type") private val type: Type? = null) {

        @Serializable(with = Type.Companion::class)
        enum class Type(override val value: String) : SerializableEnum<String> {
            WORK("work"),
            SCHOOL("school"),
            UNIVERSITY("university");

            companion object : EnumStringSerializer<Type>(Type::class)
        }

    }

    @Serializable
    data class School(
        @SerialName("id") val id: Int? = null,
        @SerialName("country") val countryId: Int? = null,
        @SerialName("city") val cityId: Int? = null,
        @SerialName("name") val name: String? = null,
        @SerialName("year_from") val yearFrom: Int? = null,
        @SerialName("year_to") val yearTo: Int? = null,
        @SerialName("year_graduated") val yearGraduated: Int? = null,
        @SerialName("class") val classLetter: String? = null,
        @SerialName("speciality") val speciality: String? = null,
        @SerialName("type") val type: Type? = null,
        @SerialName("type_str") val typeName: String? = null) {

        @Serializable(with = Type.Companion::class)
        enum class Type(override val value: Int) : SerializableEnum<Int> {
            SCHOOL(0),
            GYMNASIUM(1),
            LYCEUM(2),
            BOARDING_SCHOOL(3),
            EVENING_SCHOOL(4),
            MUSIC_SCHOOL(5),
            SPORT_SCHOOL(6),
            ARTISTIC_SCHOOL(7),
            COLLEGE(8),
            PROFESSIONAL_LYCEUM(9),
            TECHNICAL_COLLEGE(10),
            VOCATIONAL(11),
            SPECIALIZED_SCHOOL(12),
            ART_SCHOOL(13);

            companion object : EnumIntSerializer<Type>(Type::class)
        }

    }

    @Serializable
    data class University(
        @SerialName("id") val id: Int? = null,
        @SerialName("country") val countryId: Int? = null,
        @SerialName("city") val cityId: Int? = null,
        @SerialName("name") val name: String? = null,
        @SerialName("faculty") val facultyId: Int? = null,
        @SerialName("faculty_name") val facultyName: String? = null,
        @SerialName("chair") val chairId: Int? = null,
        @SerialName("chair_name") val chairName: String? = null,
        @SerialName("graduation") val yearGraduation: Int? = null,
        @SerialName("education_form") val educationForm: String? = null,
        @SerialName("education_status") val educationStatus: String? = null)

    @Serializable
    data class Career(
        @SerialName("group_id") val groupId: Int? = null,
        @SerialName("company") val company: String? = null,
        @SerialName("country_id") val countryId: Int? = null,
        @SerialName("city_id") val cityId: Int? = null,
        @SerialName("city_name") val cityName: String? = null,
        @SerialName("from") val yearFrom: Int? = null,
        @SerialName("until") val yearUntil: Int? = null,
        @SerialName("position") val position: String? = null)

    @Serializable
    data class Military(
        @SerialName("unit") val unit: String? = null,
        @SerialName("unit_id") val unitId: Int? = null,
        @SerialName("country_id") val countryId: Int? = null,
        @SerialName("from") val yearFrom: Int? = null,
        @SerialName("until") val yearUntil: Int? = null)

    @Serializable
    data class City(
        @SerialName("id") val id: Int,
        @SerialName("title") val title: String)

    @Serializable
    data class Country(
        @SerialName("id") val id: Int,
        @SerialName("title") val title: String)

    @Serializable
    data class Contacts(
        @SerialName("mobile_phone") val mobilePhone: String? = null,
        @SerialName("home_phone") val homePhone: String? = null)

    @Serializable
    data class Counters(
        @SerialName("albums") val albums: Int? = null,
        @SerialName("videos") val videos: Int? = null,
        @SerialName("audios") val audios: Int? = null,
        @SerialName("photos") val photos: Int? = null,
        @SerialName("notes") val notes: Int? = null,
        @SerialName("friends") val friends: Int? = null,
        @SerialName("groups") val groups: Int? = null,
        @SerialName("online_friends") val onlineFriends: Int? = null,
        @SerialName("mutual_friends") val mutualFriends: Int? = null,
        @SerialName("user_videos") val userVideos: Int? = null,
        @SerialName("followers") val followers: Int? = null,
        @SerialName("pages") val pages: Int? = null)

    @Serializable
    data class LastSeenInfo(
        @SerialName("time") val time: Int,
        @SerialName("platform") val platform: ClientPlatform? = null) {

        @Serializable(with = ClientPlatform.Companion::class)
        enum class ClientPlatform(override val value: Int) : SerializableEnum<Int> {
            WEB_MOBILE(1),
            APP_IPHONE(2),
            APP_IPAD(3),
            APP_ANDROID(4),
            APP_WINDOWS_PHONE(5),
            APP_WINDOWS_10(6),
            WEB_DESKTOP(7),
            VK_MOBILE(8);

            companion object : EnumIntSerializer<ClientPlatform>(ClientPlatform::class)
        }

    }

    @Serializable
    data class RelationPartner(
        @SerialName("id") val id: Int,
        @SerialName("first_name") val firstName: String,
        @SerialName("last_name") val lastName: String)

}