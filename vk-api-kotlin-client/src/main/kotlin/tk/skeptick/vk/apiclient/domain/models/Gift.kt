package tk.skeptick.vk.apiclient.domain.models

import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Gift(
    @SerialName("id") val id: Int,
    @SerialName("thumb_256") val thumb256: String,
    @SerialName("thumb_96") val thumb96: String,
    @SerialName("thumb_48") val thumb48: String,
    @Optional @SerialName("stickers_product_id") val stickerProductId: Int? = null) {

    @Transient val isStickerPack: Boolean get() = stickerProductId != 0

}