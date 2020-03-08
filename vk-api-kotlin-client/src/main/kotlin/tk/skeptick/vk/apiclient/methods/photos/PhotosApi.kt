package tk.skeptick.vk.apiclient.methods.photos

import kotlinx.serialization.builtins.list
import kotlinx.serialization.builtins.serializer
import tk.skeptick.vk.apiclient.*
import tk.skeptick.vk.apiclient.MethodsContext
import tk.skeptick.vk.apiclient.domain.CommentAttachment
import tk.skeptick.vk.apiclient.domain.Media
import tk.skeptick.vk.apiclient.domain.PrivacySettings
import tk.skeptick.vk.apiclient.domain.models.Photo
import tk.skeptick.vk.apiclient.domain.models.Album
import tk.skeptick.vk.apiclient.domain.models.Community
import tk.skeptick.vk.apiclient.domain.models.WallComment
import tk.skeptick.vk.apiclient.methods.*

class PhotosApi(override val client: ApiClient)
    : PhotosApiUser, PhotosApiCommunity, MethodsContext {

    override fun confirmTag(
        photoId: Int,
        tagId: Int,
        ownerId: Int?
    ): VkApiRequest<BooleanInt> =
        Methods.confirmTag.httpGet(BooleanInt.serializer()) {
            append("photo_id", photoId)
            append("tag_id", tagId)
            append("owner_id", ownerId)
        }

    override fun copy(
        ownerId: Int,
        photoId: Int,
        accessKey: String?
    ): VkApiRequest<Int> =
        Methods.copy.httpGet(Int.serializer()) {
            append("owner_id", ownerId)
            append("photo_id", photoId)
            append("access_key", accessKey)
        }

    override fun createAlbum(
        title: String,
        description: String?,
        privacyView: PrivacySettings,
        privacyComment: PrivacySettings,
        groupId: Int?,
        isUploadByAdminsOnly: Boolean,
        isCommentsDisabled: Boolean
    ): VkApiRequest<Album> =
        Methods.createAlbum.httpPost(Album.serializer()) {
            append("title", title)
            append("description", description)
            append("privacy_view", privacyView.toRequestString())
            append("privacy_comment", privacyComment.toRequestString())
            append("group_id", groupId)
            append("upload_by_admins_only", isUploadByAdminsOnly.asInt())
            append("comments_disabled", isCommentsDisabled.asInt())
        }

    override fun createComment(
        photoId: Int,
        ownerId: Int?,
        message: String?,
        replyToCommentId: Int?,
        attachments: List<CommentAttachment>?,
        stickerId: Int?,
        fromGroup: Boolean,
        accessKey: String?,
        guid: String?
    ): VkApiRequest<Int> =
        Methods.createComment.httpPost(Int.serializer()) {
            append("photo_id", photoId)
            append("owner_id", ownerId)
            append("message", message)
            append("attachments", attachments?.joinToString(",", transform = CommentAttachment::attachment))
            append("sticker_id", stickerId)
            append("from_group", fromGroup.asInt())
            append("access_key", accessKey)
            append("guid", guid)
        }

    override fun delete(
        photoId: Int,
        ownerId: Int?
    ): VkApiRequest<BooleanInt> =
        Methods.delete.httpGet(BooleanInt.serializer()) {
            append("photo_id", photoId)
            append("owner_id", ownerId)
        }

    override fun deleteAlbum(
        albumId: Int,
        groupId: Int?
    ): VkApiRequest<BooleanInt> =
        Methods.deleteAlbum.httpGet(BooleanInt.serializer()) {
            append("album_id", albumId)
            append("group_id", groupId)
        }

    override fun deleteComment(
        commentId: Int,
        ownerId: Int?
    ): VkApiRequest<BooleanInt> =
        Methods.deleteComment.httpGet(BooleanInt.serializer()) {
            append("comment_id", commentId)
            append("owner_id", ownerId)
        }

    override fun edit(
        photoId: Int,
        ownerId: Int?,
        caption: String,
        latitude: Double?,
        longitude: Double?,
        placeName: String?,
        foursquareId: String?,
        deletePlace: Boolean
    ): VkApiRequest<BooleanInt> =
        Methods.edit.httpPost(BooleanInt.serializer()) {
            append("photo_id", photoId)
            append("owner_id", ownerId)
            append("caption", caption)
            append("latitude", latitude)
            append("longitude", longitude)
            append("place_str", placeName)
            append("foursquare_id", foursquareId)
            append("delete_place", deletePlace.asInt())
        }

    override fun editAlbum(
        albumId: Int,
        ownerId: Int?,
        title: String?,
        description: String?,
        privacyView: PrivacySettings?,
        privacyComment: PrivacySettings?,
        isUploadByAdminsOnly: Boolean?,
        isCommentsDisabled: Boolean?
    ): VkApiRequest<BooleanInt> =
        Methods.editAlbum.httpPost(BooleanInt.serializer()) {
            append("album_id", albumId)
            append("owner_id", ownerId)
            append("title", title)
            append("description", description)
            append("privacy_view", privacyView?.toRequestString())
            append("privacy_comment", privacyComment?.toRequestString())
            append("upload_by_admins_only", isUploadByAdminsOnly?.asInt())
            append("comments_disabled", isCommentsDisabled?.asInt())
        }

    override fun editComment(
        commentId: Int,
        ownerId: Int?,
        message: String?,
        attachments: List<CommentAttachment>?
    ): VkApiRequest<BooleanInt> =
        Methods.editComment.httpPost(BooleanInt.serializer()) {
            append("comment_id", commentId)
            append("owner_id", ownerId)
            append("message", message)
            append("attachments", attachments?.joinToString(",", transform = CommentAttachment::attachment))
        }

    override fun get(
        album: PhotoAlbumType,
        ownerId: Int?,
        photoIds: List<Int>?,
        reverse: Boolean,
        extended: Boolean,
        feedType: FeedType?,
        feed: Int?,
        offset: Int,
        count: Int
    ): VkApiRequest<DefaultListResponse<Photo>> =
        Methods.get.httpPost(list(Photo.serializer())) {
            append("album_id", album.value)
            append("owner_id", ownerId)
            append("photo_ids", photoIds?.joinToString(","))
            append("rev", reverse.asInt())
            append("extended", extended.asInt())
            append("feed_type", feedType?.value)
            append("feed", feed)
            append("offset", offset)
            append("count", count)
        }

    override fun getAlbums(
        albumIds: List<Int>?,
        ownerId: Int?,
        offset: Int,
        count: Int,
        needSystem: Boolean,
        needCovers: Boolean,
        needPhotoSizes: Boolean
    ): VkApiRequest<DefaultListResponse<Album>> =
        Methods.getAlbums.httpPost(list(Album.serializer())) {
            append("album_ids", albumIds?.joinToString(","))
            append("owner_id", ownerId)
            append("offset", offset)
            append("count", count)
            append("need_system", needSystem.asInt())
            append("need_covers", needCovers.asInt())
            append("photo_sizes", needPhotoSizes.asInt())
        }

    override fun getAlbumsCountGroup(
        groupId: Int
    ): VkApiRequest<Int> =
        Methods.getAlbumsCount.httpGet(Int.serializer()) {
            append("group_id", groupId)
        }

    override fun getAlbumsCountUser(
        userId: Int?
    ): VkApiRequest<Int> =
        Methods.getAlbumsCount.httpGet(Int.serializer()) {
            append("user_id", userId)
        }

    override fun getAll(
        ownerId: Int?,
        extended: Boolean,
        offset: Int,
        count: Int,
        noServiceAlbums: Boolean,
        needHidden: Boolean,
        skipHidden: Boolean
    ): VkApiRequest<DefaultListResponse<Photo>> =
        Methods.getAll.httpGet(list(Photo.serializer())) {
            append("owner_id", ownerId)
            append("extended", extended.asInt())
            append("offset", offset)
            append("count", count)
            append("no_service_albums", noServiceAlbums.asInt())
            append("need_hidden", needHidden.asInt())
            append("skip_hidden", skipHidden.asInt())
        }

    override fun getAllComments(
        ownerId: Int?,
        albumId: Int?,
        needLikes: Boolean,
        offset: Int,
        count: Int
    ): VkApiRequest<DefaultListResponse<WallComment>> =
        Methods.getAllComments.httpGet(list(WallComment.serializer())) {
            append("owner_id", ownerId)
            append("album_id", albumId)
            append("need_likes", needLikes.asInt())
            append("offset", offset)
            append("count", count)
        }

    override fun getById(
        photos: List<Media>,
        extended: Boolean
    ): VkApiRequest<List<Photo>> =
        Methods.getById.httpPost(Photo.serializer().list) {
            append("photos", photos.joinToString(",", transform = Media::media))
            append("extended", extended.asInt())
        }

    override fun getChatUploadServer(
        chatId: Int,
        cropX: Int?,
        cropY: Int?,
        cropWidth: Int?
    ): VkApiRequest<DefaultUploadServerResponse> =
        Methods.getChatUploadServer.httpGet(DefaultUploadServerResponse.serializer()) {
            append("chat_id", chatId)
            append("crop_x", cropX)
            append("crop_y", cropY)
            append("crop_width", cropWidth)
        }

    override fun getComments(
        photoId: Int,
        ownerId: Int?,
        needLikes: Boolean,
        startCommentId: Int?,
        offset: Int,
        count: Int,
        sort: CommentsSort,
        accessKey: String?,
        extended: Boolean,
        fields: List<ObjectField>
    ): VkApiRequest<ExtendedListResponse<WallComment>> =
        Methods.getComments.httpGet(extendedList(WallComment.serializer())) {
            append("photo_id", photoId)
            append("owner_id", ownerId)
            append("need_likes", needLikes.asInt())
            append("start_comment_id", startCommentId)
            append("offset", offset)
            append("count", count)
            append("sort", sort.value)
            append("access_key", accessKey)
            append("extended", extended.asInt())
            append("fields", fields.joinToString(",") { it.value })
        }

    override fun getMarketAlbumUploadServer(
        groupId: Int
    ): VkApiRequest<DefaultUploadServerResponse> =
        Methods.getMarketAlbumUploadServer.httpGet(DefaultUploadServerResponse.serializer()) {
            append("group_id", groupId)
        }

    override fun getMarketUploadServer(
        groupId: Int,
        isMainPhoto: Boolean,
        cropX: Int?,
        cropY: Int?,
        cropWidth: Int?
    ): VkApiRequest<DefaultUploadServerResponse> =
        Methods.getMarketUploadServer.httpGet(DefaultUploadServerResponse.serializer()) {
            append("group_id", groupId)
            append("main_photo", isMainPhoto.asInt())
            append("crop_x", cropX)
            append("crop_y", cropY)
            append("crop_width", cropWidth)
        }

    override fun getMessagesUploadServer(
        peerId: Int
    ): VkApiRequest<PhotosUploadServerResponse> =
        Methods.getMessagesUploadServer.httpGet(PhotosUploadServerResponse.serializer()) {
            append("peer_id", peerId)
        }

    override fun getNewTags(
        offset: Int,
        count: Int
    ): VkApiRequest<DefaultListResponse<Photo>> =
        Methods.getNewTags.httpGet(list(Photo.serializer())) {
            append("offset", offset)
            append("count", count)
        }

    override fun getOwnerCoverPhotoUploadServer(
        groupId: Int,
        cropX: Int,
        cropY: Int,
        cropX2: Int,
        cropY2: Int
    ): VkApiRequest<DefaultUploadServerResponse> =
        Methods.getOwnerCoverPhotoUploadServer.httpGet(DefaultUploadServerResponse.serializer()) {
            append("group_id", groupId)
            append("crop_x", cropX)
            append("crop_y", cropY)
            append("crop_x2", cropX2)
            append("crop_y2", cropY2)
        }

    override fun getOwnerPhotoUploadServer(
        ownerId: Int?
    ): VkApiRequest<DefaultUploadServerResponse> =
        Methods.getOwnerPhotoUploadServer.httpGet(DefaultUploadServerResponse.serializer()) {
            append("owner_id", ownerId)
        }

    override fun getTags(
        photoId: Int,
        ownerId: Int?,
        accessKey: String?
    ): VkApiRequest<List<Tag>> =
        Methods.getTags.httpGet(Tag.serializer().list) {
            append("photo_id", photoId)
            append("owner_id", ownerId)
            append("access_key", accessKey)
        }

    override fun getUploadServer(
        albumId: Int,
        groupId: Int?
    ): VkApiRequest<PhotosUploadServerResponse> =
        Methods.getUploadServer.httpGet(PhotosUploadServerResponse.serializer()) {
            append("album_id", albumId)
            append("group_id", groupId)
        }

    override fun getUserPhotos(
        userId: Int?,
        offset: Int,
        count: Int,
        extended: Boolean,
        sortDescending: Boolean
    ): VkApiRequest<DefaultListResponse<Photo>> =
        Methods.getUserPhotos.httpGet(list(Photo.serializer())) {
            append("user_id", userId)
            append("offset", offset)
            append("count", count)
            append("extended", extended.asInt())
            append("sort", sortDescending.not().asInt())
        }

    override fun getWallUploadServer(
        groupId: Int?
    ): VkApiRequest<PhotosUploadServerResponse> =
        Methods.getWallUploadServer.httpGet(PhotosUploadServerResponse.serializer()) {
            append("group_id", groupId)
        }

    override fun makeCover(
        photoId: Int,
        albumId: Int,
        ownerId: Int?
    ): VkApiRequest<BooleanInt> =
        Methods.makeCover.httpGet(BooleanInt.serializer()) {
            append("photo_id", photoId)
            append("album_id", albumId)
            append("owner_id", ownerId)
        }

    override fun move(
        photoId: Int,
        targetAlbumId: Int,
        ownerId: Int?
    ): VkApiRequest<BooleanInt> =
        Methods.move.httpGet(BooleanInt.serializer()) {
            append("photo_id", photoId)
            append("target_album_id", targetAlbumId)
            append("owner_id", ownerId)
        }

    override fun putTag(
        photoId: Int,
        userId: Int,
        x: Double,
        y: Double,
        x2: Double,
        y2: Double,
        ownerId: Int?
    ): VkApiRequest<Int> =
        Methods.putTag.httpGet(Int.serializer()) {
            append("photo_id", photoId)
            append("user_id", userId)
            append("x", x)
            append("y", y)
            append("x2", x2)
            append("y2", y2)
            append("owner_id", ownerId)
        }

    override fun removeTag(
        photoId: Int,
        tagId: Int,
        ownerId: Int?
    ): VkApiRequest<BooleanInt> =
        Methods.removeTag.httpGet(BooleanInt.serializer()) {
            append("photo_id", photoId)
            append("tag_id", tagId)
            append("owner_id", ownerId)
        }

    override fun reorderAlbums(
        albumId: Int,
        before: Int?,
        after: Int?,
        ownerId: Int?
    ): VkApiRequest<BooleanInt> =
        Methods.reorderAlbums.httpGet(BooleanInt.serializer()) {
            append("album_id", albumId)
            append("before", before)
            append("after", after)
            append("owner_id", ownerId)
        }

    override fun reorderPhotos(
        photoId: Int,
        before: Int?,
        after: Int?,
        ownerId: Int?
    ): VkApiRequest<BooleanInt> =
        Methods.reorderPhotos.httpGet(BooleanInt.serializer()) {
            append("photo_id", photoId)
            append("before", before)
            append("after", after)
            append("owner_id", ownerId)
        }

    override fun report(
        ownerId: Int,
        photoId: Int,
        reason: PostReportComplaintType
    ): VkApiRequest<BooleanInt> =
        Methods.report.httpGet(BooleanInt.serializer()) {
            append("owner_id", ownerId)
            append("photo_id", photoId)
            append("reason", reason.value)
        }

    override fun reportComment(
        ownerId: Int,
        commentId: Int,
        reason: PostReportComplaintType
    ): VkApiRequest<BooleanInt> =
        Methods.reportComment.httpGet(BooleanInt.serializer()) {
            append("owner_id", ownerId)
            append("comment_id", commentId)
            append("reason", reason.value)
        }

    override fun restore(
        photoId: Int,
        ownerId: Int?
    ): VkApiRequest<BooleanInt> =
        Methods.restore.httpGet(BooleanInt.serializer()) {
            append("photo_id", photoId)
            append("owner_id", ownerId)
        }

    override fun restoreComment(
        commentId: Int,
        ownerId: Int?
    ): VkApiRequest<BooleanInt> =
        Methods.restoreComment.httpGet(BooleanInt.serializer()) {
            append("comment_id", commentId)
            append("owner_id", ownerId)
        }

    override fun save(
        albumId: Int,
        server: Int,
        photosList: String,
        hash: String,
        latitude: Double?,
        longitude: Double?,
        caption: String?,
        groupId: Int?
    ): VkApiRequest<List<Photo>> =
        Methods.save.httpPost(Photo.serializer().list) {
            append("album_id", albumId)
            append("server", server)
            append("photos_list", photosList)
            append("hash", hash)
            append("latitude", latitude)
            append("longitude", longitude)
            append("caption", caption)
            append("group_id", groupId)
        }

    override fun saveMarketAlbumPhoto(
        groupId: Int,
        server: Int,
        photo: String,
        hash: String
    ): VkApiRequest<List<Photo>> =
        Methods.saveMarketAlbumPhoto.httpPost(Photo.serializer().list) {
            append("group_id", groupId)
            append("server", server)
            append("photo", photo)
            append("hash", hash)
        }

    override fun saveMarketPhoto(
        server: Int,
        photo: String,
        hash: String,
        cropData: String?,
        cropHash: String?,
        groupId: Int?
    ): VkApiRequest<List<Photo>> =
        Methods.saveMarketPhoto.httpPost(Photo.serializer().list) {
            append("server", server)
            append("photo", photo)
            append("hash", hash)
            append("crop_data", cropData)
            append("crop_hash", cropHash)
            append("group_id", groupId)
        }

    override fun saveMessagesPhoto(
        server: Int,
        photo: String,
        hash: String
    ): VkApiRequest<List<Photo>> =
        Methods.saveMessagesPhoto.httpPost(Photo.serializer().list) {
            append("server", server)
            append("photo", photo)
            append("hash", hash)
        }

    override fun saveOwnerCoverPhoto(
        photo: String,
        hash: String
    ): VkApiRequest<List<Community.Cover.Image>> =
        Methods.saveOwnerCoverPhoto.httpPost(Community.Cover.Image.serializer().list) {
            append("photo", photo)
            append("hash", hash)
        }

    override fun saveOwnerPhoto(
        server: Int,
        photo: String,
        hash: String
    ): VkApiRequest<SaveOwnerPhotoResponse> =
        Methods.saveOwnerPhoto.httpPost(SaveOwnerPhotoResponse.serializer()) {
            append("server", server)
            append("photo", photo)
            append("hash", hash)
        }

    override fun saveWallPhoto(
        server: Int,
        photo: String,
        hash: String,
        userId: Int?,
        groupId: Int?,
        latitude: Double?,
        longitude: Double?,
        caption: String?
    ): VkApiRequest<List<Photo>> =
        Methods.saveWallPhoto.httpPost(Photo.serializer().list) {
            append("server", server)
            append("photo", photo)
            append("hash", hash)
            append("user_id", userId)
            append("group_id", groupId)
            append("latitude", latitude)
            append("longitude", longitude)
            append("caption", caption)
        }

    override fun search(
        query: String?,
        latitude: Double?,
        longitude: Double?,
        startTime: Int?,
        endTime: Int?,
        sort: PhotoSearchSort,
        offset: Int,
        count: Int,
        radius: Int
    ): VkApiRequest<DefaultListResponse<Photo>> =
        Methods.search.httpGet(list(Photo.serializer())) {
            append("q", query)
            append("lat", latitude)
            append("long", longitude)
            append("start_time", startTime)
            append("end_time", endTime)
            append("sort", sort.value)
            append("offset", offset)
            append("count", count)
            append("radius", radius)
        }

    private object Methods {
        private const val it = "photos."
        const val confirmTag = it + "confirmTag"
        const val copy = it + "copy"
        const val createAlbum = it + "createAlbum"
        const val createComment = it + "createComment"
        const val delete = it + "delete"
        const val deleteAlbum = it + "deleteAlbum"
        const val deleteComment = it + "deleteComment"
        const val edit = it + "edit"
        const val editAlbum = it + "editAlbum"
        const val editComment = it + "editComment"
        const val get = it + "get"
        const val getAlbums = it + "getAlbums"
        const val getAlbumsCount = it + "getAlbumsCount"
        const val getAll = it + "getAll"
        const val getAllComments = it + "getAllComments"
        const val getById = it + "getById"
        const val getChatUploadServer = it + "getChatUploadServer"
        const val getComments = it + "getComments"
        const val getMarketAlbumUploadServer = it + "getMarketAlbumUploadServer"
        const val getMarketUploadServer = it + "getMarketUploadServer"
        const val getMessagesUploadServer = it + "getMessagesUploadServer"
        const val getNewTags = it + "getNewTags"
        const val getOwnerCoverPhotoUploadServer = it + "getOwnerCoverPhotoUploadServer"
        const val getOwnerPhotoUploadServer = it + "getOwnerPhotoUploadServer"
        const val getTags = it + "getTags"
        const val getUploadServer = it + "getUploadServer"
        const val getUserPhotos = it + "getUserPhotos"
        const val getWallUploadServer = it + "getWallUploadServer"
        const val makeCover = it + "makeCover"
        const val move = it + "move"
        const val putTag = it + "putTag"
        const val removeTag = it + "removeTag"
        const val reorderAlbums = it + "reorderAlbums"
        const val reorderPhotos = it + "reorderPhotos"
        const val report = it + "report"
        const val reportComment = it + "reportComment"
        const val restore = it + "restore"
        const val restoreComment = it + "restoreComment"
        const val save = it + "save"
        const val saveMarketAlbumPhoto = it + "saveMarketAlbumPhoto"
        const val saveMarketPhoto = it + "saveMarketPhoto"
        const val saveMessagesPhoto = it + "saveMessagesPhoto"
        const val saveOwnerCoverPhoto = it + "saveOwnerCoverPhoto"
        const val saveOwnerPhoto = it + "saveOwnerPhoto"
        const val saveWallPhoto = it + "saveWallPhoto"
        const val search = it + "search"
    }

}