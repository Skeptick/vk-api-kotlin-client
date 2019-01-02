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
        Methods.createChat.httpPost(
            "user_ids" to userIds.joinToString(","),
            "title" to title
        ).withSerializer(IntSerializer)

    override fun delete(
        messageIds: List<Int>,
        markAsSpam: Boolean,
        deleteForAll: Boolean,
        groupId: Int?
    ): VkApiRequest<Map<Int, BooleanInt>> =
        Methods.delete.httpPost(
            "message_ids" to messageIds.joinToString(","),
            "spam" to markAsSpam.asInt(),
            "delete_for_all" to deleteForAll.asInt(),
            "group_id" to groupId
        ).withSerializer((IntSerializer to BooleanInt.serializer()).map)

    override fun delete(
        messageIds: List<Int>,
        markAsSpam: Boolean,
        deleteForAll: Boolean
    ): VkApiRequest<Map<Int, BooleanInt>> = delete(
        messageIds = messageIds,
        markAsSpam = markAsSpam,
        deleteForAll = deleteForAll,
        groupId = null
    )

    override fun deleteChatPhoto(
        chatId: Int,
        groupId: Int?
    ): VkApiRequest<ChatChangePhotoResponse> =
        Methods.deleteChatPhoto.httpGet(
            "chat_id" to chatId,
            "group_id" to groupId
        ).withSerializer(ChatChangePhotoResponse.serializer())

    override fun deleteChatPhoto(
        chatId: Int
    ): VkApiRequest<ChatChangePhotoResponse> = deleteChatPhoto(
        chatId = chatId,
        groupId = null
    )

    override fun deleteConversation(
        peerId: Int,
        offset: Int,
        count: Int,
        groupId: Int?
    ): VkApiRequest<BooleanInt> =
        Methods.deleteConversation.httpGet(
            "peer_id" to peerId,
            "offset" to offset,
            "count" to count,
            "group_id" to groupId
        ).withSerializer(BooleanInt.serializer())

    override fun deleteConversation(
        peerId: Int,
        offset: Int,
        count: Int
    ): VkApiRequest<BooleanInt> = deleteConversation(
        peerId = peerId,
        offset = offset,
        count = count,
        groupId = null
    )

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
        keepSnippets: Boolean,
        groupId: Int?
    ): VkApiRequest<BooleanInt> = 
        Methods.edit.httpPost(
            "peer_id" to peerId,
            "message_id" to messageId,
            "message" to message,
            "lat" to latitude,
            "long" to longitude,
            "attachment" to attachments?.let(::prepareAttachments),
            "keep_forward_messages" to keepForwardMessages.asInt(),
            "keep_snippets" to keepSnippets.asInt(),
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
    ): VkApiRequest<BooleanInt> = edit(
        peerId = peerId,
        messageId = messageId,
        message = message,
        latitude = latitude,
        longitude = longitude,
        attachments = attachments,
        keepForwardMessages = keepForwardMessages,
        keepSnippets = keepSnippets,
        groupId = null
    )

    override fun editChat(
        chatId: Int,
        title: String,
        groupId: Int?
    ): VkApiRequest<BooleanInt> = 
        Methods.editChat.httpGet(
            "chat_id" to chatId,
            "title" to title,
            "group_id" to groupId
        ).withSerializer(BooleanInt.serializer())

    override fun editChat(
        chatId: Int,
        title: String
    ): VkApiRequest<BooleanInt> = editChat(
        chatId = chatId,
        title = title,
        groupId = null
    )

    override fun getByConversationMessageId(
        peerId: Int,
        conversationMessageIds: List<Int>,
        extended: Boolean,
        fields: List<ObjectField>,
        groupId: Int?
    ): VkApiRequest<ExtendedListResponse<Message>> =
        Methods.getByConversationMessageId.httpPost(
            "peer_id" to peerId,
            "conversation_message_ids" to conversationMessageIds.joinToString(","),
            "extended" to extended.asInt(),
            "fields" to fields.joinToString(",") { it.value },
            "group_id" to groupId
        ).withSerializer(extendedList(Message.serializer()))

    override fun getByConversationMessageId(
        peerId: Int,
        conversationMessageIds: List<Int>,
        extended: Boolean,
        fields: List<ObjectField>
    ): VkApiRequest<ExtendedListResponse<Message>> = getByConversationMessageId(
        peerId = peerId,
        conversationMessageIds = conversationMessageIds,
        extended = extended,
        fields = fields,
        groupId = null
    )

    override fun getById(
        messageIds: List<Int>,
        previewLength: Int,
        extended: Boolean,
        fields: List<ObjectField>,
        groupId: Int?
    ): VkApiRequest<ExtendedListResponse<Message>> =
        Methods.getById.httpPost(
            "message_ids" to messageIds.joinToString(","),
            "preview_length" to previewLength,
            "extended" to extended.asInt(),
            "fields" to fields.joinToString(",") { it.value },
            "group_id" to groupId
        ).withSerializer(extendedList(Message.serializer()))

    override fun getById(
        messageIds: List<Int>,
        previewLength: Int,
        extended: Boolean,
        fields: List<ObjectField>
    ): VkApiRequest<ExtendedListResponse<Message>> = getById(
        messageIds = messageIds,
        previewLength = previewLength,
        extended = extended,
        fields = fields,
        groupId = null
    )

    override fun getChat(
        chatIds: List<Int>
    ): VkApiRequest<List<Chat>> =
        Methods.getChat.httpPost(
            "chat_ids" to chatIds.joinToString(",")
        ).withSerializer(Chat.serializer().list)

    override fun getChatPreview(
        link: String,
        fields: List<ObjectField>
    ): VkApiRequest<ChatPreview> =
        Methods.getChatPreview.httpPost(
            "link" to link,
            "fields" to fields.joinToString(",") { it.value }
        ).withSerializer(ChatPreview.serializer())

    override fun getConversationMembers(
        peerId: Int,
        fields: List<ObjectField>,
        groupId: Int?
    ): VkApiRequest<ExtendedListResponse<ConversationMember>> =
        Methods.getConversationMembers.httpPost(
            "peer_id" to peerId,
            "fields" to fields.joinToString(",") { it.value },
            "group_id" to groupId
        ).withSerializer(extendedList(ConversationMember.serializer()))

    override fun getConversationMembers(
        peerId: Int,
        fields: List<ObjectField>
    ): VkApiRequest<ExtendedListResponse<ConversationMember>> = getConversationMembers(
        peerId = peerId,
        fields = fields,
        groupId = null
    )

    override fun getConversations(
        offset: Int,
        count: Int,
        filter: ConversationFilter,
        startMessageId: Int?,
        extended: Boolean,
        fields: List<ObjectField>,
        groupId: Int?
    ): VkApiRequest<ConversationsListResponse> =
        Methods.getConversations.httpPost(
            "offset" to offset,
            "count" to count,
            "filter" to filter.value,
            "start_message_id" to startMessageId,
            "extended" to extended.asInt(),
            "fields" to fields.joinToString(",") { it.value },
            "group_id" to groupId
        ).withSerializer(ConversationsListResponse.serializer())

    override fun getConversations(
        offset: Int,
        count: Int,
        filter: ConversationFilter,
        startMessageId: Int?,
        extended: Boolean,
        fields: List<ObjectField>
    ): VkApiRequest<ConversationsListResponse> = getConversations(
        offset = offset,
        count = count,
        filter = filter,
        startMessageId = startMessageId,
        extended = extended,
        fields = fields,
        groupId = null
    )

    override fun getConversationsById(
        peerIds: List<Int>,
        extended: Boolean,
        fields: List<ObjectField>,
        groupId: Int?
    ): VkApiRequest<ExtendedListResponse<Conversation>> =
        Methods.getConversationsById.httpPost(
            "peer_ids" to peerIds.joinToString(","),
            "extended" to extended.asInt(),
            "fields" to fields.joinToString(",") { it.value },
            "group_id" to groupId
        ).withSerializer(extendedList(Conversation.serializer()))

    override fun getConversationsById(
        peerIds: List<Int>,
        extended: Boolean,
        fields: List<ObjectField>
    ): VkApiRequest<ExtendedListResponse<Conversation>> = getConversationsById(
        peerIds = peerIds,
        extended = extended,
        fields = fields,
        groupId = null
    )

    override fun getHistory(
        peerId: Int,
        offset: Int,
        count: Int,
        startMessageId: Int?,
        reverse: Boolean,
        extended: Boolean,
        fields: List<ObjectField>,
        groupId: Int?
    ): VkApiRequest<HistoryMessagesListResponse> = 
        Methods.getHistory.httpPost(
            "peer_id" to peerId,
            "offset" to offset,
            "count" to count,
            "start_message_id" to startMessageId,
            "rev" to reverse.asInt(),
            "extended" to extended.asInt(),
            "fields" to fields.joinToString(",") { it.value },
            "group_id" to groupId
        ).withSerializer(HistoryMessagesListResponse.serializer())

    override fun getHistory(
        peerId: Int,
        offset: Int,
        count: Int,
        startMessageId: Int?,
        reverse: Boolean,
        extended: Boolean,
        fields: List<ObjectField>
    ): VkApiRequest<HistoryMessagesListResponse> = getHistory(
        peerId = peerId,
        offset = offset,
        count = count,
        startMessageId = startMessageId,
        reverse = reverse,
        extended = extended,
        fields = fields,
        groupId = null
    )

    override fun getHistoryAttachments(
        peerId: Int,
        mediaType: AttachmentType,
        startFrom: Int?,
        count: Int,
        withPhotoSizes: Boolean,
        fields: List<ObjectField>,
        groupId: Int?
    ): VkApiRequest<HistoryAttachmentsResponse> = 
        Methods.getHistoryAttachments.httpPost(
            "peer_id" to peerId,
            "media_type" to mediaType.value,
            "start_from" to startFrom,
            "count" to count,
            "photo_sizes" to withPhotoSizes.asInt(),
            "fields" to fields.joinToString(",") { it.value },
            "group_id" to groupId
        ).withSerializer(HistoryAttachmentsResponse.serializer())

    override fun getHistoryAttachments(
        peerId: Int,
        mediaType: AttachmentType,
        startFrom: Int?,
        count: Int,
        withPhotoSizes: Boolean,
        fields: List<ObjectField>
    ): VkApiRequest<HistoryAttachmentsResponse> = getHistoryAttachments(
        peerId = peerId,
        mediaType = mediaType,
        startFrom = startFrom,
        count = count,
        withPhotoSizes = withPhotoSizes,
        fields = fields,
        groupId = null
    )

    override fun getImportantMessages(
        count: Int,
        offset: Int,
        startMessageId: Int?,
        previewLength: Int,
        extended: Boolean,
        fields: List<ObjectField>,
        groupId: Int?
    ): VkApiRequest<ImportantMessagesResponse> =
        Methods.getImportantMessages.httpPost(
            "count" to count,
            "offset" to offset,
            "start_message_id" to startMessageId,
            "preview_length" to previewLength,
            "extended" to extended.asInt(),
            "fields" to fields.joinToString(",") { it.value },
            "group_id" to groupId
        ).withSerializer(ImportantMessagesResponse.serializer())

    override fun getImportantMessages(
        count: Int,
        offset: Int,
        startMessageId: Int?,
        previewLength: Int,
        extended: Boolean,
        fields: List<ObjectField>
    ): VkApiRequest<ImportantMessagesResponse> = getImportantMessages(
        count = count,
        offset = offset,
        startMessageId = startMessageId,
        previewLength = previewLength,
        extended = extended,
        fields = fields,
        groupId = null
    )

    override fun getInviteLink(
        peerId: Int,
        generateNewLink: Boolean,
        groupId: Int?
    ): VkApiRequest<ChatInviteLink> =
        Methods.getInviteLink.httpGet(
            "peer_id" to peerId,
            "reset" to generateNewLink.asInt(),
            "group_id" to groupId
        ).withSerializer(ChatInviteLink.serializer())

    override fun getInviteLink(
        peerId: Int,
        generateNewLink: Boolean
    ): VkApiRequest<ChatInviteLink> = getInviteLink(
        peerId = peerId,
        generateNewLink = generateNewLink,
        groupId = null
    )

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
        longPollVersion: Int,
        groupId: Int?
    ): VkApiRequest<LongPollHistoryResponse> = 
        Methods.getLongPollHistory.httpPost(
            "ts" to ts,
            "pts" to pts,
            "preview_length" to previewLength,
            "onlines" to withOnlineStatuses.asInt(),
            "fields" to userFields.joinToString(",") { it.value },
            "events_limit" to eventsLimit,
            "msgs_limit" to messagesLimit,
            "max_msg_id" to maxMessageId,
            "lp_version" to longPollVersion,
            "group_id" to groupId
        ).withSerializer(LongPollHistoryResponse.serializer())

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
    ): VkApiRequest<LongPollHistoryResponse> = getLongPollHistory(
        ts = ts,
        pts = pts,
        previewLength = previewLength,
        withOnlineStatuses = withOnlineStatuses,
        userFields = userFields,
        eventsLimit = eventsLimit,
        messagesLimit = messagesLimit,
        maxMessageId = maxMessageId,
        longPollVersion = longPollVersion,
        groupId = null
    )

    override fun getLongPollServer(
        needPts: Boolean,
        longPollVersion: Int,
        groupId: Int?
    ): VkApiRequest<LongPollServerResponse> = 
        Methods.getLongPollServer.httpGet(
            "need_pts" to needPts.asInt(),
            "lp_version" to longPollVersion,
            "group_id" to groupId
        ).withSerializer(LongPollServerResponse.serializer())

    override fun getLongPollServer(
        needPts: Boolean,
        longPollVersion: Int
    ): VkApiRequest<LongPollServerResponse> = getLongPollServer(
        needPts = needPts,
        longPollVersion = longPollVersion,
        groupId = null
    )

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

    override fun markAsAnsweredConversation(
        peerId: Int,
        isAnswered: Boolean,
        groupId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.markAsAnsweredConversation.httpGet(
            "peer_id" to peerId,
            "answered" to isAnswered.asInt(),
            "group_id" to groupId
        ).withSerializer(BooleanInt.serializer())

    override fun markAsImportant(
        messageIds: List<Int>,
        markAsImportant: Boolean
    ): VkApiRequest<List<Int>> = 
        Methods.markAsImportant.httpPost(
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

    override fun markAsImportantConversation(
        peerId: Int,
        isImportant: Boolean,
        groupId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.markAsImportantConversation.httpGet(
            "peer_id" to peerId,
            "important" to isImportant.asInt(),
            "group_id" to groupId
        ).withSerializer(BooleanInt.serializer())

    override fun markAsRead(
        peerId: Int,
        startMessageId: Int?,
        groupId: Int?
    ): VkApiRequest<BooleanInt> = 
        Methods.markAsRead.httpGet(
            "peer_id" to peerId,
            "start_message_id" to startMessageId,
            "group_id" to groupId
        ).withSerializer(BooleanInt.serializer())

    override fun markAsRead(
        peerId: Int,
        startMessageId: Int?
    ): VkApiRequest<BooleanInt> = markAsRead(
        peerId = peerId,
        startMessageId = startMessageId,
        groupId = null
    )

    override fun pin(
        peerId: Int,
        messageId: Int,
        groupId: Int?
    ): VkApiRequest<Message.Pinned> =
        Methods.pin.httpGet(
            "peer_id" to peerId,
            "message_id" to messageId,
            "group_id" to groupId
        ).withSerializer(Message.Pinned.serializer())

    override fun pin(
        peerId: Int,
        messageId: Int
    ): VkApiRequest<Message.Pinned> = pin(
        peerId = peerId,
        messageId = messageId,
        groupId = null
    )

    override fun removeChatUser(
        chatId: Int,
        memberId: Int,
        groupId: Int?
    ): VkApiRequest<BooleanInt> = 
        Methods.removeChatUser.httpGet(
            "chat_id" to chatId,
            "member_id" to memberId,
            "group_id" to groupId
        ).withSerializer(BooleanInt.serializer())

    override fun removeChatUser(
        chatId: Int,
        memberId: Int
    ): VkApiRequest<BooleanInt> = removeChatUser(
        chatId = chatId,
        memberId = memberId,
        groupId = null
    )

    override fun restore(
        messageId: Int,
        groupId: Int?
    ): VkApiRequest<BooleanInt> = 
        Methods.restore.httpGet(
            "message_id" to messageId,
            "group_id" to groupId
        ).withSerializer(BooleanInt.serializer())

    override fun restore(
        messageId: Int
    ): VkApiRequest<BooleanInt> = restore(
        messageId = messageId,
        groupId = null
    )

    override fun search(
        query: String,
        peerId: Int?,
        maxDate: Date?,
        previewLength: Int,
        offset: Int,
        count: Int,
        groupId: Int?
    ): VkApiRequest<DefaultListResponse<Message>> =
        Methods.search.httpGet(
            "q" to query,
            "peer_id" to peerId,
            "date" to maxDate?.toDMYString(),
            "preview_length" to previewLength,
            "offset" to offset,
            "count" to count,
            "group_id" to groupId
        ).withSerializer(list(Message.serializer()))

    override fun search(
        query: String,
        peerId: Int?,
        maxDate: Date?,
        previewLength: Int,
        offset: Int,
        count: Int
    ): VkApiRequest<DefaultListResponse<Message>> = search(
        query = query,
        peerId = peerId,
        maxDate = maxDate,
        previewLength = previewLength,
        offset = offset,
        count = count,
        groupId = null
    )

    override fun searchExtended(
        query: String,
        peerId: Int?,
        maxDate: Date?,
        previewLength: Int,
        offset: Int,
        count: Int,
        groupId: Int?,
        fields: List<ObjectField>
    ): VkApiRequest<ExtendedListResponse<Message>> =
        Methods.search.httpGet(
            "q" to query,
            "peer_id" to peerId,
            "date" to maxDate?.toDMYString(),
            "preview_length" to previewLength,
            "offset" to offset,
            "count" to count,
            "extended" to 1,
            "fields" to fields.joinToString(",") { it.value },
            "group_id" to groupId
        ).withSerializer(extendedList(Message.serializer()))

    override fun searchExtended(
        query: String,
        peerId: Int?,
        maxDate: Date?,
        previewLength: Int,
        offset: Int,
        count: Int,
        fields: List<ObjectField>
    ): VkApiRequest<ExtendedListResponse<Message>> = searchExtended(
        query = query,
        peerId = peerId,
        maxDate = maxDate,
        previewLength = previewLength,
        offset = offset,
        count = count,
        fields = fields,
        groupId = null
    )

    override fun searchConversations(
        query: String,
        count: Int,
        extended: Boolean,
        fields: List<ObjectField>,
        groupId: Int?
    ): VkApiRequest<ExtendedListResponse<Conversation>> =
        Methods.searchConversations.httpPost(
            "q" to query,
            "count" to count,
            "extended" to extended.asInt(),
            "fields" to fields.joinToString(",") { it.value },
            "group_id" to groupId
        ).withSerializer(extendedList(Conversation.serializer()))

    override fun searchConversations(
        query: String,
        count: Int,
        extended: Boolean,
        fields: List<ObjectField>
    ): VkApiRequest<ExtendedListResponse<Conversation>> = searchConversations(
        query = query,
        count = count,
        extended = extended,
        fields = fields,
        groupId = null
    )

    override fun send(
        peerId: Int,
        randomId: Int,
        message: String?,
        latitude: Int?,
        longitude: Int?,
        attachments: List<MessageAttachment>?,
        replyToMessageId: Int?,
        forwardedMessages: List<Int>?,
        stickerId: Int?,
        keyboard: Keyboard?,
        payload: MessagePayload?,
        dontParseLink: Boolean
    ): VkApiRequest<Int> =
        Methods.send.httpPost(
            "peer_id" to peerId,
            "random_id" to randomId,
            "message" to message,
            "lat" to latitude,
            "long" to longitude,
            "attachment" to attachments?.let(::prepareAttachments),
            "reply_to" to replyToMessageId,
            "forward_messages" to forwardedMessages?.joinToString(","),
            "sticker_id" to stickerId,
            "keyboard" to keyboard?.serialize(),
            "payload" to payload?.value,
            "dont_parse_links" to dontParseLink.asInt()
        ).withSerializer(IntSerializer)

    override fun send(
        peerId: Int,
        randomId: Int,
        message: String?,
        latitude: Int?,
        longitude: Int?,
        attachments: List<MessageAttachment>?,
        replyToMessageId: Int?,
        forwardedMessages: List<Int>?,
        stickerId: Int?,
        dontParseLink: Boolean,
        groupId: Int?
    ): VkApiRequest<Int> =
        Methods.send.httpPost(
            "peer_id" to peerId,
            "random_id" to randomId,
            "message" to message,
            "lat" to latitude,
            "long" to longitude,
            "attachment" to attachments?.let(::prepareAttachments),
            "reply_to" to replyToMessageId,
            "forward_messages" to forwardedMessages?.joinToString(","),
            "sticker_id" to stickerId,
            "dont_parse_links" to dontParseLink.asInt(),
            "group_id" to groupId
        ).withSerializer(IntSerializer)

    override fun sendBulk(
        userIds: List<Int>,
        randomId: Int,
        message: String?,
        latitude: Int?,
        longitude: Int?,
        attachments: List<MessageAttachment>?,
        forwardedMessages: List<Int>?,
        stickerId: Int?,
        keyboard: Keyboard?,
        payload: MessagePayload?,
        dontParseLink: Boolean
    ): VkApiRequest<List<SendBulkMessageResponse>> =
        Methods.send.httpPost(
            "user_ids" to userIds.joinToString(","),
            "random_id" to randomId,
            "message" to message,
            "lat" to latitude,
            "long" to longitude,
            "attachment" to attachments?.let(::prepareAttachments),
            "forward_messages" to forwardedMessages?.joinToString(","),
            "sticker_id" to stickerId,
            "keyboard" to keyboard?.serialize(),
            "payload" to payload?.value,
            "dont_parse_links" to dontParseLink.asInt()
        ).withSerializer(SendBulkMessageResponse.serializer().list)

    override fun setActivity(
        peerId: Int,
        type: String,
        groupId: Int?
    ): VkApiRequest<BooleanInt> = 
        Methods.setActivity.httpGet(
            "peer_id" to peerId,
            "type" to type
        ).withSerializer(BooleanInt.serializer())

    override fun setActivity(
        peerId: Int,
        type: String
    ): VkApiRequest<BooleanInt> = setActivity(
        peerId = peerId,
        type = type,
        groupId = null
    )

    override fun setChatPhoto(
        file: String,
        groupId: Int?
    ): VkApiRequest<ChatChangePhotoResponse> = 
        Methods.setChatPhoto.httpGet(
            "file" to file,
            "group_id" to groupId
        ).withSerializer(ChatChangePhotoResponse.serializer())

    override fun setChatPhoto(
        file: String
    ): VkApiRequest<ChatChangePhotoResponse> = setChatPhoto(
        file = file,
        groupId = null
    )

    override fun unpin(
        peerId: Int,
        groupId: Int?
    ): VkApiRequest<BooleanInt> =
        Methods.unpin.httpGet(
            "peer_id" to peerId,
            "group_id" to groupId
        ).withSerializer(BooleanInt.serializer())

    override fun unpin(
        peerId: Int
    ): VkApiRequest<BooleanInt> = unpin(
        peerId = peerId,
        groupId = null
    )

    private companion object {

        private val dateFormatter =
            SimpleDateFormat("ddMMyyyy", Locale.getDefault())

        fun Date.toDMYString(): String =
            dateFormatter.format(this)

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
        const val getImportantMessages = it + "getImportantMessages"
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