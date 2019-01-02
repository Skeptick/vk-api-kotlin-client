package tk.skeptick.vk.apiclient.methods.gifts

import tk.skeptick.vk.apiclient.MethodsContext
import tk.skeptick.vk.apiclient.VkApiClient
import tk.skeptick.vk.apiclient.VkApiRequest
import tk.skeptick.vk.apiclient.list
import tk.skeptick.vk.apiclient.methods.DefaultListResponse

class GiftsApi(override val client: VkApiClient)
    : GiftsApiUser, MethodsContext {

    override fun get(
        userId: Int?,
        count: Int?,
        offset: Int
    ): VkApiRequest<DefaultListResponse<UserGift>> =
        Methods.get.httpGet(
            "user_id" to userId,
            "count" to count,
            "offset" to offset
        ).withSerializer(list(UserGift.serializer()))

    private object Methods {
        private const val it = "gifts."
        const val get = it + "get"
    }

}