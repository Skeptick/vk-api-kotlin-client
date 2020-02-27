package tk.skeptick.vk.apiclient.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tk.skeptick.vk.apiclient.domain.AttachmentType
import tk.skeptick.vk.apiclient.domain.WallAttachment

@Serializable
data class Note(
    @SerialName("id") override val id: Int,
    @SerialName("owner_id") override val ownerId: Int,
    @SerialName("title") val title: String,
    @SerialName("text") val text: String,
    @SerialName("date") val date: Int,
    @SerialName("view_url") val viewUrl: String,
    @SerialName("comments") val commentsCount: Int? = null,
    @SerialName("read_comments") val readCommentsCount: Int? = null
) : WallAttachment {

    override val typeAttachment: AttachmentType get() = AttachmentType.NOTE

    override val accessKey: String? = null

}