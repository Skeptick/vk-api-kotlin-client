package tk.skeptick.vk.apiclient.domain.models

import kotlinx.serialization.*
import tk.skeptick.vk.apiclient.BooleanInt
import tk.skeptick.vk.apiclient.domain.MessagePayload
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
    @SerialName("out") val isOutbox: BooleanInt? = null,
    @SerialName("important") val isImportant: Boolean? = null,
    @SerialName("is_hidden") val isHidden: Boolean? = null,
    @SerialName("update_time") val updateTime: Int? = null,
    @SerialName("attachments") val attachments: List<Attachment>? = null,
    @SerialName("geo") val geo: Geo? = null,
    @SerialName("payload") val payload: MessagePayload? = null,
    @SerialName("fwd_messages") val forwardedMessages: List<Forward>? = null,
    @SerialName("reply_message") val replyMessage: Forward? = null,
    @SerialName("action") val serviceAction: ServiceAction? = null,
    @SerialName("random_id") val randomId: Int? = null,
    @SerialName("ref") val ref: String? = null,
    @SerialName("ref_source") val refSource: String? = null,
    @SerialName("chat_id") val chatId: Int? = null,
    @SerialName("admin_author_id") val adminAuthorId: Int? = null,
    @SerialName("expire_ttl") val expireTtl: Int? = null,
    @SerialName("is_expired") val isExpired: Boolean? = null,
    @SerialName("ttl") val ttl: Int? = null) {

    val isFromChat: Boolean get() = peerId.isChatPeerId
    val isServiceAction: Boolean get() = serviceAction != null

    @Serializable
    data class Forward(
        @SerialName("id") val id: Int? = null,
        @SerialName("conversation_message_id") val conversationMessageId: Int? = null,
        @SerialName("peer_id") val peerId: Int? = null,
        @SerialName("from_id") val fromId: Int,
        @SerialName("date") val date: Int,
        @SerialName("text") val body: String,
        @SerialName("geo") val geo: Geo? = null,
        @SerialName("update_time") val updateTime: Int? = null,
        @SerialName("attachments") val attachments: List<Attachment>? = null,
        @SerialName("fwd_messages") val forwardedMessages: List<Forward>? = null)

    @Serializable
    data class Pinned(
        @SerialName("id") val id: Int,
        @SerialName("from_id") val fromId: Int,
        @SerialName("peer_id") val peerId: Int,
        @SerialName("date") val date: Int,
        @SerialName("text") val body: String,
        @SerialName("conversation_message_id") val conversationMessageId: Int? = null,
        @SerialName("geo") val geo: Geo? = null,
        @SerialName("attachments") val attachments: List<Attachment>? = null,
        @SerialName("fwd_messages") val forwardedMessages: List<Forward>? = null)

    @Serializable
    data class Attachment(
        @SerialName("type") val type: AttachmentType,
        @SerialName("photo") val photo: Photo? = null,
        @SerialName("video") val video: Video? = null,
        @SerialName("audio") val audio: Audio? = null,
        @SerialName("doc") val document: Document? = null,
        @SerialName("link") val link: Link? = null,
        @SerialName("market") val market: Market? = null,
        @SerialName("market_album") val marketAlbum: Market.Album? = null,
        @SerialName("poll") val poll: Poll? = null,
        @SerialName("wall") val wallPost: WallPost? = null,
        @SerialName("wall_reply") val wallReply: WallComment? = null,
        @SerialName("sticker") val sticker: Sticker? = null,
        @SerialName("gift") val gift: Gift? = null,
        @SerialName("graffiti") val graffiti: Graffiti? = null,
        @SerialName("audio_message") val audioMessage: AudioMessage? = null,
        @SerialName("story") val story: Story? = null)

    @Serializable
    data class Geo(
        @SerialName("type") val type: String,
        @SerialName("coordinates") val coordinates: Coordinates,
        @SerialName("place") val place: Place? = null) {

        @Serializable
        data class Coordinates(
            @SerialName("latitude") val latitude: Double,
            @SerialName("longitude") val longitude: Double)

        @Serializable
        data class Place(
            @SerialName("title") val title: String,
            @SerialName("country") val country: String,
            @SerialName("city") val city: String)

    }

    @Serializable
    data class ServiceAction(
        @SerialName("type") val type: Type,
        @SerialName("member_id") val memberId: Int? = null,
        @SerialName("text") val conversationTitle: String? = null,
        @SerialName("email") val email: String? = null,
        @SerialName("photo") val photo: SimplePhoto? = null) {

        val isActionFromEmail: Boolean get() = memberId?.let { it < 0 } ?: false

        @Serializable
        enum class Type(val value: String) {
            @SerialName("chat_photo_update") CHAT_PHOTO_UPDATE("chat_photo_update"),
            @SerialName("chat_photo_remove") CHAT_PHOTO_REMOVE("chat_photo_remove"),
            @SerialName("chat_create") CHAT_CREATE("chat_create"),
            @SerialName("chat_title_update") CHAT_TITLE_UPDATE("chat_title_update"),
            @SerialName("chat_invite_user") CHAT_INVITE_USER("chat_invite_user"),
            @SerialName("chat_kick_user") CHAT_KICK_USER("chat_kick_user"),
            @SerialName("chat_pin_message") CHAT_PIN_MESSAGE("chat_pin_message"),
            @SerialName("chat_unpin_message") CHAT_UNPIN_MESSAGE("chat_unpin_message"),
            @SerialName("chat_invite_user_by_link") CHAT_INVITE_USER_BY_LINK("chat_invite_user_by_link"),
            @SerialName("chat_screenshot") CHAT_SCREENSHOT("chat_screenshot")
        }

    }

}





