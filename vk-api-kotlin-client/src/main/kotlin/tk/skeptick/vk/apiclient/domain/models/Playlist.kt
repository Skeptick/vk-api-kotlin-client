package tk.skeptick.vk.apiclient.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tk.skeptick.vk.apiclient.EnumIntSerializer
import tk.skeptick.vk.apiclient.IntEnum
import tk.skeptick.vk.apiclient.domain.AttachmentType
import tk.skeptick.vk.apiclient.domain.MessageAttachment
import tk.skeptick.vk.apiclient.domain.WallAttachment

@Serializable
data class Playlist(
    @SerialName("id") override val id: Int,
    @SerialName("owner_id") override val ownerId: Int,
    @SerialName("type") val type: Type,
    @SerialName("title") val title: String,
    @SerialName("description") val description: String,
    @SerialName("genres") val genres: List<Genre>,
    @SerialName("count") val count: Int,
    @SerialName("followers") val followers: Int,
    @SerialName("plays") val plays: Int,
    @SerialName("create_time") val createTime: Int,
    @SerialName("update_time") val updateTime: Int,
    @SerialName("main_artists") val mainArtists: List<Audio.Artist>? = null,
    @SerialName("year") val year: Int? = null,
    @SerialName("is_following") val isFollowing: Boolean? = null,
    @SerialName("followed") val followed: Followed? = null,
    @SerialName("original") val original: Original? = null,
    @SerialName("thumbs") val thumbs: List<Thumb>? = null,
    @SerialName("photo") val photo: Thumb? = null,
    @SerialName("meta") val meta: Meta? = null,
    @SerialName("access_key") override val accessKey: String? = null
) : WallAttachment, MessageAttachment {

    override val typeAttachment: AttachmentType get() = AttachmentType.AUDIO_PLAYLIST

    @Serializable(with = Type.Companion::class)
    enum class Type(override val value: Int) : IntEnum {
        USER_PLAYLIST(0),
        OFFICIAL_ALBUM(1);

        companion object : EnumIntSerializer<Type>(Type::class, values())
    }

    @Serializable
    data class Genre(
        @SerialName("id") val id: Int,
        @SerialName("name") val name: String)

    @Serializable
    data class Followed(
        @SerialName("playlist_id") val playlistId: Int)

    @Serializable
    data class Original(
        @SerialName("owner_id") val ownerId: Int,
        @SerialName("playlist_id") val playlistId: Int)

    @Serializable
    data class Thumb(
        @SerialName("width") val width: Int,
        @SerialName("height") val height: Int,
        @SerialName("photo_34") val photo34: String,
        @SerialName("photo_68") val photo68: String,
        @SerialName("photo_135") val photo135: String,
        @SerialName("photo_270") val photo270: String,
        @SerialName("photo_300") val photo300: String,
        @SerialName("photo_600") val photo600: String,
        @SerialName("sizes") val sizes: List<Photo.Size>? = null)

    @Serializable
    data class Meta(
        @SerialName("view") val view: View) {

        @Serializable
        enum class View(val value: String) {
            @SerialName("compact") COMPACT("compact")
        }

    }

}