package tk.skeptick.vk.apiclient.methods

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tk.skeptick.vk.apiclient.domain.VkApiError

@Serializable
data class VkApiResponse<T>(
    @SerialName("response") val response: T? = null,
    @SerialName("error") val error: VkApiError? = null)