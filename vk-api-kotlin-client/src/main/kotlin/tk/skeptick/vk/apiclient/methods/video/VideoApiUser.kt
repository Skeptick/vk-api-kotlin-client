package tk.skeptick.vk.apiclient.methods.video

import tk.skeptick.vk.apiclient.BooleanInt
import tk.skeptick.vk.apiclient.DefaultMethodsParams
import tk.skeptick.vk.apiclient.VkApiRequest
import tk.skeptick.vk.apiclient.domain.CommentAttachment
import tk.skeptick.vk.apiclient.domain.Media
import tk.skeptick.vk.apiclient.domain.PrivacySettings
import tk.skeptick.vk.apiclient.domain.models.Video
import tk.skeptick.vk.apiclient.domain.models.WallComment
import tk.skeptick.vk.apiclient.methods.*

interface VideoApiUser : VideoApiCommon {

    /**
     * @see <a href="https://vk.com/dev/video.add">VK API</a>
     */
    fun add(
        videoId: Int,
        ownerId: Int,
        targetId: Int? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/video.addAlbum">VK API</a>
     */
    fun addAlbum(
        title: String,
        privacy: PrivacySettings = PrivacySettings(PrivacySettings.Category.ALL),
        groupId: Int? = null
    ): VkApiRequest<AddVideoAlbumResponse>

    /**
     * @see <a href="https://vk.com/dev/video.addToAlbum">VK API</a>
     */
    fun addToAlbum(
        videoId: Int,
        ownerId: Int,
        albumIds: List<VideoAlbumType> = listOf(VideoAlbumType.Added),
        targetId: Int? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/video.createComment">VK API</a>
     */
    fun createComment(
        videoId: Int,
        ownerId: Int? = null,
        message: String? = null,
        replyToCommentId: Int? = null,
        attachments: List<CommentAttachment>? = null,
        stickerId: Int? = null,
        fromGroup: Boolean = false,
        guid: String? = null
    ): VkApiRequest<Int>

    /**
     * @see <a href="https://vk.com/dev/video.delete">VK API</a>
     */
    fun delete(
        videoId: Int,
        ownerId: Int? = null,
        targetId: Int? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/video.deleteAlbum">VK API</a>
     */
    fun deleteAlbum(
        albumId: Int,
        groupId: Int? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/video.deleteComment">VK API</a>
     */
    fun deleteComment(
        commentId: Int,
        ownerId: Int? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/video.edit">VK API</a>
     */
    fun edit(
        videoId: Int,
        ownerId: Int? = null,
        name: String? = null,
        description: String? = null,
        privacyView: PrivacySettings? = null,
        privacyComment: PrivacySettings? = null,
        disableComments: Boolean? = null,
        enableRepeat: Boolean? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/video.editAlbum">VK API</a>
     */
    fun editAlbum(
        albumId: Int,
        title: String,
        privacy: PrivacySettings? = null,
        groupId: Int? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/video.editComment">VK API</a>
     */
    fun editComment(
        commentId: Int,
        ownerId: Int? = null,
        message: String? = null,
        attachments: List<CommentAttachment>? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @param[count] maximum value 200
     * @see <a href="https://vk.com/dev/video.get">VK API</a>
     */
    fun get(
        ownerId: Int? = null,
        videos: List<Media>? = null,
        albumId: Int? = null,
        count: Int = 100,
        offset: Int = 0,
        extended: Boolean = false
    ): VkApiRequest<DefaultListResponse<Video>>

    /**
     * @see <a href="https://vk.com/dev/video.getAlbumById">VK API</a>
     */
    fun getAlbumById(
        albumId: Int,
        ownerId: Int? = null
    ): VkApiRequest<VideoAlbum>

    /**
     * @param[count] maximum value 100
     * @see <a href="https://vk.com/dev/video.getAlbums">VK API</a>
     */
    fun getAlbums(
        ownerId: Int? = null,
        offset: Int = 0,
        count: Int = 50,
        extended: Boolean = false,
        needSystem: Boolean = false
    ): VkApiRequest<DefaultListResponse<VideoAlbum>>

    /**
     * @see <a href="https://vk.com/dev/video.getAlbumsByVideo">VK API</a>
     */
    fun getAlbumsIdsByVideo(
        videoId: Int,
        ownerId: Int,
        targetId: Int? = null
    ): VkApiRequest<List<Int>>

    /**
     * @see <a href="https://vk.com/dev/video.getAlbumsByVideo">VK API</a>
     */
    fun getAlbumsByVideo(
        videoId: Int,
        ownerId: Int,
        targetId: Int? = null
    ): VkApiRequest<DefaultListResponse<VideoAlbum>>

    /**
     * @param[count] maximum value 100
     * @see <a href="https://vk.com/dev/video.getComments">VK API</a>
     */
    fun getComments(
        videoId: Int,
        ownerId: Int? = null,
        needLikes: Boolean = false,
        startCommentId: Int? = null,
        offset: Int = 0,
        count: Int = 20,
        sort: CommentsSort = CommentsSort.ASC,
        extended: Boolean = false,
        fields: List<ObjectField> = DefaultMethodsParams.userFields
    ): VkApiRequest<ExtendedListResponse<WallComment>>

    /**
     * @return ID of the albums from which the video was deleted
     * @see <a href="https://vk.com/dev/video.removeFromAlbum">VK API</a>
     */
    fun removeFromAlbum(
        videoId: Int,
        ownerId: Int,
        albumIds: List<VideoAlbumType>,
        targetId: Int? = null
    ): VkApiRequest<DefaultListResponse<Int>>

    /**
     * @see <a href="https://vk.com/dev/video.reorderAlbums">VK API</a>
     */
    fun reorderAlbums(
        albumId: Int,
        before: Int? = null,
        after: Int? = null,
        ownerId: Int? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/video.reorderVideos">VK API</a>
     */
    fun reorderVideos(
        videoId: Int,
        ownerId: Int,
        albumId: Int,
        before: Media? = null,
        after: Media? = null,
        targetId: Int? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/video.report">VK API</a>
     */
    fun report(
        videoId: Int,
        ownerId: Int,
        reason: PostReportComplaintType = PostReportComplaintType.SPAM,
        comment: String? = null,
        searchQuery: String? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/video.reportComment">VK API</a>
     */
    fun reportComment(
        ownerId: Int,
        commentId: Int,
        reason: PostReportComplaintType = PostReportComplaintType.SPAM
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/video.restore">VK API</a>
     */
    fun restore(
        videoId: Int,
        ownerId: Int? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/video.restoreComment">VK API</a>
     */
    fun restoreComment(
        commentId: Int,
        ownerId: Int? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/video.save">VK API</a>
     */
    fun save(
        name: String? = null,
        description: String? = null,
        isPrivate: Boolean = false,
        publishOnWall: Boolean = false,
        link: String? = null,
        groupId: Int? = null,
        albumId: VideoAlbumType? = null,
        privacyView: PrivacySettings = PrivacySettings(PrivacySettings.Category.ALL),
        privacyComment: PrivacySettings = PrivacySettings(PrivacySettings.Category.ALL),
        disableComments: Boolean = false,
        enableRepeat: Boolean = false,
        enableCompression: Boolean = false
    ): VkApiRequest<SaveVideoResponse>

    /**
     * @param[count] maximum value 200
     * @see <a href="https://vk.com/dev/video.search">VK API</a>
     */
    fun search(
        query: String,
        sort: VideoSearchSort = VideoSearchSort.RELEVANCE,
        onlyHd: Boolean = false,
        disableSafeSearch: Boolean = false,
        filters: List<VideoSearchFilter>? = null,
        searchOwn: Boolean = false,
        offset: Int = 0,
        count: Int = 20,
        longerThan: Int? = null, // sec
        shorterThan: Int? = null, // sec
        extended: Boolean = false
    ): VkApiRequest<ExtendedListResponse<Video>>

}