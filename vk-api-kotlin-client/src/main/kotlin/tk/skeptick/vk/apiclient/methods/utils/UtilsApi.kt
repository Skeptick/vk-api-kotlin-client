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
        Methods.checkLink.httpGet(
            "url" to url
        ).withSerializer(CheckLinkResponse.serializer())

    override fun deleteFromLastShortened(
        key: String
    ): VkApiRequest<BooleanInt> =
        Methods.deleteFromLastShortened.httpGet(
            "key" to key
        ).withSerializer(BooleanInt.serializer())

    override fun getLastShortenedLinks(
        count: Int,
        offset: Int
    ): VkApiRequest<DefaultListResponse<ShortLink>> =
        Methods.getLastShortenedLinks.httpGet(
            "count" to count,
            "offset" to offset
        ).withSerializer(list(ShortLink.serializer()))

    override fun getLinkStats(
        key: String,
        accessKey: String?,
        interval: ShortLinkStatsInterval,
        intervalsCount: Int,
        extended: Boolean
    ): VkApiRequest<ShortLinkStats> =
        Methods.getLinkStats.httpGet(
            "key" to key,
            "access_key" to accessKey,
            "interval" to interval.value,
            "intervals_count" to intervalsCount,
            "extended" to extended.asInt()
        ).withSerializer(ShortLinkStats.serializer())

    override fun getServerTime(): VkApiRequest<Int> =
        Methods.getServerTime.httpGet()
            .withSerializer(IntSerializer)

    override fun getShortLink(
        url: String,
        private: Boolean
    ): VkApiRequest<ShortLink> =
        Methods.getShortLink.httpGet(
            "url" to url,
            "private" to private.asInt()
        ).withSerializer(ShortLink.serializer())

    override fun resolveScreenName(
        screenName: String
    ): VkApiRequest<ResolveScreenNameResponse> =
        Methods.resolveScreenName.httpGet(
            "screen_name" to screenName
        ).withSerializer(ResolveScreenNameResponse.serializer())

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