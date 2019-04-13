package tk.skeptick.vk.apiclient.domain.models

import kotlinx.serialization.*
import tk.skeptick.vk.apiclient.domain.AttachmentType
import tk.skeptick.vk.apiclient.domain.MessageAttachment

@Serializable
data class Photo(
    @SerialName("id") override val id: Int,
    @SerialName("album_id") val albumId: Int,
    @SerialName("owner_id") override val ownerId: Int,
    @SerialName("sizes") val sizes: List<Size>,
    @SerialName("text") val description: String,
    @SerialName("date") val date: Int,
    @SerialName("user_id") val userId: Int? = null,
    @SerialName("height") val height: Int? = null,
    @SerialName("width") val width: Int? = null,
    @SerialName("access_key") override val accessKey: String? = null,
    @SerialName("post_id") val wallPostId: Int? = null
) : MessageAttachment {

    override val typeAttachment get() = AttachmentType.PHOTO.value
    val isInCommunity get() = userId == 100

    @Serializable
    data class Size(
        @SerialName("url") val src: String,
        @SerialName("width") val width: Int,
        @SerialName("height") val height: Int,
        @SerialName("type") val type: String? = null)

    @Serializable
    data class Album(
        @SerialName("id") val id: Int,
        @SerialName("owner_id") val ownerId: Int,
        @SerialName("title") val title: String,
        @SerialName("created") val createdDate: Int,
        @SerialName("updated") val updatedDate: Int,
        @SerialName("size") val size: Int,
        @SerialName("description") val description: String? = null,
        @SerialName("thumb") val thumb: Photo? = null)

}

