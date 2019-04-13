package tk.skeptick.vk.apiclient.domain

import kotlinx.serialization.Serializable
import tk.skeptick.vk.apiclient.EnumIntSerializer
import tk.skeptick.vk.apiclient.EnumStringSerializer
import tk.skeptick.vk.apiclient.SerializableEnum

@Serializable(with = Language.Companion::class)
enum class Language(override val value: Int) : SerializableEnum<Int> {
    RUSSIAN(0),
    UKRAINIAN(1),
    BELORUSSIAN(2),
    ENGLISH(3),
    SPANISH(4),
    FINNISH(5),
    GERMAN(6),
    ITALIAN(7);

    companion object : EnumIntSerializer<Language>(Language::class)
}

@Serializable(with = AttachmentType.Companion::class)
enum class AttachmentType(override val value: String) : SerializableEnum<String> {
    PHOTO("photo"),
    VIDEO("video"),
    AUDIO("audio"),
    DOC("doc"),
    LINK("link"),
    NOTE("note"),
    POLL("poll"),
    PAGE("page"),
    ALBUM("album"),
    PHOTOS_LIST("photos_list"),
    MARKET("market"),
    MARKET_ALBUM("market_album"),
    WALL("wall"),
    WALL_REPLY("wall_reply"),
    STICKER("sticker"),
    GIFT("gift"),
    MONEY("money"),
    GRAFFITI("graffiti"),
    AUDIO_MESSAGE("audio_message"),
    PRETTY_CARDS("pretty_cards");

    companion object : EnumStringSerializer<AttachmentType>(AttachmentType::class)
}