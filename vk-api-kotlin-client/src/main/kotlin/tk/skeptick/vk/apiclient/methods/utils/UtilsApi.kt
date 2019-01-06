package tk.skeptick.vk.apiclient.methods.utils

import kotlinx.serialization.internal.IntSerializer
import tk.skeptick.vk.apiclient.*
import tk.skeptick.vk.apiclient.methods.DefaultListResponse
import tk.skeptick.vk.apiclient.methods.ShortLinkStatsInterval

class UtilsApi(override val client: VkApiClient)
    : UtilsApiUser, UtilsApiCommunity, MethodsContext {

    override fun checkLink(
        url: String
    ): VkApiRequest<CheckLinkResponse> =
        Methods.checkLink.httpGet(CheckLinkResponse.serializer()) {
            append("url", url)
        }

    override fun deleteFromLastShortened(
        key: String
    ): VkApiRequest<BooleanInt> =
        Methods.deleteFromLastShortened.httpGet(BooleanInt.serializer()) {
            append("key", key)
        }

    override fun getLastShortenedLinks(
        count: Int,
        offset: Int
    ): VkApiRequest<DefaultListResponse<ShortLink>> =
        Methods.getLastShortenedLinks.httpGet(list(ShortLink.serializer())) {
            append("count", count)
            append("offset", offset)
        }

    override fun getLinkStats(
        key: String,
        accessKey: String?,
        interval: ShortLinkStatsInterval,
        intervalsCount: Int,
        extended: Boolean
    ): VkApiRequest<ShortLinkStats> =
        Methods.getLinkStats.httpGet(ShortLinkStats.serializer()) {
            append("key", key)
            append("access_key", accessKey)
            append("interval", interval.value)
            append("intervals_count", intervalsCount)
            append("extended", extended.asInt())
        }

    override fun getServerTime(): VkApiRequest<Int> =
        Methods.getServerTime.httpGet(IntSerializer)

    override fun getShortLink(
        url: String,
        private: Boolean
    ): VkApiRequest<ShortLink> =
        Methods.getShortLink.httpGet(ShortLink.serializer()) {
            append("url", url)
            append("private", private.asInt())
        }

    override fun resolveScreenName(
        screenName: String
    ): VkApiRequest<ResolveScreenNameResponse> =
        Methods.resolveScreenName.httpGet(ResolveScreenNameResponse.serializer()) {
            append("screen_name", screenName)
        }

    private object Methods {
        private const val it = "utils."
        const val checkLink = it + "checkLink"
        const val deleteFromLastShortened = it + "deleteFromLastShortened"
        const val getLastShortenedLinks = it + "getLastShortenedLinks"
        const val getLinkStats = it + "getLinkStats"
        const val getServerTime = it + "getServerTime"
        const val getShortLink = it + "getShortLink"
        const val resolveScreenName = it + "resolveScreenName"
    }

}