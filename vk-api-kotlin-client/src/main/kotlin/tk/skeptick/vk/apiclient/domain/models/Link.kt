package tk.skeptick.vk.apiclient.domain.models

import kotlinx.serialization.*
import tk.skeptick.vk.apiclient.EnumStringSerializer
import tk.skeptick.vk.apiclient.SerializableEnum

@Serializable
data class Link(
    @SerialName("url") val url: String,
    @SerialName("title") val title: String,
    @Optional @SerialName("caption") val caption: String? = null,
    @Optional @SerialName("description") val description: String? = null,
    @Optional @SerialName("photo") val photo: String? = null,
    @Optional @SerialName("product") val product: Product? = null,
    @Optional @SerialName("button") val button: Button? = null,
    @Optional @SerialName("preview_page") val previewPage: String? = null,
    @Optional @SerialName("preview_url") val previewUrl: String? = null) {

    @Serializable
    data class Product(
        @SerialName("price") val price: Market.Price)

    @Serializable
    data class Button(
        @SerialName("title") val title: String,
        @SerialName("action") val action: Action) {

        @Serializable
        data class Action(
            @SerialName("url") val url: String,
            @SerialName("type") val type: Type) {

            @Serializable(with = Type.Companion::class)
            enum class Type(override val value: String) : SerializableEnum<String> {
                OPEN_URL("open_url");

                companion object : EnumStringSerializer<Type>(
                    clazz = Type::class,
                    values = enumValues()
                )
            }

        }

    }

}