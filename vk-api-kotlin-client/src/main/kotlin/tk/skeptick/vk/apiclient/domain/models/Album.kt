package tk.skeptick.vk.apiclient.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tk.skeptick.vk.apiclient.BooleanInt
import tk.skeptick.vk.apiclient.domain.AttachmentType
import tk.skeptick.vk.apiclient.domain.PrivacySettings
import tk.skeptick.vk.apiclient.domain.WallAttachment

@Serializable
data class Album(
    @SerialName("id") override val id: Int,
    @SerialName("thumb_id") val thumpId: Int,
    @SerialName("owner_id") override val ownerId: Int,
    @SerialName("title") val title: String,
    @SerialName("size") val size: Int,
    @SerialName("created") val created: Int? = null,
    @SerialName("updated") val updated: Int? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("privacy_view") val privacyView: PrivacySettings? = null,
    @SerialName("privacy_comment") val privacyComment: PrivacySettings? = null,
    @SerialName("sizes") val photoSizes: List<Photo.Size>? = null,
    @SerialName("upload_by_admins_only") val isUploadByAdminsOnly: BooleanInt? = null,
    @SerialName("comments_disabled") val isCommentsDisabled: BooleanInt? = null,
    @SerialName("can_upload") val canUpload: BooleanInt? = null,
    @SerialName("thumb") val thumb: Photo? = null,
    @SerialName("thumb_src") val thumbSrc: String? = null,
    @SerialName("thumb_is_last") val thumbIsLast: BooleanInt? = null
) : WallAttachment {

    override val accessKey: String? = null
    override val typeAttachment: AttachmentType get() = AttachmentType.ALBUM

}