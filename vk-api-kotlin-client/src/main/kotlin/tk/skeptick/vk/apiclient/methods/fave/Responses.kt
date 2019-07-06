package tk.skeptick.vk.apiclient.methods.fave

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tk.skeptick.vk.apiclient.EnumStringSerializer
import tk.skeptick.vk.apiclient.SerializableEnum
import tk.skeptick.vk.apiclient.domain.models.*

@Serializable
data class FaveTag(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String)

@Serializable
data class FaveItem(
    @SerialName("type") val type: Type,
    @SerialName("seen") val isSeen: Boolean,
    @SerialName("added_date") val addedDate: Int,
    @SerialName("tags") val tags: List<FaveTag>,
    @SerialName("post") val post: WallPost? = null,
    @SerialName("video") val video: Video? = null,
    @SerialName("product") val product: Market? = null,
    @SerialName("article") val article: Article? = null,
    @SerialName("link") val link: Link? = null) {

    @Serializable(with = Type.Companion::class)
    enum class Type(override val value: String) : SerializableEnum<String> {
        POST("post"),
        VIDEO("video"),
        PRODUCT("product"),
        ARTICLE("article"),
        LINK("link");

        companion object : EnumStringSerializer<Type>(Type::class)
    }

}

@Serializable
data class FavePage(
    @SerialName("type") val type: Type,
    @SerialName("description") val description: String,
    @SerialName("tags") val tags: List<FaveTag>,
    @SerialName("updated_date") val updatedDate: Int,
    @SerialName("user") val user: User? = null,
    @SerialName("page") val group: Community? = null) {

    @Serializable(with = Type.Companion::class)
    enum class Type(override val value: String) : SerializableEnum<String> {
        USER("user"),
        GROUP("group");

        companion object : EnumStringSerializer<Type>(Type::class)
    }

}