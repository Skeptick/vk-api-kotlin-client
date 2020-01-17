package tk.skeptick.vk.apiclient.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tk.skeptick.vk.apiclient.BooleanInt

@Serializable
data class Geo(
    @SerialName("type") val type: String,
    @SerialName("coordinates") val coordinates: String,
    @SerialName("place") val place: Place? = null,
    @SerialName("showmap") val showMap: BooleanInt? = null) {

    @Serializable
    data class Place(
        @SerialName("id") val id: Int? = null,
        @SerialName("title") val title: String? = null,
        @SerialName("latitude") val latitude: Double? = null,
        @SerialName("longitude") val longitude: Double? = null,
        @SerialName("created") val createdDate: Int? = null,
        @SerialName("updated") val updatedDate: Int? = null,
        @SerialName("icon") val iconUrl: String? = null,
        @SerialName("country") val country: String? = null,
        @SerialName("city") val city: String? = null,
        @SerialName("type") val type: Int? = null,
        @SerialName("group_id") val groupId: Int? = null,
        @SerialName("group_photo") val groupPhoto: String? = null,
        @SerialName("checkins") val checkinsCount: Int? = null,
        @SerialName("total_checkins") val totalCheckinsCount: Int? = null,
        @SerialName("address") val address: String? = null,
        @SerialName("is_deleted") val isDeleted: Boolean? = null)

}