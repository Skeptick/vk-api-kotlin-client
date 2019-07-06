package tk.skeptick.vk.apiclient.methods.fave

import tk.skeptick.vk.apiclient.BooleanInt
import tk.skeptick.vk.apiclient.DefaultMethodsParams
import tk.skeptick.vk.apiclient.VkApiRequest
import tk.skeptick.vk.apiclient.domain.models.*
import tk.skeptick.vk.apiclient.methods.DefaultListResponse
import tk.skeptick.vk.apiclient.methods.ExtendedListResponse
import tk.skeptick.vk.apiclient.methods.FavePagesType
import tk.skeptick.vk.apiclient.methods.ObjectField

interface FaveApiUser : FaveApiCommon {

    /**
     * @see <a href="https://vk.com/dev/fave.addArticle">VK API</a>
     */
    fun addArticle(
        url: String,
        ref: String? = null,
        trackCode: String? = null,
        source: String? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/fave.addPage">VK API</a>
     */
    fun addGroup(
        groupId: Int
    ): VkApiRequest<BooleanInt>

    /**
     * @param[link] internal links to vk.com
     * @see <a href="https://vk.com/dev/fave.addLink">VK API</a>
     */
    fun addLink(
        link: String
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/fave.addPage">VK API</a>
     */
    fun addUser(
        userId: Int
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/fave.addPost">VK API</a>
     */
    fun addPost(
        id: Int,
        ownerId: Int,
        accessKey: String? = null,
        ref: String? = null,
        trackCode: String? = null,
        source: String? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/fave.addProduct">VK API</a>
     */
    fun addProduct(
        id: Int,
        ownerId: Int,
        accessKey: String? = null,
        ref: String? = null,
        source: String? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @param[name] maximum length 50
     * @see <a href="https://vk.com/dev/fave.addTag">VK API</a>
     */
    fun addTag(
        name: String
    ): VkApiRequest<FaveTag>

    /**
     * @see <a href="https://vk.com/dev/fave.addVideo">VK API</a>
     */
    fun addVideo(
        id: Int,
        ownerId: Int,
        accessKey: String? = null,
        ref: String? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @param[name] maximum length 50
     * @see <a href="https://vk.com/dev/fave.editTag">VK API</a>
     */
    fun editTag(
        id: Int,
        name: String
    ): VkApiRequest<BooleanInt>

    /**
     * @param[count] maximum value 100
     * @see <a href="https://vk.com/dev/fave.get">VK API</a>
     */
    fun get(
        itemType: FaveItem.Type? = null,
        tagId: Int? = null,
        offset: Int = 0,
        count: Int = 50,
        isFromSnackbar: Boolean? = null,
        extended: Boolean = false,
        fields: List<ObjectField> = DefaultMethodsParams.userFields
    ): VkApiRequest<ExtendedListResponse<FaveItem>>

    /**
     * @param[count] maximum value 500
     * @see <a href="https://vk.com/dev/fave.getPages">VK API</a>
     */
    fun getPages(
        type: FavePagesType? = null,
        tagId: Int? = null,
        offset: Int = 0,
        count: Int = 50,
        fields: List<ObjectField> = DefaultMethodsParams.userFields
    ): VkApiRequest<DefaultListResponse<FavePage>>

    /**
     * @see <a href="https://vk.com/dev/fave.getTags">VK API</a>
     */
    fun getTags(): VkApiRequest<DefaultListResponse<FaveTag>>

    /**
     * @see <a href="https://vk.com/dev/fave.markSeen">VK API</a>
     */
    fun markSeen(): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/fave.removeArticle">VK API</a>
     */
    fun removeArticle(
        articleId: Int,
        ownerId: Int,
        ref: String? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/fave.removePage">VK API</a>
     */
    fun removeGroup(
        groupId: Int
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/fave.removeLink">VK API</a>
     */
    fun removeLink(
        linkId: String
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/fave.removePost">VK API</a>
     */
    fun removePost(
        id: Int,
        ownerId: Int
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/fave.removeProduct">VK API</a>
     */
    fun removeProduct(
        id: Int,
        ownerId: Int
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/fave.removeTag">VK API</a>
     */
    fun removeTag(
        id: Int
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/fave.removePage">VK API</a>
     */
    fun removeUser(
        userId: Int
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/fave.removeVideo">VK API</a>
     */
    fun removeVideo(
        id: Int,
        ownerId: Int
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/fave.reorderTags">VK API</a>
     */
    fun reorderTags(
        ids: List<Int>
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/fave.setPageTags">VK API</a>
     */
    fun setGroupTags(
        groupId: Int,
        tagIds: List<Int>? = null,
        ref: String? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/fave.setTags">VK API</a>
     */
    fun setItemTags(
        itemType: FaveItem.Type,
        itemId: Int,
        itemOwnerId: Int,
        tagIds: List<Int>? = null,
        ref: String? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/fave.setTags">VK API</a>
     */
    fun setLinkTags(
        linkId: String,
        linkUrl: String,
        tagIds: List<Int>? = null,
        ref: String? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/fave.setPageTags">VK API</a>
     */
    fun setUserTags(
        userId: Int,
        tagIds: List<Int>? = null,
        ref: String? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/fave.trackPageInteraction">VK API</a>
     */
    fun trackGroupInteraction(
        groupId: Int
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/fave.trackPageInteraction">VK API</a>
     */
    fun trackUserInteraction(
        userId: Int
    ): VkApiRequest<BooleanInt>

}