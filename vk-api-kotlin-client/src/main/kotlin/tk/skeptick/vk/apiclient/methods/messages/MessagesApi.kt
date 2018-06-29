package tk.skeptick.vk.apiclient.methods.messages

import kotlinx.serialization.internal.IntSerializer
import kotlinx.serialization.list
import kotlinx.serialization.map
import tk.skeptick.vk.apiclient.*
import tk.skeptick.vk.apiclient.domain.*
import tk.skeptick.vk.apiclient.domain.models.Chat
import tk.skeptick.vk.apiclient.domain.models.Conversation
import tk.skeptick.vk.apiclient.domain.models.Message
import tk.skeptick.vk.apiclient.methods.ConversationFilter
import tk.skeptick.vk.apiclient.methods.DefaultListResponse
import tk.skeptick.vk.apiclient.methods.ExtendedListResponse
import tk.skeptick.vk.apiclient.methods.ObjectField
import java.text.SimpleDateFormat
import java.util.*

class MessagesApi(override val client: VkApiClient)
    : MessagesApiUser, MessagesApiCommunity, MethodsContext {

    override fun addChatUser(
        chatId: Int,
        userId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.addChatUser.httpGet(
            "chat_id" to chatId, 
            "user_id" to userId
        ).withSerializer(BooleanInt.serializer())

    override fun allowMessagesFromGroup(
        groupId: Int,
        key: String?
    ): VkApiRequest<BooleanInt> = 
        Methods.allowMessagesFromGroup.httpGet(
            "group_id" to groupId,
            "key" to key
        ).withSerializer(BooleanInt.serializer())
    
    override fun createChat(
        userIds: List<Int>,
        title: String
    ): VkApiRequest<Int> =
        Methods.createChat.httpGet(
            "user_ids" to userIds.joinToString(","),
            "title" to title
        ).withSerializer(IntSerializer)

    override fun delete(
        messageIds: List<Int>,
        markAsSpam: Boolean,
        deleteForAll: Boolean
    ): VkApiRequest<Map<Int, BooleanInt>> =
        Methods.delete.httpGet(
            "message_ids" to messageIds.joinToString(","),
            "spam" to markAsSpam.asInt(),
            "delete_for_all" to deleteForAll.asInt()
        ).withSerializer((IntSerializer to BooleanInt.serializer()).map)

    override fun deleteChatPhoto(
        chatId: Int
    ): VkApiRequest<ChatChangePhotoResponse> =
        Methods.deleteChatPhoto.httpGet(
            "chat_id" to chatId
        ).withSerializer(ChatChangePhotoResponse.serializer())

    override fun deleteConversation(
        peerId: Int,
        offset: Int,
        count: Int
    ): VkApiRequest<BooleanInt> =
        Methods.deleteConversation.httpGet(
            "peer_id" to peerId,
            "offset" to offset,
            "count" to count
        ).withSerializer(BooleanInt.serializer())

    override fun denyMessagesFromGroup(
        groupId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.denyMessagesFromGroup.httpGet(
            "group_id" to groupId
        ).withSerializer(BooleanInt.serializer())

    override fun edit(
        peerId: Int,
        messageId: Int,
        message: String?,
        latitude: Int?,
        longitude: Int?,
        attachments: List<MessageAttachment>?,
        keepForwardMessages: Boolean,
        keepSnippets: Boolean
    ): VkApiRequest<BooleanInt> = 
        Methods.edit.httpPost(
            "peer_id" to peerId,
            "message_id" to messageId,
            "message" to message,
            "lat" to latitude,
            "long" to longitude,
            "attachment" to attachments?.let(Companion::prepareAttachments),
            "keep_forward_messages" to keepForwardMessages.asInt(),
            "keep_snippets" to keepSnippets.asInt()
        ).withSerializer(BooleanInt.serializer())

    override fun editChat(
        chatId: Int,
        title: String
    ): VkApiRequest<BooleanInt> = 
        Methods.editChat.httpGet(
            "chat_id" to chatId,
            "title" to title
        ).withSerializer(BooleanInt.serializer())

    override fun getByConversationMessageId(
        peerId: Int,
        conversationMessageIds: List<Int>,
        extended: Boolean,
        fields: List<ObjectField>,
        groupId: Int?
    ): VkApiRequest<ExtendedListResponse<Message>> =
        Methods.getByConversationMessageId.httpGet(
            "peer_id" to peerId,
            "conversation_message_ids" to conversationMessageIds.joinToString(","),
            "extended" to extended.asInt(),
            "fields" to fields.joinToString(",") { it.value },
            "group_id" to groupId
        ).withSerializer(extendedList(Message.serializer()))

    override fun getById(
        messageIds: List<Int>,
        previewLength: Int,
        extended: Boolean,
        fields: List<ObjectField>
    ): VkApiRequest<ExtendedListResponse<Message>> =
        Methods.getById.httpGet(
            "message_ids" to messageIds.joinToString(","),
            "preview_length" to previewLength,
            "extended" to extended.asInt(),
            "fields" to fields.joinToString(",") { it.value }
        ).withSerializer(extendedList(Message.serializer()))

    override fun getChat(
        chatIds: List<Int>
    ): VkApiRequest<List<Chat>> =
        Methods.getChat.httpGet(
            "chat_ids" to chatIds.joinToString(",")
        ).withSerializer(Chat.serializer().list)

    override fun getChatPreview(
        link: String,
        fields: List<ObjectField>
    ): VkApiRequest<ChatPreview> =
        Methods.getChatPreview.httpGet(
            "link" to link,
            "fields" to fields.joinToString(",") { it.value }
        ).withSerializer(ChatPreview.serializer())

    override fun getConversationMembers(
        peerId: Int,
        fields: List<ObjectField>,
        groupId: Int?
    ): VkApiRequest<ExtendedListResponse<ConversationMember>> =
        Methods.getConversationMembers.httpGet(
            "peer_id" to peerId,
            "fields" to fields.joinToString(",") { it.value },
            "group_id" to groupId
        ).withSerializer(extendedList(ConversationMember.serializer()))

    override fun getConversations(
        offset: Int,
        count: Int,
        filter: ConversationFilter,
        startMessageId: Int?,
        extended: Boolean,
        fields: List<ObjectField>,
        groupId: Int?
    ): VkApiRequest<ConversationsListResponse> =
        Methods.getConversations.httpGet(
            "offset" to offset,
            "count" to count,
            "filter" to filter.value,
            "start_message_id" to startMessageId,
            "extended" to extended.asInt(),
            "fields" to fields.joinToString(",") { it.value },
            "group_id" to groupId
        ).withSerializer(ConversationsListResponse.serializer())

    override fun getConversationsById(
        peerIds: List<Int>,
        extended: Boolean,
        fields: List<ObjectField>,
        groupId: Int?
    ): VkApiRequest<ExtendedListResponse<Conversation>> =
        Methods.getConversationsById.httpGet(
            "peer_ids" to peerIds.joinToString(","),
            "extended" to extended.asInt(),
            "fields" to fields.joinToString(",") { it.value },
            "group_id" to groupId
        ).withSerializer(extendedList(Conversation.serializer()))

    override fun getHistory(
        peerId: Int,
        offset: Int,
        count: Int,
        startMessageId: Int,
        reverse: Boolean,
        extended: Boolean,
        fields: List<ObjectField>
    ): VkApiRequest<HistoryMessagesListResponse> = 
        Methods.getHistory.httpGet(
            "peer_id" to peerId,
            "offset" to offset,
            "count" to count,
            "start_message_id" to startMessageId,
            "rev" to reverse.asInt(),
            "extended" to extended.asInt(),
            "fields" to fields.joinToString(",") { it.value }
        ).withSerializer(HistoryMessagesListResponse.serializer())

    override fun getHistoryAttachments(
        peerId: Int,
        mediaType: AttachmentType,
        startFrom: Int?,
        count: Int,
        withPhotoSizes: Boolean,
        fields: List<ObjectField>
    ): VkApiRequest<HistoryAttachmentsResponse> = 
        Methods.getHistoryAttachments.httpGet(
            "peer_id" to peerId,
            "media_type" to mediaType.value,
            "start_from" to startFrom,
            "count" to count,
            "photo_sizes" to withPhotoSizes.asInt(),
            "fields" to fields.joinToString(",") { it.value }
        ).withSerializer(HistoryAttachmentsResponse.serializer())

    override fun getInviteLink(
        peerId: Int,
        generateNewLink: Boolean
    ): VkApiRequest<ChatInviteLink> =
        Methods.getInviteLink.httpGet(
            "peer_id" to peerId,
            "reset" to generateNewLink.asInt()
        ).withSerializer(ChatInviteLink.serializer())

    override fun getLastActivity(
        userId: Int
    ): VkApiRequest<LastActivityResponse> = 
        Methods.getLastActivity.httpGet(
            "user_id" to userId
        ).withSerializer(LastActivityResponse.serializer())

    override fun getLongPollHistory(
        ts: Long,
        pts: Long,
        previewLength: Int,
        withOnlineStatuses: Boolean,
        userFields: List<ObjectField>,
        eventsLimit: Int,
        messagesLimit: Int,
        maxMessageId: Int?,
        longPollVersion: Int
    ): VkApiRequest<LongPollHistoryResponse> = 
        Methods.getLongPollHistory.httpGet(
            "ts" to ts,
            "pts" to pts,
            "preview_length" to previewLength,
            "onlines" to withOnlineStatuses.asInt(),
            "fields" to userFields.joinToString(",") { it.value },
            "events_limit" to eventsLimit,
            "msgs_limit" to messagesLimit,
            "max_msg_id" to maxMessageId,
            "lp_version" to longPollVersion
        ).withSerializer(LongPollHistoryResponse.serializer())

    override fun getLongPollServer(
        needPts: Boolean,
        longPollVersion: Int
    ): VkApiRequest<LongPollServerResponse> = 
        Methods.getLongPollServer.httpGet(
            "need_pts" to needPts.asInt(),
            "lp_version" to longPollVersion
        ).withSerializer(LongPollServerResponse.serializer())

    override fun isMessagesFromGroupAllowed(
        groupId: Int,
        userId: Int
    ): VkApiRequest<MessagesFromGroupAllowedResponse> =
        Methods.isMessagesFromGroupAllowed.httpGet(
            "group_id" to groupId,
            "user_id" to userId
        ).withSerializer(MessagesFromGroupAllowedResponse.serializer())

    override fun joinChatByInviteLink(
        link: String
    ): VkApiRequest<JoinChatByLinkResponse> = 
        Methods.joinChatByInviteLink.httpGet(
            "link" to link
        ).withSerializer(JoinChatByLinkResponse.serializer())

    override fun markAsAnsweredConversation(
        peerId: Int,
        isAnswered: Boolean
    ): VkApiRequest<BooleanInt> = 
        Methods.markAsAnsweredConversation.httpGet(
            "peer_id" to peerId,
            "answered" to isAnswered.asInt()
        ).withSerializer(BooleanInt.serializer())

    override fun markAsImportant(
        messageIds: List<Int>,
        markAsImportant: Boolean
    ): VkApiRequest<List<Int>> = 
        Methods.markAsImportant.httpGet(
            "message_ids" to messageIds.joinToString(","),
            "important" to markAsImportant.asInt()
        ).withSerializer(IntSerializer.list)

    override fun markAsImportantConversation(
        peerId: Int,
        isImportant: Boolean
    ): VkApiRequest<BooleanInt> = 
        Methods.markAsImportantConversation.httpGet(
            "peer_id" to peerId,
            "important" to isImportant.asInt()
        ).withSerializer(BooleanInt.serializer())

    override fun markAsRead(
        peerId: Int,
        startMessageId: Int?
    ): VkApiRequest<BooleanInt> = 
        Methods.markAsRead.httpGet(
            "peer_id" to peerId,
            "start_message_id" to startMessageId
        ).withSerializer(BooleanInt.serializer())

    override fun pin(
        peerId: Int,
        messageId: Int
    ): VkApiRequest<Message.Pinned> =
        Methods.pin.httpGet(
            "peer_id" to peerId,
            "message_id" to messageId
        ).withSerializer(Message.Pinned.serializer())

    override fun removeChatUser(
        chatId: Int,
        userId: Int
    ): VkApiRequest<BooleanInt> = 
        Methods.removeChatUser.httpGet(
            "chat_id" to chatId,
            "user_id" to userId
        ).withSerializer(BooleanInt.serializer())

    override fun restore(
        messageId: Int
    ): VkApiRequest<BooleanInt> = 
        Methods.restore.httpGet(
            "message_id" to messageId
        ).withSerializer(BooleanInt.serializer())

    override fun search(
        query: String,
        peerId: Int?,
        maxDate: Date?,
        previewLength: Int,
        offset: Int,
        count: Int
    ): VkApiRequest<DefaultListResponse<Message>> =
        Methods.search.httpGet(
            "q" to query,
            "peer_id" to peerId,
            "date" to maxDate?.toDMYString(),
            "preview_length" to previewLength,
            "offset" to offset,
            "count" to count
        ).withSerializer(list(Message.serializer()))

    override fun searchConversations(
        query: String,
        count: Int,
        extended: Boolean,
        fields: List<ObjectField>,
        groupId: Int?
    ): VkApiRequest<ExtendedListResponse<Conversation>> =
        Methods.searchConversations.httpGet(
            "q" to query,
            "count" to count,
            "extended" to extended.asInt(),
            "fields" to fields.joinToString(",") { it.value },
            "group_id" to groupId
        ).withSerializer(extendedList(Conversation.serializer()))

    override fun send(
        peerId: Int,
        randomId: Int?,
        message: String?,
        lat: Int?,
        long: Int?,
        attachments: List<MessageAttachment>?,
        forwardedMessages: List<Int>?,
        stickerId: Int?,
        keyboard: Keyboard?,
        payload: MessagePayload?
    ): VkApiRequest<Int> =
        Methods.send.httpPost(
            "peer_id" to peerId,
            "random_id" to randomId,
            "message" to message,
            "lat" to lat,
            "long" to long,
            "attachment" to attachments?.let(Companion::prepareAttachments),
            "forward_messages" to forwardedMessages?.joinToString(","),
            "sticker_id" to stickerId,
            "keyboard" to keyboard?.let(json::stringify),
            "payload" to payload?.value
        ).withSerializer(IntSerializer)

    override fun send(
        peerId: Int,
        randomId: Int?,
        message: String?,
        lat: Int?,
        long: Int?,
        attachments: List<MessageAttachment>?,
        forwardedMessages: List<Int>?,
        stickerId: Int?
    ): VkApiRequest<Int> =
        Methods.send.httpPost(
            "peer_id" to peerId,
            "random_id" to randomId,
            "message" to message,
            "lat" to lat,
            "long" to long,
            "attachment" to attachments?.let(Companion::prepareAttachments),
            "forward_messages" to forwardedMessages?.joinToString(","),
            "sticker_id" to stickerId
        ).withSerializer(IntSerializer)

    override fun sendBulk(
        userIds: List<Int>,
        randomId: Int?,
        message: String?,
        lat: Int?,
        long: Int?,
        attachments: List<MessageAttachment>?,
        forwardedMessages: List<Int>?,
        stickerId: Int?,
        keyboard: Keyboard?,
        payload: MessagePayload?
    ): VkApiRequest<List<SendBulkMessageResponse>> =
        Methods.send.httpPost(
            "user_ids" to userIds.joinToString(","),
            "random_id" to randomId,
            "message" to message,
            "lat" to lat,
            "long" to long,
            "attachment" to attachments?.let(Companion::prepareAttachments),
            "forward_messages" to forwardedMessages?.joinToString(","),
            "sticker_id" to stickerId,
            "keyboard" to keyboard?.let(json::stringify),
            "payload" to payload?.value
        ).withSerializer(SendBulkMessageResponse.serializer().list)

    override fun setActivity(
        peerId: Int,
        type: String
    ): VkApiRequest<BooleanInt> = 
        Methods.setActivity.httpGet(
            "peer_id" to peerId,
            "type" to type
        ).withSerializer(BooleanInt.serializer())

    override fun setChatPhoto(
        file: String
    ): VkApiRequest<ChatChangePhotoResponse> = 
        Methods.setChatPhoto.httpGet(
            "file" to file
        ).withSerializer(ChatChangePhotoResponse.serializer())

    override fun unpin(
        peerId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.unpin.httpGet(
            "peer_id" to peerId
        ).withSerializer(BooleanInt.serializer())

    private companion object {

        private val dateFormatter =
            SimpleDateFormat("ddMMyyyy", Locale.getDefault())

        fun Date.toDMYString(): String =
            dateFormatter.format(this)

        fun prepareAttachments(
            attachments: List<MessageAttachment>
        ): String = attachments.joinToString(",") {
            buildString {
                append(it.typeAttachment)
                append(it.ownerId)
                append('_').append(it.id)
                it.accessKey?.let { append(it) }
            }
        }

    }

    private object Methods {
        private const val it = "messages."
        const val addChatUser = it + "addChatUser"
        const val allowMessagesFromGroup = it + "allowMessagesFromGroup"
        const val createChat = it + "createChat"
        const val delete = it + "delete"
        const val deleteChatPhoto = it + "deleteChatPhoto"
        const val deleteConversation = it + "deleteConversation"
        const val denyMessagesFromGroup = it + "denyMessagesFromGroup"
        const val edit = it + "edit"
        const val editChat = it + "editChat"
        const val getByConversationMessageId = it + "getByConversationMessageId"
        const val getById = it + "getById"
        const val getChat = it + "getChat"
        const val getChatPreview = it + "getChatPreview"
        const val getConversationMembers = it + "getConversationMembers"
        const val getConversations = it + "getConversations"
        const val getConversationsById = it + "getConversationsById"
        const val getHistory = it + "getHistory"
        const val getHistoryAttachments = it + "getHistoryAttachments"
        const val getInviteLink = it + "getInviteLink"
        const val getLastActivity = it + "getLastActivity"
        const val getLongPollHistory = it + "getLongPollHistory"
        const val getLongPollServer = it + "getLongPollServer"
        const val isMessagesFromGroupAllowed = it + "isMessagesFromGroupAllowed"
        const val joinChatByInviteLink = it + "joinChatByInviteLink"
        const val markAsAnsweredConversation = it + "markAsAnsweredConversation"
        const val markAsImportant = it + "markAsImportant"
        const val markAsImportantConversation = it + "markAsImportantConversation"
        const val markAsRead = it + "markAsRead"
        const val pin = it + "pin"
        const val removeChatUser = it + "removeChatUser"
        const val restore = it + "restore"
        const val search = it + "search"
        const val searchConversations = it + "searchConversations"
        const val send = it + "send"
        const val setActivity = it + "setActivity"
        const val setChatPhoto = it + "setChatPhoto"
        const val unpin = it + "unpin"
    }

}