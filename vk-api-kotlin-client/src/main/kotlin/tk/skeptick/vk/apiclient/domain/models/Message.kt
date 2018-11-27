package tk.skeptick.vk.apiclient.domain.models

import kotlinx.serialization.*
import tk.skeptick.vk.apiclient.BooleanInt
import tk.skeptick.vk.apiclient.EnumStringSerializer
import tk.skeptick.vk.apiclient.domain.MessagePayload
import tk.skeptick.vk.apiclient.SerializableEnum
import tk.skeptick.vk.apiclient.domain.*
import tk.skeptick.vk.apiclient.isChatPeerId

@Serializable
data class Message(
    @SerialName("id") val id: Int,
    @SerialName("peer_id") val peerId: Int,
    @SerialName("from_id") val fromId: Int,
    @SerialName("date") val date: Int,
    @SerialName("text") val text: String,
    @SerialName("conversation_message_id") val conversationMessageId: Int,
    @Optional @SerialName("out") val isOutbox: BooleanInt? = null,
    @Optional @SerialName("important") val isImportant: Boolean? = null,
    @Optional @SerialName("is_hidden") val isHidden: Boolean? = null,
    @Optional @SerialName("update_time") val updateTime: Int? = null,
    @Optional @SerialName("attachments") val attachments: List<Attachment>? = null,
    @Optional @SerialName("geo") val geo: Geo? = null,
    @Optional @SerialName("payload") val payload: MessagePayload? = null,
    @Optional @SerialName("fwd_messages") val forwardedMessages: List<Forward>? = null,
    @Optional @SerialName("action") val serviceAction: ServiceAction? = null,
    @Optional @SerialName("random_id") val randomId: Int? = null,
    @Optional @SerialName("chat_id") val chatId: Int? = null) {

    @Transient val isFromChat: Boolean get() = peerId.isChatPeerId
    @Transient val isServiceAction: Boolean get() = serviceAction != null

    @Serializable
    data class Forward(
        @SerialName("from_id") val userId: Int,
        @SerialName("date") val date: Int,
        @SerialName("text") val body: String,
        @SerialName("update_time") val updateTime: Int,
        @Optional @SerialName("geo") val geo: Geo? = null,
        @Optional @SerialName("attachments") val attachments: List<Attachment>? = null,
        @Optional @SerialName("fwd_messages") val forwardedMessages: List<Forward>? = null)

    @Serializable
    data class Pinned(
        @SerialName("from_id") val userId: Int,
        @SerialName("date") val date: Int,
        @SerialName("text") val body: String,
        @Optional @SerialName("geo") val geo: Geo? = null,
        @Optional @SerialName("attachments") val attachments: List<Attachment>? = null,
        @Optional @SerialName("fwd_messages") val forwardedMessages: List<Forward>? = null)

    @Serializable
    data class Attachment(
        @SerialName("type") val type: AttachmentType,
        @Optional @SerialName("photo") val photo: Photo? = null,
        @Optional @SerialName("video") val video: Video? = null,
        @Optional @SerialName("audio") val audio: Audio? = null,
        @Optional @SerialName("doc") val document: Document? = null,
        @Optional @SerialName("link") val link: Link? = null,
        @Optional @SerialName("market") val market: Market? = null,
        @Optional @SerialName("market_album") val marketAlbum: Market.Album? = null,
        @Optional @SerialName("wall") val wallPost: WallPost? = null,
        @Optional @SerialName("wall_reply") val wallReply: WallComment? = null,
        @Optional @SerialName("sticker") val sticker: Sticker? = null,
        @Optional @SerialName("gift") val gift: Gift? = null,
        @Optional @SerialName("graffiti") val graffiti: Graffiti? = null,
        @Optional @SerialName("audio_message") val audioMessage: AudioMessage? = null)

    @Serializable
    data class ServiceAction(
        @SerialName("type") val type: Type,
        @Optional @SerialName("member_id") val memberId: Int? = null,
        @Optional @SerialName("text") val conversationTitle: String? = null,
        @Optional @SerialName("email") val email: String? = null,
        @Optional @SerialName("photo") val photo: SimplePhoto? = null) {

        @Transient val isActionFromEmail: Boolean get() = memberId?.let { it < 0 } ?: false

        @Serializable(with = Type.Companion::class)
        enum class Type(override val value: String) : SerializableEnum<String> {
            CHAT_PHOTO_UPDATE("chat_photo_update"),
            CHAT_PHOTO_REMOVE("chat_photo_remove"),
            CHAT_CREATE("chat_create"),
            CHAT_TITLE_UPDATE("chat_title_update"),
            CHAT_INVITE_USER("chat_invite_user"),
            CHAT_KICK_USER("chat_kick_user"),
            CHAT_PIN_MESSAGE("chat_pin_message"),
            CHAT_UNPIN_MESSAGE("chat_unpin_message"),
            CHAT_INVITE_USER_BY_LINK("chat_invite_user_by_link");

            companion object : EnumStringSerializer<Type>(Type::class)
        }

    }

}





