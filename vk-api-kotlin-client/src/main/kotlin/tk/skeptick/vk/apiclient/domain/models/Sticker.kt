package tk.skeptick.vk.apiclient.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Sticker(
    @SerialName("product_id") val productId: Int,
    @SerialName("sticker_id") val stickerId: Int,
    @SerialName("images") val images: List<Image>,
    @SerialName("images_with_background") val imagesWithBackground: List<Image>) {

    @Serializable
    data class Image(
        @SerialName("url") val url: String,
        @SerialName("width") val width: Int, // px
        @SerialName("height") val height: Int) // px

}