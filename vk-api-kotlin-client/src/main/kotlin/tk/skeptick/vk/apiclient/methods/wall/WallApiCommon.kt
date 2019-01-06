package tk.skeptick.vk.apiclient.methods.wall

import tk.skeptick.vk.apiclient.BooleanInt
import tk.skeptick.vk.apiclient.VkApiRequest

interface WallApiCommon {

    /**
     * @see <a href="https://vk.com/dev/wall.closeComments">VK API</a>
     */
    fun closeComments(
        ownerId: Int,
        postId: Int
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/wall.openComments">VK API</a>
     */
    fun openComments(
        ownerId: Int,
        postId: Int
    ): VkApiRequest<BooleanInt>

}