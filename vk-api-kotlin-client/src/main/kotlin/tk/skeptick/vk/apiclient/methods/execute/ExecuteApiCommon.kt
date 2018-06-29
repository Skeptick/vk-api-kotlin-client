package tk.skeptick.vk.apiclient.methods.execute

import kotlinx.serialization.KSerializer
import tk.skeptick.vk.apiclient.VkApiRequest

interface ExecuteApiCommon {

    operator fun <T : Any> invoke(
        code: CharSequence,
        serializer: KSerializer<T>
    ): VkApiRequest<T>

}