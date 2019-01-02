package tk.skeptick.vk.apiclient.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CropPhoto(
    @SerialName("photo") val photo: Photo,
    @SerialName("crop") val crop: CropRect,
    @SerialName("rect") val rect: CropRect) {

    @Serializable
    data class CropRect(
        @SerialName("x") val x: Double,
        @SerialName("y") val y: Double,
        @SerialName("x2") val x2: Double,
        @SerialName("y2") val y2: Double)

}