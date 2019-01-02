package tk.skeptick.vk.apiclient.methods.likes

import kotlinx.serialization.internal.IntSerializer
import tk.skeptick.vk.apiclient.*
import tk.skeptick.vk.apiclient.domain.EntityWrapper
import tk.skeptick.vk.apiclient.methods.DefaultListResponse
import tk.skeptick.vk.apiclient.methods.LikeType
import tk.skeptick.vk.apiclient.methods.LikesFilter

class LikesApi(override val client: VkApiClient)
    : LikesApiUser, MethodsContext {

    override fun add(
        type: LikeType,
        itemId: Int,
        ownerId: Int?,
        accessKey: String?
    ): VkApiRequest<ChangeLikeResponse> =
        Methods.add.httpGet(
            "type" to type.value,
            "owner_id" to ownerId,
            "item_id" to itemId,
            "access_key" to accessKey
        ).withSerializer(ChangeLikeResponse.serializer())

    override fun delete(
        type: LikeType,
        itemId: Int,
        ownerId: Int?
    ): VkApiRequest<ChangeLikeResponse> =
        Methods.delete.httpGet(
            "type" to type.value,
            "owner_id" to ownerId,
            "item_id" to itemId
        ).withSerializer(ChangeLikeResponse.serializer())

    override fun getList(
        type: LikeType,
        itemId: Int,
        ownerId: Int?,
        pageUrl: String?,
        filter: LikesFilter,
        onlyFriends: Boolean,
        offset: Int,
        count: Int,
        skipOwn: Boolean
    ): VkApiRequest<DefaultListResponse<EntityWrapper>> =
        Methods.getList.httpGet(
            "type" to type.value,
            "item_id" to itemId,
            "owner_id" to ownerId,
            "page_url" to pageUrl,
            "filter" to filter.value,
            "friends_only" to onlyFriends.asInt(),
            "extended" to 1,
            "offset" to offset,
            "count" to count,
            "skip_own" to skipOwn.asInt()
        ).withSerializer(list(EntityWrapper.serializer()))

    override fun getListIds(
        type: LikeType,
        itemId: Int,
        ownerId: Int?,
        pageUrl: String?,
        filter: LikesFilter,
        onlyFriends: Boolean,
        offset: Int,
        count: Int,
        skipOwn: Boolean
    ): VkApiRequest<DefaultListResponse<Int>> =
        Methods.getList.httpGet(
            "type" to type.value,
            "item_id" to itemId,
            "owner_id" to ownerId,
            "page_url" to pageUrl,
            "filter" to filter.value,
            "friends_only" to onlyFriends.asInt(),
            "offset" to offset,
            "count" to count,
            "skip_own" to skipOwn.asInt()
        ).withSerializer(list(IntSerializer))

    override fun isLiked(
        type: LikeType,
        itemId: Int,
        ownerId: Int?,
        userId: Int?
    ): VkApiRequest<IsLikedResponse> =
        Methods.isLiked.httpGet(
            "type" to type.value,
            "owner_id" to ownerId,
            "item_id" to itemId,
            "user_id" to userId
        ).withSerializer(IsLikedResponse.serializer())

    private object Methods {
        private const val it = "likes."
        const val add = it + "add"
        const val delete = it + "delete"
        const val getList = it + "getList"
        const val isLiked = it + "isLiked"
    }

}