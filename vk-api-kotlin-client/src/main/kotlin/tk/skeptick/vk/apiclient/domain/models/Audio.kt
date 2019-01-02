package tk.skeptick.vk.apiclient.domain.models

import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import tk.skeptick.vk.apiclient.BooleanInt
import tk.skeptick.vk.apiclient.domain.AttachmentType
import tk.skeptick.vk.apiclient.domain.MessageAttachment

@Serializable
data class Audio(
    @SerialName("id") override val id: Int,
    @SerialName("owner_id") override val ownerId: Int,
    @SerialName("date") val date: Int,
    @SerialName("artist") val artist: String,
    @SerialName("title") val title: String,
    @SerialName("duration") val duration: Int, // sec
    @SerialName("url") val url: String,
    @Optional @SerialName("genre_id") val genreId: Int? = null,
    @Optional @SerialName("lyrics_id") val lyricsId: Int? = null,
    @Optional @SerialName("album_id") val albumId: Int? = null,
    @Optional @SerialName("no_search") val isNoSearch: BooleanInt? = null,
    @Optional @SerialName("is_hq") val isHq: Boolean = false
) : MessageAttachment {

    @Transient override val typeAttachment get() = AttachmentType.AUDIO.value

}