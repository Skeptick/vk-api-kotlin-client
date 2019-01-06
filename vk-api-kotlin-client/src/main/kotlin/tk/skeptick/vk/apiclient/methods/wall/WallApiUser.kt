package tk.skeptick.vk.apiclient.methods.wall

import io.ktor.util.date.GMTDate
import tk.skeptick.vk.apiclient.BooleanInt
import tk.skeptick.vk.apiclient.DefaultMethodsParams
import tk.skeptick.vk.apiclient.VkApiRequest
import tk.skeptick.vk.apiclient.domain.MessageAttachment
import tk.skeptick.vk.apiclient.domain.models.WallComment
import tk.skeptick.vk.apiclient.domain.models.WallPost
import tk.skeptick.vk.apiclient.methods.*

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
        attachmentLink: String? = null,
        servicesForExport: List<String>? = null,
        signed: Boolean? = null,
        publishDate: GMTDate? = null,
        latitude: Double? = null,
        longitude: Double? = null,
        placeId: Int? = null,
        markAsAds: Boolean? = null,
        closeComments: Boolean? = null,
        posterBackgroundId: Int?
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/wall.editAdsStealth">VK API</a>
     */
    fun editAdsStealth(
        postId: Int,
        ownerId: Int? = null,
        message: String? = null,
        attachments: List<MessageAttachment>? = null,
        attachmentLink: String? = null,
        signed: Boolean = false,
        latitude: Double? = null,
        longitude: Double? = null,
        placeId: Int? = null,
        linkButton: LinkButtonType? = null,
        linkTitle: String? = null,
        linkImage: String? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/wall.editComment">VK API</a>
     */
    fun editComment(
        commentId: Int,
        ownerId: Int? = null,
        message: String? = null,
        attachments: List<MessageAttachment>? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @param[count] maximum value 100
     * @see <a href="https://vk.com/dev/wall.get">VK API</a>
     */
    fun get(
        ownerId: Int? = null,
        offset: Int = 0,
        count: Int = 100,
        filter: WallPostFilter = WallPostFilter.ALL,
        extended: Boolean = false,
        fields: List<ObjectField> = DefaultMethodsParams.userFields
    ): VkApiRequest<ExtendedListResponse<WallPost>>

    /**
     * @param[posts] map where Key is owner id, and Value is list of posts ids. Max 100 posts.
     * @see <a href="https://vk.com/dev/wall.getById">VK API</a>
     */
    fun getById(
        posts: Map<Int, List<Int>>,
        copyHistoryDepth: Int = 2
    ): VkApiRequest<List<WallPost>>

    /**
     * @param[posts] map where Key is owner id, and Value is list of posts ids. Max 100 posts.
     * @see <a href="https://vk.com/dev/wall.getById">VK API</a>
     */
    fun getByIdExtended(
        posts: Map<Int, List<Int>>,
        copyHistoryDepth: Int = 2,
        fields: List<ObjectField> = DefaultMethodsParams.userFields
    ): VkApiRequest<ExtendedListResponse<WallPost>>

    /**
     * @see <a href="https://vk.com/dev/wall.getComment">VK API</a>
     */
    fun getComment(
        commentId: Int,
        ownerId: Int? = null,
        extended: Boolean = false,
        fields: List<ObjectField> = DefaultMethodsParams.userFields
    ): VkApiRequest<ExtendedListResponse<WallComment>>

    /**
     * @param[threadItemsCount] maximum value 10
     * @param[count] maximum value 100
     * @see <a href="https://vk.com/dev/wall.getComments">VK API</a>
     */
    fun getComments(
        postId: Int,
        ownerId: Int? = null,
        commentId: Int? = null,
        needLikes: Boolean = false,
        startCommentId: Int? = null,
        threadItemsCount: Int = 0,
        offset: Int = 0,
        count: Int = 10,
        sort: CommentsSort = CommentsSort.ASC,
        previewLength: Int = 0,
        extended: Boolean = false,
        fields: List<ObjectField> = DefaultMethodsParams.userFields
    ): VkApiRequest<CommentsListResponse>

    /**
     * @param[count] maximum value 1000
     * @see <a href="https://vk.com/dev/wall.getReposts">VK API</a>
     */
    fun getReposts(
        postId: Int,
        ownerId: Int? = null,
        offset: Int = 0,
        count: Int = 20
    ): VkApiRequest<ExtendedListResponse<WallPost>>

    /**
     * @see <a href="https://vk.com/dev/wall.pin">VK API</a>
     */
    fun pin(
        postId: Int,
        ownerId: Int? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/wall.post">VK API</a>
     */
    fun post(
        ownerId: Int? = null,
        friendsOnly: Boolean = false,
        fromGroup: Boolean = false,
        message: String? = null,
        attachments: List<MessageAttachment>? = null,
        attachmentLink: String? = null,
        servicesForExport: List<String>? = null,
        signed: Boolean = false,
        publishDate: GMTDate? = null,
        latitude: Double? = null,
        longitude: Double? = null,
        placeId: Int? = null,
        postId: Int? = null,
        guid: String? = null,
        markAsAds: Boolean = false,
        closeComments: Boolean = false
    ): VkApiRequest<PostResponse>

    /**
     * @see <a href="https://vk.com/dev/wall.postAdsStealth">VK API</a>
     */
    fun postAdsStealth(
        ownerId: Int,
        message: String? = null,
        attachments: List<MessageAttachment>? = null,
        attachmentLink: String? = null,
        signed: Boolean = false,
        latitude: Double? = null,
        longitude: Double? = null,
        placeId: Int? = null,
        guid: String? = null,
        linkButton: LinkButtonType? = null,
        linkTitle: String? = null,
        linkImage: String? = null
    ): VkApiRequest<PostResponse>

    /**
     * @see <a href="https://vk.com/dev/wall.reportComment">VK API</a>
     */
    fun reportComment(
        ownerId: Int,
        commentId: Int,
        reason: PostReportComplaintType = PostReportComplaintType.SPAM
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/wall.reportPost">VK API</a>
     */
    fun reportPost(
        ownerId: Int,
        postId: Int,
        reason: PostReportComplaintType = PostReportComplaintType.SPAM
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/wall.repost">VK API</a>
     */
    fun repost(
        repostObject: MessageAttachment,
        message: String? = null,
        groupId: Int? = null,
        markAsAds: Boolean = false
    ): VkApiRequest<RepostResponse>

    /**
     * @see <a href="https://vk.com/dev/wall.restore">VK API</a>
     */
    fun restore(
        postId: Int,
        ownerId: Int? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/wall.restoreComment">VK API</a>
     */
    fun restoreComment(
        commentId: Int,
        ownerId: Int? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @param[count] maximum value 100
     * @see <a href="https://vk.com/dev/wall.search">VK API</a>
     */
    fun search(
        query: String,
        ownerId: Int? = null,
        ownersOnly: Boolean = false,
        count: Int = 20,
        offset: Int = 0,
        extended: Boolean = false,
        fields: List<ObjectField> = DefaultMethodsParams.userFields
    ): VkApiRequest<ExtendedListResponse<WallPost>>

    /**
     * @see <a href="https://vk.com/dev/wall.unpin">VK API</a>
     */
    fun unpin(
        postId: Int,
        ownerId: Int? = null
    ): VkApiRequest<BooleanInt>

}