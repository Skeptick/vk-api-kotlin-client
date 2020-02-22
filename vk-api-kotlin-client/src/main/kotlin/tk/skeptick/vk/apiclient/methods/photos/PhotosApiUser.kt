package tk.skeptick.vk.apiclient.methods.photos

import tk.skeptick.vk.apiclient.BooleanInt
import tk.skeptick.vk.apiclient.DefaultMethodsParams
import tk.skeptick.vk.apiclient.VkApiRequest
import tk.skeptick.vk.apiclient.domain.CommentAttachment
import tk.skeptick.vk.apiclient.domain.Media
import tk.skeptick.vk.apiclient.domain.PrivacySettings
import tk.skeptick.vk.apiclient.domain.models.Photo
import tk.skeptick.vk.apiclient.domain.models.Album
import tk.skeptick.vk.apiclient.domain.models.WallComment
import tk.skeptick.vk.apiclient.methods.*

interface PhotosApiUser : PhotosApiCommon {

    /**
     * @see <a href="https://vk.com/dev/photos.confirmTag">VK API</a>
     */
    fun confirmTag(
        photoId: Int,
        tagId: Int,
        ownerId: Int? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/photos.copy">VK API</a>
     */
    fun copy(
        ownerId: Int,
        photoId: Int,
        accessKey: String? = null
    ): VkApiRequest<Int>

    /**
     * @see <a href="https://vk.com/dev/photos.createAlbum">VK API</a>
     */
    fun createAlbum(
        title: String,
        description: String? = null,
        privacyView: PrivacySettings = PrivacySettings(PrivacySettings.Category.ALL),
        privacyComment: PrivacySettings = PrivacySettings(PrivacySettings.Category.ALL),
        groupId: Int? = null,
        isUploadByAdminsOnly: Boolean = false,
        isCommentsDisabled: Boolean = false
    ): VkApiRequest<Album>

    /**
     * @see <a href="https://vk.com/dev/photos.createComment">VK API</a>
     */
    fun createComment(
        photoId: Int,
        ownerId: Int? = null,
        message: String? = null,
        replyToCommentId: Int? = null,
        attachments: List<CommentAttachment>? = null,
        stickerId: Int? = null,
        fromGroup: Boolean = false,
        accessKey: String? = null,
        guid: String? = null
    ): VkApiRequest<Int>

    /**
     * @see <a href="https://vk.com/dev/photos.delete">VK API</a>
     */
    fun delete(
        photoId: Int,
        ownerId: Int? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/photos.deleteAlbum">VK API</a>
     */
    fun deleteAlbum(
        albumId: Int,
        groupId: Int? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/photos.deleteComment">VK API</a>
     */
    fun deleteComment(
        commentId: Int,
        ownerId: Int? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/photos.edit">VK API</a>
     */
    fun edit(
        photoId: Int,
        ownerId: Int? = null,
        caption: String = "",
        latitude: Double? = null,
        longitude: Double? = null,
        placeName: String? = null,
        foursquareId: String? = null,
        deletePlace: Boolean = false
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/photos.editAlbum">VK API</a>
     */
    fun editAlbum(
        albumId: Int,
        ownerId: Int? = null,
        title: String? = null,
        description: String? = null,
        privacyView: PrivacySettings? = null,
        privacyComment: PrivacySettings? = null,
        isUploadByAdminsOnly: Boolean? = null,
        isCommentsDisabled: Boolean? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/photos.editComment">VK API</a>
     */
    fun editComment(
        commentId: Int,
        ownerId: Int? = null,
        message: String? = null,
        attachments: List<CommentAttachment>? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @param[count] maximum value 1000
     * @see <a href="https://vk.com/dev/photos.get">VK API</a>
     */
    fun get(
        album: PhotoAlbumType,
        ownerId: Int? = null,
        photoIds: List<Int>? = null,
        reverse: Boolean = false,
        extended: Boolean = false,
        feedType: FeedType? = null,
        feed: Int? = null,
        offset: Int = 0,
        count: Int = 50
    ): VkApiRequest<DefaultListResponse<Photo>>

    /**
     * @see <a href="https://vk.com/dev/photos.getAlbums">VK API</a>
     */
    fun getAlbums(
        albumIds: List<Int>? = null,
        ownerId: Int? = null,
        offset: Int = 0,
        count: Int = Int.MAX_VALUE,
        needSystem: Boolean = false,
        needCovers: Boolean = false,
        needPhotoSizes: Boolean = false
    ): VkApiRequest<DefaultListResponse<Album>>

    /**
     * @see <a href="https://vk.com/dev/photos.getAlbumsCount">VK API</a>
     */
    fun getAlbumsCountGroup(
        groupId: Int
    ): VkApiRequest<Int>

    /**
     * @see <a href="https://vk.com/dev/photos.getAlbumsCount">VK API</a>
     */
    fun getAlbumsCountUser(
        userId: Int? = null
    ): VkApiRequest<Int>

    /**
     * @param[count] maximum value 200
     * @see <a href="https://vk.com/dev/photos.getAll">VK API</a>
     */
    fun getAll(
        ownerId: Int? = null,
        extended: Boolean = false,
        offset: Int = 0,
        count: Int = 20,
        noServiceAlbums: Boolean = false,
        needHidden: Boolean = false,
        skipHidden: Boolean = false
    ): VkApiRequest<DefaultListResponse<Photo>>

    /**
     * @param[count] maximum value 100
     * @see <a href="https://vk.com/dev/photos.getAllComments">VK API</a>
     */
    fun getAllComments(
        ownerId: Int? = null,
        albumId: Int? = null,
        needLikes: Boolean = false,
        offset: Int = 0,
        count: Int = 20
    ): VkApiRequest<DefaultListResponse<WallComment>>

    /**
     * @see <a href="https://vk.com/dev/photos.getById">VK API</a>
     */
    fun getById(
        photos: List<Media>,
        extended: Boolean = false
    ): VkApiRequest<List<Photo>>

    /**
     * @see <a href="https://vk.com/dev/photos.getChatUploadServer">VK API</a>
     */
    fun getChatUploadServer(
        chatId: Int,
        cropX: Int? = null,
        cropY: Int? = null,
        cropWidth: Int? = null
    ): VkApiRequest<DefaultUploadServerResponse>

    /**
     * @param[count] maximum value 100
     * @see <a href="https://vk.com/dev/photos.getComments">VK API</a>
     */
    fun getComments(
        photoId: Int,
        ownerId: Int? = null,
        needLikes: Boolean = false,
        startCommentId: Int? = null,
        offset: Int = 0,
        count: Int = 20,
        sort: CommentsSort = CommentsSort.ASC,
        accessKey: String? = null,
        extended: Boolean = false,
        fields: List<ObjectField> = DefaultMethodsParams.userFields
    ): VkApiRequest<ExtendedListResponse<WallComment>>

    /**
     * @see <a href="https://vk.com/dev/photos.getMarketAlbumUploadServer">VK API</a>
     */
    fun getMarketAlbumUploadServer(
        groupId: Int
    ): VkApiRequest<DefaultUploadServerResponse>

    /**
     * @see <a href="https://vk.com/dev/photos.getMarketUploadServer">VK API</a>
     */
    fun getMarketUploadServer(
        groupId: Int,
        isMainPhoto: Boolean = false,
        cropX: Int? = null,
        cropY: Int? = null,
        cropWidth: Int? = null
    ): VkApiRequest<DefaultUploadServerResponse>

    /**
     * @param[count] maximum value 100
     * @see <a href="https://vk.com/dev/photos.getNewTags">VK API</a>
     */
    fun getNewTags(
        offset: Int = 0,
        count: Int = 20
    ): VkApiRequest<DefaultListResponse<Photo>>

    /**
     * @see <a href="https://vk.com/dev/photos.getOwnerPhotoUploadServer">VK API</a>
     */
    fun getOwnerPhotoUploadServer(
        ownerId: Int? = null
    ): VkApiRequest<DefaultUploadServerResponse>

    /**
     * @see <a href="https://vk.com/dev/photos.getTags">VK API</a>
     */
    fun getTags(
        photoId: Int,
        ownerId: Int? = null,
        accessKey: String? = null
    ): VkApiRequest<List<Tag>>

    /**
     * @see <a href="https://vk.com/dev/photos.getUploadServer">VK API</a>
     */
    fun getUploadServer(
        albumId: Int,
        groupId: Int? = null
    ): VkApiRequest<PhotosUploadServerResponse>

    /**
     * @param[count] maximum value 1000
     * @see <a href="https://vk.com/dev/photos.getUserPhotos">VK API</a>
     */
    fun getUserPhotos(
        userId: Int? = null,
        offset: Int = 0,
        count: Int = 20,
        extended: Boolean = false,
        sortDescending: Boolean = true
    ): VkApiRequest<DefaultListResponse<Photo>>

    /**
     * @see <a href="https://vk.com/dev/photos.getWallUploadServer">VK API</a>
     */
    fun getWallUploadServer(
        groupId: Int? = null
    ): VkApiRequest<PhotosUploadServerResponse>

    /**
     * @see <a href="https://vk.com/dev/photos.makeCover">VK API</a>
     */
    fun makeCover(
        photoId: Int,
        albumId: Int,
        ownerId: Int? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/photos.move">VK API</a>
     */
    fun move(
        photoId: Int,
        targetAlbumId: Int,
        ownerId: Int? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/photos.putTag">VK API</a>
     */
    fun putTag(
        photoId: Int,
        userId: Int,
        x: Double,
        y: Double,
        x2: Double,
        y2: Double,
        ownerId: Int? = null
    ): VkApiRequest<Int>

    /**
     * @see <a href="https://vk.com/dev/photos.removeTag">VK API</a>
     */
    fun removeTag(
        photoId: Int,
        tagId: Int,
        ownerId: Int? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/photos.reorderAlbums">VK API</a>
     */
    fun reorderAlbums(
        albumId: Int,
        before: Int? = null,
        after: Int? = null,
        ownerId: Int? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/photos.reorderPhotos">VK API</a>
     */
    fun reorderPhotos(
        photoId: Int,
        before: Int? = null,
        after: Int? = null,
        ownerId: Int? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/photos.report">VK API</a>
     */
    fun report(
        ownerId: Int,
        photoId: Int,
        reason: PostReportComplaintType = PostReportComplaintType.SPAM
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/photos.reportComment">VK API</a>
     */
    fun reportComment(
        ownerId: Int,
        commentId: Int,
        reason: PostReportComplaintType = PostReportComplaintType.SPAM
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/photos.restore">VK API</a>
     */
    fun restore(
        photoId: Int,
        ownerId: Int? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/photos.restoreComment">VK API</a>
     */
    fun restoreComment(
        commentId: Int,
        ownerId: Int? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/photos.save">VK API</a>
     */
    fun save(
        albumId: Int,
        server: Int,
        photosList: String,
        hash: String,
        latitude: Double? = null,
        longitude: Double? = null,
        caption: String? = null,
        groupId: Int? = null
    ): VkApiRequest<List<Photo>>

    /**
     * @see <a href="https://vk.com/dev/photos.saveMarketAlbumPhoto">VK API</a>
     */
    fun saveMarketAlbumPhoto(
        groupId: Int,
        server: Int,
        photo: String,
        hash: String
    ): VkApiRequest<List<Photo>>

    /**
     * @see <a href="https://vk.com/dev/photos.saveMarketPhoto">VK API</a>
     */
    fun saveMarketPhoto(
        server: Int,
        photo: String,
        hash: String,
        cropData: String? = null,
        cropHash: String? = null,
        groupId: Int? = null
    ): VkApiRequest<List<Photo>>

    /**
     * @see <a href="https://vk.com/dev/photos.saveOwnerPhoto">VK API</a>
     */
    fun saveOwnerPhoto(
        server: Int,
        photo: String,
        hash: String
    ): VkApiRequest<SaveOwnerPhotoResponse>

    /**
     * @see <a href="https://vk.com/dev/photos.saveWallPhoto">VK API</a>
     */
    fun saveWallPhoto(
        server: Int,
        photo: String,
        hash: String,
        userId: Int? = null,
        groupId: Int? = null,
        latitude: Double? = null,
        longitude: Double? = null,
        caption: String? = null
    ): VkApiRequest<List<Photo>>

    /**
     * @param[count] maximum value 1000
     * @see <a href="https://vk.com/dev/photos.search">VK API</a>
     */
    fun search(
        query: String? = null,
        latitude: Double? = null,
        longitude: Double? = null,
        startTime: Int? = null,
        endTime: Int? = null,
        sort: PhotoSearchSort = PhotoSearchSort.DATE,
        offset: Int = 0,
        count: Int = 100,
        radius: Int = 5000
    ): VkApiRequest<DefaultListResponse<Photo>>

}