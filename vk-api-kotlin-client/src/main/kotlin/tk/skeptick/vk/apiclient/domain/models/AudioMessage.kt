package tk.skeptick.vk.apiclient.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tk.skeptick.vk.apiclient.domain.AttachmentType
import tk.skeptick.vk.apiclient.domain.CommentAttachment
import tk.skeptick.vk.apiclient.domain.MessageAttachment
import tk.skeptick.vk.apiclient.domain.WallAttachment

@Serializable
data class AudioMessage(
    @SerialName("id") override val id: Int,
    @SerialName("owner_id") override val ownerId: Int,
    @SerialName("duration") val duration: Int,
    @SerialName("waveform") val waveform: List<Int>,
    @SerialName("link_ogg") val linkOgg: String,
    @SerialName("link_mp3") val linkMp3: String,
    @SerialName("access_key") override val accessKey: String? = null
) : CommentAttachment, WallAttachment, MessageAttachment {

    override val typeAttachment: AttachmentType get() = AttachmentType.DOC

}