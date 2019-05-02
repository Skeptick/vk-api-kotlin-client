package tk.skeptick.vk.apiclient.methods.messages

import io.ktor.util.date.GMTDate
import tk.skeptick.vk.apiclient.*
import tk.skeptick.vk.apiclient.domain.AttachmentType
import tk.skeptick.vk.apiclient.domain.models.*
import tk.skeptick.vk.apiclient.domain.MessageAttachment
import tk.skeptick.vk.apiclient.methods.ConversationFilter
import tk.skeptick.vk.apiclient.methods.DefaultListResponse
import tk.skeptick.vk.apiclient.methods.ExtendedListResponse
import tk.skeptick.vk.apiclient.methods.ObjectField

interface MessagesApiUser : MessagesApiCommon {

    /**
     * May return "true" even if not added
     * @see <a href="https://vk.com/dev/messages.addChatUser">VK API</a>
     */
    fun addChatUser(
        chatId: Int,
        userId: Int
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/messages.allowMessagesFromGroup">VK API</a>
     */
    fun allowMessagesFromGroup(
        groupId: Int,
        key: String? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @return ID of the created chat (chat_id)
     * @see <a href="https://vk.com/dev/messages.createChat">VK API</a>
     */
    fun createChat(
        userIds: List<Int>,
        title: String
    ): VkApiRequest<Int>

    /**
     * @return Success status for every deleted message
     * @see <a href="https://vk.com/dev/messages.delete">VK API</a>
     */
    fun delete(
        messageIds: List<Int>,
        markAsSpam: Boolean = false,
        deleteForAll: Boolean = false,
        groupId: Int? = null
    ): VkApiRequest<Map<Int, BooleanInt>>

    /**
     * @see <a href="https://vk.com/dev/messages.deleteChatPhoto">VK API</a>
     */
    fun deleteChatPhoto(
        chatId: Int
    ): VkApiRequest<ChatChangePhotoResponse>

    /**
     * @param[count] maximum value 10000
     * @see <a href="https://vk.com/dev/messages.deleteConversation">VK API</a>
     */
    fun deleteConversation(
        peerId: Int,
        offset: Int = 0,
        count: Int = 10000,
        groupId: Int? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/messages.denyMessagesFromGroup">VK API</a>
     */
    fun denyMessagesFromGroup(
        groupId: Int
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/messages.edit">VK API</a>
     */
    fun edit(
        peerId: Int,
        messageId: Int,
        message: String? = null,
        latitude: Int? = null,
        longitude: Int? = null,
        attachments: List<MessageAttachment>? = null,
        keepForwardMessages: Boolean = true,
        keepSnippets: Boolean = true,
        groupId: Int? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/messages.editChat">VK API</a>
     */
    fun editChat(
        chatId: Int,
        title: String,
        groupId: Int? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/messages.getByConversationMessageId">VK API</a>
     */
    fun getByConversationMessageId(
        peerId: Int,
        conversationMessageIds: List<Int>,
        extended: Boolean = false,
        fields: List<ObjectField> = DefaultMethodsParams.userFields,
        groupId: Int? = null
    ): VkApiRequest<ExtendedListResponse<Message>>

    /**
     * @see <a href="https://vk.com/dev/messages.getById">VK API</a>
     */
    fun getById(
        messageIds: List<Int>,
        previewLength: Int = 0,
        extended: Boolean = false,
        fields: List<ObjectField> = DefaultMethodsParams.userFields,
        groupId: Int? = null
    ): VkApiRequest<ExtendedListResponse<Message>>

    /**
     * @see <a href="https://vk.com/dev/messages.getChat">VK API</a>
     */
    fun getChat(
        chatIds: List<Int>
    ): VkApiRequest<List<Chat>>

    /**
     * @see <a href="https://vk.com/dev/messages.getChatPreview">VK API</a>
     */
    fun getChatPreview(
        link: String,
        fields: List<ObjectField> = DefaultMethodsParams.userFields
    ): VkApiRequest<ChatPreview>

    /**
     * @see <a href="https://vk.com/dev/messages.getConversationMembers">VK API</a>
     */
    fun getConversationMembers(
        peerId: Int,
        fields: List<ObjectField> = DefaultMethodsParams.userFields,
        groupId: Int? = null
    ): VkApiRequest<ExtendedListResponse<ConversationMember>>

    /**
     * @param[count] maximum value 200
     * @see <a href="https://vk.com/dev/messages.getConversations">VK API</a>
     */
    fun getConversations(
        offset: Int = 0,
        count: Int = 20,
        filter: ConversationFilter = ConversationFilter.ALL,
        startMessageId: Int? = null,
        extended: Boolean = false,
        fields: List<ObjectField> = DefaultMethodsParams.userFields,
        groupId: Int? = null
    ): VkApiRequest<ConversationsListResponse>

    /**
     * @see <a href="https://vk.com/dev/messages.getConversationsById">VK API</a>
     */
    fun getConversationsById(
        peerIds: List<Int>,
        extended: Boolean = false,
        fields: List<ObjectField> = DefaultMethodsParams.userFields,
        groupId: Int? = null
    ): VkApiRequest<ExtendedListResponse<Conversation>>

    /**
     * @param[count] maximum value 200
     * @see <a href="https://vk.com/dev/messages.getHistory">VK API</a>
     */
    fun getHistory(
        peerId: Int,
        offset: Int = 0,
        count: Int = 20,
        startMessageId: Int? = null,
        reverse: Boolean = false,
        extended: Boolean = false,
        fields: List<ObjectField> = DefaultMethodsParams.userFields,
        groupId: Int? = null
    ): VkApiRequest<HistoryMessagesListResponse>

    /**
     * @param[count] maximum value 200
     * @see <a href="https://vk.com/dev/messages.getHistoryAttachments">VK API</a>
     */
    fun getHistoryAttachments(
        peerId: Int,
        mediaType: AttachmentType = AttachmentType.PHOTO,
        startFrom: Int? = null,
        count: Int = 30,
        withPhotoSizes: Boolean = false,
        fields: List<ObjectField> = DefaultMethodsParams.userFields,
        groupId: Int? = null
    ): VkApiRequest<HistoryAttachmentsResponse>

    /**
     * @param[count] maximum value 200
     * @see <a href="https://vk.com/dev/messages.getImportantMessages">VK API</a>
     */
    fun getImportantMessages(
        count: Int = 20,
        offset: Int = 0,
        startMessageId: Int? = null,
        previewLength: Int = 0,
        extended: Boolean = false,
        fields: List<ObjectField> = DefaultMethodsParams.userFields,
        groupId: Int? = null
    ): VkApiRequest<ImportantMessagesResponse>

    /**
     * @see <a href="https://vk.com/dev/messages.getInviteLink">VK API</a>
     */
    fun getInviteLink(
        peerId: Int,
        generateNewLink: Boolean = false,
        groupId: Int? = null
    ): VkApiRequest<ChatInviteLink>

    /**
     * @see <a href="https://vk.com/dev/messages.getLastActivity">VK API</a>
     */
    fun getLastActivity(
        userId: Int
    ): VkApiRequest<LastActivityResponse>

    /**
     * @see <a href="https://vk.com/dev/messages.getLongPollHistory">VK API</a>
     */
    fun getLongPollHistory(
        ts: Long,
        pts: Long,
        previewLength: Int = 0,
        withOnlineStatuses: Boolean = false,
        userFields: List<ObjectField> = DefaultMethodsParams.userFields,
        eventsLimit: Int = 1000,
        messagesLimit: Int = 200,
        maxMessageId: Int? = null,
        longPollVersion: Int = DefaultApiParams.LONG_POLL_VERSION,
        groupId: Int? = null
    ): VkApiRequest<LongPollHistoryResponse>

    /**
     * @see <a href="https://vk.com/dev/messages.getLongPollServer">VK API</a>
     */
    fun getLongPollServer(
        needPts: Boolean = true,
        longPollVersion: Int = DefaultApiParams.LONG_POLL_VERSION,
        groupId: Int? = null
    ): VkApiRequest<LongPollServerResponse>

    /**
     * @param[count] maximum value 500
     * @see <a href="https://vk.com/dev/messages.getRecentCalls">VK API</a>
     */
    fun getRecentCalls(
        count: Int = 40,
        startMessageId: Int? = null,
        fields: List<ObjectField> = DefaultMethodsParams.userFields,
        extended: Boolean = false
    ): VkApiRequest<ExtendedListResponse<RecentCall>>

    /**
     * @see <a href="https://vk.com/dev/messages.joinChatByInviteLink">VK API</a>
     */
    fun joinChatByInviteLink(
        link: String
    ): VkApiRequest<JoinChatByLinkResponse>

    /**
     * @see <a href="https://vk.com/dev/messages.markAsAnsweredConversation">VK API</a>
     */
    fun markAsAnsweredConversation(
        peerId: Int,
        isAnswered: Boolean = true,
        groupId: Int
    ): VkApiRequest<BooleanInt>

    /**
     * @return list of IDs for successfully marked messages
     * @see <a href="https://vk.com/dev/messages.markAsImportant">VK API</a>
     */
    fun markAsImportant(
        messageIds: List<Int>,
        markAsImportant: Boolean
    ): VkApiRequest<List<Int>>

    /**
     * @see <a href="https://vk.com/dev/messages.markAsImportantConversation">VK API</a>
     */
    fun markAsImportantConversation(
        peerId: Int,
        isImportant: Boolean = true,
        groupId: Int
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/messages.markAsRead">VK API</a>
     */
    fun markAsRead(
        peerId: Int,
        startMessageId: Int? = null,
        groupId: Int? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/messages.pin">VK API</a>
     */
    fun pin(
        peerId: Int,
        messageId: Int,
        groupId: Int? = null
    ): VkApiRequest<Message.Pinned>

    /**
     * @see <a href="https://vk.com/dev/messages.removeChatUser">VK API</a>
     */
    fun removeChatUser(
        chatId: Int,
        memberId: Int,
        groupId: Int? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/messages.restore">VK API</a>
     */
    fun restore(
        messageId: Int,
        groupId: Int? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @param[count] maximum value 100
     * @see <a href="https://vk.com/dev/messages.search">VK API</a>
     */
    fun search(
        query: String,
        peerId: Int? = null,
        maxDate: GMTDate? = null,
        previewLength: Int = 0,
        offset: Int = 0,
        count: Int = 20,
        groupId: Int? = null
    ): VkApiRequest<DefaultListResponse<Message>>

    /**
     * @param[count] maximum value 100
     * @see <a href="https://vk.com/dev/messages.search">VK API</a>
     */
    fun searchExtended(
        query: String,
        peerId: Int? = null,
        maxDate: GMTDate? = null,
        previewLength: Int = 0,
        offset: Int = 0,
        count: Int = 20,
        groupId: Int? = null,
        fields: List<ObjectField> = DefaultMethodsParams.userFields
    ): VkApiRequest<ExtendedListResponse<Message>>

    /**
     * @see <a href="https://vk.com/dev/messages.searchConversations">VK API</a>
     */
    fun searchConversations(
        query: String,
        count: Int = 20,
        extended: Boolean = false,
        fields: List<ObjectField> = DefaultMethodsParams.userFields,
        groupId: Int? = null
    ): VkApiRequest<ExtendedListResponse<Conversation>>

    /**
     * @see <a href="https://vk.com/dev/messages.send">VK API</a>
     */
    fun send(
        peerId: Int,
        randomId: Int,
        message: String? = null,
        latitude: Int? = null,
        longitude: Int? = null,
        attachments: List<MessageAttachment>? = null,
        replyToMessageId: Int? = null,
        forwardedMessages: List<Int>? = null,
        stickerId: Int? = null,
        dontParseLink: Boolean = false,
        disableMentions: Boolean = false,
        groupId: Int? = null
    ): VkApiRequest<Int>

    /**
     * @see <a href="https://vk.com/dev/messages.setActivity">VK API</a>
     */
    fun setActivity(
        peerId: Int,
        type: String = "typing",
        groupId: Int? = null
    ): VkApiRequest<BooleanInt>

    /**
     * Note that [file] is a string returned by the API
     * after a successful upload of the document to the server.
     * @see <a href="https://vk.com/dev/messages.setChatPhoto">VK API</a>
     */
    fun setChatPhoto( // TODO its really support groupId?
        file: String,
        groupId: Int? = null
    ): VkApiRequest<ChatChangePhotoResponse>

    /**
     * @see <a href="https://vk.com/dev/messages.unpin">VK API</a>
     */
    fun unpin(
        peerId: Int,
        groupId: Int? = null
    ): VkApiRequest<BooleanInt>

}