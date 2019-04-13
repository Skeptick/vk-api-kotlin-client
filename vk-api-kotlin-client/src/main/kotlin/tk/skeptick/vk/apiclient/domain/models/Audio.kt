package tk.skeptick.vk.apiclient.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
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
    @SerialName("genre_id") val genreId: Int? = null,
    @SerialName("lyrics_id") val lyricsId: Int? = null,
    @SerialName("album_id") val albumId: Int? = null,
    @SerialName("no_search") val isNoSearch: BooleanInt? = null,
    @SerialName("is_hq") val isHq: Boolean = false
) : MessageAttachment {

    override val typeAttachment get() = AttachmentType.AUDIO.value

}