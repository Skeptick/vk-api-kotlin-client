package tk.skeptick.vk.apiclient.domain.models

import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import tk.skeptick.vk.apiclient.domain.AttachmentType
import tk.skeptick.vk.apiclient.domain.MessageAttachment

@Serializable
data class AudioMessage(
    @SerialName("id") override val id: Int,
    @SerialName("owner_id") override val ownerId: Int,
    @SerialName("duration") val duration: Int,
    @SerialName("waveform") val waveform: List<Int>,
    @SerialName("link_ogg") val linkOgg: String,
    @SerialName("link_mp3") val linkMp3: String,
    @Optional @SerialName("access_key") override val accessKey: String? = null
) : MessageAttachment {

    @Transient override val typeAttachment get() = AttachmentType.AUDIO_MESSAGE.value

}