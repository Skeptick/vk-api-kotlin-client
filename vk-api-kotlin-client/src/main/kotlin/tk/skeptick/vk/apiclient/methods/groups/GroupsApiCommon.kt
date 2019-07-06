package tk.skeptick.vk.apiclient.methods.groups

import tk.skeptick.vk.apiclient.BooleanInt
import tk.skeptick.vk.apiclient.DefaultMethodsParams
import tk.skeptick.vk.apiclient.VkApiRequest
import tk.skeptick.vk.apiclient.domain.models.Address
import tk.skeptick.vk.apiclient.domain.models.Community
import tk.skeptick.vk.apiclient.domain.models.User
import tk.skeptick.vk.apiclient.methods.*

interface GroupsApiCommon {

    /**
     * @see <a href="https://vk.com/dev/groups.addAddress">VK API</a>
     */
    fun addAddress(
        groupId: Int,
        title: String,
        address: String,
        countryId: Int,
        cityId: Int,
        latitude: Double,
        longitude: Double,
        additionalAddress: String? = null,
        metroId: Int? = null,
        phone: String? = null,
        workInfoStatus: Address.WorkInfoStatus? = null,
        timetable: Address.Timetable? = null,
        isMainAddress: Boolean? = null
    ): VkApiRequest<Address>

    /**
     * @return server ID
     * @see <a href="https://vk.com/dev/groups.addCallbackServer">VK API</a>
     */
    fun addCallbackServer(
        groupId: Int,
        url: String,
        title: String,
        secretKey: String? = null
    ): VkApiRequest<Int>

    /**
     * @see <a href="https://vk.com/dev/groups.deleteAddress">VK API</a>
     */
    fun deleteAddress(
        groupId: Int,
        addressId: Int
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/groups.deleteCallbackServer">VK API</a>
     */
    fun deleteCallbackServer(
        groupId: Int,
        serverId: Int
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/groups.disableOnline">VK API</a>
     */
    fun disableOnline(
        groupId: Int
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/groups.editAddress">VK API</a>
     */
    fun editAddress(
        groupId: Int,
        addressId: Int,
        title: String? = null,
        address: String? = null,
        additionalAddress: String? = null,
        countryId: Int? = null,
        cityId: Int? = null,
        metroId: Int? = null,
        latitude: Double? = null,
        longitude: Double? = null,
        phone: String? = null,
        workInfoStatus: Address.WorkInfoStatus? = null,
        timetable: Address.Timetable? = null,
        isMainAddress: Boolean? = null
    ): VkApiRequest<Address>

    /**
     * @see <a href="https://vk.com/dev/groups.editCallbackServer">VK API</a>
     */
    fun editCallbackServer(
        groupId: Int,
        serverId: Int,
        url: String,
        title: String,
        secretKey: String? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/groups.enableOnline">VK API</a>
     */
    fun enableOnline(
        groupId: Int
    ): VkApiRequest<BooleanInt>

    /**
     * @param[count] maximum value 200
     * @see <a href="https://vk.com/dev/groups.getBanned">VK API</a>
     */
    fun getBanned(
        groupId: Int,
        offset: Int = 0,
        count: Int = 20,
        fields: List<ObjectField> = DefaultMethodsParams.userFields,
        ownerId: Int? = null
    ): VkApiRequest<DefaultListResponse<CommunityBan>>

    /**
     * @see <a href="https://vk.com/dev/groups.getById">VK API</a>
     */
    fun getByScreenName(
        groupNames: List<String>,
        communityFields: List<CommunityOptionalField>? = null
    ): VkApiRequest<List<Community>>

    /**
     * @see <a href="https://vk.com/dev/groups.getById">VK API</a>
     */
    fun getById(
        groupIds: List<Int>,
        communityFields: List<CommunityOptionalField>? = null
    ): VkApiRequest<List<Community>>

    /**
     * @see <a href="https://vk.com/dev/groups.getCallbackConfirmationCode">VK API</a>
     */
    fun getCallbackConfirmationCode(
        groupId: Int
    ): VkApiRequest<String>

    /**
     * @see <a href="https://vk.com/dev/groups.getCallbackServers">VK API</a>
     */
    fun getCallbackServers(
        groupId: Int,
        serverIds: List<Int>? = null
    ): VkApiRequest<DefaultListResponse<CommunityCallbackServer>>

    /**
     * @see <a href="https://vk.com/dev/groups.getCallbackSettings">VK API</a>
     */
    fun getCallbackSettings(
        groupId: Int,
        serverId: Int
    ): VkApiRequest<ServerSettings>

    /**
     * @see <a href="https://vk.com/dev/groups.getLongPollServer">VK API</a>
     */
    fun getLongPollServer(
        groupId: Int
    ): VkApiRequest<LongPollServerResponse>

    /**
     * @see <a href="https://vk.com/dev/groups.getLongPollSettings">VK API</a>
     */
    fun getLongPollSettings(
        groupId: Int
    ): VkApiRequest<ServerSettings>

    /**
     * Alternative to [getMembers] that allows to get only users IDs.
     * @param[count] maximum value 1000
     * @see <a href="https://vk.com/dev/groups.getMembers">VK API</a>
     */
    fun getMembersIds(
        groupId: Int,
        sort: CommunityMembersSort = CommunityMembersSort.ID_ASC,
        offset: Int = 0,
        count: Int = 1000,
        onlyFriends: Boolean = false
    ): VkApiRequest<DefaultListResponse<Int>>

    /**
     * @param[count] maximum value 1000
     * @see <a href="https://vk.com/dev/groups.getMembers">VK API</a>
     */
    fun getMembers(
        groupId: Int,
        sort: CommunityMembersSort = CommunityMembersSort.ID_ASC,
        offset: Int = 0,
        count: Int = 1000,
        userFields: List<UserOptionalField> = DefaultMethodsParams.userFields,
        onlyFriends: Boolean = false
    ): VkApiRequest<DefaultListResponse<User>>

    /**
     * Alternative to [getMembers] that allows to get
     * only community managers IDs with role.
     * @param[count] maximum value 1000
     * @see <a href="https://vk.com/dev/groups.getMembers">VK API</a>
     */
    fun getManagersIds(
        groupId: Int,
        sort: CommunityMembersSort = CommunityMembersSort.ID_ASC,
        offset: Int = 0,
        count: Int = 1000
    ): VkApiRequest<DefaultListResponse<CommunityManager>>

    /**
     * Alternative to [getMembers] that allows to get
     * only community managers profile.
     * @param[count] maximum value 1000
     * @see <a href="https://vk.com/dev/groups.getMembers">VK API</a>
     */
    fun getManagers(
        groupId: Int,
        sort: CommunityMembersSort = CommunityMembersSort.ID_ASC,
        offset: Int = 0,
        count: Int = 1000,
        userFields: List<UserOptionalField> = DefaultMethodsParams.userFields
    ): VkApiRequest<DefaultListResponse<User>>

    /**
     * @see <a href="https://vk.com/dev/groups.getOnlineStatus">VK API</a>
     */
    fun getOnlineStatus(
        groupId: Int
    ): VkApiRequest<CommunityOnlineStatus>

    /**
     * @see <a href="https://vk.com/dev/groups.isMember">VK API</a>
     */
    fun isMember(
        groupId: Int,
        userId: Int? = null
    ): VkApiRequest<CommunityMemberResponse>

    /**
     * @see <a href="https://vk.com/dev/groups.isMember">VK API</a>
     */
    fun isMembers(
        groupId: Int,
        userIds: List<Int>
    ): VkApiRequest<List<CommunityMemberResponse>>

    /**
     * @see <a href="https://vk.com/dev/groups.setCallbackSettings">VK API</a>
     */
    fun setCallbackSettings(
        groupId: Int,
        serverId: Int,
        apiVersion: String? = null,
        messageNew: Boolean? = null,
        messageReply: Boolean? = null,
        photoNew: Boolean? = null,
        audioNew: Boolean? = null,
        videoNew: Boolean? = null,
        wallReplyNew: Boolean? = null,
        wallReplyEdit: Boolean? = null,
        wallReplyDelete: Boolean? = null,
        wallReplyRestore: Boolean? = null,
        wallPostNew: Boolean? = null,
        boardPostNew: Boolean? = null,
        boardPostEdit: Boolean? = null,
        boardPostRestore: Boolean? = null,
        boardPostDelete: Boolean? = null,
        photoCommentNew: Boolean? = null,
        photoCommentEdit: Boolean? = null,
        photoCommentDelete: Boolean? = null,
        photoCommentRestore: Boolean? = null,
        videoCommentNew: Boolean? = null,
        videoCommentEdit: Boolean? = null,
        videoCommentDelete: Boolean? = null,
        videoCommentRestore: Boolean? = null,
        marketCommentNew: Boolean? = null,
        marketCommentEdit: Boolean? = null,
        marketCommentDelete: Boolean? = null,
        marketCommentRestore: Boolean? = null,
        pollVoteNew: Boolean? = null,
        groupJoin: Boolean? = null,
        groupLeave: Boolean? = null,
        groupChangeSettings: Boolean? = null,
        groupChangePhoto: Boolean? = null,
        groupOfficersEdit: Boolean? = null,
        messageAllow: Boolean? = null,
        messageDeny: Boolean? = null,
        wallRepost: Boolean? = null,
        userBlock: Boolean? = null,
        userUnblock: Boolean? = null,
        messagesEdit: Boolean? = null,
        messageTypingState: Boolean? = null,
        leadFormsNew: Boolean? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/groups.setLongPollSettings">VK API</a>
     */
    fun setLongPollSettings(
        groupId: Int,
        enabled: Boolean? = null,
        messageNew: Boolean? = null,
        messageReply: Boolean? = null,
        photoNew: Boolean? = null,
        audioNew: Boolean? = null,
        videoNew: Boolean? = null,
        wallReplyNew: Boolean? = null,
        wallReplyEdit: Boolean? = null,
        wallReplyDelete: Boolean? = null,
        wallReplyRestore: Boolean? = null,
        wallPostNew: Boolean? = null,
        boardPostNew: Boolean? = null,
        boardPostEdit: Boolean? = null,
        boardPostRestore: Boolean? = null,
        boardPostDelete: Boolean? = null,
        photoCommentNew: Boolean? = null,
        photoCommentEdit: Boolean? = null,
        photoCommentDelete: Boolean? = null,
        photoCommentRestore: Boolean? = null,
        videoCommentNew: Boolean? = null,
        videoCommentEdit: Boolean? = null,
        videoCommentDelete: Boolean? = null,
        videoCommentRestore: Boolean? = null,
        marketCommentNew: Boolean? = null,
        marketCommentEdit: Boolean? = null,
        marketCommentDelete: Boolean? = null,
        marketCommentRestore: Boolean? = null,
        pollVoteNew: Boolean? = null,
        groupJoin: Boolean? = null,
        groupLeave: Boolean? = null,
        groupChangeSettings: Boolean? = null,
        groupChangePhoto: Boolean? = null,
        groupOfficersEdit: Boolean? = null,
        messageAllow: Boolean? = null,
        messageDeny: Boolean? = null,
        wallRepost: Boolean? = null,
        userBlock: Boolean? = null,
        userUnblock: Boolean? = null,
        messagesEdit: Boolean? = null,
        messageTypingState: Boolean? = null,
        leadFormsNew: Boolean? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/groups.setSettings">VK API</a>
     */
    fun setSettings(
        groupId: Int,
        messages: Boolean? = null,
        botsCapabilities: Boolean? = null,
        botsStartButton: Boolean? = null,
        botsAddToChat: Boolean? = null
    ): VkApiRequest<BooleanInt>

}