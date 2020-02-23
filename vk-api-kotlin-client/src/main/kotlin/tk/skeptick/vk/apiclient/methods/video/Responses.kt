package tk.skeptick.vk.apiclient.methods.video

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tk.skeptick.vk.apiclient.BooleanInt
import tk.skeptick.vk.apiclient.domain.*
import tk.skeptick.vk.apiclient.domain.models.Video

@Serializable
data class AddVideoAlbumResponse(
    @SerialName("album_id") val albumId: Int)

@Serializable
data class VideoAlbum(
    @SerialName("id") val id: Int,
    @SerialName("owner_id") val ownerId: Int,
    @SerialName("title") val title: String,
    @SerialName("count") val count: Int? = null,
    @SerialName("updated_time") val updatedTime: Int? = null,
    @SerialName("image_blur") val imageBlur: BooleanInt? = null,
    @SerialName("is_system") val isSystem: BooleanInt? = null,
    @SerialName("image") val image: List<Video.VideoImage>? = null,
    @SerialName("privacy") val privacy: PrivacySettings? = null)

@Serializable
data class SaveVideoResponse(
    @SerialName("video_id") val videoId: Int,
    @SerialName("owner_id") override val ownerId: Int,
    @SerialName("upload_url") val uploadUrl: String,
    @SerialName("title") val title: String,
    @SerialName("description") val description: String,
    @SerialName("access_key") override val accessKey: String
) : CommentAttachment, WallAttachment, MessageAttachment {

    override val typeAttachment: AttachmentType get() = AttachmentType.VIDEO

    override val id: Int get() = videoId

}