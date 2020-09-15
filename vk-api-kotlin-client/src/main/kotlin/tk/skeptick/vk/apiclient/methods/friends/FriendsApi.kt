package tk.skeptick.vk.apiclient.methods.friends

import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import tk.skeptick.vk.apiclient.*
import tk.skeptick.vk.apiclient.domain.models.User
import tk.skeptick.vk.apiclient.methods.DefaultListResponse
import tk.skeptick.vk.apiclient.methods.FriendsOrder
import tk.skeptick.vk.apiclient.methods.NameCase
import tk.skeptick.vk.apiclient.methods.UserOptionalField

class FriendsApi(override val client: ApiClient)
    : FriendsApiUser, MethodsContext {

    override fun add(
        userId: Int,
        text: String?,
        declineRequest: Boolean
    ): VkApiRequest<AddFriendResponse> =
        Methods.add.httpPost(AddFriendResponse.Companion) {
            append("user_id", userId)
            append("text", text)
            append("follow", declineRequest.asInt())
        }

    override fun addList(
        name: String,
        userIds: List<Int>?
    ): VkApiRequest<Int> =
        Methods.addList.httpPost(Int.serializer()) {
            append("name", name)
            append("user_ids", userIds?.joinToString(","))
        }

    override fun areFriends(
        userIds: List<Int>,
        needSign: Boolean
    ): VkApiRequest<AreFriendResponse> =
        Methods.areFriends.httpPost(AreFriendResponse.serializer()) {
            append("user_ids", userIds.joinToString(","))
            append("need_sign", needSign.asInt())
        }

    override fun delete(
        userId: Int
    ): VkApiRequest<DeleteFriendResponse> =
        Methods.delete.httpGet(DeleteFriendResponse.serializer()) {
            append("user_id", userId)
        }

    override fun deleteAllRequests(): VkApiRequest<BooleanInt> =
        Methods.deleteAllRequests.httpGet(BooleanInt.serializer())

    override fun deleteList(
        listId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.deleteList.httpGet(BooleanInt.serializer()) {
            append("list_id", listId)
        }

    override fun edit(
        userId: Int,
        listIds: List<Int>?
    ): VkApiRequest<BooleanInt> =
        Methods.edit.httpPost(BooleanInt.serializer()) {
            append("user_id", userId)
            append("list_ids", listIds?.joinToString(","))
        }

    override fun editList(
        listId: Int,
        userIds: List<Int>,
        name: String?
    ): VkApiRequest<BooleanInt> =
        Methods.editList.httpPost(BooleanInt.serializer()) {
            append("list_id", listId)
            append("user_ids", userIds.joinToString(","))
            append("name", name)
        }

    override fun editList(
        listId: Int,
        addUserIds: List<Int>?,
        deleteUserIds: List<Int>?,
        name: String?
    ): VkApiRequest<BooleanInt> =
        Methods.editList.httpPost(BooleanInt.serializer()) {
            append("list_id", listId)
            append("add_user_ids", addUserIds?.joinToString(","))
            append("delete_user_ids", deleteUserIds?.joinToString(","))
            append("name", name)
        }

    override fun get(
        userId: Int?,
        order: FriendsOrder?,
        listId: Int?,
        count: Int,
        offset: Int,
        userFields: List<UserOptionalField>,
        nameCase: NameCase
    ): VkApiRequest<DefaultListResponse<User>> =
        Methods.get.httpPost(list(User.serializer())) {
            append("user_id", userId)
            append("order", order?.value)
            append("list_id", listId)
            append("count", count)
            append("offset", offset)
            append("fields", userFields.joinToString(",") { it.value })
            append("name_case", nameCase.value)
        }

    override fun getIds(
        userId: Int?,
        order: FriendsOrder?,
        listId: Int?,
        count: Int,
        offset: Int
    ): VkApiRequest<DefaultListResponse<Int>> =
        Methods.get.httpGet(list(Int.serializer())) {
            append("user_id", userId)
            append("order", order?.value)
            append("list_id", listId)
            append("count", count)
            append("offset", offset)
        }

    override fun getAppUsers(): VkApiRequest<List<Int>> =
        Methods.getAppUsers.httpGet(ListSerializer(Int.serializer()))

    override fun getByPhones(
        phones: List<String>,
        userFields: List<UserOptionalField>
    ): VkApiRequest<List<User>> =
        Methods.getByPhones.httpPost(ListSerializer(User.serializer())) {
            append("phones", phones.joinToString(","))
            append("fields", userFields.joinToString(",") { it.value })
        }

    override fun getLists(
        userId: Int?,
        withSystem: Boolean
    ): VkApiRequest<DefaultListResponse<FriendsList>> =
        Methods.getLists.httpGet(list(FriendsList.serializer())) {
            append("user_id", userId)
            append("return_system", withSystem.asInt())
        }

    override fun getMutual(
        targetUserId: Int,
        sourceUserId: Int?,
        sortRandomly: Boolean,
        count: Int?,
        offset: Int
    ): VkApiRequest<List<Int>> =
        Methods.getMutual.httpGet(ListSerializer(Int.serializer())) {
            append("target_uid", targetUserId)
            append("source_uid", sourceUserId)
            append("order", if (sortRandomly) "random" else null)
            append("count", count)
            append("offset", offset)
        }

    override fun getMutual(
        targetUserIds: List<Int>,
        sourceUserId: Int?,
        sortRandomly: Boolean,
        count: Int?,
        offset: Int
    ): VkApiRequest<List<MutualFriendsResponse>> =
        Methods.getMutual.httpPost(ListSerializer(MutualFriendsResponse.serializer())) {
            append("target_uids", targetUserIds.joinToString(","))
            append("source_uid", sourceUserId)
            append("order", if (sortRandomly) "random" else null)
            append("count", count)
            append("offset", offset)
        }

    override fun getOnline(
        userId: Int?,
        listId: Int?,
        sortRandomly: Boolean,
        count: Int?,
        offset: Int
    ): VkApiRequest<List<Int>> =
        Methods.getOnline.httpGet(ListSerializer(Int.serializer())) {
            append("user_id", userId)
            append("list_id", listId)
            append("order", if (sortRandomly) "random" else null)
            append("count", count)
            append("offset", offset)
        }

    override fun getOnlineWithOnlineFromMobile(
        userId: Int?,
        listId: Int?,
        sortRandomly: Boolean,
        count: Int?,
        offset: Int
    ): VkApiRequest<OnlineFriendsResponse> =
        Methods.getOnline.httpGet(OnlineFriendsResponse.serializer()) {
            append("user_id", userId)
            append("list_id", listId)
            append("online_mobile", 1)
            append("order", if (sortRandomly) "random" else null)
            append("count", count)
            append("offset", offset)
        }

    override fun getRecent(
        count: Int
    ): VkApiRequest<List<Int>> =
        Methods.getRecent.httpGet(ListSerializer(Int.serializer())) {
            append("count", count)
        }

    override fun getOutgoingRequests(
        count: Int,
        offset: Int
    ): VkApiRequest<DefaultListResponse<Int>> =
        Methods.getRequests.httpGet(list(Int.serializer())) {
            append("count", count)
            append("offset", offset)
            append("out", 1)
        }

    override fun getOutgoingRequestsWithMutual(
        offset: Int
    ): VkApiRequest<DefaultListResponse<FriendRequest>> =
        Methods.getRequests.httpGet(list(FriendRequest.serializer())) {
            append("offset", offset)
            append("need_mutual", 1)
            append("out", 1)
        }

    override fun getRequests(
        count: Int,
        offset: Int,
        sortByMutual: Boolean,
        needViewed: Boolean
    ): VkApiRequest<DefaultListResponse<Int>> =
        Methods.getRequests.httpGet(list(Int.serializer())) {
            append("count", count)
            append("offset", offset)
            append("sort", sortByMutual.asInt())
            append("need_viewed", needViewed.asInt())
        }

    override fun getRequestsWithMutual(
        offset: Int,
        sortByMutual: Boolean,
        needViewed: Boolean
    ): VkApiRequest<DefaultListResponse<FriendRequest>> =
        Methods.getRequests.httpGet(list(FriendRequest.serializer())) {
            append("offset", offset)
            append("sort", sortByMutual.asInt())
            append("need_viewed", needViewed.asInt())
            append("need_mutual", 1)
        }

    override fun getSuggestedRequests(
        count: Int,
        offset: Int,
        sortByMutual: Boolean
    ): VkApiRequest<DefaultListResponse<Int>> =
        Methods.getRequests.httpGet(list(Int.serializer())) {
            append("count", count)
            append("offset", offset)
            append("sort", sortByMutual.asInt())
            append("suggested", 1)
        }

    override fun getSuggestedRequestsExtended(
        count: Int,
        offset: Int,
        sortByMutual: Boolean
    ): VkApiRequest<DefaultListResponse<FriendRequest>> =
        Methods.getRequests.httpGet(list(FriendRequest.serializer())) {
            append("count", count)
            append("offset", offset)
            append("sort", sortByMutual.asInt())
            append("extended", 1)
            append("suggested", 1)
        }

    override fun getSuggestedRequestsWithMutual(
        offset: Int,
        sortByMutual: Boolean
    ): VkApiRequest<DefaultListResponse<FriendRequest>> =
        Methods.getRequests.httpGet(list(FriendRequest.serializer())) {
            append("offset", offset)
            append("sort", sortByMutual.asInt())
            append("extended", 1)
            append("need_mutual", 1)
            append("suggested", 1)
        }

    override fun getSuggestions(
        count: Int,
        offset: Int,
        onlyWithMutual: Boolean,
        userFields: List<UserOptionalField>,
        nameCase: NameCase
    ): VkApiRequest<DefaultListResponse<User>> =
        Methods.getSuggestions.httpPost(list(User.serializer())) {
            append("count", count)
            append("offset", offset)
            append("filter", if (onlyWithMutual) "mutual" else null)
            append("fields", userFields.joinToString(",") { it.value })
            append("name_case", nameCase.value)
        }

    override fun search(
        query: String,
        userId: Int?,
        count: Int,
        offset: Int,
        userFields: List<UserOptionalField>,
        nameCase: NameCase
    ): VkApiRequest<DefaultListResponse<User>> =
        Methods.search.httpPost(list(User.serializer())) {
            append("q", query)
            append("user_id", userId)
            append("count", count)
            append("offset", offset)
            append("fields", userFields.joinToString(",") { it.value })
            append("name_case", nameCase.value)
        }

    private object Methods {
        private const val it = "friends."
        const val add = it + "add"
        const val addList = it + "addList"
        const val areFriends = it + "areFriends"
        const val delete = it + "delete"
        const val deleteAllRequests = it + "deleteAllRequests"
        const val deleteList = it + "deleteList"
        const val edit = it + "edit"
        const val editList = it + "editList"
        const val get = it + "get"
        const val getAppUsers = it + "getAppUsers"
        const val getByPhones = it + "getByPhones"
        const val getLists = it + "getLists"
        const val getMutual = it + "getMutual"
        const val getOnline = it + "getOnline"
        const val getRecent = it + "getRecent"
        const val getRequests = it + "getRequests"
        const val getSuggestions = it + "getSuggestions"
        const val search = it + "search"
    }

}