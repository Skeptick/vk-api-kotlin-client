package tk.skeptick.vk.apiclient.methods.gifts

import tk.skeptick.vk.apiclient.VkApiRequest
import tk.skeptick.vk.apiclient.methods.DefaultListResponse

interface GiftsApiUser : GiftsApiCommon {

    /**
     * @see <a href="https://vk.com/dev/gifts.get">VK API</a>
     */
    fun get(
        userId: Int? = null,
        count: Int? = null,
        offset: Int = 0
    ): VkApiRequest<DefaultListResponse<UserGift>>

}