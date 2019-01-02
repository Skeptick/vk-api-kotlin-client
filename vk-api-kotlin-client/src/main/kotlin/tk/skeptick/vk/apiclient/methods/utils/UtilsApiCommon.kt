package tk.skeptick.vk.apiclient.methods.utils

import tk.skeptick.vk.apiclient.VkApiRequest
import tk.skeptick.vk.apiclient.methods.ShortLinkStatsInterval

interface UtilsApiCommon {

    /**
     * @see <a href="https://vk.com/dev/utils.checkLink">VK API</a>
     */
    fun checkLink(
        url: String
    ): VkApiRequest<CheckLinkResponse>

    /**
     * @see <a href="https://vk.com/dev/utils.getLinkStats">VK API</a>
     */
    fun getLinkStats(
        key: String,
        accessKey: String? = null,
        interval: ShortLinkStatsInterval = ShortLinkStatsInterval.DAY,
        intervalsCount: Int = 1,
        extended: Boolean = false
    ): VkApiRequest<ShortLinkStats>

    /**
     * @see <a href="https://vk.com/dev/utils.getServerTime">VK API</a>
     */
    fun getServerTime(): VkApiRequest<Int>

    /**
     * @see <a href="https://vk.com/dev/utils.getShortLink">VK API</a>
     */
    fun getShortLink(
        url: String,
        private: Boolean = false
    ): VkApiRequest<ShortLink>

    /**
     * @see <a href="https://vk.com/dev/utils.resolveScreenName">VK API</a>
     */
    fun resolveScreenName(
        screenName: String
    ): VkApiRequest<ResolveScreenNameResponse>

}