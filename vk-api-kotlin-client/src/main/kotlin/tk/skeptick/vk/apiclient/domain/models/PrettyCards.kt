package tk.skeptick.vk.apiclient.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PrettyCards(
    @SerialName("cards") val cards: List<Card>) {

    @Serializable
    data class Card(
        @SerialName("card_id") val cardId: String,
        @SerialName("link_url") val linkUrl: String,
        @SerialName("link_url_target") val linkUrlTraget: String? = null,
        @SerialName("title") val title: String,
        @SerialName("images") val images: List<Image>,
        @SerialName("button") val button: Link.Button? = null,
        @SerialName("price") val price: String? = null,
        @SerialName("price_old") val priceOld: String? = null) {

        @Serializable
        data class Image(
            @SerialName("url") val url: String,
            @SerialName("width") val width: Int,
            @SerialName("height") val height: Int)

    }

}