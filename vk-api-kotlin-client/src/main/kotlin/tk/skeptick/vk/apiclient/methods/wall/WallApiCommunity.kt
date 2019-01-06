package tk.skeptick.vk.apiclient.methods.wall

import tk.skeptick.vk.apiclient.VkApiRequest
import tk.skeptick.vk.apiclient.domain.MessageAttachment

interface WallApiCommunity : WallApiCommon {

    /**
     * @see <a href="https://vk.com/dev/wall.createComment">VK API</a>
     */
    fun createComment(
        postId: Int,
        ownerId: Int? = null,
        message: String? = null,
        replyToCommentId: Int? = null,
        attachments: List<MessageAttachment>? = null,
        stickerId: Int? = null,
        guid: String? = null
    ): VkApiRequest<CreateCommentResponse>

}