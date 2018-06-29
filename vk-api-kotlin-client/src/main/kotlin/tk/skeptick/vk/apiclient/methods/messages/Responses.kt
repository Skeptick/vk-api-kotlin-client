package tk.skeptick.vk.apiclient.methods.messages

import kotlinx.serialization.*
import tk.skeptick.vk.apiclient.BooleanInt
import tk.skeptick.vk.apiclient.domain.models.*
import tk.skeptick.vk.apiclient.methods.ListResponse

@Serializable
data class ChatChangePhotoResponse(
    @SerialName("message_id") val messageId: Int,
    @SerialName("chat") val chat: Chat)

@Serializable
data class ChatPreview(
    @SerialName("preview") val preview: Preview,
    @Optional @SerialName("profiles") val profiles: List<User>? = null,
    @Optional @SerialName("groups") val groups: List<Community>? = null,
    @Optional @SerialName("emails") val emails: List<Email>? = null) {

    @Serializable
    data class Preview(
        @SerialName("admin_id") val adminId: Int,
        @SerialName("members") val members: List<Int>,
        @SerialName("title") val title: String,
        @Optional @SerialName("photo") val photo: SimplePhoto? = null,
        @Optional @SerialName("local_id") val localChatId: Int? = null)

    @Serializable
    data class Email(
        @SerialName("id") val id: Int,
        @SerialName("address") val address: String)

}

@Serializable
data class ConversationMember(
    @SerialName("member_id") val memberId: Int,
    @SerialName("invited_by") val invitedBy: Int,
    @SerialName("join_date") val joinDate: Int,
    @Optional @SerialName("is_admin") val isAdmin: Boolean = false)

@Serializable
data class ConversationsListResponse(
    @SerialName("count") override val count: Int,
    @SerialName("items") override val items: List<Item>,
    @Optional @SerialName("unread_count") val unreadCount: Int = 0,
    @Optional @SerialName("profiles") val profiles: List<User>? = null,
    @Optional @SerialName("groups") val groups: List<Community>? = null
) : ListResponse<ConversationsListResponse.Item> {

    @Serializable
    data class Item(
        @SerialName("conversation") val conversation: Conversation,
        @SerialName("last_message") val lastMessage: Message)

}

@Serializable
data class HistoryMessagesListResponse(
    @SerialName("count") override val count: Int,
    @SerialName("items") override val items: List<Message>,
    @SerialName("in_read") val inReadMessageId: Int,
    @SerialName("out_read") val outReadMessageId: Int,
    @Optional @SerialName("unread") val unreadCount: Int = 0,
    @Optional @SerialName("skipped") val skippedCount: Int = 0,
    @Optional @SerialName("profiles") val profiles: List<User>? = null,
    @Optional @SerialName("groups") val groups: List<Community>? = null
) : ListResponse<Message>

@Serializable
data class HistoryAttachmentsResponse(
    @SerialName("items") val items: List<Item>,
    @Optional @SerialName("next_from") val nextFrom: String? = null) {

    @Serializable
    data class Item(
        @SerialName("message_id") val messageId: Int,
        @SerialName("attachment") val attachment: Message.Attachment)

}

@Serializable
data class ChatInviteLink(
    @SerialName("link") val link: String)

@Serializable
data class LastActivityResponse(
    @SerialName("online") val isOnline: BooleanInt,
    @SerialName("time") val time: Int)

@Serializable
data class LongPollHistoryResponse(
    @SerialName("history") val history: List<List<Int>>,
    @SerialName("messages") val messages: ListResponse<Message>,
    @SerialName("profiles") val profiles: List<User>,
    @Optional @SerialName("groups") val communities: List<Community>? = null,
    @Optional @SerialName("new_pts") val newPts: Long? = null,
    @Optional @SerialName("more") val hasMore: BooleanInt = BooleanInt(false))

@Serializable
data class LongPollServerResponse(
    @SerialName("key") val key: String,
    @SerialName("server") val server: String,
    @SerialName("ts") val ts: Long,
    @Optional @SerialName("pts") val pts: Long? = null)

@Serializable
data class MessagesFromGroupAllowedResponse(
    @SerialName("is_allowed") val isAllowed: BooleanInt)

@Serializable
data class JoinChatByLinkResponse(
    @SerialName("chat_id") val chatId: Int)

@Serializable
data class SendBulkMessageResponse(
    @SerialName("peer_id") val peerId: Int,
    @Optional @SerialName("message_id") val messageId: Int? = null,
    @Optional @SerialName("error") val error: String? = null) // TODO check