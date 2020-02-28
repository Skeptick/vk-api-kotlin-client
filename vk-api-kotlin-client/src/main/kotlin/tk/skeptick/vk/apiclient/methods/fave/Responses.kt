package tk.skeptick.vk.apiclient.methods.fave

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
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

    @Serializable
    enum class Type(val value: String) {
        @SerialName("post") POST("post"),
        @SerialName("video") VIDEO("video"),
        @SerialName("product") PRODUCT("product"),
        @SerialName("article") ARTICLE("article"),
        @SerialName("link") LINK("link")
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

    @Serializable
    enum class Type(val value: String) {
        @SerialName("user") USER("user"),
        @SerialName("group") GROUP("group")
    }

}