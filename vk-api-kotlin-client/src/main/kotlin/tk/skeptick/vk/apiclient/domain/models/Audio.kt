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
    @SerialName("subtitle") val subtitle: String? = null,
    @SerialName("track_code") val trackCode: String? = null,
    @SerialName("album") val album: Album? = null,
    @SerialName("main_artists") val mainArtists: List<Artist>? = null,
    @SerialName("featured_artists") val featuredArtists: List<Artist>? = null,
    @SerialName("genre_id") val genreId: Int? = null,
    @SerialName("lyrics_id") val lyricsId: Int? = null,
    @SerialName("album_id") val albumId: Int? = null,
    @SerialName("no_search") val isNoSearch: BooleanInt? = null,
    @SerialName("is_hq") val isHq: Boolean = false,
    @SerialName("is_explicit") val isExplicit: Boolean? = null,
    @SerialName("is_licensed") val isLicensed: Boolean? = null,
    @SerialName("access_key") override val accessKey: String? = null
) : MessageAttachment {

    override val typeAttachment get() = AttachmentType.AUDIO.value

    @Serializable
    data class Artist(
        @SerialName("id") val id: String,
        @SerialName("domain") val domain: String,
        @SerialName("name") val name: String,
        @SerialName("is_followed") val isFollowed: Boolean,
        @SerialName("can_follow") val canFollow: Boolean)

    @Serializable
    data class Album(
        @SerialName("id") val id: Int,
        @SerialName("owner_id") val ownerId: Int,
        @SerialName("title") val title: String,
        @SerialName("thumb") val thumb: Playlist.Thumb? = null,
        @SerialName("access_key") val accessKey: String? = null)

}