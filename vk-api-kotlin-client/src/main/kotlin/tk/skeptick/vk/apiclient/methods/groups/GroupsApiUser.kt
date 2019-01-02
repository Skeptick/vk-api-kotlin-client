package tk.skeptick.vk.apiclient.methods.groups

import tk.skeptick.vk.apiclient.BooleanInt
import tk.skeptick.vk.apiclient.DefaultMethodsParams
import tk.skeptick.vk.apiclient.VkApiRequest
import tk.skeptick.vk.apiclient.domain.models.Community
import tk.skeptick.vk.apiclient.domain.models.User
import tk.skeptick.vk.apiclient.methods.*
import java.util.*

interface GroupsApiUser : GroupsApiCommon {

    /**
     * @see <a href="https://vk.com/dev/groups.addLink">VK API</a>
     */
    fun addLink(
        groupId: Int,
        link: String,
        text: String? = null
    ): VkApiRequest<AddLinkResponse>

    /**
     * @see <a href="https://vk.com/dev/groups.approveRequest">VK API</a>
     */
    fun approveRequest(
        groupId: Int,
        userId: Int
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/groups.ban">VK API</a>
     */
    fun ban(
        groupId: Int,
        ownerId: Int,
        endDate: Date? = null,
        reason: CommunityBan.Reason = CommunityBan.Reason.OTHER,
        comment: String? = null,
        commentVisible: Boolean = false
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/groups.create">VK API</a>
     */
    fun createEvent(
        title: String,
        description: String
    ): VkApiRequest<Community>

    /**
     * @see <a href="https://vk.com/dev/groups.create">VK API</a>
     */
    fun createGroup(
        title: String,
        description: String
    ): VkApiRequest<Community>

    /**
     * @see <a href="https://vk.com/dev/groups.create">VK API</a>
     */
    fun createPublic(
        title: String,
        subtype: PublicSubtype,
        publicCategory: Int? = null
    ): VkApiRequest<Community>

    /**
     * @see <a href="https://vk.com/dev/groups.deleteLink">VK API</a>
     */
    fun deleteLink(
        groupId: Int,
        linkId: Int
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/groups.edit">VK API</a>
     */
    fun editPublic(
        groupId: Int,
        title: String? = null,
        description: String? = null,
        screenName: String? = null,
        access: Community.CloseType? = null,
        website: String? = null,
        subject: GroupSubject? = null,
        rss: String? = null,
        publicCategory: Int? = null,
        publicSubcategory: Int? = null,
        publicDate: String? = null,
        wall: PublicUnitAccessType? = null,
        topics: PublicUnitAccessType? = null,
        photos: PublicUnitAccessType? = null,
        video: PublicUnitAccessType? = null,
        audio: PublicUnitAccessType? = null,
        docs: PublicUnitAccessType? = null,
        wiki: PublicUnitAccessType? = null,
        links: Boolean? = null,
        events: Boolean? = null,
        places: Boolean? = null,
        contacts: Boolean? = null,
        messages: Boolean? = null,
        market: Boolean? = null,
        marketComments: Boolean? = null,
        marketCountries: List<Int>? = null,
        marketCities: List<Int>? = null,
        ageLimits: Community.AgeLimits? = null,
        marketCurrency: MarketCurrency? = null,
        marketContact: Int? = null,
        marketWiki: Int? = null,
        obsceneFilter: Boolean? = null,
        obsceneStopwords: Boolean? = null,
        obsceneWords: List<String>? = null,
        mainSection: Community.MainSectionType? = null,
        secondarySection: Community.MainSectionType? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/groups.edit">VK API</a>
     */
    fun editEvent(
        groupId: Int,
        title: String? = null,
        description: String? = null,
        screenName: String? = null,
        access: Community.CloseType? = null,
        website: String? = null,
        subject: GroupSubject? = null,
        email: String? = null,
        phone: String? = null,
        rss: String? = null,
        eventStartDate: Date? = null,
        eventFinishDate: Date? = null,
        eventGroupId: Int? = null,
        wall: GroupUnitAccessTypeExtended? = null,
        topics: GroupUnitAccessType? = null,
        photos: GroupUnitAccessType? = null,
        video: GroupUnitAccessType? = null,
        audio: GroupUnitAccessType? = null,
        docs: GroupUnitAccessType? = null,
        wiki: GroupUnitAccessType? = null,
        messages: Boolean? = null,
        market: Boolean? = null,
        marketComments: Boolean? = null,
        marketCountries: List<Int>? = null,
        marketCities: List<Int>? = null,
        ageLimits: Community.AgeLimits? = null,
        marketCurrency: MarketCurrency? = null,
        marketContact: Int? = null,
        marketWiki: Int? = null,
        obsceneFilter: Boolean? = null,
        obsceneStopwords: Boolean? = null,
        obsceneWords: List<String>? = null,
        mainSection: Community.MainSectionType? = null,
        secondarySection: Community.MainSectionType? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/groups.edit">VK API</a>
     */
    fun editGroup(
        groupId: Int,
        title: String? = null,
        description: String? = null,
        screenName: String? = null,
        access: Community.CloseType? = null,
        website: String? = null,
        subject: GroupSubject? = null,
        rss: String? = null,
        wall: GroupUnitAccessTypeExtended? = null,
        topics: GroupUnitAccessType? = null,
        photos: GroupUnitAccessType? = null,
        video: GroupUnitAccessType? = null,
        audio: GroupUnitAccessType? = null,
        docs: GroupUnitAccessType? = null,
        wiki: GroupUnitAccessType? = null,
        messages: Boolean? = null,
        market: Boolean? = null,
        marketComments: Boolean? = null,
        marketCountries: List<Int>? = null,
        marketCities: List<Int>? = null,
        ageLimits: Community.AgeLimits? = null,
        marketCurrency: MarketCurrency? = null,
        marketContact: Int? = null,
        marketWiki: Int? = null,
        obsceneFilter: Boolean? = null,
        obsceneStopwords: Boolean? = null,
        obsceneWords: List<String>? = null,
        mainSection: Community.MainSectionType? = null,
        secondarySection: Community.MainSectionType? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/groups.editLink">VK API</a>
     */
    fun editLink(
        groupId: Int,
        linkId: Int,
        text: String? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/groups.editManager">VK API</a>
     */
    fun editManager(
        groupId: Int,
        userId: Int,
        role: CommunityManagerRole? = null,
        isContact: Boolean? = null,
        contactPosition: String? = null,
        contactPhone: String? = null,
        contactEmail: String? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/groups.editPlace">VK API</a>
     */
    fun editPlace(
        groupId: Int,
        title: String? = null,
        address: String? = null,
        countryId: Int? = null,
        cityId: Int? = null,
        latitude: Double? = null,
        longitude: Double? = null
    ): VkApiRequest<EditPlaceResponse>

    /**
     * Alternative to [get] that allows to get only communities IDs.
     * @param[count] maximum value 1000
     * @see <a href="https://vk.com/dev/groups.get">VK API</a>
     */
    fun getIds(
        userId: Int? = null,
        filter: GroupsFilter? = null,
        offset: Int = 0,
        count: Int = 1000
    ): VkApiRequest<DefaultListResponse<Int>>

    /**
     * @param[count] maximum value 1000
     * @see <a href="https://vk.com/dev/groups.get">VK API</a>
     */
    fun get(
        userId: Int? = null,
        filter: GroupsFilter? = null,
        fields: List<CommunityOptionalField>? = null,
        offset: Int = 0,
        count: Int = 1000
    ): VkApiRequest<DefaultListResponse<Community>>

    /**
     * @see <a href="https://vk.com/dev/groups.getCatalog">VK API</a>
     */
    fun getCatalog(
        categoryId: Int? = null,
        subcategoryId: Int? = null
    ): VkApiRequest<DefaultListResponse<Community>>

    /**
     * @see <a href="https://vk.com/dev/groups.getCatalogInfo">VK API</a>
     */
    fun getCatalogInfo(
        extended: Boolean = false,
        withSubcategories: Boolean = false
    ): VkApiRequest<CommunitiesCatalog>

    /**
     * @see <a href="https://vk.com/dev/groups.getInvitedUsers">VK API</a>
     */
    fun getInvitedUsers(
        groupId: Int,
        offset: Int = 0,
        count: Int = 20,
        userFields: List<UserOptionalField> = DefaultMethodsParams.userFields,
        nameCase: NameCase = NameCase.NOM
    ): VkApiRequest<DefaultListResponse<User>>

    /**
     * @see <a href="https://vk.com/dev/groups.getInvites">VK API</a>
     */
    fun getInvites(
        offset: Int = 0,
        count: Int = 20,
        extended: Boolean = false
    ): VkApiRequest<ExtendedListResponse<Community>>

    /**
     * Alternative to [getRequests] that allows to get only users IDs.
     * @param[count] maximum value 200
     * @see <a href="https://vk.com/dev/groups.getRequests">VK API</a>
     */
    fun getRequestsIds(
        groupId: Int,
        offset: Int = 0,
        count: Int = 20
    ): VkApiRequest<DefaultListResponse<Int>>

    /**
     * @param[count] maximum value 200
     * @see <a href="https://vk.com/dev/groups.getRequests">VK API</a>
     */
    fun getRequests(
        groupId: Int,
        offset: Int = 0,
        count: Int = 20,
        userFields: List<UserOptionalField> = DefaultMethodsParams.userFields
    ): VkApiRequest<DefaultListResponse<User>>

    /**
     * @see <a href="https://vk.com/dev/groups.getSettings">VK API</a>
     */
    fun getSettings(
        groupId: Int
    ): VkApiRequest<CommunitySettings>

    /**
     * @see <a href="https://vk.com/dev/groups.invite">VK API</a>
     */
    fun invite(
        groupId: Int,
        userId: Int
    ): VkApiRequest<BooleanInt>

    /**
     * @param[notSure] taken into account when [groupId] belongs to the event
     * @see <a href="https://vk.com/dev/groups.join">VK API</a>
     */
    fun join(
        groupId: Int,
        notSure: Boolean? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/groups.leave">VK API</a>
     */
    fun leave(
        groupId: Int
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/groups.removeUser">VK API</a>
     */
    fun removeUser(
        groupId: Int,
        userId: Int
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/groups.reorderLink">VK API</a>
     */
    fun reorderLink(
        groupId: Int,
        linkId: Int,
        after: Int = 0
    ): VkApiRequest<BooleanInt>

    /**
     * @param[isFuture] works with the [type] = [Community.Type.EVENT]
     * @param[count] maximum value 1000
     * @see <a href="https://vk.com/dev/groups.search">VK API</a>
     */
    fun search(
        query: String,
        type: Community.Type? = null,
        countryId: Int? = null,
        cityId: Int? = null,
        isFuture: Boolean? = null,
        hasMarket: Boolean? = null,
        sort: CommunitySearchOrder = CommunitySearchOrder.DEFAULT,
        offset: Int = 0,
        count: Int = 20
    ): VkApiRequest<DefaultListResponse<Community>>

    /**
     * @see <a href="https://vk.com/dev/groups.unban">VK API</a>
     */
    fun unban(
        groupId: Int,
        ownerId: Int
    ): VkApiRequest<BooleanInt>

}