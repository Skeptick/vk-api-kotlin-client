package tk.skeptick.vk.apiclient.methods

import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tk.skeptick.vk.apiclient.domain.VkApiError

@Serializable
data class VkApiResponse<T>(
    @Optional @SerialName("response") val response: T? = null,
    @Optional @SerialName("error") val error: VkApiError? = null)