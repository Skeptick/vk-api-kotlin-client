package tk.skeptick.vk.apiclient.methods.utils

import tk.skeptick.vk.apiclient.BooleanInt
import tk.skeptick.vk.apiclient.VkApiRequest
import tk.skeptick.vk.apiclient.methods.DefaultListResponse

interface UtilsApiUser : UtilsApiCommon {

    /**
     * @see <a href="https://vk.com/dev/utils.deleteFromLastShortened">VK API</a>
     */
    fun deleteFromLastShortened(
        key: String
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/utils.getLastShortenedLinks">VK API</a>
     */
    fun getLastShortenedLinks(
        count: Int = 10,
        offset: Int = 0
    ): VkApiRequest<DefaultListResponse<ShortLink>>

}