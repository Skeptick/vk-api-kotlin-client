package tk.skeptick.vk.apiclient.methods.messages

import tk.skeptick.vk.apiclient.BooleanInt
import tk.skeptick.vk.apiclient.VkApiRequest
import tk.skeptick.vk.apiclient.domain.Keyboard
import tk.skeptick.vk.apiclient.domain.MessageAttachment
import tk.skeptick.vk.apiclient.domain.MessagePayload

interface MessagesApiCommunity : MessagesApiCommon {

    /**
     * @see <a href="https://vk.com/dev/messages.markAsAnsweredConversation">VK API</a>
     */
    fun markAsAnsweredConversation(
        peerId: Int,
        isAnswered: Boolean = true
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/messages.markAsImportantConversation">VK API</a>
     */
    fun markAsImportantConversation(
        peerId: Int,
        isImportant: Boolean = true
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/messages.send">VK API</a>
     */
    fun send(
        peerId: Int,
        randomId: Int? = null,
        message: String? = null,
        latitude: Int? = null,
        longitude: Int? = null,
        attachments: List<MessageAttachment>? = null,
        forwardedMessages: List<Int>? = null,
        stickerId: Int? = null,
        keyboard: Keyboard? = null,
        payload: MessagePayload? = null
    ): VkApiRequest<Int>

    /**
     * @see <a href="https://vk.com/dev/messages.send">VK API</a>
     */
    fun sendBulk(
        userIds: List<Int>,
        randomId: Int? = null,
        message: String? = null,
        latitude: Int? = null,
        longitude: Int? = null,
        attachments: List<MessageAttachment>? = null,
        forwardedMessages: List<Int>? = null,
        stickerId: Int? = null,
        keyboard: Keyboard? = null,
        payload: MessagePayload? = null
    ): VkApiRequest<List<SendBulkMessageResponse>>

}