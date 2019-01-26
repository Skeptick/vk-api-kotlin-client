package tk.skeptick.vk.apiclient.methods.gifts

import tk.skeptick.vk.apiclient.*
import tk.skeptick.vk.apiclient.methods.DefaultListResponse

class GiftsApi(override val client: ApiClient)
    : GiftsApiUser, MethodsContext {

    override fun get(
        userId: Int?,
        count: Int?,
        offset: Int
    ): VkApiRequest<DefaultListResponse<UserGift>> =
        Methods.get.httpGet(list(UserGift.serializer())) {
            append("user_id", userId)
            append("count", count)
            append("offset", offset)
        }

    private object Methods {
        private const val it = "gifts."
        const val get = it + "get"
    }

}