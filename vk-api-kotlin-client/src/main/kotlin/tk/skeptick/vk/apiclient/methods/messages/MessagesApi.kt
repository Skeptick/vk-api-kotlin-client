package tk.skeptick.vk.apiclient.methods.messages

import io.ktor.util.date.GMTDate
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

class MessagesApi(override val client: VkApiClient)
    : MessagesApiUser, MessagesApiCommunity, MethodsContext {

    override fun addChatUser(
        chatId: Int,
        userId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.addChatUser.httpGet(BooleanInt.serializer()) {
            append("chat_id", chatId)
            append("user_id", userId)
        }

    override fun allowMessagesFromGroup(
        groupId: Int,
        key: String?
    ): VkApiRequest<BooleanInt> = 
        Methods.allowMessagesFromGroup.httpGet(BooleanInt.serializer()) {
            append("group_id", groupId)
            append("key", key)
        }
    
    override fun createChat(
        userIds: List<Int>,
        title: String
    ): VkApiRequest<Int> =
        Methods.createChat.httpPost(IntSerializer) {
            append("user_ids", userIds.joinToString(","))
            append("title", title)
        }

    override fun delete(
        messageIds: List<Int>,
        markAsSpam: Boolean,
        deleteForAll: Boolean,
        groupId: Int?
    ): VkApiRequest<Map<Int, BooleanInt>> =
        Methods.delete.httpPost((IntSerializer to BooleanInt.serializer()).map) {
            append("message_ids", messageIds.joinToString(","))
            append("spam", markAsSpam.asInt())
            append("delete_for_all", deleteForAll.asInt())
            append("group_id", groupId)
        }

    override fun delete(
        messageIds: List<Int>,
        markAsSpam: Boolean,
        deleteForAll: Boolean
    ): VkApiRequest<Map<Int, BooleanInt>> =
        delete(
            messageIds = messageIds,
            markAsSpam = markAsSpam,
            deleteForAll = deleteForAll,
            groupId = null
        )

    override fun deleteChatPhoto(
        chatId: Int,
        groupId: Int?
    ): VkApiRequest<ChatChangePhotoResponse> =
        Methods.deleteChatPhoto.httpGet(ChatChangePhotoResponse.serializer()) {
            append("chat_id", chatId)
            append("group_id", groupId)
        }

    override fun deleteChatPhoto(
        chatId: Int
    ): VkApiRequest<ChatChangePhotoResponse> =
        deleteChatPhoto(
            chatId = chatId,
            groupId = null
        )

    override fun deleteConversation(
        peerId: Int,
        offset: Int,
        count: Int,
        groupId: Int?
    ): VkApiRequest<BooleanInt> =
        Methods.deleteConversation.httpGet(BooleanInt.serializer()) {
            append("peer_id", peerId)
            append("offset", offset)
            append("count", count)
            append("group_id", groupId)
        }

    override fun deleteConversation(
        peerId: Int,
        offset: Int,
        count: Int
    ): VkApiRequest<BooleanInt> =
        deleteConversation(
            peerId = peerId,
            offset = offset,
            count = count,
            groupId = null
        )

    override fun denyMessagesFromGroup(
        groupId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.denyMessagesFromGroup.httpGet(BooleanInt.serializer()) {
            append("group_id", groupId)
        }

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
        Methods.edit.httpPost(BooleanInt.serializer()) {
            append("peer_id", peerId)
            append("message_id", messageId)
            append("message", message)
            append("lat", latitude)
            append("long", longitude)
            append("attachment", attachments?.joinToString(",") { it.attachment })
            append("keep_forward_messages", keepForwardMessages.asInt())
            append("keep_snippets", keepSnippets.asInt())
            append("group_id", groupId)
        }

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
        edit(
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
        Methods.editChat.httpGet(BooleanInt.serializer()) {
            append("chat_id", chatId)
            append("title", title)
            append("group_id", groupId)
        }

    override fun editChat(
        chatId: Int,
        title: String
    ): VkApiRequest<BooleanInt> =
        editChat(
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
        Methods.getByConversationMessageId.httpPost(extendedList(Message.serializer())) {
            append("peer_id", peerId)
            append("conversation_message_ids", conversationMessageIds.joinToString(","))
            append("extended", extended.asInt())
            append("fields", fields.joinToString(",") { it.value })
            append("group_id", groupId)
        }

    override fun getByConversationMessageId(
        peerId: Int,
        conversationMessageIds: List<Int>,
        extended: Boolean,
        fields: List<ObjectField>
    ): VkApiRequest<ExtendedListResponse<Message>> =
        getByConversationMessageId(
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
        Methods.getById.httpPost(extendedList(Message.serializer())) {
            append("message_ids", messageIds.joinToString(","))
            append("preview_length", previewLength)
            append("extended", extended.asInt())
            append("fields", fields.joinToString(",") { it.value })
            append("group_id", groupId)
        }

    override fun getById(
        messageIds: List<Int>,
        previewLength: Int,
        extended: Boolean,
        fields: List<ObjectField>
    ): VkApiRequest<ExtendedListResponse<Message>> =
        getById(
            messageIds = messageIds,
            previewLength = previewLength,
            extended = extended,
            fields = fields,
            groupId = null
        )

    override fun getChat(
        chatIds: List<Int>
    ): VkApiRequest<List<Chat>> =
        Methods.getChat.httpPost(Chat.serializer().list) {
            append("chat_ids", chatIds.joinToString(","))
        }

    override fun getChatPreview(
        link: String,
        fields: List<ObjectField>
    ): VkApiRequest<ChatPreview> =
        Methods.getChatPreview.httpPost(ChatPreview.serializer()) {
            append("link", link)
            append("fields", fields.joinToString(",") { it.value })
        }

    override fun getConversationMembers(
        peerId: Int,
        fields: List<ObjectField>,
        groupId: Int?
    ): VkApiRequest<ExtendedListResponse<ConversationMember>> =
        Methods.getConversationMembers.httpPost(extendedList(ConversationMember.serializer())) {
            append("peer_id", peerId)
            append("fields", fields.joinToString(",") { it.value })
            append("group_id", groupId)
        }

    override fun getConversationMembers(
        peerId: Int,
        fields: List<ObjectField>
    ): VkApiRequest<ExtendedListResponse<ConversationMember>> =
        getConversationMembers(
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
        Methods.getConversations.httpPost(ConversationsListResponse.serializer()) {
            append("offset", offset)
            append("count", count)
            append("filter", filter.value)
            append("start_message_id", startMessageId)
            append("extended", extended.asInt())
            append("fields", fields.joinToString(",") { it.value })
            append("group_id", groupId)
        }

    override fun getConversations(
        offset: Int,
        count: Int,
        filter: ConversationFilter,
        startMessageId: Int?,
        extended: Boolean,
        fields: List<ObjectField>
    ): VkApiRequest<ConversationsListResponse> =
        getConversations(
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
        Methods.getConversationsById.httpPost(extendedList(Conversation.serializer())) {
            append("peer_ids", peerIds.joinToString(","))
            append("extended", extended.asInt())
            append("fields", fields.joinToString(",") { it.value })
            append("group_id", groupId)
        }

    override fun getConversationsById(
        peerIds: List<Int>,
        extended: Boolean,
        fields: List<ObjectField>
    ): VkApiRequest<ExtendedListResponse<Conversation>> =
        getConversationsById(
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
        Methods.getHistory.httpPost(HistoryMessagesListResponse.serializer()) {
            append("peer_id", peerId)
            append("offset", offset)
            append("count", count)
            append("start_message_id", startMessageId)
            append("rev", reverse.asInt())
            append("extended", extended.asInt())
            append("fields", fields.joinToString(",") { it.value })
            append("group_id", groupId)
        }

    override fun getHistory(
        peerId: Int,
        offset: Int,
        count: Int,
        startMessageId: Int?,
        reverse: Boolean,
        extended: Boolean,
        fields: List<ObjectField>
    ): VkApiRequest<HistoryMessagesListResponse> =
        getHistory(
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
        Methods.getHistoryAttachments.httpPost(HistoryAttachmentsResponse.serializer()) {
            append("peer_id", peerId)
            append("media_type", mediaType.value)
            append("start_from", startFrom)
            append("count", count)
            append("photo_sizes", withPhotoSizes.asInt())
            append("fields", fields.joinToString(",") { it.value })
            append("group_id", groupId)
        }

    override fun getHistoryAttachments(
        peerId: Int,
        mediaType: AttachmentType,
        startFrom: Int?,
        count: Int,
        withPhotoSizes: Boolean,
        fields: List<ObjectField>
    ): VkApiRequest<HistoryAttachmentsResponse> =
        getHistoryAttachments(
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
        Methods.getImportantMessages.httpPost(ImportantMessagesResponse.serializer()) {
            append("count", count)
            append("offset", offset)
            append("start_message_id", startMessageId)
            append("preview_length", previewLength)
            append("extended", extended.asInt())
            append("fields", fields.joinToString(",") { it.value })
            append("group_id", groupId)
        }

    override fun getImportantMessages(
        count: Int,
        offset: Int,
        startMessageId: Int?,
        previewLength: Int,
        extended: Boolean,
        fields: List<ObjectField>
    ): VkApiRequest<ImportantMessagesResponse> =
        getImportantMessages(
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
        Methods.getInviteLink.httpGet(ChatInviteLink.serializer()) {
            append("peer_id", peerId)
            append("reset", generateNewLink.asInt())
            append("group_id", groupId)
        }

    override fun getInviteLink(
        peerId: Int,
        generateNewLink: Boolean
    ): VkApiRequest<ChatInviteLink> =
        getInviteLink(
            peerId = peerId,
            generateNewLink = generateNewLink,
            groupId = null
        )

    override fun getLastActivity(
        userId: Int
    ): VkApiRequest<LastActivityResponse> = 
        Methods.getLastActivity.httpGet(LastActivityResponse.serializer()) {
            append("user_id", userId)
        }

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
        Methods.getLongPollHistory.httpPost(LongPollHistoryResponse.serializer()) {
            append("ts", ts)
            append("pts", pts)
            append("preview_length", previewLength)
            append("onlines", withOnlineStatuses.asInt())
            append("fields", userFields.joinToString(",") { it.value })
            append("events_limit", eventsLimit)
            append("msgs_limit", messagesLimit)
            append("max_msg_id", maxMessageId)
            append("lp_version", longPollVersion)
            append("group_id", groupId)
        }

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
        getLongPollHistory(
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
        Methods.getLongPollServer.httpGet(LongPollServerResponse.serializer()) {
            append("need_pts", needPts.asInt())
            append("lp_version", longPollVersion)
            append("group_id", groupId)
        }

    override fun getLongPollServer(
        needPts: Boolean,
        longPollVersion: Int
    ): VkApiRequest<LongPollServerResponse> =
        getLongPollServer(
            needPts = needPts,
            longPollVersion = longPollVersion,
            groupId = null
        )

    override fun isMessagesFromGroupAllowed(
        groupId: Int,
        userId: Int
    ): VkApiRequest<MessagesFromGroupAllowedResponse> =
        Methods.isMessagesFromGroupAllowed.httpGet(MessagesFromGroupAllowedResponse.serializer()) {
            append("group_id", groupId)
            append("user_id", userId)
        }

    override fun joinChatByInviteLink(
        link: String
    ): VkApiRequest<JoinChatByLinkResponse> = 
        Methods.joinChatByInviteLink.httpGet(JoinChatByLinkResponse.serializer()) {
            append("link", link)
        }

    override fun markAsAnsweredConversation(
        peerId: Int,
        isAnswered: Boolean
    ): VkApiRequest<BooleanInt> = 
        Methods.markAsAnsweredConversation.httpGet(BooleanInt.serializer()) {
            append("peer_id", peerId)
            append("answered", isAnswered.asInt())
        }

    override fun markAsAnsweredConversation(
        peerId: Int,
        isAnswered: Boolean,
        groupId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.markAsAnsweredConversation.httpGet(BooleanInt.serializer()) {
            append("peer_id", peerId)
            append("answered", isAnswered.asInt())
            append("group_id", groupId)
        }

    override fun markAsImportant(
        messageIds: List<Int>,
        markAsImportant: Boolean
    ): VkApiRequest<List<Int>> = 
        Methods.markAsImportant.httpPost(IntSerializer.list) {
            append("message_ids", messageIds.joinToString(","))
            append("important", markAsImportant.asInt())
        }

    override fun markAsImportantConversation(
        peerId: Int,
        isImportant: Boolean
    ): VkApiRequest<BooleanInt> = 
        Methods.markAsImportantConversation.httpGet(BooleanInt.serializer()) {
            append("peer_id", peerId)
            append("important", isImportant.asInt())
        }

    override fun markAsImportantConversation(
        peerId: Int,
        isImportant: Boolean,
        groupId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.markAsImportantConversation.httpGet(BooleanInt.serializer()) {
            append("peer_id", peerId)
            append("important", isImportant.asInt())
            append("group_id", groupId)
        }

    override fun markAsRead(
        peerId: Int,
        startMessageId: Int?,
        groupId: Int?
    ): VkApiRequest<BooleanInt> = 
        Methods.markAsRead.httpGet(BooleanInt.serializer()) {
            append("peer_id", peerId)
            append("start_message_id", startMessageId)
            append("group_id", groupId)
        }

    override fun markAsRead(
        peerId: Int,
        startMessageId: Int?
    ): VkApiRequest<BooleanInt> =
        markAsRead(
            peerId = peerId,
            startMessageId = startMessageId,
            groupId = null
        )

    override fun pin(
        peerId: Int,
        messageId: Int,
        groupId: Int?
    ): VkApiRequest<Message.Pinned> =
        Methods.pin.httpGet(Message.Pinned.serializer()) {
            append("peer_id", peerId)
            append("message_id", messageId)
            append("group_id", groupId)
        }

    override fun pin(
        peerId: Int,
        messageId: Int
    ): VkApiRequest<Message.Pinned> =
        pin(
            peerId = peerId,
            messageId = messageId,
            groupId = null
        )

    override fun removeChatUser(
        chatId: Int,
        memberId: Int,
        groupId: Int?
    ): VkApiRequest<BooleanInt> = 
        Methods.removeChatUser.httpGet(BooleanInt.serializer()) {
            append("chat_id", chatId)
            append("member_id", memberId)
            append("group_id", groupId)
        }

    override fun removeChatUser(
        chatId: Int,
        memberId: Int
    ): VkApiRequest<BooleanInt> =
        removeChatUser(
            chatId = chatId,
            memberId = memberId,
            groupId = null
        )

    override fun restore(
        messageId: Int,
        groupId: Int?
    ): VkApiRequest<BooleanInt> = 
        Methods.restore.httpGet(BooleanInt.serializer()) {
            append("message_id", messageId)
            append("group_id", groupId)
        }

    override fun restore(
        messageId: Int
    ): VkApiRequest<BooleanInt> =
        restore(
            messageId = messageId,
            groupId = null
        )

    override fun search(
        query: String,
        peerId: Int?,
        maxDate: GMTDate?,
        previewLength: Int,
        offset: Int,
        count: Int,
        groupId: Int?
    ): VkApiRequest<DefaultListResponse<Message>> =
        Methods.search.httpGet(list(Message.serializer())) {
            append("q", query)
            append("peer_id", peerId)
            append("date", maxDate?.toDMYString())
            append("preview_length", previewLength)
            append("offset", offset)
            append("count", count)
            append("group_id", groupId)
        }

    override fun search(
        query: String,
        peerId: Int?,
        maxDate: GMTDate?,
        previewLength: Int,
        offset: Int,
        count: Int
    ): VkApiRequest<DefaultListResponse<Message>> =
        search(
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
        maxDate: GMTDate?,
        previewLength: Int,
        offset: Int,
        count: Int,
        groupId: Int?,
        fields: List<ObjectField>
    ): VkApiRequest<ExtendedListResponse<Message>> =
        Methods.search.httpGet(extendedList(Message.serializer())) {
            append("q", query)
            append("peer_id", peerId)
            append("date", maxDate?.toDMYString())
            append("preview_length", previewLength)
            append("offset", offset)
            append("count", count)
            append("extended", 1)
            append("fields", fields.joinToString(",") { it.value })
            append("group_id", groupId)
        }

    override fun searchExtended(
        query: String,
        peerId: Int?,
        maxDate: GMTDate?,
        previewLength: Int,
        offset: Int,
        count: Int,
        fields: List<ObjectField>
    ): VkApiRequest<ExtendedListResponse<Message>> =
        searchExtended(
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
        Methods.searchConversations.httpPost(extendedList(Conversation.serializer())) {
            append("q", query)
            append("count", count)
            append("extended", extended.asInt())
            append("fields", fields.joinToString(",") { it.value })
            append("group_id", groupId)
        }

    override fun searchConversations(
        query: String,
        count: Int,
        extended: Boolean,
        fields: List<ObjectField>
    ): VkApiRequest<ExtendedListResponse<Conversation>> =
        searchConversations(
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
        Methods.send.httpPost(IntSerializer) {
            append("peer_id", peerId)
            append("random_id", randomId)
            append("message", message)
            append("lat", latitude)
            append("long", longitude)
            append("attachment", attachments?.joinToString(",") { it.attachment })
            append("reply_to", replyToMessageId)
            append("forward_messages", forwardedMessages?.joinToString(","))
            append("sticker_id", stickerId)
            append("keyboard", keyboard?.serialize())
            append("payload", payload?.value)
            append("dont_parse_links", dontParseLink.asInt())
        }

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
        Methods.send.httpPost(IntSerializer) {
            append("peer_id", peerId)
            append("random_id", randomId)
            append("message", message)
            append("lat", latitude)
            append("long", longitude)
            append("attachment", attachments?.joinToString(",") { it.attachment })
            append("reply_to", replyToMessageId)
            append("forward_messages", forwardedMessages?.joinToString(","))
            append("sticker_id", stickerId)
            append("dont_parse_links", dontParseLink.asInt())
            append("group_id", groupId)
        }

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
        Methods.send.httpPost(SendBulkMessageResponse.serializer().list) {
            append("user_ids", userIds.joinToString(","))
            append("random_id", randomId)
            append("message", message)
            append("lat", latitude)
            append("long", longitude)
            append("attachment", attachments?.joinToString(",") { it.attachment })
            append("forward_messages", forwardedMessages?.joinToString(","))
            append("sticker_id", stickerId)
            append("keyboard", keyboard?.serialize())
            append("payload", payload?.value)
            append("dont_parse_links", dontParseLink.asInt())
        }

    override fun setActivity(
        peerId: Int,
        type: String,
        groupId: Int?
    ): VkApiRequest<BooleanInt> = 
        Methods.setActivity.httpGet(BooleanInt.serializer()) {
            append("peer_id", peerId)
            append("type", type)
        }

    override fun setActivity(
        peerId: Int,
        type: String
    ): VkApiRequest<BooleanInt> =
        setActivity(
            peerId = peerId,
            type = type,
            groupId = null
        )

    override fun setChatPhoto(
        file: String,
        groupId: Int?
    ): VkApiRequest<ChatChangePhotoResponse> = 
        Methods.setChatPhoto.httpGet(ChatChangePhotoResponse.serializer()) {
            append("file", file)
            append("group_id", groupId)
        }

    override fun setChatPhoto(
        file: String
    ): VkApiRequest<ChatChangePhotoResponse> =
        setChatPhoto(
            file = file,
            groupId = null
        )

    override fun unpin(
        peerId: Int,
        groupId: Int?
    ): VkApiRequest<BooleanInt> =
        Methods.unpin.httpGet(BooleanInt.serializer()) {
            append("peer_id", peerId)
            append("group_id", groupId)
        }

    override fun unpin(
        peerId: Int
    ): VkApiRequest<BooleanInt> =
        unpin(
            peerId = peerId,
            groupId = null
        )

    private companion object {

        fun GMTDate.toDMYString(): String = buildString {
            append(dayOfMonth.toString().padStart(2, '0'))
            append((month.ordinal + 1).toString().padStart(2, '0'))
            append(year.toString().padStart(4, '0'))
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