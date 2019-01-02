package tk.skeptick.vk.apiclient.methods.groups

import kotlinx.serialization.internal.IntSerializer
import kotlinx.serialization.internal.StringSerializer
import kotlinx.serialization.list
import tk.skeptick.vk.apiclient.*
import tk.skeptick.vk.apiclient.domain.models.Community
import tk.skeptick.vk.apiclient.domain.models.User
import tk.skeptick.vk.apiclient.methods.*
import java.util.*

class GroupsApi(override val client: VkApiClient)
    : GroupsApiUser, GroupsApiCommunity, MethodsContext {

    override fun addCallbackServer(
        groupId: Int,
        url: String,
        title: String,
        secretKey: String?
    ): VkApiRequest<Int> =
        Methods.addCallbackServer.httpGet(
            "group_id" to groupId,
            "url" to url,
            "title" to title,
            "secret_key" to secretKey
        ).withSerializer(IntSerializer)

    override fun addLink(
        groupId: Int,
        link: String,
        text: String?
    ): VkApiRequest<AddLinkResponse> =
        Methods.addLink.httpPost(
            "group_id" to groupId,
            "link" to link,
            "text" to text
        ).withSerializer(AddLinkResponse.serializer())

    override fun approveRequest(
        groupId: Int,
        userId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.approveRequest.httpGet(
            "group_id" to groupId,
            "user_id" to userId
        ).withSerializer(BooleanInt.serializer())

    override fun ban(
        groupId: Int,
        ownerId: Int,
        endDate: Date?,
        reason: CommunityBan.Reason,
        comment: String?,
        commentVisible: Boolean
    ): VkApiRequest<BooleanInt> =
        Methods.ban.httpPost(
            "group_id" to groupId,
            "owner_id" to ownerId,
            "end_date" to endDate?.time,
            "reason" to reason.value,
            "comment" to comment,
            "comment_visible" to commentVisible.asInt()
        ).withSerializer(BooleanInt.serializer())

    private fun create(
        title: String,
        type: GroupType,
        description: String? = null,
        publicCategory: Int? = null,
        subtype: PublicSubtype? = null
    ): VkApiRequest<Community> =
        Methods.create.httpPost(
            "title" to title,
            "description" to description,
            "type" to type.value,
            "public_category" to publicCategory,
            "subtype" to subtype?.value
        ).withSerializer(Community.serializer())

    override fun createEvent(
        title: String,
        description: String
    ): VkApiRequest<Community> = create(
        title = title,
        type = GroupType.EVENT,
        description = description
    )

    override fun createGroup(
        title: String,
        description: String
    ): VkApiRequest<Community> = create(
        title = title,
        type = GroupType.GROUP,
        description = description
    )

    override fun createPublic(
        title: String,
        subtype: PublicSubtype,
        publicCategory: Int?
    ): VkApiRequest<Community> = create(
        title = title,
        type = GroupType.PUBLIC,
        publicCategory = publicCategory,
        subtype = subtype
    )

    override fun deleteCallbackServer(
        groupId: Int,
        serverId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.deleteCallbackServer.httpGet(
            "group_id" to groupId,
            "server_id" to serverId
        ).withSerializer(BooleanInt.serializer())

    override fun deleteLink(
        groupId: Int,
        linkId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.deleteLink.httpGet(
            "group_id" to groupId,
            "link_id" to linkId
        ).withSerializer(BooleanInt.serializer())

    override fun disableOnline(
        groupId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.disableOnline.httpGet(
            "group_id" to groupId
        ).withSerializer(BooleanInt.serializer())

    private fun edit(
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
        Methods.edit.httpPost(
            "group_id" to groupId,
            "title" to title,
            "description" to description,
            "screen_name" to screenName,
            "access" to access?.value,
            "website" to website,
            "subject" to subject?.value,
            "email" to email,
            "phone" to phone,
            "rss" to rss,
            "event_start_date" to eventStartDate?.time,
            "event_finish_date" to eventFinishDate?.time,
            "event_group_id" to eventGroupId,
            "public_category" to publicCategory,
            "public_subcategory" to publicSubcategory,
            "public_date" to publicDate,
            "wall" to wall,
            "topics" to topics,
            "photos" to photos,
            "video" to video,
            "audio" to audio,
            "docs" to docs,
            "wiki" to wiki,
            "links" to links?.asInt(),
            "events" to events?.asInt(),
            "places" to places?.asInt(),
            "contacts" to contacts?.asInt(),
            "messages" to messages?.asInt(),
            "age_limits" to ageLimits?.value,
            "market" to market?.asInt(),
            "market_comments" to marketComments?.asInt(),
            "market_country" to marketCountries?.joinToString(","),
            "market_city" to marketCities?.joinToString(","),
            "market_currency" to marketCurrency?.value,
            "market_contact" to marketContact,
            "market_wiki" to marketWiki,
            "obscene_filter" to obsceneFilter?.asInt(),
            "obscene_stopwords" to obsceneStopwords?.asInt(),
            "obscene_words" to obsceneWords?.joinToString(","),
            "main_section" to mainSection?.value,
            "secondary_section" to secondarySection?.value
        ).withSerializer(BooleanInt.serializer())

    override fun editPublic(
        groupId: Int,
        title: String?,
        description: String?,
        screenName: String?,
        access: Community.CloseType?,
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
    ): VkApiRequest<BooleanInt> = edit(
        groupId = groupId,
        title = title,
        description = description,
        screenName = screenName,
        access = access,
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
        website: String?,
        subject: GroupSubject?,
        email: String?,
        phone: String?,
        rss: String?,
        eventStartDate: Date?,
        eventFinishDate: Date?,
        eventGroupId: Int?,
        wall: GroupUnitAccessTypeExtended?,
        topics: GroupUnitAccessType?,
        photos: GroupUnitAccessType?,
        video: GroupUnitAccessType?,
        audio: GroupUnitAccessType?,
        docs: GroupUnitAccessType?,
        wiki: GroupUnitAccessType?,
        messages: Boolean?,
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
    ): VkApiRequest<BooleanInt> = edit(
        groupId = groupId,
        title = title,
        description = description,
        screenName = screenName,
        access = access,
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
    ): VkApiRequest<BooleanInt> = edit(
        groupId = groupId,
        title = title,
        description = description,
        screenName = screenName,
        access = access,
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
        Methods.editCallbackServer.httpPost(
            "group_id" to groupId,
            "server_id" to serverId,
            "url" to url,
            "title" to title,
            "secret_key" to secretKey
        ).withSerializer(BooleanInt.serializer())

    override fun editLink(
        groupId: Int,
        linkId: Int,
        text: String?
    ): VkApiRequest<BooleanInt> =
        Methods.editLink.httpPost(
            "group_id" to groupId,
            "link_id" to linkId,
            "text" to text
        ).withSerializer(BooleanInt.serializer())

    override fun editManager(
        groupId: Int,
        userId: Int,
        role: CommunityManagerRole?,
        isContact: Boolean?,
        contactPosition: String?,
        contactPhone: String?,
        contactEmail: String?
    ): VkApiRequest<BooleanInt> =
        Methods.editManager.httpGet(
            "group_id" to groupId,
            "user_id" to userId,
            "role" to role?.value,
            "is_contact" to isContact?.asInt(),
            "contact_position" to contactPosition,
            "contact_phone" to contactPhone,
            "contact_email" to contactEmail
        ).withSerializer(BooleanInt.serializer())

    override fun editPlace(
        groupId: Int,
        title: String?,
        address: String?,
        countryId: Int?,
        cityId: Int?,
        latitude: Double?,
        longitude: Double?
    ): VkApiRequest<EditPlaceResponse> =
        Methods.editPlace.httpPost(
            "group_id" to groupId,
            "title" to title,
            "address" to address,
            "country_id" to countryId,
            "city_id" to cityId,
            "latitude" to latitude,
            "longitude" to longitude
        ).withSerializer(EditPlaceResponse.serializer())

    override fun enableOnline(
        groupId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.enableOnline.httpGet(
            "group_id" to groupId
        ).withSerializer(BooleanInt.serializer())

    override fun getIds(
        userId: Int?,
        filter: GroupsFilter?,
        offset: Int,
        count: Int
    ): VkApiRequest<DefaultListResponse<Int>> =
        Methods.get.httpGet(
            "user_id" to userId,
            "filter" to filter?.value,
            "offset" to offset,
            "count" to count
        ).withSerializer(list(IntSerializer))

    override fun get(
        userId: Int?,
        filter: GroupsFilter?,
        fields: List<CommunityOptionalField>?,
        offset: Int,
        count: Int
    ): VkApiRequest<DefaultListResponse<Community>> =
        Methods.get.httpPost(
            "user_id" to userId,
            "extended" to 1,
            "filter" to filter?.value,
            "fields" to fields?.joinToString(",") { it.value },
            "offset" to offset,
            "count" to count
        ).withSerializer(list(Community.serializer()))

    override fun getBanned(
        groupId: Int,
        offset: Int,
        count: Int,
        fields: List<ObjectField>,
        ownerId: Int?
    ): VkApiRequest<DefaultListResponse<CommunityBan>> =
        Methods.getBanned.httpPost(
            "group_id" to groupId,
            "offset" to offset,
            "count" to count,
            "fields" to fields.joinToString(",") { it.value },
            "owner_id" to ownerId
        ).withSerializer(list(CommunityBan.serializer()))

    override fun getByScreenName(
        groupNames: List<String>,
        communityFields: List<CommunityOptionalField>?
    ): VkApiRequest<List<Community>> =
        Methods.getById.httpPost(
            "group_ids" to groupNames.joinToString(","),
            "fields" to communityFields?.joinToString(",") { it.value }
        ).withSerializer(Community.serializer().list)

    override fun getById(
        groupIds: List<Int>,
        communityFields: List<CommunityOptionalField>?
    ): VkApiRequest<List<Community>> = getByScreenName(
        groupNames = groupIds.map(Int::toString),
        communityFields = communityFields
    )

    override fun getCallbackConfirmationCode(
        groupId: Int
    ): VkApiRequest<String> =
        Methods.getCallbackConfirmationCode.httpGet(
            "group_id" to groupId
        ).withSerializer(StringSerializer)

    override fun getCallbackServers(
        groupId: Int,
        serverIds: List<Int>?
    ): VkApiRequest<DefaultListResponse<CommunityCallbackServer>> =
        Methods.getCallbackServers.httpGet(
            "group_id" to groupId,
            "server_ids" to serverIds?.joinToString(",")
        ).withSerializer(list(CommunityCallbackServer.serializer()))

    override fun getCallbackSettings(
        groupId: Int,
        serverId: Int
    ): VkApiRequest<ServerSettings> =
        Methods.getCallbackSettings.httpGet(
            "group_id" to groupId,
            "server_id" to serverId
        ).withSerializer(ServerSettings.serializer())

    override fun getCatalog(
        categoryId: Int?,
        subcategoryId: Int?
    ): VkApiRequest<DefaultListResponse<Community>> =
        Methods.getCatalog.httpGet(
            "category_id" to categoryId,
            "subcategory_id" to subcategoryId
        ).withSerializer(list(Community.serializer()))

    override fun getCatalogInfo(
        extended: Boolean,
        withSubcategories: Boolean
    ): VkApiRequest<CommunitiesCatalog> =
        Methods.getCatalogInfo.httpGet(
            "extended" to extended.asInt(),
            "subcategories" to withSubcategories.asInt()
        ).withSerializer(CommunitiesCatalog.serializer())

    override fun getInvitedUsers(
        groupId: Int,
        offset: Int,
        count: Int,
        userFields: List<UserOptionalField>,
        nameCase: NameCase
    ): VkApiRequest<DefaultListResponse<User>> =
        Methods.getInvitedUsers.httpPost(
            "group_id" to groupId,
            "offset" to offset,
            "count" to count,
            "fields" to userFields.joinToString(",") { it.value },
            "name_case" to nameCase.value
        ).withSerializer(list(User.serializer()))

    override fun getInvites(
        offset: Int,
        count: Int,
        extended: Boolean
    ): VkApiRequest<ExtendedListResponse<Community>> =
        Methods.getInvites.httpGet(
            "offset" to offset,
            "count" to count,
            "extended" to extended.asInt()
        ).withSerializer(extendedList(Community.serializer()))

    override fun getLongPollServer(
        groupId: Int
    ): VkApiRequest<LongPollServerResponse> =
        Methods.getLongPollServer.httpGet(
            "group_id" to groupId
        ).withSerializer(LongPollServerResponse.serializer())

    override fun getLongPollSettings(
        groupId: Int
    ): VkApiRequest<ServerSettings> =
        Methods.getLongPollSettings.httpGet(
            "group_id" to groupId
        ).withSerializer(ServerSettings.serializer())

    override fun getMembersIds(
        groupId: Int,
        sort: CommunityMembersSort,
        offset: Int,
        count: Int,
        onlyFriends: Boolean
    ): VkApiRequest<DefaultListResponse<Int>> =
        Methods.getMembers.httpGet(
            "group_id" to groupId,
            "sort" to sort.value,
            "offset" to offset,
            "count" to count,
            "filter" to if (onlyFriends) "friends" else null
        ).withSerializer(list(IntSerializer))

    override fun getMembers(
        groupId: Int,
        sort: CommunityMembersSort,
        offset: Int,
        count: Int,
        userFields: List<UserOptionalField>,
        onlyFriends: Boolean
    ): VkApiRequest<DefaultListResponse<User>> =
        Methods.getMembers.httpPost(
            "group_id" to groupId,
            "sort" to sort.value,
            "offset" to offset,
            "count" to count,
            "fields" to userFields.joinToString(",") { it.value },
            "filter" to if (onlyFriends) "friends" else null
        ).withSerializer(list(User.serializer()))

    override fun getManagersIds(
        groupId: Int,
        sort: CommunityMembersSort,
        offset: Int,
        count: Int
    ): VkApiRequest<DefaultListResponse<CommunityManager>> =
        Methods.getMembers.httpGet(
            "group_id" to groupId,
            "sort" to sort.value,
            "offset" to offset,
            "count" to count,
            "filter" to "managers"
        ).withSerializer(list(CommunityManager.serializer()))

    override fun getManagers(
        groupId: Int,
        sort: CommunityMembersSort,
        offset: Int,
        count: Int,
        userFields: List<UserOptionalField>
    ): VkApiRequest<DefaultListResponse<User>> =
        Methods.getMembers.httpPost(
            "group_id" to groupId,
            "sort" to sort.value,
            "offset" to offset,
            "count" to count,
            "fields" to userFields.joinToString(",") { it.value },
            "filter" to "managers"
        ).withSerializer(list(User.serializer()))

    override fun getOnlineStatus(
        groupId: Int
    ): VkApiRequest<CommunityOnlineStatus> =
        Methods.getOnlineStatus.httpGet(
            "group_id" to groupId
        ).withSerializer(CommunityOnlineStatus.serializer())

    override fun getRequestsIds(
        groupId: Int,
        offset: Int,
        count: Int
    ): VkApiRequest<DefaultListResponse<Int>> =
        Methods.getRequests.httpGet(
            "group_id" to groupId,
            "offset" to offset,
            "count" to count
        ).withSerializer(list(IntSerializer))

    override fun getRequests(
        groupId: Int,
        offset: Int,
        count: Int,
        userFields: List<UserOptionalField>
    ): VkApiRequest<DefaultListResponse<User>> =
        Methods.getRequests.httpPost(
            "group_id" to groupId,
            "offset" to offset,
            "count" to count,
            "fields" to userFields.joinToString(",") { it.value }
        ).withSerializer(list(User.serializer()))

    override fun getSettings(
        groupId: Int
    ): VkApiRequest<CommunitySettings> =
        Methods.getSettings.httpGet(
            "group_id" to groupId
        ).withSerializer(CommunitySettings.serializer())

    override fun getTokenPermissions(): VkApiRequest<CommunityTokenPermissions> =
        Methods.getTokenPermissions.httpGet()
            .withSerializer(CommunityTokenPermissions.serializer())

    override fun invite(
        groupId: Int,
        userId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.invite.httpGet(
            "group_id" to groupId,
            "user_id" to userId
        ).withSerializer(BooleanInt.serializer())

    override fun isMember(
        groupId: Int,
        userId: Int?
    ): VkApiRequest<CommunityMemberResponse> =
        Methods.isMember.httpGet(
            "group_id" to groupId,
            "user_id" to userId,
            "extended" to 1
        ).withSerializer(CommunityMemberResponse.serializer())

    override fun isMembers(
        groupId: Int,
        userIds: List<Int>
    ): VkApiRequest<List<CommunityMemberResponse>> =
        Methods.isMember.httpPost(
            "group_id" to groupId,
            "user_ids" to userIds.joinToString(",")
        ).withSerializer(CommunityMemberResponse.serializer().list)

    override fun join(
        groupId: Int,
        notSure: Boolean?
    ): VkApiRequest<BooleanInt> =
        Methods.join.httpGet(
            "group_id" to groupId,
            "not_sure" to notSure?.asInt()
        ).withSerializer(BooleanInt.serializer())

    override fun leave(
        groupId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.leave.httpGet(
            "group_id" to groupId
        ).withSerializer(BooleanInt.serializer())

    override fun removeUser(
        groupId: Int,
        userId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.removeUser.httpGet(
            "group_id" to groupId,
            "user_id" to userId
        ).withSerializer(BooleanInt.serializer())

    override fun reorderLink(
        groupId: Int,
        linkId: Int,
        after: Int
    ): VkApiRequest<BooleanInt> =
        Methods.reorderLink.httpGet(
            "group_id" to groupId,
            "link_id" to linkId,
            "after" to after
        ).withSerializer(BooleanInt.serializer())

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
        Methods.search.httpPost(
            "q" to query,
            "type" to type?.value,
            "country_id" to countryId,
            "city_id" to cityId,
            "future" to isFuture?.asInt(),
            "market" to hasMarket?.asInt(),
            "sort" to sort.value,
            "offset" to offset,
            "count" to count
        ).withSerializer(list(Community.serializer()))

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
        Methods.setCallbackSettings.httpGet(
            "group_id" to groupId,
            "server_id" to serverId,
            "api_version" to apiVersion,
            "message_new" to messageNew?.asInt(),
            "message_reply" to messageReply?.asInt(),
            "photo_new" to photoNew?.asInt(),
            "audio_new" to audioNew?.asInt(),
            "video_new" to videoNew?.asInt(),
            "wall_reply_new" to wallReplyNew?.asInt(),
            "wall_reply_edit" to wallReplyEdit?.asInt(),
            "wall_reply_delete" to wallReplyDelete?.asInt(),
            "wall_reply_restore" to wallReplyRestore?.asInt(),
            "wall_post_new" to wallPostNew?.asInt(),
            "board_post_new" to boardPostNew?.asInt(),
            "board_post_edit" to boardPostEdit?.asInt(),
            "board_post_restore" to boardPostRestore?.asInt(),
            "board_post_delete" to boardPostDelete?.asInt(),
            "photo_comment_new" to photoCommentNew?.asInt(),
            "photo_comment_edit" to photoCommentEdit?.asInt(),
            "photo_comment_delete" to photoCommentDelete?.asInt(),
            "photo_comment_restore" to photoCommentRestore?.asInt(),
            "video_comment_new" to videoCommentNew?.asInt(),
            "video_comment_edit" to videoCommentEdit?.asInt(),
            "video_comment_delete" to videoCommentDelete?.asInt(),
            "video_comment_restore" to videoCommentRestore?.asInt(),
            "market_comment_new" to marketCommentNew?.asInt(),
            "market_comment_edit" to marketCommentEdit?.asInt(),
            "market_comment_delete" to marketCommentDelete?.asInt(),
            "market_comment_restore" to marketCommentRestore?.asInt(),
            "poll_vote_new" to pollVoteNew?.asInt(),
            "group_join" to groupJoin?.asInt(),
            "group_leave" to groupLeave?.asInt(),
            "group_change_settings" to groupChangeSettings?.asInt(),
            "group_change_photo" to groupChangePhoto?.asInt(),
            "group_officers_edit" to groupOfficersEdit?.asInt(),
            "message_allow" to messageAllow?.asInt(),
            "message_deny" to messageDeny?.asInt(),
            "wall_repost" to wallRepost?.asInt(),
            "user_block" to userBlock?.asInt(),
            "user_unblock" to userUnblock?.asInt(),
            "messages_edit" to messagesEdit?.asInt(),
            "message_typing_state" to messageTypingState?.asInt(),
            "lead_forms_new" to leadFormsNew?.asInt()
        ).withSerializer(BooleanInt.serializer())

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
        Methods.setLongPollSettings.httpGet(
            "group_id" to groupId,
            "enabled" to enabled?.asInt(),
            "message_new" to messageNew?.asInt(),
            "message_reply" to messageReply?.asInt(),
            "photo_new" to photoNew?.asInt(),
            "audio_new" to audioNew?.asInt(),
            "video_new" to videoNew?.asInt(),
            "wall_reply_new" to wallReplyNew?.asInt(),
            "wall_reply_edit" to wallReplyEdit?.asInt(),
            "wall_reply_delete" to wallReplyDelete?.asInt(),
            "wall_reply_restore" to wallReplyRestore?.asInt(),
            "wall_post_new" to wallPostNew?.asInt(),
            "board_post_new" to boardPostNew?.asInt(),
            "board_post_edit" to boardPostEdit?.asInt(),
            "board_post_restore" to boardPostRestore?.asInt(),
            "board_post_delete" to boardPostDelete?.asInt(),
            "photo_comment_new" to photoCommentNew?.asInt(),
            "photo_comment_edit" to photoCommentEdit?.asInt(),
            "photo_comment_delete" to photoCommentDelete?.asInt(),
            "photo_comment_restore" to photoCommentRestore?.asInt(),
            "video_comment_new" to videoCommentNew?.asInt(),
            "video_comment_edit" to videoCommentEdit?.asInt(),
            "video_comment_delete" to videoCommentDelete?.asInt(),
            "video_comment_restore" to videoCommentRestore?.asInt(),
            "market_comment_new" to marketCommentNew?.asInt(),
            "market_comment_edit" to marketCommentEdit?.asInt(),
            "market_comment_delete" to marketCommentDelete?.asInt(),
            "market_comment_restore" to marketCommentRestore?.asInt(),
            "poll_vote_new" to pollVoteNew?.asInt(),
            "group_join" to groupJoin?.asInt(),
            "group_leave" to groupLeave?.asInt(),
            "group_change_settings" to groupChangeSettings?.asInt(),
            "group_change_photo" to groupChangePhoto?.asInt(),
            "group_officers_edit" to groupOfficersEdit?.asInt(),
            "message_allow" to messageAllow?.asInt(),
            "message_deny" to messageDeny?.asInt(),
            "wall_repost" to wallRepost?.asInt(),
            "user_block" to userBlock?.asInt(),
            "user_unblock" to userUnblock?.asInt(),
            "messages_edit" to messagesEdit?.asInt(),
            "message_typing_state" to messageTypingState?.asInt(),
            "lead_forms_new" to leadFormsNew?.asInt()
        ).withSerializer(BooleanInt.serializer())

    override fun unban(
        groupId: Int,
        ownerId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.unban.httpGet(
            "group_id" to groupId,
            "owner_id" to ownerId
        ).withSerializer(BooleanInt.serializer())

    private object Methods {
        private const val it = "groups."
        const val addCallbackServer = it + "addCallbackServer"
        const val addLink = it + "addLink"
        const val approveRequest = it + "approveRequest"
        const val ban = it + "ban"
        const val create = it + "create"
        const val deleteCallbackServer = it + "deleteCallbackServer"
        const val deleteLink = it + "deleteLink"
        const val disableOnline = it + "disableOnline"
        const val edit = it + "edit"
        const val editCallbackServer = it + "editCallbackServer"
        const val editLink = it + "editLink"
        const val editManager = it + "editManager"
        const val editPlace = it + "editPlace"
        const val enableOnline = it + "enableOnline"
        const val get = it + "get"
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
        const val unban = it + "unban"
    }

}