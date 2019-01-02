package tk.skeptick.vk.apiclient.methods.users

import kotlinx.serialization.*
import kotlinx.serialization.internal.StringSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonTreeMapper
import kotlinx.serialization.json.JsonTreeParser
import tk.skeptick.vk.apiclient.EnumStringSerializer
import tk.skeptick.vk.apiclient.SerializableEnum
import tk.skeptick.vk.apiclient.domain.models.Community
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

