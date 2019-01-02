package tk.skeptick.vk.apiclient.methods.friends

import tk.skeptick.vk.apiclient.*
import tk.skeptick.vk.apiclient.domain.models.User
import tk.skeptick.vk.apiclient.methods.DefaultListResponse
import tk.skeptick.vk.apiclient.methods.FriendsOrder
import tk.skeptick.vk.apiclient.methods.NameCase
import tk.skeptick.vk.apiclient.methods.UserOptionalField

interface FriendsApiUser : FriendsApiCommon {

    /**
     * @see <a href="https://vk.com/dev/friends.add">VK API</a>
     */
    fun add(
        userId: Int,
        text: String? = null,
        declineRequest: Boolean = false
    ): VkApiRequest<AddFriendResponse>

    /**
     * @return ID of the created list
     * @see <a href="https://vk.com/dev/friends.addList">VK API</a>
     */
    fun addList(
        name: String,
        userIds: List<Int>? = null
    ): VkApiRequest<Int>

    /**
     * @see <a href="https://vk.com/dev/friends.areFriends">VK API</a>
     */
    fun areFriends(
        userIds: List<Int>,
        needSign: Boolean = false
    ): VkApiRequest<AreFriendResponse>

    /**
     * @see <a href="https://vk.com/dev/friends.delete">VK API</a>
     */
    fun delete(
        userId: Int
    ): VkApiRequest<DeleteFriendResponse>

    /**
     * @see <a href="https://vk.com/dev/friends.deleteAllRequests">VK API</a>
     */
    fun deleteAllRequests(): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/friends.deleteList">VK API</a>
     */
    fun deleteList(
        listId: Int
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/friends.edit">VK API</a>
     */
    fun edit(
        userId: Int,
        listIds: List<Int>? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/friends.editList">VK API</a>
     */
    fun editList(
        listId: Int,
        userIds: List<Int>,
        name: String? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/friends.editList">VK API</a>
     */
    fun editList(
        listId: Int,
        addUserIds: List<Int>? = null,
        deleteUserIds: List<Int>? = null,
        name: String? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @param[count] maximum value 5000
     * @see <a href="https://vk.com/dev/friends.get">VK API</a>
     */
    fun get(
        userId: Int? = null,
        order: FriendsOrder? = null,
        listId: Int? = null,
        count: Int = 5000,
        offset: Int = 0,
        userFields: List<UserOptionalField> = DefaultMethodsParams.userFields,
        nameCase: NameCase = DefaultMethodsParams.userNameCase
    ): VkApiRequest<DefaultListResponse<User>>

    /**
     * Alternative to [get] that allows to get only users IDs,
     * but without a limit of 5000 items.
     * @param[count] maximum value 10000
     * @see <a href="https://vk.com/dev/friends.get">VK API</a>
     */
    fun getIds(
        userId: Int? = null,
        order: FriendsOrder? = null,
        listId: Int? = null,
        count: Int = 10000,
        offset: Int = 0
    ): VkApiRequest<DefaultListResponse<Int>>

    /**
     * @see <a href="https://vk.com/dev/friends.getAppUsers">VK API</a>
     */
    fun getAppUsers(): VkApiRequest<List<Int>>

    /**
     * @param[phones] phone numbers in MSISDN format (maximum 1000)
     * @see <a href="https://vk.com/dev/friends.getByPhones">VK API</a>
     */
    fun getByPhones(
        phones: List<String>,
        userFields: List<UserOptionalField> = DefaultMethodsParams.userFields
    ): VkApiRequest<List<User>>

    /**
     * @see <a href="https://vk.com/dev/friends.getLists">VK API</a>
     */
    fun getLists(
        userId: Int? = null,
        withSystem: Boolean = false
    ): VkApiRequest<DefaultListResponse<FriendsList>>

    /**
     * @see <a href="https://vk.com/dev/friends.getMutual">VK API</a>
     */
    fun getMutual(
        targetUserId: Int,
        sourceUserId: Int? = null,
        sortRandomly: Boolean = false,
        count: Int? = null,
        offset: Int = 0
    ): VkApiRequest<List<Int>>

    /**
     * @see <a href="https://vk.com/dev/friends.getMutual">VK API</a>
     */
    fun getMutual(
        targetUserIds: List<Int>,
        sourceUserId: Int? = null,
        sortRandomly: Boolean = false,
        count: Int? = null,
        offset: Int = 0
    ): VkApiRequest<List<MutualFriendsResponse>>

    /**
     * @see <a href="https://vk.com/dev/friends.getOnline">VK API</a>
     */
    fun getOnline(
        userId: Int? = null,
        listId: Int? = null,
        sortRandomly: Boolean = false,
        count: Int? = null,
        offset: Int = 0
    ): VkApiRequest<List<Int>>

    /**
     * Alternative to [getOnline] that return friends
     * with the division of online from mobile and online from computers,
     * @see <a href="https://vk.com/dev/friends.getOnline">VK API</a>
     */
    fun getOnlineWithOnlineFromMobile(
        userId: Int? = null,
        listId: Int? = null,
        sortRandomly: Boolean = false,
        count: Int? = null,
        offset: Int = 0
    ): VkApiRequest<OnlineFriendsResponse>

    /**
     * @param[count] maximum value 1000
     * @see <a href="https://vk.com/dev/friends.getRecent">VK API</a>
     */
    fun getRecent(
        count: Int = 100
    ): VkApiRequest<List<Int>>

    /**
     * @param[count] maximum value 1000
     * @see <a href="https://vk.com/dev/friends.getRequests">VK API</a>
     */
    fun getOutgoingRequests(
        count: Int = 100,
        offset: Int = 0
    ): VkApiRequest<DefaultListResponse<Int>>

    /**
     * @see <a href="https://vk.com/dev/friends.getRequests">VK API</a>
     */
    fun getOutgoingRequestsWithMutual(
        offset: Int = 0
    ): VkApiRequest<DefaultListResponse<FriendRequest>>

    /**
     * @param[count] maximum value 1000
     * @see <a href="https://vk.com/dev/friends.getRequests">VK API</a>
     */
    fun getRequests(
        count: Int = 100,
        offset: Int = 0,
        sortByMutual: Boolean = false,
        needViewed: Boolean = false
    ): VkApiRequest<DefaultListResponse<Int>>

    /**
     * @see <a href="https://vk.com/dev/friends.getRequests">VK API</a>
     */
    fun getRequestsWithMutual(
        offset: Int = 0,
        sortByMutual: Boolean = false,
        needViewed: Boolean = false
    ): VkApiRequest<DefaultListResponse<FriendRequest>>

    /**
     * @param[count] maximum value 1000
     * @see <a href="https://vk.com/dev/friends.getRequests">VK API</a>
     */
    fun getSuggestedRequests(
        count: Int = 100,
        offset: Int = 0,
        sortByMutual: Boolean = false
    ): VkApiRequest<DefaultListResponse<Int>>

    /**
     * @param[count] maximum value 1000
     * @see <a href="https://vk.com/dev/friends.getRequests">VK API</a>
     */
    fun getSuggestedRequestsExtended(
        count: Int = 100,
        offset: Int = 0,
        sortByMutual: Boolean = false
    ): VkApiRequest<DefaultListResponse<FriendRequest>>

    /**
     * @see <a href="https://vk.com/dev/friends.getRequests">VK API</a>
     */
    fun getSuggestedRequestsWithMutual(
        offset: Int = 0,
        sortByMutual: Boolean = false
    ): VkApiRequest<DefaultListResponse<FriendRequest>>

    /**
     * @param[count] maximum value 500
     * @see <a href="https://vk.com/dev/friends.getSuggestions">VK API</a>
     */
    fun getSuggestions(
        count: Int = 500,
        offset: Int = 0,
        onlyWithMutual: Boolean = false,
        userFields: List<UserOptionalField> = DefaultMethodsParams.userFields,
        nameCase: NameCase = DefaultMethodsParams.userNameCase
    ): VkApiRequest<DefaultListResponse<User>>

    /**
     * @param[count] maximum value 1000
     * @see <a href="https://vk.com/dev/friends.getSuggestions">VK API</a>
     */
    fun search(
        query: String,
        userId: Int? = null,
        count: Int = 20,
        offset: Int = 0,
        userFields: List<UserOptionalField> = DefaultMethodsParams.userFields,
        nameCase: NameCase = DefaultMethodsParams.userNameCase
    ): VkApiRequest<DefaultListResponse<User>>

}