package tk.skeptick.vk.apiclient.methods.fave

import tk.skeptick.vk.apiclient.BooleanInt
import tk.skeptick.vk.apiclient.VkApiRequest
import tk.skeptick.vk.apiclient.domain.models.*
import tk.skeptick.vk.apiclient.methods.DefaultListResponse
import tk.skeptick.vk.apiclient.methods.ExtendedListResponse

interface FaveApiUser : FaveApiCommon {

    /**
     * @see <a href="https://vk.com/dev/fave.addGroup">VK API</a>
     */
    fun addGroup(
        groupId: Int
    ): VkApiRequest<BooleanInt>

    /**
     * @param[link] internal links to vk.com
     * @see <a href="https://vk.com/dev/fave.addLink">VK API</a>
     */
    fun addLink(
        link: String,
        description: String? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/fave.addUser">VK API</a>
     */
    fun addUser(
        userId: Int
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/fave.getLinks">VK API</a>
     */
    fun getLinks(
        count: Int = 50,
        offset: Int = 0
    ): VkApiRequest<DefaultListResponse<FaveLink>>

    /**
     * @see <a href="https://vk.com/dev/fave.getMarketItems">VK API</a>
     */
    fun getMarketItems(
        count: Int = 50,
        offset: Int = 0,
        extended: Boolean = false
    ): VkApiRequest<DefaultListResponse<Market>>

    /**
     * @see <a href="https://vk.com/dev/fave.getPhotos">VK API</a>
     */
    fun getPhotos(
        count: Int = 50,
        offset: Int = 0,
        withPhotoSizes: Boolean = false
    ): VkApiRequest<DefaultListResponse<Photo>>


    /**
     * @param[count] maximum value 100
     * @see <a href="https://vk.com/dev/fave.getPosts">VK API</a>
     */
    fun getPosts(
        count: Int = 50,
        offset: Int = 0,
        extended: Boolean = false
    ): VkApiRequest<ExtendedListResponse<WallPost>>

    /**
     * @see <a href="https://vk.com/dev/fave.getUsers">VK API</a>
     */
    fun getUsers(
        count: Int = 50,
        offset: Int = 0
    ): VkApiRequest<DefaultListResponse<User>>

    /**
     * @see <a href="https://vk.com/dev/fave.getVideos">VK API</a>
     */
    fun getVideos(
        count: Int = 50,
        offset: Int = 0,
        extended: Boolean = false
    ): VkApiRequest<ExtendedListResponse<Video>>

    /**
     * @see <a href="https://vk.com/dev/fave.removeGroup">VK API</a>
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
     * @see <a href="https://vk.com/dev/fave.removeUser">VK API</a>
     */
    fun removeUser(
        userId: Int
    ): VkApiRequest<BooleanInt>

}