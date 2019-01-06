package tk.skeptick.vk.apiclient.methods.execute

import kotlinx.serialization.KSerializer
import tk.skeptick.vk.apiclient.MethodsContext
import tk.skeptick.vk.apiclient.VkApiClient
import tk.skeptick.vk.apiclient.VkApiRequest

class ExecuteApi(override val client: VkApiClient)
    : ExecuteApiUser, ExecuteApiCommunity, MethodsContext {

    override fun <T : Any> invoke(
        code: CharSequence,
        serializer: KSerializer<T>
    ): VkApiRequest<T> =
        Methods.execute.httpPost(serializer) {
            append("code", code.toString())
        }

    private object Methods {
        const val execute = "execute"
    }

}