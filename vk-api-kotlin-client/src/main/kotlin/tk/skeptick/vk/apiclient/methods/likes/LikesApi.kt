package tk.skeptick.vk.apiclient.methods.likes

import kotlinx.serialization.internal.IntSerializer
import tk.skeptick.vk.apiclient.*
import tk.skeptick.vk.apiclient.domain.EntityWrapper
import tk.skeptick.vk.apiclient.methods.DefaultListResponse
import tk.skeptick.vk.apiclient.methods.LikeType
import tk.skeptick.vk.apiclient.methods.LikesFilter

class LikesApi(override val client: ApiClient)
    : LikesApiUser, MethodsContext {

    override fun add(
        type: LikeType,
        itemId: Int,
        ownerId: Int?,
        accessKey: String?
    ): VkApiRequest<ChangeLikeResponse> =
        Methods.add.httpGet(ChangeLikeResponse.serializer()) {
            append("type", type.value)
            append("owner_id", ownerId)
            append("item_id", itemId)
            append("access_key", accessKey)
        }

    override fun delete(
        type: LikeType,
        itemId: Int,
        ownerId: Int?
    ): VkApiRequest<ChangeLikeResponse> =
        Methods.delete.httpGet(ChangeLikeResponse.serializer()) {
            append("type", type.value)
            append("owner_id", ownerId)
            append("item_id", itemId)
        }

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
        Methods.getList.httpGet(list(EntityWrapper.serializer())) {
            append("type", type.value)
            append("item_id", itemId)
            append("owner_id", ownerId)
            append("page_url", pageUrl)
            append("filter", filter.value)
            append("friends_only", onlyFriends.asInt())
            append("extended", 1)
            append("offset", offset)
            append("count", count)
            append("skip_own", skipOwn.asInt())
        }

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
        Methods.getList.httpGet(list(IntSerializer)) {
            append("type", type.value)
            append("item_id", itemId)
            append("owner_id", ownerId)
            append("page_url", pageUrl)
            append("filter", filter.value)
            append("friends_only", onlyFriends.asInt())
            append("offset", offset)
            append("count", count)
            append("skip_own", skipOwn.asInt())
        }

    override fun isLiked(
        type: LikeType,
        itemId: Int,
        ownerId: Int?,
        userId: Int?
    ): VkApiRequest<IsLikedResponse> =
        Methods.isLiked.httpGet(IsLikedResponse.serializer()) {
            append("type", type.value)
            append("owner_id", ownerId)
            append("item_id", itemId)
            append("user_id", userId)
        }

    private object Methods {
        private const val it = "likes."
        const val add = it + "add"
        const val delete = it + "delete"
        const val getList = it + "getList"
        const val isLiked = it + "isLiked"
    }

}