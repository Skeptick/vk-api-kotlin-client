package tk.skeptick.vk.apiclient.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tk.skeptick.vk.apiclient.EnumIntSerializer
import tk.skeptick.vk.apiclient.IntEnum

@Serializable(with = Language.Companion::class)
enum class Language(override val value: Int) : IntEnum {
    RUSSIAN(0),
    UKRAINIAN(1),
    BELORUSSIAN(2),
    ENGLISH(3),
    SPANISH(4),
    FINNISH(5),
    GERMAN(6),
    ITALIAN(7);

    companion object : EnumIntSerializer<Language>(Language::class, values())
}

@Serializable
enum class AttachmentType(val value: String) {
    @SerialName("photo") PHOTO("photo"),
    @SerialName("video") VIDEO("video"),
    @SerialName("audio") AUDIO("audio"),
    @SerialName("doc") DOC("doc"),
    @SerialName("link") LINK("link"),
    @SerialName("note") NOTE("note"),
    @SerialName("poll") POLL("poll"),
    @SerialName("page") PAGE("page"),
    @SerialName("album") ALBUM("album"),
    @SerialName("photos_list") PHOTOS_LIST("photos_list"),
    @SerialName("market") MARKET("market"),
    @SerialName("market_album") MARKET_ALBUM("market_album"),
    @SerialName("wall") WALL("wall"),
    @SerialName("wall_reply") WALL_REPLY("wall_reply"),
    @SerialName("sticker") STICKER("sticker"),
    @SerialName("gift") GIFT("gift"),
    @SerialName("money") MONEY("money"),
    @SerialName("graffiti") GRAFFITI("graffiti"),
    @SerialName("audio_message") AUDIO_MESSAGE("audio_message"),
    @SerialName("pretty_cards") PRETTY_CARDS("pretty_cards"),
    @SerialName("event") EVENT("event"),
    @SerialName("article") ARTICLE("article"),
    @SerialName("audio_playlist") AUDIO_PLAYLIST("audio_playlist"),
    @SerialName("story") STORY("story")
}