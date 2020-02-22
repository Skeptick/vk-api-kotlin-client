package tk.skeptick.vk.apiclient.domain.models

import kotlinx.serialization.*
import tk.skeptick.vk.apiclient.EnumStringSerializer
import tk.skeptick.vk.apiclient.SerializableEnum

@Serializable
data class Link(
    @SerialName("url") val url: String,
    @SerialName("title") val title: String,
    @SerialName("id") val id: String? = null,
    @SerialName("caption") val caption: String? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("photo") val photo: Photo? = null,
    @SerialName("video") val video: Video? = null,
    @SerialName("product") val product: Product? = null,
    @SerialName("button") val button: Button? = null,
    @SerialName("preview_page") val previewPage: String? = null,
    @SerialName("preview_url") val previewUrl: String? = null,
    @SerialName("is_favorite") val isFavorite: Boolean? = null) {

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
            @SerialName("type") val type: Type,
            @SerialName("target") val target: String? = null,
            @SerialName("group_id") val groupId: Int? = null) {

            @Serializable(with = Type.Companion::class)
            enum class Type(override val value: String) : SerializableEnum<String> {
                OPEN_URL("open_url"),
                JOIN_GROUP_AND_OPEN_URL("join_group_and_open_url");

                companion object : EnumStringSerializer<Type>(Type::class)
            }

        }

    }

}