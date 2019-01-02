package tk.skeptick.vk.apiclient.methods.messages

import tk.skeptick.vk.apiclient.*
import tk.skeptick.vk.apiclient.domain.AttachmentType
import tk.skeptick.vk.apiclient.domain.MessageAttachment
import tk.skeptick.vk.apiclient.domain.models.*
import tk.skeptick.vk.apiclient.methods.ConversationFilter
import tk.skeptick.vk.apiclient.methods.DefaultListResponse
import tk.skeptick.vk.apiclient.methods.ExtendedListResponse
import tk.skeptick.vk.apiclient.methods.ObjectField
import java.util.*

interface MessagesApiCommon {

    /**
     * @return Success status for every deleted message
     * @see <a href="https://vk.com/dev/messages.delete">VK API</a>
     */
    fun delete(
        messageIds: List<Int>,
        markAsSpam: Boolean = false,
        deleteForAll: Boolean = false
    ): VkApiRequest<Map<Int, BooleanInt>>

    /**
     * @see <a href="https://vk.com/dev/messages.deleteChatPhoto">VK API</a>
     */
    fun deleteChatPhoto(
        chatId: Int,
        groupId: Int? = null
    ): VkApiRequest<ChatChangePhotoResponse>

    /**
     * @param[count] maximum value 10000
     * @see <a href="https://vk.com/dev/messages.deleteConversation">VK API</a>
     */
    fun deleteConversation(
        peerId: Int,
        offset: Int = 0,
        count: Int = 10000
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
        keepSnippets: Boolean = true
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/messages.editChat">VK API</a>
     */
    fun editChat(
        chatId: Int,
        title: String
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/messages.getByConversationMessageId">VK API</a>
     */
    fun getByConversationMessageId(
        peerId: Int,
        conversationMessageIds: List<Int>,
        extended: Boolean = false,
        fields: List<ObjectField> = DefaultMethodsParams.userFields
    ): VkApiRequest<ExtendedListResponse<Message>>

    /**
     * @see <a href="https://vk.com/dev/messages.getById">VK API</a>
     */
    fun getById(
        messageIds: List<Int>,
        previewLength: Int = 0,
        extended: Boolean = false,
        fields: List<ObjectField> = DefaultMethodsParams.userFields
    ): VkApiRequest<ExtendedListResponse<Message>>

    /**
     * @see <a href="https://vk.com/dev/messages.getConversationMembers">VK API</a>
     */
    fun getConversationMembers(
        peerId: Int,
        fields: List<ObjectField> = DefaultMethodsParams.userFields
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
        fields: List<ObjectField> = DefaultMethodsParams.userFields
    ): VkApiRequest<ConversationsListResponse>

    /**
     * @see <a href="https://vk.com/dev/messages.getConversationsById">VK API</a>
     */
    fun getConversationsById(
        peerIds: List<Int>,
        extended: Boolean = false,
        fields: List<ObjectField> = DefaultMethodsParams.userFields
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
        fields: List<ObjectField> = DefaultMethodsParams.userFields
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
        fields: List<ObjectField> = DefaultMethodsParams.userFields
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
        fields: List<ObjectField> = DefaultMethodsParams.userFields
    ): VkApiRequest<ImportantMessagesResponse>

    /**
     * @see <a href="https://vk.com/dev/messages.getInviteLink">VK API</a>
     */
    fun getInviteLink(
        peerId: Int,
        generateNewLink: Boolean = false
    ): VkApiRequest<ChatInviteLink>

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
        longPollVersion: Int = DefaultApiParams.LONG_POLL_VERSION
    ): VkApiRequest<LongPollHistoryResponse>

    /**
     * @see <a href="https://vk.com/dev/messages.getLongPollServer">VK API</a>
     */
    fun getLongPollServer(
        needPts: Boolean = true,
        longPollVersion: Int = DefaultApiParams.LONG_POLL_VERSION
    ): VkApiRequest<LongPollServerResponse>

    /**
     * @see <a href="https://vk.com/dev/messages.isMessagesFromGroupAllowed">VK API</a>
     */
    fun isMessagesFromGroupAllowed(
        groupId: Int,
        userId: Int
    ): VkApiRequest<MessagesFromGroupAllowedResponse>

    /**
     * @see <a href="https://vk.com/dev/messages.markAsRead">VK API</a>
     */
    fun markAsRead(
        peerId: Int,
        startMessageId: Int? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/messages.pin">VK API</a>
     */
    fun pin(
        peerId: Int,
        messageId: Int
    ): VkApiRequest<Message.Pinned>

    /**
     * @see <a href="https://vk.com/dev/messages.removeChatUser">VK API</a>
     */
    fun removeChatUser(
        chatId: Int,
        memberId: Int
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/messages.restore">VK API</a>
     */
    fun restore(
        messageId: Int
    ): VkApiRequest<BooleanInt>

    /**
     * @param[count] maximum value 100
     * @see <a href="https://vk.com/dev/messages.search">VK API</a>
     */
    fun search(
        query: String,
        peerId: Int? = null,
        maxDate: Date? = null,
        previewLength: Int = 0,
        offset: Int = 0,
        count: Int = 20
    ): VkApiRequest<DefaultListResponse<Message>>

    /**
     * @param[count] maximum value 100
     * @see <a href="https://vk.com/dev/messages.search">VK API</a>
     */
    fun searchExtended(
        query: String,
        peerId: Int? = null,
        maxDate: Date? = null,
        previewLength: Int = 0,
        offset: Int = 0,
        count: Int = 20,
        fields: List<ObjectField> = DefaultMethodsParams.userFields
    ): VkApiRequest<ExtendedListResponse<Message>>

    /**
     * @see <a href="https://vk.com/dev/messages.searchConversations">VK API</a>
     */
    fun searchConversations(
        query: String,
        count: Int = 20,
        extended: Boolean = false,
        fields: List<ObjectField> = DefaultMethodsParams.userFields
    ): VkApiRequest<ExtendedListResponse<Conversation>>

    /**
     * @see <a href="https://vk.com/dev/messages.setActivity">VK API</a>
     */
    fun setActivity(
        peerId: Int,
        type: String = "typing"
    ): VkApiRequest<BooleanInt>

    /**
     * Note that [file] is a string returned by the API
     * after a successful upload of the document to the server.
     * @see <a href="https://vk.com/dev/messages.setChatPhoto">VK API</a>
     */
    fun setChatPhoto(
        file: String
    ): VkApiRequest<ChatChangePhotoResponse>

    /**
     * @see <a href="https://vk.com/dev/messages.unpin">VK API</a>
     */
    fun unpin(
        peerId: Int
    ): VkApiRequest<BooleanInt>

}