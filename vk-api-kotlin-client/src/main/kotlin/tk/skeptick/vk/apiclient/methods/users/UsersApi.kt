package tk.skeptick.vk.apiclient.methods.users

import kotlinx.serialization.builtins.list
import tk.skeptick.vk.apiclient.*
import tk.skeptick.vk.apiclient.domain.EntityWrapper
import tk.skeptick.vk.apiclient.domain.models.User
import tk.skeptick.vk.apiclient.methods.*

class UsersApi(override val client: ApiClient)
    : UsersApiUser, UsersApiCommunity, MethodsContext {

    override fun get(
        userNames: List<String>?,
        userFields: List<UserOptionalField>,
        nameCase: NameCase
    ): VkApiRequest<List<User>> =
        Methods.get.httpPost(User.serializer().list) {
            append("user_ids", userNames?.joinToString(","))
            append("fields", userFields.joinToString(",") { it.value })
            append("name_case", nameCase.value)
        }

    override fun getById(
        userIds: List<Int>,
        userFields: List<UserOptionalField>,
        nameCase: NameCase
    ): VkApiRequest<List<User>> =
        get(
            userNames = userIds.map(Int::toString),
            userFields = userFields,
            nameCase = nameCase
        )

    override fun getFollowers(
        userId: Int?,
        offset: Int,
        count: Int,
        userFields: List<UserOptionalField>,
        nameCase: NameCase
    ): VkApiRequest<DefaultListResponse<User>> =
        Methods.getFollowers.httpPost(list(User.serializer())) {
            append("user_id", userId)
            append("offset", offset)
            append("count", count)
            append("fields", userFields.joinToString(",") { it.value })
            append("name_case", nameCase.value)
        }

    override fun getNearby(
        latitude: Double,
        longitude: Double,
        accuracy: Int?,
        timeout: Int,
        radius: NearbyRadius,
        userFields: List<UserOptionalField>,
        nameCase: NameCase,
        needDescription: Boolean
    ): VkApiRequest<NearbyUsersListResponse> =
        Methods.getNearby.httpPost(NearbyUsersListResponse.serializer()) {
            append("latitude", latitude)
            append("longitude", longitude)
            append("accuracy", accuracy)
            append("timeout", timeout)
            append("radius", radius.value)
            append("fields", userFields.joinToString(",") { it.value })
            append("name_case", nameCase.value)
            append("need_description", needDescription.asInt())
        }

    override fun getSubscriptions(
        userId: Int?,
        offset: Int,
        count: Int,
        fields: List<ObjectField>
    ): VkApiRequest<DefaultListResponse<EntityWrapper>> =
        Methods.getSubscriptions.httpPost(list(EntityWrapper.serializer())) {
            append("user_id", userId)
            append("extended", 1)
            append("offset", offset)
            append("count", count)
            append("fields", fields.joinToString(",") { it.value })
        }

    override fun getSubscriptionsIds(
        userId: Int?
    ): VkApiRequest<SubscriptionsResponse> =
        Methods.getSubscriptions.httpGet(SubscriptionsResponse.serializer()) {
            append("user_id", userId)
        }

    override fun isAppUser(
        userId: Int?
    ): VkApiRequest<BooleanInt> =
        Methods.isAppUser.httpGet(BooleanInt.serializer()) {
            append("user_id", userId)
        }

    override fun report(
        userId: Int,
        complaintType: UserReportComplaintType,
        comment: String?
    ): VkApiRequest<BooleanInt> =
        Methods.report.httpPost(BooleanInt.serializer()) {
            append("user_id", userId)
            append("type", complaintType.value)
            append("comment", comment)
        }

    override fun search(
        query: String?,
        sort: UserSearchSort,
        offset: Int,
        count: Int,
        userFields: List<UserOptionalField>,
        cityId: Int?,
        countryId: Int?,
        hometown: String?,
        universityCountryId: Int?,
        universityId: Int?,
        universityYear: Int?,
        universityFacultyId: Int?,
        universityChairId: Int?,
        sex: User.Sex,
        relationStatus: User.RelationStatus,
        ageFrom: Int?,
        ageTo: Int?,
        birthDay: Int?,
        birthMonth: Int?,
        birthYear: Int?,
        onlyOnline: Boolean,
        onlyWithPhoto: Boolean,
        schoolCountryId: Int?,
        schoolCityId: Int?,
        schoolClassId: Int?,
        schoolId: Int?,
        schoolYear: Int?,
        religion: String?,
        interests: String?,
        companyName: String?,
        positionName: String?,
        groupId: Int?,
        fromList: List<UsersListType>?
    ): VkApiRequest<DefaultListResponse<User>> =
        Methods.search.httpPost(list(User.serializer())) {
            append("q", query)
            append("sort", sort.value)
            append("offset", offset)
            append("count", count)
            append("fields", userFields.joinToString(",") { it.value })
            append("city", cityId)
            append("country", countryId)
            append("hometown", hometown)
            append("university_country", universityCountryId)
            append("university", universityId)
            append("university_year", universityYear)
            append("university_faculty", universityFacultyId)
            append("university_chair", universityChairId)
            append("sex", sex.value)
            append("status", relationStatus.value)
            append("age_from", ageFrom)
            append("age_to", ageTo)
            append("birth_day", birthDay)
            append("birth_month", birthMonth)
            append("birth_year", birthYear)
            append("online", onlyOnline.asInt())
            append("has_photo", onlyWithPhoto.asInt())
            append("school_country", schoolCountryId)
            append("school_city", schoolCityId)
            append("school_class", schoolClassId)
            append("school", schoolId)
            append("school_year", schoolYear)
            append("religion", religion)
            append("interests", interests)
            append("company", companyName)
            append("position", positionName)
            append("group_id", groupId)
            append("from_list", fromList?.joinToString(",") { it.value })
        }

    private object Methods {
        private const val it = "users."
        const val get = it + "get"
        const val getFollowers = it + "getFollowers"
        const val getNearby = it + "getNearby"
        const val getSubscriptions = it + "getSubscriptions"
        const val isAppUser = it + "isAppUser"
        const val report = it + "report"
        const val search = it + "search"
    }

}