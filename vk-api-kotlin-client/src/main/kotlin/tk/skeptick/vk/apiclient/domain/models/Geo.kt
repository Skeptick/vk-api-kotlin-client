package tk.skeptick.vk.apiclient.domain.models

import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Geo(
    @SerialName("type") val type: String,
    @SerialName("coordinates") val coordinates: String,
    @Optional @SerialName("place") val place: Place? = null) {

    @Serializable
    data class Place(
        @Optional @SerialName("id") val id: Int? = null,
        @Optional @SerialName("title") val title: String? = null,
        @Optional @SerialName("latitude") val latitude: Double? = null,
        @Optional @SerialName("longitude") val longitude: Double? = null,
        @Optional @SerialName("created") val createdDate: Int? = null,
        @Optional @SerialName("icon") val iconUrl: String? = null,
        @Optional @SerialName("country") val country: String? = null,
        @Optional @SerialName("city") val city: String? = null,

        // только для постов на стене и мест сообществ
        @Optional @SerialName("type") val type: Int? = null,
        @Optional @SerialName("group_id") val groupId: Int? = null,
        @Optional @SerialName("group_photo") val groupPhoto: String? = null,
        @Optional @SerialName("checkins") val checkinsCount: Int? = null,
        @Optional @SerialName("updated") val updatedTime: Int? = null,
        @Optional @SerialName("address") val address: Int? = null)

}