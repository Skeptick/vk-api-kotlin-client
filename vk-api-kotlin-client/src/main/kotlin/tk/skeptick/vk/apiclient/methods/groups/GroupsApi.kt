package tk.skeptick.vk.apiclient.methods.groups

import io.ktor.util.date.GMTDate
import kotlinx.serialization.internal.IntSerializer
import kotlinx.serialization.internal.StringSerializer
import kotlinx.serialization.list
import tk.skeptick.vk.apiclient.*
import tk.skeptick.vk.apiclient.domain.models.Address
import tk.skeptick.vk.apiclient.domain.models.Community
import tk.skeptick.vk.apiclient.domain.models.User
import tk.skeptick.vk.apiclient.methods.*

class GroupsApi(override val client: ApiClient)
    : GroupsApiUser, GroupsApiCommunity, MethodsContext {

    override fun addAddress(
        groupId: Int,
        title: String,
        address: String,
        countryId: Int,
        cityId: Int,
        latitude: Double,
        longitude: Double,
        additionalAddress: String?,
        metroId: Int?,
        phone: String?,
        workInfoStatus: Address.WorkInfoStatus?,
        timetable: Address.Timetable?,
        isMainAddress: Boolean?
    ): VkApiRequest<Address> =
        Methods.addAddress.httpPost(Address.serializer()) {
            append("group_id", groupId)
            append("title", title)
            append("address", address)
            append("country_id", countryId)
            append("city_id", cityId)
            append("latitude", latitude)
            append("longitude", longitude)
            append("additional_address", additionalAddress)
            append("metro_id", metroId)
            append("phone", phone)
            append("work_info_status", workInfoStatus?.value)
            append("timetable", timetable?.serialize())
            append("is_main_address", isMainAddress?.asInt())
        }

    override fun addCallbackServer(
        groupId: Int,
        url: String,
        title: String,
        secretKey: String?
    ): VkApiRequest<Int> =
        Methods.addCallbackServer.httpGet(IntSerializer) {
            append("group_id", groupId)
            append("url", url)
            append("title", title)
            append("secret_key", secretKey)
        }

    override fun addLink(
        groupId: Int,
        link: String,
        text: String?
    ): VkApiRequest<AddLinkResponse> =
        Methods.addLink.httpPost(AddLinkResponse.serializer()) {
            append("group_id", groupId)
            append("link", link)
            append("text", text)
        }

    override fun approveRequest(
        groupId: Int,
        userId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.approveRequest.httpGet(BooleanInt.serializer()) {
            append("group_id", groupId)
            append("user_id", userId)
        }

    override fun ban(
        groupId: Int,
        ownerId: Int,
        endDate: GMTDate?,
        reason: CommunityBan.Reason,
        comment: String?,
        commentVisible: Boolean
    ): VkApiRequest<BooleanInt> =
        Methods.ban.httpPost(BooleanInt.serializer()) {
            append("group_id", groupId)
            append("owner_id", ownerId)
            append("end_date", endDate?.unixtime)
            append("reason", reason.value)
            append("comment", comment)
            append("comment_visible", commentVisible.asInt())
        }

    private fun create(
        title: String,
        type: GroupType,
        description: String? = null,
        publicCategory: Int? = null,
        subtype: PublicSubtype? = null
    ): VkApiRequest<Community> =
        Methods.create.httpPost(Community.serializer()) {
            append("title", title)
            append("description", description)
            append("type", type.value)
            append("public_category", publicCategory)
            append("subtype", subtype?.value)
        }

    override fun createEvent(
        title: String,
        description: String
    ): VkApiRequest<Community> =
        create(
            title = title,
            type = GroupType.EVENT,
            description = description
        )

    override fun createGroup(
        title: String,
        description: String
    ): VkApiRequest<Community> =
        create(
            title = title,
            type = GroupType.GROUP,
            description = description
        )

    override fun createPublic(
        title: String,
        subtype: PublicSubtype,
        publicCategory: Int?
    ): VkApiRequest<Community> =
        create(
            title = title,
            type = GroupType.PUBLIC,
            publicCategory = publicCategory,
            subtype = subtype
        )

    override fun deleteAddress(
        groupId: Int,
        addressId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.deleteAddress.httpGet(BooleanInt.serializer()) {
            append("group_id", groupId)
            append("address_id", addressId)
        }

    override fun deleteCallbackServer(
        groupId: Int,
        serverId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.deleteCallbackServer.httpGet(BooleanInt.serializer()) {
            append("group_id", groupId)
            append("server_id", serverId)
        }

    override fun deleteLink(
        groupId: Int,
        linkId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.deleteLink.httpGet(BooleanInt.serializer()) {
            append("group_id", groupId)
            append("link_id", linkId)
        }

    override fun disableOnline(
        groupId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.disableOnline.httpGet(BooleanInt.serializer()) {
            append("group_id", groupId)
        }

    private fun edit(
        groupId: Int,
        title: String? = null,
        description: String? = null,
        screenName: String? = null,
        access: Community.CloseType? = null,
        countryId: Int? = null,
        cityId: Int? = null,
        website: String? = null,
        subject: GroupSubject? = null,
        email: String? = null,
        phone: String? = null,
        rss: String? = null,
        eventStartDate: GMTDate? = null,
        eventFinishDate: GMTDate? = null,
        eventGroupId: Int? = null,
        publicCategory: Int? = null,
        publicSubcategory: Int? = null,
        publicDate: String? = null,
        wall: Int? = null,
        topics: Int? = null,
        photos: Int? = null,
        video: Int? = null,
        audio: Int? = null,
        docs: Int? = null,
        wiki: Int? = null,
        links: Boolean? = null,
        events: Boolean? = null,
        places: Boolean? = null,
        contacts: Boolean? = null,
        messages: Boolean? = null,
        articles: Boolean? = null,
        addresses: Boolean? = null,
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
    ): VkApiRequest<BooleanInt> =
        Methods.edit.httpPost(BooleanInt.serializer()) {
            append("group_id", groupId)
            append("title", title)
            append("description", description)
            append("screen_name", screenName)
            append("access", access?.value)
            append("country", countryId)
            append("city", cityId)
            append("website", website)
            append("subject", subject?.value)
            append("email", email)
            append("phone", phone)
            append("rss", rss)
            append("event_start_date", eventStartDate?.unixtime)
            append("event_finish_date", eventFinishDate?.unixtime)
            append("event_group_id", eventGroupId)
            append("public_category", publicCategory)
            append("public_subcategory", publicSubcategory)
            append("public_date", publicDate)
            append("wall", wall)
            append("topics", topics)
            append("photos", photos)
            append("video", video)
            append("audio", audio)
            append("docs", docs)
            append("wiki", wiki)
            append("links", links?.asInt())
            append("events", events?.asInt())
            append("places", places?.asInt())
            append("contacts", contacts?.asInt())
            append("messages", messages?.asInt())
            append("articles", articles?.asInt())
            append("addresses", addresses?.asInt())
            append("age_limits", ageLimits?.value)
            append("market", market?.asInt())
            append("market_comments", marketComments?.asInt())
            append("market_country", marketCountries?.joinToString(","))
            append("market_city", marketCities?.joinToString(","))
            append("market_currency", marketCurrency?.value)
            append("market_contact", marketContact)
            append("market_wiki", marketWiki)
            append("obscene_filter", obsceneFilter?.asInt())
            append("obscene_stopwords", obsceneStopwords?.asInt())
            append("obscene_words", obsceneWords?.joinToString(","))
            append("main_section", mainSection?.value)
            append("secondary_section", secondarySection?.value)
        }

    override fun editAddress(
        groupId: Int,
        addressId: Int,
        title: String?,
        address: String?,
        additionalAddress: String?,
        countryId: Int?,
        cityId: Int?,
        metroId: Int?,
        latitude: Double?,
        longitude: Double?,
        phone: String?,
        workInfoStatus: Address.WorkInfoStatus?,
        timetable: Address.Timetable?,
        isMainAddress: Boolean?
    ): VkApiRequest<Address> =
        Methods.editAddress.httpPost(Address.serializer()) {
            append("group_id", groupId)
            append("address_id", addressId)
            append("title", title)
            append("address", address)
            append("additional_address", additionalAddress)
            append("country_id", countryId)
            append("city_id", cityId)
            append("metro_id", metroId)
            append("latitude", latitude)
            append("longitude", longitude)
            append("phone", phone)
            append("work_info_status", workInfoStatus?.value)
            append("timetable", timetable?.serialize())
            append("is_main_address", isMainAddress?.asInt())
        }

    override fun editPublic(
        groupId: Int,
        title: String?,
        description: String?,
        screenName: String?,
        access: Community.CloseType?,
        countryId: Int?,
        cityId: Int?,
        website: String?,
        subject: GroupSubject?,
        rss: String?,
        publicCategory: Int?,
        publicSubcategory: Int?,
        publicDate: String?,
        wall: PublicUnitAccessType?,
        topics: PublicUnitAccessType?,
        photos: PublicUnitAccessType?,
        video: PublicUnitAccessType?,
        audio: PublicUnitAccessType?,
        docs: PublicUnitAccessType?,
        wiki: PublicUnitAccessType?,
        links: Boolean?,
        events: Boolean?,
        places: Boolean?,
        contacts: Boolean?,
        messages: Boolean?,
        articles: Boolean?,
        addresses: Boolean?,
        market: Boolean?,
        marketComments: Boolean?,
        marketCountries: List<Int>?,
        marketCities: List<Int>?,
        ageLimits: Community.AgeLimits?,
        marketCurrency: MarketCurrency?,
        marketContact: Int?,
        marketWiki: Int?,
        obsceneFilter: Boolean?,
        obsceneStopwords: Boolean?,
        obsceneWords: List<String>?,
        mainSection: Community.MainSectionType?,
        secondarySection: Community.MainSectionType?
    ): VkApiRequest<BooleanInt> =
        edit(
            groupId = groupId,
            title = title,
            description = description,
            screenName = screenName,
            access = access,
            countryId = countryId,
            cityId = cityId,
            website = website,
            subject = subject,
            rss = rss,
            publicCategory = publicCategory,
            publicSubcategory = publicSubcategory,
            publicDate = publicDate,
            wall = wall?.value,
            topics = topics?.value,
            photos = photos?.value,
            video = video?.value,
            audio = audio?.value,
            docs = docs?.value,
            wiki = wiki?.value,
            links = links,
            events = events,
            places = places,
            contacts = contacts,
            messages = messages,
            articles = articles,
            addresses = addresses,
            market = market,
            marketComments = marketComments,
            marketCountries = marketCountries,
            marketCities = marketCities,
            ageLimits = ageLimits,
            marketCurrency = marketCurrency,
            marketContact = marketContact,
            marketWiki = marketWiki,
            obsceneFilter = obsceneFilter,
            obsceneStopwords = obsceneStopwords,
            obsceneWords = obsceneWords,
            mainSection = mainSection,
            secondarySection = secondarySection
        )

    override fun editEvent(
        groupId: Int,
        title: String?,
        description: String?,
        screenName: String?,
        access: Community.CloseType?,
        countryId: Int?,
        cityId: Int?,
        website: String?,
        subject: GroupSubject?,
        email: String?,
        phone: String?,
        rss: String?,
        eventStartDate: GMTDate?,
        eventFinishDate: GMTDate?,
        eventGroupId: Int?,
        wall: GroupUnitAccessTypeExtended?,
        topics: GroupUnitAccessType?,
        photos: GroupUnitAccessType?,
        video: GroupUnitAccessType?,
        audio: GroupUnitAccessType?,
        docs: GroupUnitAccessType?,
        wiki: GroupUnitAccessType?,
        messages: Boolean?,
        articles: Boolean?,
        addresses: Boolean?,
        market: Boolean?,
        marketComments: Boolean?,
        marketCountries: List<Int>?,
        marketCities: List<Int>?,
        ageLimits: Community.AgeLimits?,
        marketCurrency: MarketCurrency?,
        marketContact: Int?,
        marketWiki: Int?,
        obsceneFilter: Boolean?,
        obsceneStopwords: Boolean?,
        obsceneWords: List<String>?,
        mainSection: Community.MainSectionType?,
        secondarySection: Community.MainSectionType?
    ): VkApiRequest<BooleanInt> =
        edit(
            groupId = groupId,
            title = title,
            description = description,
            screenName = screenName,
            access = access,
            countryId = countryId,
            cityId = cityId,
            website = website,
            subject = subject,
            email = email,
            phone = phone,
            rss = rss,
            eventStartDate = eventStartDate,
            eventFinishDate = eventFinishDate,
            eventGroupId = eventGroupId,
            wall = wall?.value,
            topics = topics?.value,
            photos = photos?.value,
            video = video?.value,
            audio = audio?.value,
            docs = docs?.value,
            wiki = wiki?.value,
            messages = messages,
            articles = articles,
            addresses = addresses,
            market = market,
            marketComments = marketComments,
            marketCountries = marketCountries,
            marketCities = marketCities,
            ageLimits = ageLimits,
            marketCurrency = marketCurrency,
            marketContact = marketContact,
            marketWiki = marketWiki,
            obsceneFilter = obsceneFilter,
            obsceneStopwords = obsceneStopwords,
            obsceneWords = obsceneWords,
            mainSection = mainSection,
            secondarySection = secondarySection
        )

    override fun editGroup(
        groupId: Int,
        title: String?,
        description: String?,
        screenName: String?,
        access: Community.CloseType?,
        countryId: Int?,
        cityId: Int?,
        website: String?,
        subject: GroupSubject?,
        rss: String?,
        wall: GroupUnitAccessTypeExtended?,
        topics: GroupUnitAccessType?,
        photos: GroupUnitAccessType?,
        video: GroupUnitAccessType?,
        audio: GroupUnitAccessType?,
        docs: GroupUnitAccessType?,
        wiki: GroupUnitAccessType?,
        messages: Boolean?,
        articles: Boolean?,
        addresses: Boolean?,
        market: Boolean?,
        marketComments: Boolean?,
        marketCountries: List<Int>?,
        marketCities: List<Int>?,
        ageLimits: Community.AgeLimits?,
        marketCurrency: MarketCurrency?,
        marketContact: Int?,
        marketWiki: Int?,
        obsceneFilter: Boolean?,
        obsceneStopwords: Boolean?,
        obsceneWords: List<String>?,
        mainSection: Community.MainSectionType?,
        secondarySection: Community.MainSectionType?
    ): VkApiRequest<BooleanInt> =
        edit(
            groupId = groupId,
            title = title,
            description = description,
            screenName = screenName,
            access = access,
            countryId = countryId,
            cityId = cityId,
            website = website,
            subject = subject,
            rss = rss,
            wall = wall?.value,
            topics = topics?.value,
            photos = photos?.value,
            video = video?.value,
            audio = audio?.value,
            docs = docs?.value,
            wiki = wiki?.value,
            messages = messages,
            articles = articles,
            addresses = addresses,
            market = market,
            marketComments = marketComments,
            marketCountries = marketCountries,
            marketCities = marketCities,
            ageLimits = ageLimits,
            marketCurrency = marketCurrency,
            marketContact = marketContact,
            marketWiki = marketWiki,
            obsceneFilter = obsceneFilter,
            obsceneStopwords = obsceneStopwords,
            obsceneWords = obsceneWords,
            mainSection = mainSection,
            secondarySection = secondarySection
        )

    override fun editCallbackServer(
        groupId: Int,
        serverId: Int,
        url: String,
        title: String,
        secretKey: String?
    ): VkApiRequest<BooleanInt> =
        Methods.editCallbackServer.httpPost(BooleanInt.serializer()) {
            append("group_id", groupId)
            append("server_id", serverId)
            append("url", url)
            append("title", title)
            append("secret_key", secretKey)
        }

    override fun editLink(
        groupId: Int,
        linkId: Int,
        text: String?
    ): VkApiRequest<BooleanInt> =
        Methods.editLink.httpPost(BooleanInt.serializer()) {
            append("group_id", groupId)
            append("link_id", linkId)
            append("text", text)
        }

    override fun editManager(
        groupId: Int,
        userId: Int,
        role: CommunityManagerRole?,
        isContact: Boolean?,
        contactPosition: String?,
        contactPhone: String?,
        contactEmail: String?
    ): VkApiRequest<BooleanInt> =
        Methods.editManager.httpGet(BooleanInt.serializer()) {
            append("group_id", groupId)
            append("user_id", userId)
            append("role", role?.value)
            append("is_contact", isContact?.asInt())
            append("contact_position", contactPosition)
            append("contact_phone", contactPhone)
            append("contact_email", contactEmail)
        }

    override fun editPlace(
        groupId: Int,
        title: String?,
        address: String?,
        countryId: Int?,
        cityId: Int?,
        latitude: Double?,
        longitude: Double?
    ): VkApiRequest<EditPlaceResponse> =
        Methods.editPlace.httpPost(EditPlaceResponse.serializer()) {
            append("group_id", groupId)
            append("title", title)
            append("address", address)
            append("country_id", countryId)
            append("city_id", cityId)
            append("latitude", latitude)
            append("longitude", longitude)
        }

    override fun enableOnline(
        groupId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.enableOnline.httpGet(BooleanInt.serializer()) {
            append("group_id", groupId)
        }

    override fun getIds(
        userId: Int?,
        filter: GroupsFilter?,
        offset: Int,
        count: Int
    ): VkApiRequest<DefaultListResponse<Int>> =
        Methods.get.httpGet(list(IntSerializer)) {
            append("user_id", userId)
            append("filter", filter?.value)
            append("offset", offset)
            append("count", count)
        }

    override fun get(
        userId: Int?,
        filter: GroupsFilter?,
        fields: List<CommunityOptionalField>?,
        offset: Int,
        count: Int
    ): VkApiRequest<DefaultListResponse<Community>> =
        Methods.get.httpPost(list(Community.serializer())) {
            append("user_id", userId)
            append("extended", 1)
            append("filter", filter?.value)
            append("fields", fields?.joinToString(",") { it.value })
            append("offset", offset)
            append("count", count)
        }

    override fun getAddresses(
        groupId: Int,
        addressIds: List<Int>?,
        latitude: Double?,
        longitude: Double?,
        offset: Int,
        count: Int,
        fields: List<AddressOptionalFields>?
    ): VkApiRequest<DefaultListResponse<Address>> =
        Methods.getAddresses.httpGet(list(Address.serializer())) {
            append("group_id", groupId)
            append("address_ids", addressIds?.joinToString(","))
            append("latitude", latitude)
            append("longitude", longitude)
            append("offset", offset)
            append("count", count)
            append("fields", fields?.joinToString(",") { it.value })
        }

    override fun getBanned(
        groupId: Int,
        offset: Int,
        count: Int,
        fields: List<ObjectField>,
        ownerId: Int?
    ): VkApiRequest<DefaultListResponse<CommunityBan>> =
        Methods.getBanned.httpPost(list(CommunityBan.serializer())) {
            append("group_id", groupId)
            append("offset", offset)
            append("count", count)
            append("fields", fields.joinToString(",") { it.value })
            append("owner_id", ownerId)
        }

    override fun getByScreenName(
        groupNames: List<String>,
        communityFields: List<CommunityOptionalField>?
    ): VkApiRequest<List<Community>> =
        Methods.getById.httpPost(Community.serializer().list) {
            append("group_ids", groupNames.joinToString(","))
            append("fields", communityFields?.joinToString(",") { it.value })
        }

    override fun getById(
        groupIds: List<Int>,
        communityFields: List<CommunityOptionalField>?
    ): VkApiRequest<List<Community>> =
        getByScreenName(
            groupNames = groupIds.map(Int::toString),
            communityFields = communityFields
        )

    override fun getCallbackConfirmationCode(
        groupId: Int
    ): VkApiRequest<String> =
        Methods.getCallbackConfirmationCode.httpGet(StringSerializer) {
            append("group_id", groupId)
        }

    override fun getCallbackServers(
        groupId: Int,
        serverIds: List<Int>?
    ): VkApiRequest<DefaultListResponse<CommunityCallbackServer>> =
        Methods.getCallbackServers.httpGet(list(CommunityCallbackServer.serializer())) {
            append("group_id", groupId)
            append("server_ids", serverIds?.joinToString(","))
        }

    override fun getCallbackSettings(
        groupId: Int,
        serverId: Int
    ): VkApiRequest<ServerSettings> =
        Methods.getCallbackSettings.httpGet(ServerSettings.serializer()) {
            append("group_id", groupId)
            append("server_id", serverId)
        }

    override fun getCatalog(
        categoryId: Int?,
        subcategoryId: Int?
    ): VkApiRequest<DefaultListResponse<Community>> =
        Methods.getCatalog.httpGet(list(Community.serializer())) {
            append("category_id", categoryId)
            append("subcategory_id", subcategoryId)
        }

    override fun getCatalogInfo(
        extended: Boolean,
        withSubcategories: Boolean
    ): VkApiRequest<CommunitiesCatalog> =
        Methods.getCatalogInfo.httpGet(CommunitiesCatalog.serializer()) {
            append("extended", extended.asInt())
            append("subcategories", withSubcategories.asInt())
        }

    override fun getInvitedUsers(
        groupId: Int,
        offset: Int,
        count: Int,
        userFields: List<UserOptionalField>,
        nameCase: NameCase
    ): VkApiRequest<DefaultListResponse<User>> =
        Methods.getInvitedUsers.httpPost(list(User.serializer())) {
            append("group_id", groupId)
            append("offset", offset)
            append("count", count)
            append("fields", userFields.joinToString(",") { it.value })
            append("name_case", nameCase.value)
        }

    override fun getInvites(
        offset: Int,
        count: Int,
        extended: Boolean
    ): VkApiRequest<ExtendedListResponse<Community>> =
        Methods.getInvites.httpGet(extendedList(Community.serializer())) {
            append("offset", offset)
            append("count", count)
            append("extended", extended.asInt())
        }

    override fun getLongPollServer(
        groupId: Int
    ): VkApiRequest<LongPollServerResponse> =
        Methods.getLongPollServer.httpGet(LongPollServerResponse.serializer()) {
            append("group_id", groupId)
        }

    override fun getLongPollSettings(
        groupId: Int
    ): VkApiRequest<ServerSettings> =
        Methods.getLongPollSettings.httpGet(ServerSettings.serializer()) {
            append("group_id", groupId)
        }

    override fun getMembersIds(
        groupId: Int,
        sort: CommunityMembersSort,
        offset: Int,
        count: Int,
        onlyFriends: Boolean
    ): VkApiRequest<DefaultListResponse<Int>> =
        Methods.getMembers.httpGet(list(IntSerializer)) {
            append("group_id", groupId)
            append("sort", sort.value)
            append("offset", offset)
            append("count", count)
            append("filter", if (onlyFriends) "friends" else null)
        }

    override fun getMembers(
        groupId: Int,
        sort: CommunityMembersSort,
        offset: Int,
        count: Int,
        userFields: List<UserOptionalField>,
        onlyFriends: Boolean
    ): VkApiRequest<DefaultListResponse<User>> =
        Methods.getMembers.httpPost(list(User.serializer())) {
            append("group_id", groupId)
            append("sort", sort.value)
            append("offset", offset)
            append("count", count)
            append("fields", userFields.joinToString(",") { it.value })
            append("filter", if (onlyFriends) "friends" else null)
        }

    override fun getManagersIds(
        groupId: Int,
        sort: CommunityMembersSort,
        offset: Int,
        count: Int
    ): VkApiRequest<DefaultListResponse<CommunityManager>> =
        Methods.getMembers.httpGet(list(CommunityManager.serializer())) {
            append("group_id", groupId)
            append("sort", sort.value)
            append("offset", offset)
            append("count", count)
            append("filter", "managers")
        }

    override fun getManagers(
        groupId: Int,
        sort: CommunityMembersSort,
        offset: Int,
        count: Int,
        userFields: List<UserOptionalField>
    ): VkApiRequest<DefaultListResponse<User>> =
        Methods.getMembers.httpPost(list(User.serializer())) {
            append("group_id", groupId)
            append("sort", sort.value)
            append("offset", offset)
            append("count", count)
            append("fields", userFields.joinToString(",") { it.value })
            append("filter", "managers")
        }

    override fun getOnlineStatus(
        groupId: Int
    ): VkApiRequest<CommunityOnlineStatus> =
        Methods.getOnlineStatus.httpGet(CommunityOnlineStatus.serializer()) {
            append("group_id", groupId)
        }

    override fun getRequestsIds(
        groupId: Int,
        offset: Int,
        count: Int
    ): VkApiRequest<DefaultListResponse<Int>> =
        Methods.getRequests.httpGet(list(IntSerializer)) {
            append("group_id", groupId)
            append("offset", offset)
            append("count", count)
        }

    override fun getRequests(
        groupId: Int,
        offset: Int,
        count: Int,
        userFields: List<UserOptionalField>
    ): VkApiRequest<DefaultListResponse<User>> =
        Methods.getRequests.httpPost(list(User.serializer())) {
            append("group_id", groupId)
            append("offset", offset)
            append("count", count)
            append("fields", userFields.joinToString(",") { it.value })
        }

    override fun getSettings(
        groupId: Int
    ): VkApiRequest<CommunitySettings> =
        Methods.getSettings.httpGet(CommunitySettings.serializer()) {
            append("group_id", groupId)
        }

    override fun getTokenPermissions(): VkApiRequest<CommunityTokenPermissions> =
        Methods.getTokenPermissions.httpGet(CommunityTokenPermissions.serializer())

    override fun invite(
        groupId: Int,
        userId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.invite.httpGet(BooleanInt.serializer()) {
            append("group_id", groupId)
            append("user_id", userId)
        }

    override fun isMember(
        groupId: Int,
        userId: Int?
    ): VkApiRequest<CommunityMemberResponse> =
        Methods.isMember.httpGet(CommunityMemberResponse.serializer()) {
            append("group_id", groupId)
            append("user_id", userId)
            append("extended", 1)
        }

    override fun isMembers(
        groupId: Int,
        userIds: List<Int>
    ): VkApiRequest<List<CommunityMemberResponse>> =
        Methods.isMember.httpPost(CommunityMemberResponse.serializer().list) {
            append("group_id", groupId)
            append("user_ids", userIds.joinToString(","))
        }

    override fun join(
        groupId: Int,
        notSure: Boolean?
    ): VkApiRequest<BooleanInt> =
        Methods.join.httpGet(BooleanInt.serializer()) {
            append("group_id", groupId)
            append("not_sure", notSure?.asInt())
        }

    override fun leave(
        groupId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.leave.httpGet(BooleanInt.serializer()) {
            append("group_id", groupId)
        }

    override fun removeUser(
        groupId: Int,
        userId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.removeUser.httpGet(BooleanInt.serializer()) {
            append("group_id", groupId)
            append("user_id", userId)
        }

    override fun reorderLink(
        groupId: Int,
        linkId: Int,
        after: Int
    ): VkApiRequest<BooleanInt> =
        Methods.reorderLink.httpGet(BooleanInt.serializer()) {
            append("group_id", groupId)
            append("link_id", linkId)
            append("after", after)
        }

    override fun search(
        query: String,
        type: Community.Type?,
        countryId: Int?,
        cityId: Int?,
        isFuture: Boolean?,
        hasMarket: Boolean?,
        sort: CommunitySearchOrder,
        offset: Int,
        count: Int
    ): VkApiRequest<DefaultListResponse<Community>> =
        Methods.search.httpPost(list(Community.serializer())) {
            append("q", query)
            append("type", type?.value)
            append("country_id", countryId)
            append("city_id", cityId)
            append("future", isFuture?.asInt())
            append("market", hasMarket?.asInt())
            append("sort", sort.value)
            append("offset", offset)
            append("count", count)
        }

    override fun setCallbackSettings(
        groupId: Int,
        serverId: Int,
        apiVersion: String?,
        messageNew: Boolean?,
        messageReply: Boolean?,
        photoNew: Boolean?,
        audioNew: Boolean?,
        videoNew: Boolean?,
        wallReplyNew: Boolean?,
        wallReplyEdit: Boolean?,
        wallReplyDelete: Boolean?,
        wallReplyRestore: Boolean?,
        wallPostNew: Boolean?,
        boardPostNew: Boolean?,
        boardPostEdit: Boolean?,
        boardPostRestore: Boolean?,
        boardPostDelete: Boolean?,
        photoCommentNew: Boolean?,
        photoCommentEdit: Boolean?,
        photoCommentDelete: Boolean?,
        photoCommentRestore: Boolean?,
        videoCommentNew: Boolean?,
        videoCommentEdit: Boolean?,
        videoCommentDelete: Boolean?,
        videoCommentRestore: Boolean?,
        marketCommentNew: Boolean?,
        marketCommentEdit: Boolean?,
        marketCommentDelete: Boolean?,
        marketCommentRestore: Boolean?,
        pollVoteNew: Boolean?,
        groupJoin: Boolean?,
        groupLeave: Boolean?,
        groupChangeSettings: Boolean?,
        groupChangePhoto: Boolean?,
        groupOfficersEdit: Boolean?,
        messageAllow: Boolean?,
        messageDeny: Boolean?,
        wallRepost: Boolean?,
        userBlock: Boolean?,
        userUnblock: Boolean?,
        messagesEdit: Boolean?,
        messageTypingState: Boolean?,
        leadFormsNew: Boolean?
    ): VkApiRequest<BooleanInt> =
        Methods.setCallbackSettings.httpGet(BooleanInt.serializer()) {
            append("group_id", groupId)
            append("server_id", serverId)
            append("api_version", apiVersion)
            append("message_new", messageNew?.asInt())
            append("message_reply", messageReply?.asInt())
            append("photo_new", photoNew?.asInt())
            append("audio_new", audioNew?.asInt())
            append("video_new", videoNew?.asInt())
            append("wall_reply_new", wallReplyNew?.asInt())
            append("wall_reply_edit", wallReplyEdit?.asInt())
            append("wall_reply_delete", wallReplyDelete?.asInt())
            append("wall_reply_restore", wallReplyRestore?.asInt())
            append("wall_post_new", wallPostNew?.asInt())
            append("board_post_new", boardPostNew?.asInt())
            append("board_post_edit", boardPostEdit?.asInt())
            append("board_post_restore", boardPostRestore?.asInt())
            append("board_post_delete", boardPostDelete?.asInt())
            append("photo_comment_new", photoCommentNew?.asInt())
            append("photo_comment_edit", photoCommentEdit?.asInt())
            append("photo_comment_delete", photoCommentDelete?.asInt())
            append("photo_comment_restore", photoCommentRestore?.asInt())
            append("video_comment_new", videoCommentNew?.asInt())
            append("video_comment_edit", videoCommentEdit?.asInt())
            append("video_comment_delete", videoCommentDelete?.asInt())
            append("video_comment_restore", videoCommentRestore?.asInt())
            append("market_comment_new", marketCommentNew?.asInt())
            append("market_comment_edit", marketCommentEdit?.asInt())
            append("market_comment_delete", marketCommentDelete?.asInt())
            append("market_comment_restore", marketCommentRestore?.asInt())
            append("poll_vote_new", pollVoteNew?.asInt())
            append("group_join", groupJoin?.asInt())
            append("group_leave", groupLeave?.asInt())
            append("group_change_settings", groupChangeSettings?.asInt())
            append("group_change_photo", groupChangePhoto?.asInt())
            append("group_officers_edit", groupOfficersEdit?.asInt())
            append("message_allow", messageAllow?.asInt())
            append("message_deny", messageDeny?.asInt())
            append("wall_repost", wallRepost?.asInt())
            append("user_block", userBlock?.asInt())
            append("user_unblock", userUnblock?.asInt())
            append("messages_edit", messagesEdit?.asInt())
            append("message_typing_state", messageTypingState?.asInt())
            append("lead_forms_new", leadFormsNew?.asInt())
        }

    override fun setLongPollSettings(
        groupId: Int,
        enabled: Boolean?,
        messageNew: Boolean?,
        messageReply: Boolean?,
        photoNew: Boolean?,
        audioNew: Boolean?,
        videoNew: Boolean?,
        wallReplyNew: Boolean?,
        wallReplyEdit: Boolean?,
        wallReplyDelete: Boolean?,
        wallReplyRestore: Boolean?,
        wallPostNew: Boolean?,
        boardPostNew: Boolean?,
        boardPostEdit: Boolean?,
        boardPostRestore: Boolean?,
        boardPostDelete: Boolean?,
        photoCommentNew: Boolean?,
        photoCommentEdit: Boolean?,
        photoCommentDelete: Boolean?,
        photoCommentRestore: Boolean?,
        videoCommentNew: Boolean?,
        videoCommentEdit: Boolean?,
        videoCommentDelete: Boolean?,
        videoCommentRestore: Boolean?,
        marketCommentNew: Boolean?,
        marketCommentEdit: Boolean?,
        marketCommentDelete: Boolean?,
        marketCommentRestore: Boolean?,
        pollVoteNew: Boolean?,
        groupJoin: Boolean?,
        groupLeave: Boolean?,
        groupChangeSettings: Boolean?,
        groupChangePhoto: Boolean?,
        groupOfficersEdit: Boolean?,
        messageAllow: Boolean?,
        messageDeny: Boolean?,
        wallRepost: Boolean?,
        userBlock: Boolean?,
        userUnblock: Boolean?,
        messagesEdit: Boolean?,
        messageTypingState: Boolean?,
        leadFormsNew: Boolean?
    ): VkApiRequest<BooleanInt> =
        Methods.setLongPollSettings.httpGet(BooleanInt.serializer()) {
            append("group_id", groupId)
            append("enabled", enabled?.asInt())
            append("message_new", messageNew?.asInt())
            append("message_reply", messageReply?.asInt())
            append("photo_new", photoNew?.asInt())
            append("audio_new", audioNew?.asInt())
            append("video_new", videoNew?.asInt())
            append("wall_reply_new", wallReplyNew?.asInt())
            append("wall_reply_edit", wallReplyEdit?.asInt())
            append("wall_reply_delete", wallReplyDelete?.asInt())
            append("wall_reply_restore", wallReplyRestore?.asInt())
            append("wall_post_new", wallPostNew?.asInt())
            append("board_post_new", boardPostNew?.asInt())
            append("board_post_edit", boardPostEdit?.asInt())
            append("board_post_restore", boardPostRestore?.asInt())
            append("board_post_delete", boardPostDelete?.asInt())
            append("photo_comment_new", photoCommentNew?.asInt())
            append("photo_comment_edit", photoCommentEdit?.asInt())
            append("photo_comment_delete", photoCommentDelete?.asInt())
            append("photo_comment_restore", photoCommentRestore?.asInt())
            append("video_comment_new", videoCommentNew?.asInt())
            append("video_comment_edit", videoCommentEdit?.asInt())
            append("video_comment_delete", videoCommentDelete?.asInt())
            append("video_comment_restore", videoCommentRestore?.asInt())
            append("market_comment_new", marketCommentNew?.asInt())
            append("market_comment_edit", marketCommentEdit?.asInt())
            append("market_comment_delete", marketCommentDelete?.asInt())
            append("market_comment_restore", marketCommentRestore?.asInt())
            append("poll_vote_new", pollVoteNew?.asInt())
            append("group_join", groupJoin?.asInt())
            append("group_leave", groupLeave?.asInt())
            append("group_change_settings", groupChangeSettings?.asInt())
            append("group_change_photo", groupChangePhoto?.asInt())
            append("group_officers_edit", groupOfficersEdit?.asInt())
            append("message_allow", messageAllow?.asInt())
            append("message_deny", messageDeny?.asInt())
            append("wall_repost", wallRepost?.asInt())
            append("user_block", userBlock?.asInt())
            append("user_unblock", userUnblock?.asInt())
            append("messages_edit", messagesEdit?.asInt())
            append("message_typing_state", messageTypingState?.asInt())
            append("lead_forms_new", leadFormsNew?.asInt())
        }

    override fun setSettings(
        groupId: Int,
        messages: Boolean?,
        botsCapabilities: Boolean?,
        botsStartButton: Boolean?,
        botsAddToChat: Boolean?
    ): VkApiRequest<BooleanInt> =
        Methods.setSettings.httpGet(BooleanInt.serializer()) {
            append("group_id", groupId)
            append("messages", messages?.asInt())
            append("bots_capabilities", botsCapabilities?.asInt())
            append("bots_start_button", botsStartButton?.asInt())
            append("bots_add_to_chat", botsAddToChat?.asInt())
        }

    override fun unban(
        groupId: Int,
        ownerId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.unban.httpGet(BooleanInt.serializer()) {
            append("group_id", groupId)
            append("owner_id", ownerId)
        }

    private object Methods {
        private const val it = "groups."
        const val addAddress = it + "addAddress"
        const val addCallbackServer = it + "addCallbackServer"
        const val addLink = it + "addLink"
        const val approveRequest = it + "approveRequest"
        const val ban = it + "ban"
        const val create = it + "create"
        const val deleteAddress = it + "deleteAddress"
        const val deleteCallbackServer = it + "deleteCallbackServer"
        const val deleteLink = it + "deleteLink"
        const val disableOnline = it + "disableOnline"
        const val edit = it + "edit"
        const val editAddress = it + "editAddress"
        const val editCallbackServer = it + "editCallbackServer"
        const val editLink = it + "editLink"
        const val editManager = it + "editManager"
        const val editPlace = it + "editPlace"
        const val enableOnline = it + "enableOnline"
        const val get = it + "get"
        const val getAddresses = it + "getAddresses"
        const val getBanned = it + "getBanned"
        const val getById = it + "getById"
        const val getCallbackConfirmationCode = it + "getCallbackConfirmationCode"
        const val getCallbackServers = it + "getCallbackServers"
        const val getCallbackSettings = it + "getCallbackSettings"
        const val getCatalog = it + "getCatalog"
        const val getCatalogInfo = it + "getCatalogInfo"
        const val getInvitedUsers = it + "getInvitedUsers"
        const val getInvites = it + "getInvites"
        const val getLongPollServer = it + "getLongPollServer"
        const val getLongPollSettings = it + "getLongPollSettings"
        const val getMembers = it + "getMembers"
        const val getOnlineStatus = it + "getOnlineStatus"
        const val getRequests = it + "getRequests"
        const val getSettings = it + "getSettings"
        const val getTokenPermissions = it + "getTokenPermissions"
        const val invite = it + "invite"
        const val isMember = it + "isMember"
        const val join = it + "join"
        const val leave = it + "leave"
        const val removeUser = it + "removeUser"
        const val reorderLink = it + "reorderLink"
        const val search = it + "search"
        const val setCallbackSettings = it + "setCallbackSettings"
        const val setLongPollSettings = it + "setLongPollSettings"
        const val setSettings = it + "setSettings"
        const val unban = it + "unban"
    }

}