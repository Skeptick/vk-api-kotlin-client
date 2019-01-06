package tk.skeptick.vk.apiclient.methods.users

import kotlinx.serialization.*
import tk.skeptick.vk.apiclient.domain.models.User
import tk.skeptick.vk.apiclient.methods.DefaultListResponse
import tk.skeptick.vk.apiclient.methods.ListResponse

@Serializable
data class NearbyUsersListResponse(
    @SerialName("count") override val count: Int,
    @SerialName("items") override val items: List<User>,
    @SerialName("time_interval") val timeInterval: Int
) : ListResponse<User>

@Serializable
data class SubscriptionsResponse(
    @SerialName("users") val users: DefaultListResponse<Int>,
    @SerialName("groups") val groups: DefaultListResponse<Int>)

