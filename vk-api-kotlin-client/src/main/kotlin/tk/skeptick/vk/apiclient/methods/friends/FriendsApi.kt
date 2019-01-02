package tk.skeptick.vk.apiclient.methods.friends

import kotlinx.serialization.internal.IntSerializer
import kotlinx.serialization.list
import tk.skeptick.vk.apiclient.*
import tk.skeptick.vk.apiclient.domain.models.User
import tk.skeptick.vk.apiclient.methods.DefaultListResponse
import tk.skeptick.vk.apiclient.methods.FriendsOrder
import tk.skeptick.vk.apiclient.methods.NameCase
import tk.skeptick.vk.apiclient.methods.UserOptionalField

class FriendsApi(override val client: VkApiClient)
    : FriendsApiUser, MethodsContext {

    override fun add(
        userId: Int,
        text: String?,
        declineRequest: Boolean
    ): VkApiRequest<AddFriendResponse> =
        Methods.add.httpPost(
            "user_id" to userId,
            "text" to text,
            "follow" to declineRequest.asInt()
        ).withSerializer(AddFriendResponse.Companion)

    override fun addList(
        name: String,
        userIds: List<Int>?
    ): VkApiRequest<Int> =
        Methods.addList.httpPost(
            "name" to name,
            "user_ids" to userIds?.joinToString(",")
        ).withSerializer(IntSerializer)

    override fun areFriends(
        userIds: List<Int>,
        needSign: Boolean
    ): VkApiRequest<AreFriendResponse> =
        Methods.areFriends.httpPost(
            "user_ids" to userIds.joinToString(","),
            "need_sign" to needSign.asInt()
        ).withSerializer(AreFriendResponse.serializer())

    override fun delete(
        userId: Int
    ): VkApiRequest<DeleteFriendResponse> =
        Methods.delete.httpGet(
            "user_id" to userId
        ).withSerializer(DeleteFriendResponse.serializer())

    override fun deleteAllRequests(): VkApiRequest<BooleanInt> =
        Methods.deleteAllRequests.httpGet()
            .withSerializer(BooleanInt.serializer())

    override fun deleteList(
        listId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.deleteList.httpGet(
            "list_id" to listId
        ).withSerializer(BooleanInt.serializer())

    override fun edit(
        userId: Int,
        listIds: List<Int>?
    ): VkApiRequest<BooleanInt> =
        Methods.edit.httpPost(
            "user_id" to userId,
            "list_ids" to listIds?.joinToString(",")
        ).withSerializer(BooleanInt.serializer())

    override fun editList(
        listId: Int,
        userIds: List<Int>,
        name: String?
    ): VkApiRequest<BooleanInt> =
        Methods.editList.httpPost(
            "list_id" to listId,
            "user_ids" to userIds.joinToString(","),
            "name" to name
        ).withSerializer(BooleanInt.serializer())

    override fun editList(
        listId: Int,
        addUserIds: List<Int>?,
        deleteUserIds: List<Int>?,
        name: String?
    ): VkApiRequest<BooleanInt> =
        Methods.editList.httpPost(
            "list_id" to listId,
            "add_user_ids" to addUserIds?.joinToString(","),
            "delete_user_ids" to deleteUserIds?.joinToString(","),
            "name" to name
        ).withSerializer(BooleanInt.serializer())

    override fun get(
        userId: Int?,
        order: FriendsOrder?,
        listId: Int?,
        count: Int,
        offset: Int,
        userFields: List<UserOptionalField>,
        nameCase: NameCase
    ): VkApiRequest<DefaultListResponse<User>> =
        Methods.get.httpPost(
            "user_id" to userId,
            "order" to order?.value,
            "list_id" to listId,
            "count" to count,
            "offset" to offset,
            "fields" to userFields.joinToString(",") { it.value },
            "name_case" to nameCase.value
        ).withSerializer(list(User.serializer()))

    override fun getIds(
        userId: Int?,
        order: FriendsOrder?,
        listId: Int?,
        count: Int,
        offset: Int
    ): VkApiRequest<DefaultListResponse<Int>> =
        Methods.get.httpGet(
            "user_id" to userId,
            "order" to order?.value,
            "list_id" to listId,
            "count" to count,
            "offset" to offset
        ).withSerializer(list(IntSerializer))

    override fun getAppUsers(): VkApiRequest<List<Int>> =
        Methods.getAppUsers.httpGet()
            .withSerializer(IntSerializer.list)

    override fun getByPhones(
        phones: List<String>,
        userFields: List<UserOptionalField>
    ): VkApiRequest<List<User>> =
        Methods.getByPhones.httpPost(
            "phones" to phones.joinToString(","),
            "fields" to userFields.joinToString(",") { it.value }
        ).withSerializer(User.serializer().list)

    override fun getLists(
        userId: Int?,
        withSystem: Boolean
    ): VkApiRequest<DefaultListResponse<FriendsList>> =
        Methods.getLists.httpGet(
            "user_id" to userId,
            "return_system" to withSystem.asInt()
        ).withSerializer(list(FriendsList.serializer()))

    override fun getMutual(
        targetUserId: Int,
        sourceUserId: Int?,
        sortRandomly: Boolean,
        count: Int?,
        offset: Int
    ): VkApiRequest<List<Int>> =
        Methods.getMutual.httpGet(
            "target_uid" to targetUserId,
            "source_uid" to sourceUserId,
            "order" to if (sortRandomly) "random" else null,
            "count" to count,
            "offset" to offset
        ).withSerializer(IntSerializer.list)

    override fun getMutual(
        targetUserIds: List<Int>,
        sourceUserId: Int?,
        sortRandomly: Boolean,
        count: Int?,
        offset: Int
    ): VkApiRequest<List<MutualFriendsResponse>> =
        Methods.getMutual.httpPost(
            "target_uids" to targetUserIds.joinToString(","),
            "source_uid" to sourceUserId,
            "order" to if (sortRandomly) "random" else null,
            "count" to count,
            "offset" to offset
        ).withSerializer(MutualFriendsResponse.serializer().list)

    override fun getOnline(
        userId: Int?,
        listId: Int?,
        sortRandomly: Boolean,
        count: Int?,
        offset: Int
    ): VkApiRequest<List<Int>> =
        Methods.getOnline.httpGet(
            "user_id" to userId,
            "list_id" to listId,
            "order" to if (sortRandomly) "random" else null,
            "count" to count,
            "offset" to offset
        ).withSerializer(IntSerializer.list)

    override fun getOnlineWithOnlineFromMobile(
        userId: Int?,
        listId: Int?,
        sortRandomly: Boolean,
        count: Int?,
        offset: Int
    ): VkApiRequest<OnlineFriendsResponse> =
        Methods.getOnline.httpGet(
            "user_id" to userId,
            "list_id" to listId,
            "online_mobile" to 1,
            "order" to if (sortRandomly) "random" else null,
            "count" to count,
            "offset" to offset
        ).withSerializer(OnlineFriendsResponse.serializer())

    override fun getRecent(
        count: Int
    ): VkApiRequest<List<Int>> =
        Methods.getRecent.httpGet(
            "count" to count
        ).withSerializer(IntSerializer.list)

    override fun getOutgoingRequests(
        count: Int,
        offset: Int
    ): VkApiRequest<DefaultListResponse<Int>> =
        Methods.getRequests.httpGet(
            "count" to count,
            "offset" to offset,
            "out" to 1
        ).withSerializer(list(IntSerializer))

    override fun getOutgoingRequestsWithMutual(
        offset: Int
    ): VkApiRequest<DefaultListResponse<FriendRequest>> =
        Methods.getRequests.httpGet(
            "offset" to offset,
            "need_mutual" to 1,
            "out" to 1
        ).withSerializer(list(FriendRequest.serializer()))

    override fun getRequests(
        count: Int,
        offset: Int,
        sortByMutual: Boolean,
        needViewed: Boolean
    ): VkApiRequest<DefaultListResponse<Int>> =
        Methods.getRequests.httpGet(
            "count" to count,
            "offset" to offset,
            "sort" to sortByMutual.asInt(),
            "need_viewed" to needViewed.asInt()
        ).withSerializer(list(IntSerializer))

    override fun getRequestsWithMutual(
        offset: Int,
        sortByMutual: Boolean,
        needViewed: Boolean
    ): VkApiRequest<DefaultListResponse<FriendRequest>> =
        Methods.getRequests.httpGet(
            "offset" to offset,
            "sort" to sortByMutual.asInt(),
            "need_viewed" to needViewed.asInt(),
            "need_mutual" to 1
        ).withSerializer(list(FriendRequest.serializer()))

    override fun getSuggestedRequests(
        count: Int,
        offset: Int,
        sortByMutual: Boolean
    ): VkApiRequest<DefaultListResponse<Int>> =
        Methods.getRequests.httpGet(
            "count" to count,
            "offset" to offset,
            "sort" to sortByMutual.asInt(),
            "suggested" to 1
        ).withSerializer(list(IntSerializer))

    override fun getSuggestedRequestsExtended(
        count: Int,
        offset: Int,
        sortByMutual: Boolean
    ): VkApiRequest<DefaultListResponse<FriendRequest>> =
        Methods.getRequests.httpGet(
            "count" to count,
            "offset" to offset,
            "sort" to sortByMutual.asInt(),
            "extended" to 1,
            "suggested" to 1
        ).withSerializer(list(FriendRequest.serializer()))

    override fun getSuggestedRequestsWithMutual(
        offset: Int,
        sortByMutual: Boolean
    ): VkApiRequest<DefaultListResponse<FriendRequest>> =
        Methods.getRequests.httpGet(
            "offset" to offset,
            "sort" to sortByMutual.asInt(),
            "extended" to 1,
            "need_mutual" to 1,
            "suggested" to 1
        ).withSerializer(list(FriendRequest.serializer()))

    override fun getSuggestions(
        count: Int,
        offset: Int,
        onlyWithMutual: Boolean,
        userFields: List<UserOptionalField>,
        nameCase: NameCase
    ): VkApiRequest<DefaultListResponse<User>> =
        Methods.getSuggestions.httpPost(
            "count" to count,
            "offset" to offset,
            "filter" to if (onlyWithMutual) "mutual" else null,
            "fields" to userFields.joinToString(",") { it.value },
            "name_case" to nameCase.value
        ).withSerializer(list(User.serializer()))

    override fun search(
        query: String,
        userId: Int?,
        count: Int,
        offset: Int,
        userFields: List<UserOptionalField>,
        nameCase: NameCase
    ): VkApiRequest<DefaultListResponse<User>> =
        Methods.search.httpPost(
            "q" to query,
            "user_id" to userId,
            "count" to count,
            "offset" to offset,
            "fields" to userFields.joinToString(",") { it.value },
            "name_case" to nameCase.value
        ).withSerializer(list(User.serializer()))

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