package tk.skeptick.vk.apiclient.methods.messages

import tk.skeptick.vk.apiclient.*
import tk.skeptick.vk.apiclient.domain.models.*
import tk.skeptick.vk.apiclient.domain.MessageAttachment
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
     * @see <a href="https://vk.com/dev/messages.deleteChatPhoto">VK API</a>
     */
    fun deleteChatPhoto(
        chatId: Int
    ): VkApiRequest<ChatChangePhotoResponse>

    /**
     * @see <a href="https://vk.com/dev/messages.denyMessagesFromGroup">VK API</a>
     */
    fun denyMessagesFromGroup(
        groupId: Int
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/messages.editChat">VK API</a>
     */
    fun editChat(
        chatId: Int,
        title: String
    ): VkApiRequest<BooleanInt>

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
     * @see <a href="https://vk.com/dev/messages.getInviteLink">VK API</a>
     */
    fun getInviteLink(
        peerId: Int,
        generateNewLink: Boolean = false
    ): VkApiRequest<ChatInviteLink>

    /**
     * @see <a href="https://vk.com/dev/messages.getLastActivity">VK API</a>
     */
    fun getLastActivity(
        userId: Int
    ): VkApiRequest<LastActivityResponse>

    /**
     * @see <a href="https://vk.com/dev/messages.joinChatByInviteLink">VK API</a>
     */
    fun joinChatByInviteLink(
        link: String
    ): VkApiRequest<JoinChatByLinkResponse>

    /**
     * @return list of IDs for successfully marked messages
     * @see <a href="https://vk.com/dev/messages.markAsImportant">VK API</a>
     */
    fun markAsImportant(
        messageIds: List<Int>,
        markAsImportant: Boolean
    ): VkApiRequest<List<Int>>

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
        memberId: Int,
        groupId: Int? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/messages.send">VK API</a>
     */
    fun send(
        peerId: Int,
        randomId: Int? = null,
        message: String? = null,
        lat: Int? = null,
        long: Int? = null,
        attachments: List<MessageAttachment>? = null,
        forwardedMessages: List<Int>? = null,
        stickerId: Int? = null
    ): VkApiRequest<Int>

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