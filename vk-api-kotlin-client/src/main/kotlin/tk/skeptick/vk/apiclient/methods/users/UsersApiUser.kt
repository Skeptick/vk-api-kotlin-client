package tk.skeptick.vk.apiclient.methods.users

import tk.skeptick.vk.apiclient.BooleanInt
import tk.skeptick.vk.apiclient.DefaultMethodsParams
import tk.skeptick.vk.apiclient.VkApiRequest
import tk.skeptick.vk.apiclient.domain.EntityWrapper
import tk.skeptick.vk.apiclient.domain.models.User
import tk.skeptick.vk.apiclient.methods.*

interface UsersApiUser : UsersApiCommon {

    /**
     * @param[count] maximum value 1000
     * @see <a href="https://vk.com/dev/users.getFollowers">VK API</a>
     */
    fun getFollowers(
        userId: Int? = null,
        offset: Int = 0,
        count: Int = 100,
        userFields: List<UserOptionalField> = DefaultMethodsParams.userFields,
        nameCase: NameCase = NameCase.NOM
    ): VkApiRequest<DefaultListResponse<User>>

    /**
     * @param[latitude] in degrees (from -90 to 90)
     * @param[longitude] in degrees (from -180 to 180)
     * @param[accuracy] location accuracy in meters
     * @param[timeout] time when a user disappears from location search results, in seconds
     * @see <a href="https://vk.com/dev/users.getNearby">VK API</a>
     */
    fun getNearby(
        latitude: Double,
        longitude: Double,
        accuracy: Int? = null,
        timeout: Int = 600,
        radius: NearbyRadius = NearbyRadius.M_300,
        userFields: List<UserOptionalField> = DefaultMethodsParams.userFields,
        nameCase: NameCase = NameCase.NOM,
        needDescription: Boolean = false
    ): VkApiRequest<NearbyUsersListResponse>

    /**
     * @param[count] maximum value 200
     * @see <a href="https://vk.com/dev/users.getSubscriptions">VK API</a>
     */
    fun getSubscriptions(
        userId: Int? = null,
        offset: Int = 0,
        count: Int = 20,
        fields: List<ObjectField> = DefaultMethodsParams.userFields
    ): VkApiRequest<DefaultListResponse<EntityWrapper>>

    /**
     * @see <a href="https://vk.com/dev/users.getSubscriptions">VK API</a>
     */
    fun getSubscriptionsIds(
        userId: Int? = null
    ): VkApiRequest<SubscriptionsResponse>

    /**
     * @see <a href="https://vk.com/dev/users.isAppUser">VK API</a>
     */
    fun isAppUser(
        userId: Int? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/users.report">VK API</a>
     */
    fun report(
        userId: Int,
        complaintType: ReportComplaintType,
        comment: String? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @param[count] maximum value 1000
     * @see <a href="https://vk.com/dev/users.search">VK API</a>
     */
    fun search(
        query: String? = null,
        sort: UserSearchSort = UserSearchSort.RATING,
        offset: Int = 0,
        count: Int = 20,
        userFields: List<UserOptionalField> = DefaultMethodsParams.userFields,
        cityId: Int? = null,
        countryId: Int? = null,
        hometown: String? = null,
        universityCountryId: Int? = null,
        universityId: Int? = null,
        universityYear: Int? = null,
        universityFacultyId: Int? = null,
        universityChairId: Int? = null,
        sex: User.Sex = User.Sex.NOT_SPECIFIED,
        relationStatus: User.RelationStatus = User.RelationStatus.NOT_SPECIFIED,
        ageFrom: Int? = null,
        ageTo: Int? = null,
        birthDay: Int? = null,
        birthMonth: Int? = null,
        birthYear: Int? = null,
        onlyOnline: Boolean = false,
        onlyWithPhoto: Boolean = false,
        schoolCountryId: Int? = null,
        schoolCityId: Int? = null,
        schoolClassId: Int? = null,
        schoolId: Int? = null,
        schoolYear: Int? = null,
        religion: String? = null,
        interests: String? = null,
        companyName: String? = null,
        positionName: String? = null,
        groupId: Int? = null,
        fromList: List<UsersListType>? = null
    ): VkApiRequest<DefaultListResponse<User>>

}