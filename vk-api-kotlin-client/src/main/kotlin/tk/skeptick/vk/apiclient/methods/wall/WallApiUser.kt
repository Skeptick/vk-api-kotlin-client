package tk.skeptick.vk.apiclient.methods.wall

import tk.skeptick.vk.apiclient.BooleanInt
import tk.skeptick.vk.apiclient.VkApiRequest
import tk.skeptick.vk.apiclient.domain.MessageAttachment

interface WallApiUser : WallApiCommon {

    /**
     * @see <a href="https://vk.com/dev/wall.createComment">VK API</a>
     */
    fun createComment(
        postId: Int,
        ownerId: Int? = null,
        fromGroup: Int = 0,
        message: String? = null,
        replyToCommentId: Int? = null,
        attachments: List<MessageAttachment>? = null,
        stickerId: Int? = null,
        guid: String? = null
    ): VkApiRequest<CreateCommentResponse>

    /**
     * @see <a href="https://vk.com/dev/wall.delete">VK API</a>
     */
    fun delete(
        postId: Int,
        ownerId: Int? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/wall.deleteComment">VK API</a>
     */
    fun deleteComment(
        commentId: Int,
        ownerId: Int? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/wall.edit">VK API</a>
     */
    fun edit(
        postId: Int,
        ownerId: Int? = null,
        friendsOnly: Boolean? = null,
        message: String? = null,
        attachments: List<MessageAttachment>? = null,
        servicesForExport: List<String>? = null,
        signed: Boolean? = null,
        publishDate: Int? = null,
        latitude: Double? = null,
        longitude: Double? = null,
        placeId: Int? = null,
        markAsAds: Boolean? = null,
        closeComments: Boolean? = null
    ): VkApiRequest<BooleanInt>

}