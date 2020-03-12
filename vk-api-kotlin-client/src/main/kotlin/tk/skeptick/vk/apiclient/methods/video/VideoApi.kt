package tk.skeptick.vk.apiclient.methods.video

import kotlinx.serialization.builtins.list
import kotlinx.serialization.builtins.serializer
import tk.skeptick.vk.apiclient.*
import tk.skeptick.vk.apiclient.domain.CommentAttachment
import tk.skeptick.vk.apiclient.domain.Media
import tk.skeptick.vk.apiclient.domain.PrivacySettings
import tk.skeptick.vk.apiclient.domain.models.Video
import tk.skeptick.vk.apiclient.domain.models.WallComment
import tk.skeptick.vk.apiclient.methods.*

class VideoApi(override val client: ApiClient)
    : VideoApiUser, MethodsContext {

    override fun add(
        videoId: Int,
        ownerId: Int,
        targetId: Int?
    ): VkApiRequest<BooleanInt> =
        Methods.add.httpGet(BooleanInt.serializer()) {
            append("video_id", videoId)
            append("owner_id", ownerId)
            append("target_id", targetId)
        }

    override fun addAlbum(
        title: String,
        privacy: PrivacySettings,
        groupId: Int?
    ): VkApiRequest<AddVideoAlbumResponse> =
        Methods.addAlbum.httpGet(AddVideoAlbumResponse.serializer()) {
            append("title", title)
            append("privacy", privacy.toRequestString())
            append("group_id", groupId)
        }

    override fun addToAlbum(
        videoId: Int,
        ownerId: Int,
        albumIds: List<VideoAlbumType>,
        targetId: Int?
    ): VkApiRequest<BooleanInt> =
        Methods.addToAlbum.httpPost(BooleanInt.serializer()) {
            append("video_id", videoId)
            append("owner_id", ownerId)
            append("album_ids", albumIds.joinToString(",") { it.value.toString() })
            append("target_id", targetId)
        }

    override fun createComment(
        videoId: Int,
        ownerId: Int?,
        message: String?,
        replyToCommentId: Int?,
        attachments: List<CommentAttachment>?,
        stickerId: Int?,
        fromGroup: Boolean,
        guid: String?
    ): VkApiRequest<Int> =
        Methods.createComment.httpPost(Int.serializer()) {
            append("video_id", videoId)
            append("owner_id", ownerId)
            append("message", message)
            append("attachments", attachments?.joinToString(",", transform = CommentAttachment::attachmentString))
            append("sticker_id", stickerId)
            append("from_group", fromGroup.asInt())
            append("guid", guid)
        }

    override fun delete(
        videoId: Int,
        ownerId: Int?,
        targetId: Int?
    ): VkApiRequest<BooleanInt> =
        Methods.delete.httpGet(BooleanInt.serializer()) {
            append("video_id", videoId)
            append("owner_id", ownerId)
            append("target_id", targetId)
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
        videoId: Int,
        ownerId: Int?,
        name: String?,
        description: String?,
        privacyView: PrivacySettings?,
        privacyComment: PrivacySettings?,
        disableComments: Boolean?,
        enableRepeat: Boolean?
    ): VkApiRequest<BooleanInt> =
        Methods.edit.httpPost(BooleanInt.serializer()) {
            append("video_id", videoId)
            append("owner_id", ownerId)
            append("name", name)
            append("desc", description)
            append("privacy_view", privacyView?.toRequestString())
            append("privacy_comment", privacyComment?.toRequestString())
            append("no_comments", disableComments?.asInt())
            append("repeat", enableRepeat?.asInt())
        }

    override fun editAlbum(
        albumId: Int,
        title: String,
        privacy: PrivacySettings?,
        groupId: Int?
    ): VkApiRequest<BooleanInt> =
        Methods.editAlbum.httpPost(BooleanInt.serializer()) {
            append("album_id", albumId)
            append("title", title)
            append("privacy", privacy?.toRequestString())
            append("group_id", groupId)
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
            append("attachments", attachments?.joinToString(",", transform = CommentAttachment::attachmentString))
        }

    override fun get(
        ownerId: Int?,
        videos: List<Media>?,
        albumId: Int?,
        count: Int,
        offset: Int,
        extended: Boolean
    ): VkApiRequest<DefaultListResponse<Video>> =
        Methods.get.httpGet(list(Video.serializer())) {
            append("owner_id", ownerId)
            append("videos", videos?.joinToString(",", transform = Media::mediaString))
            append("album_id", albumId)
            append("count", count)
            append("offset", offset)
            append("extended", extended.asInt())
        }

    override fun getAlbumById(
        albumId: Int,
        ownerId: Int?
    ): VkApiRequest<VideoAlbum> =
        Methods.getAlbumById.httpGet(VideoAlbum.serializer()) {
            append("album_id", albumId)
            append("owner_id", ownerId)
        }

    override fun getAlbums(
        ownerId: Int?,
        offset: Int,
        count: Int,
        extended: Boolean,
        needSystem: Boolean
    ): VkApiRequest<DefaultListResponse<VideoAlbum>> =
        Methods.getAlbums.httpGet(list(VideoAlbum.serializer())) {
            append("owner_id", ownerId)
            append("offset", offset)
            append("count", count)
            append("extended", extended.asInt())
            append("need_system", needSystem.asInt())
        }

    override fun getAlbumsIdsByVideo(
        videoId: Int,
        ownerId: Int,
        targetId: Int?
    ): VkApiRequest<List<Int>> =
        Methods.getAlbumsByVideo.httpGet(Int.serializer().list) {
            append("video_id", videoId)
            append("owner_id", ownerId)
            append("target_id", targetId)
            append("extended", 0)
        }

    override fun getAlbumsByVideo(
        videoId: Int,
        ownerId: Int,
        targetId: Int?
    ): VkApiRequest<DefaultListResponse<VideoAlbum>> =
        Methods.getAlbumsByVideo.httpGet(list(VideoAlbum.serializer())) {
            append("video_id", videoId)
            append("owner_id", ownerId)
            append("target_id", targetId)
            append("extended", 1)
        }

    override fun getComments(
        videoId: Int,
        ownerId: Int?,
        needLikes: Boolean,
        startCommentId: Int?,
        offset: Int,
        count: Int,
        sort: CommentsSort,
        extended: Boolean,
        fields: List<ObjectField>
    ): VkApiRequest<ExtendedListResponse<WallComment>> =
        Methods.getComments.httpGet(extendedList(WallComment.serializer())) {
            append("video_id", videoId)
            append("owner_id", ownerId)
            append("need_likes", needLikes.asInt())
            append("start_comment_id", startCommentId)
            append("offset", offset)
            append("count", count)
            append("sort", sort.value)
            append("extended", extended.asInt())
            append("fields", fields.joinToString(",") { it.value })
        }

    override fun removeFromAlbum(
        videoId: Int,
        ownerId: Int,
        albumIds: List<VideoAlbumType>,
        targetId: Int?
    ): VkApiRequest<DefaultListResponse<Int>> =
        Methods.removeFromAlbum.httpGet(list(Int.serializer())) {
            append("video_id", videoId)
            append("owner_id", ownerId)
            append("album_ids", albumIds.joinToString(",") { it.value.toString() })
            append("target_id", targetId)
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

    override fun reorderVideos(
        videoId: Int,
        ownerId: Int,
        albumId: Int,
        before: Media?,
        after: Media?,
        targetId: Int?
    ): VkApiRequest<BooleanInt> =
        Methods.reorderVideos.httpGet(BooleanInt.serializer()) {
            append("video_id", videoId)
            append("owner_id", ownerId)
            append("album_id", albumId)
            append("before_owner_id", before?.ownerId)
            append("before_video_id", before?.id)
            append("after_owner_id", after?.ownerId)
            append("after_video_id", after?.id)
            append("target_id", targetId)
        }

    override fun report(
        videoId: Int,
        ownerId: Int,
        reason: PostReportComplaintType,
        comment: String?,
        searchQuery: String?
    ): VkApiRequest<BooleanInt> =
        Methods.report.httpPost(BooleanInt.serializer()) {
            append("video_id", videoId)
            append("owner_id", ownerId)
            append("reason", reason.value)
            append("comment", comment)
            append("search_query", searchQuery)
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
        videoId: Int,
        ownerId: Int?
    ): VkApiRequest<BooleanInt> =
        Methods.restore.httpGet(BooleanInt.serializer()) {
            append("video_id", videoId)
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
        name: String?,
        description: String?,
        isPrivate: Boolean,
        publishOnWall: Boolean,
        link: String?,
        groupId: Int?,
        albumId: VideoAlbumType?,
        privacyView: PrivacySettings,
        privacyComment: PrivacySettings,
        disableComments: Boolean,
        enableRepeat: Boolean,
        enableCompression: Boolean
    ): VkApiRequest<SaveVideoResponse> =
        Methods.save.httpPost(SaveVideoResponse.serializer()) {
            append("name", name)
            append("description", description)
            append("is_private", isPrivate.asInt())
            append("wallpost", publishOnWall.asInt())
            append("link", link)
            append("group_id", groupId)
            append("album_id", albumId?.value)
            append("privacy_view", privacyView.toRequestString())
            append("privacy_comment", privacyComment.toRequestString())
            append("no_comments", disableComments.asInt())
            append("repeat", enableRepeat.asInt())
            append("compression", enableCompression.asInt())
        }

    override fun search(
        query: String,
        sort: VideoSearchSort,
        onlyHd: Boolean,
        disableSafeSearch: Boolean,
        filters: List<VideoSearchFilter>?,
        searchOwn: Boolean,
        offset: Int,
        count: Int,
        longerThan: Int?,
        shorterThan: Int?,
        extended: Boolean
    ): VkApiRequest<ExtendedListResponse<Video>> =
        Methods.search.httpGet(extendedList(Video.serializer())) {
            append("q", query)
            append("sort", sort.value)
            append("hd", onlyHd.asInt())
            append("adult", disableSafeSearch.asInt())
            append("filters", filters?.joinToString(",") { it.value })
            append("search_own", searchOwn.asInt())
            append("offset", offset)
            append("count", count)
            append("longer", longerThan)
            append("shorter", shorterThan)
            append("extended", extended.asInt())
        }

    private object Methods {
        private const val it = "video."
        const val add = it + "add"
        const val addAlbum = it + "addAlbum"
        const val addToAlbum = it + "addToAlbum"
        const val createComment = it + "createComment"
        const val delete = it + "delete"
        const val deleteAlbum = it + "deleteAlbum"
        const val deleteComment = it + "deleteComment"
        const val edit = it + "edit"
        const val editAlbum = it + "editAlbum"
        const val editComment = it + "editComment"
        const val get = it + "get"
        const val getAlbumById = it + "getAlbumById"
        const val getAlbums = it + "getAlbums"
        const val getAlbumsByVideo = it + "getAlbumsByVideo"
        const val getComments = it + "getComments"
        const val removeFromAlbum = it + "removeFromAlbum"
        const val reorderAlbums = it + "reorderAlbums"
        const val reorderVideos = it + "reorderVideos"
        const val report = it + "report"
        const val reportComment = it + "reportComment"
        const val restore = it + "restore"
        const val restoreComment = it + "restoreComment"
        const val save = it + "save"
        const val search = it + "search"
    }

}