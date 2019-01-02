package tk.skeptick.vk.apiclient.methods.likes

import tk.skeptick.vk.apiclient.VkApiRequest
import tk.skeptick.vk.apiclient.domain.EntityWrapper
import tk.skeptick.vk.apiclient.methods.DefaultListResponse
import tk.skeptick.vk.apiclient.methods.LikeType
import tk.skeptick.vk.apiclient.methods.LikesFilter

interface LikesApiUser : LikesApiCommon {

    /**
     * @see <a href="https://vk.com/dev/likes.add">VK API</a>
     */
    fun add(
        type: LikeType,
        itemId: Int,
        ownerId: Int? = null,
        accessKey: String? = null
    ): VkApiRequest<ChangeLikeResponse>

    /**
     * @see <a href="https://vk.com/dev/likes.delete">VK API</a>
     */
    fun delete(
        type: LikeType,
        itemId: Int,
        ownerId: Int? = null
    ): VkApiRequest<ChangeLikeResponse>

    /**
     * @param[count] maximum is 1000, if [onlyFriends] is false, otherwise 100
     * @see <a href="https://vk.com/dev/likes.getList">VK API</a>
     */
    fun getList(
        type: LikeType,
        itemId: Int,
        ownerId: Int? = null,
        pageUrl: String? = null,
        filter: LikesFilter = LikesFilter.LIKES,
        onlyFriends: Boolean = false,
        offset: Int = 0,
        count: Int = 100,
        skipOwn: Boolean = false
    ): VkApiRequest<DefaultListResponse<EntityWrapper>>

    /**
     * @param[count] maximum is 1000, if [onlyFriends] is false, otherwise 100
     * @see <a href="https://vk.com/dev/likes.getList">VK API</a>
     */
    fun getListIds(
        type: LikeType,
        itemId: Int,
        ownerId: Int? = null,
        pageUrl: String? = null,
        filter: LikesFilter = LikesFilter.LIKES,
        onlyFriends: Boolean = false,
        offset: Int = 0,
        count: Int = 100,
        skipOwn: Boolean = false
    ): VkApiRequest<DefaultListResponse<Int>>

    /**
     * @see <a href="https://vk.com/dev/likes.isLiked">VK API</a>
     */
    fun isLiked(
        type: LikeType,
        itemId: Int,
        ownerId: Int? = null,
        userId: Int? = null
    ): VkApiRequest<IsLikedResponse>

}