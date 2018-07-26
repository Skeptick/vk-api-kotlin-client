package tk.skeptick.vk.apiclient.methods.users

import kotlinx.serialization.list
import tk.skeptick.vk.apiclient.*
import tk.skeptick.vk.apiclient.domain.EntityWrapper
import tk.skeptick.vk.apiclient.domain.models.User
import tk.skeptick.vk.apiclient.methods.*

class UsersApi(override val client: VkApiClient)
    : UsersApiUser, UsersApiCommunity, MethodsContext {

    override fun get(
        userNames: List<String>?,
        userFields: List<UserOptionalField>,
        nameCase: NameCase
    ): VkApiRequest<List<User>> =
        Methods.get.httpPost(
            "user_ids" to userNames?.joinToString(","),
            "fields" to userFields.joinToString(",") { it.value },
            "name_case" to nameCase.value
        ).withSerializer(User.serializer().list)

    override fun getById(
        userIds: List<Int>,
        userFields: List<UserOptionalField>,
        nameCase: NameCase
    ): VkApiRequest<List<User>> = get(
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
        Methods.getFollowers.httpPost(
            "user_id" to userId,
            "offset" to offset,
            "count" to count,
            "fields" to userFields.joinToString(",") { it.value },
            "name_case" to nameCase.value
        ).withSerializer(list(User.serializer()))

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
        Methods.getNearby.httpPost(
            "latitude" to latitude,
            "longitude" to longitude,
            "accuracy" to accuracy,
            "timeout" to timeout,
            "radius" to radius.value,
            "fields" to userFields.joinToString(",") { it.value },
            "name_case" to nameCase.value,
            "need_description" to needDescription.asInt()
        ).withSerializer(NearbyUsersListResponse.serializer())

    override fun getSubscriptions(
        userId: Int?,
        offset: Int,
        count: Int,
        fields: List<ObjectField>
    ): VkApiRequest<DefaultListResponse<EntityWrapper>> =
        Methods.getSubscriptions.httpPost(
            "user_id" to userId,
            "extended" to 1,
            "offset" to offset,
            "count" to count,
            "fields" to fields.joinToString(",") { it.value }
        ).withSerializer(list(EntityWrapper.serializer()))

    override fun getSubscriptionsIds(
        userId: Int?
    ): VkApiRequest<SubscriptionsResponse> =
        Methods.getSubscriptions.httpGet(
            "user_id" to userId
        ).withSerializer(SubscriptionsResponse.serializer())

    override fun isAppUser(
        userId: Int?
    ): VkApiRequest<BooleanInt> =
        Methods.isAppUser.httpGet(
            "user_id" to userId
        ).withSerializer(BooleanInt.serializer())

    override fun report(
        userId: Int,
        complaintType: ReportComplaintType,
        comment: String?
    ): VkApiRequest<BooleanInt> =
        Methods.report.httpPost(
            "user_id" to userId,
            "type" to complaintType.value,
            "comment" to comment
        ).withSerializer(BooleanInt.serializer())

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
        Methods.search.httpPost(
            "q" to query,
            "sort" to sort.value,
            "offset" to offset,
            "count" to count,
            "fields" to userFields.joinToString(",") { it.value },
            "city" to cityId,
            "country" to countryId,
            "hometown" to hometown,
            "university_country" to universityCountryId,
            "university" to universityId,
            "university_year" to universityYear,
            "university_faculty" to universityFacultyId,
            "university_chair" to universityChairId,
            "sex" to sex.value,
            "status" to relationStatus.value,
            "age_from" to ageFrom,
            "age_to" to ageTo,
            "birth_day" to birthDay,
            "birth_month" to birthMonth,
            "birth_year" to birthYear,
            "online" to onlyOnline.asInt(),
            "has_photo" to onlyWithPhoto.asInt(),
            "school_country" to schoolCountryId,
            "school_city" to schoolCityId,
            "school_class" to schoolClassId,
            "school" to schoolId,
            "school_year" to schoolYear,
            "religion" to religion,
            "interests" to interests,
            "company" to companyName,
            "position" to positionName,
            "group_id" to groupId,
            "from_list" to fromList?.joinToString(",") { it.value }
        ).withSerializer(list(User.serializer()))

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