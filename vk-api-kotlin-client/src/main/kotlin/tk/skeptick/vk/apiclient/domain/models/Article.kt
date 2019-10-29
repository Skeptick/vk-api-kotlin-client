package tk.skeptick.vk.apiclient.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tk.skeptick.vk.apiclient.EnumStringSerializer
import tk.skeptick.vk.apiclient.SerializableEnum

@Serializable
data class Article (
    @SerialName("id") val id: Int,
    @SerialName("owner_id") val ownerId: Int,
    @SerialName("owner_name") val ownerName: String,
    @SerialName("owner_photo") val ownerPhoto: String,
    @SerialName("state") val state: State,
    @SerialName("can_report") val canReport: Boolean,
    @SerialName("is_favorite") val isFavorite: Boolean,
    @SerialName("published_date") val publishedDate: Int? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("subtitle") val subtitle: String? = null,
    @SerialName("views") val views: Int? = null,
    @SerialName("shares") val shares: Int? = null,
    @SerialName("url") val url: String? = null,
    @SerialName("view_url") val viewUrl: String? = null,
    @SerialName("photo") val photo: Photo? = null,
    @SerialName("access_key") val accessKey: String? = null) {

    @Serializable(with = State.Companion::class)
    enum class State(override val value: String) : SerializableEnum<String> {
        AVAILABLE("available"),
        PROTECTED("protected");

        companion object : EnumStringSerializer<State>(State::class)
    }

}