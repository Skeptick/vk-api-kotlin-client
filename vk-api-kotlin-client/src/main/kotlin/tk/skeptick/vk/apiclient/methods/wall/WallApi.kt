package tk.skeptick.vk.apiclient.methods.wall

import io.ktor.util.date.GMTDate
import kotlinx.serialization.builtins.list
import tk.skeptick.vk.apiclient.*
import tk.skeptick.vk.apiclient.domain.CommentAttachment
import tk.skeptick.vk.apiclient.domain.MessageAttachment
import tk.skeptick.vk.apiclient.domain.models.WallComment
import tk.skeptick.vk.apiclient.domain.models.WallPost
import tk.skeptick.vk.apiclient.methods.*

class WallApi(override val client: ApiClient)
    : WallApiUser, WallApiCommunity, MethodsContext {

    override fun closeComments(
        ownerId: Int,
        postId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.closeComments.httpGet(BooleanInt.serializer()) {
            append("owner_id", ownerId)
            append("post_id", postId)
        }

    override fun createComment(
        postId: Int,
        ownerId: Int?,
        fromGroup: Int,
        message: String?,
        replyToCommentId: Int?,
        attachments: List<CommentAttachment>?,
        stickerId: Int?,
        guid: String?
    ): VkApiRequest<CreateCommentResponse> =
        Methods.createComment.httpPost(CreateCommentResponse.serializer()) {
            append("post_id", postId)
            append("owner_id", ownerId)
            append("from_group", fromGroup)
            append("message", message)
            append("reply_to_comment", replyToCommentId)
            append("attachments", attachments?.joinToString(",", transform = CommentAttachment::attachmentString))
            append("sticker_id", stickerId)
            append("guid", guid)
        }

    override fun createComment(
        postId: Int,
        ownerId: Int?,
        message: String?,
        replyToCommentId: Int?,
        attachments: List<MessageAttachment>?,
        stickerId: Int?,
        guid: String?
    ): VkApiRequest<CreateCommentResponse> =
        Methods.createComment.httpPost(CreateCommentResponse.serializer()) {
            append("post_id", postId)
            append("owner_id", ownerId)
            append("message", message)
            append("reply_to_comment", replyToCommentId)
            append("attachments", attachments?.joinToString(",", transform = MessageAttachment::attachmentString))
            append("sticker_id", stickerId)
            append("guid", guid)
        }

    override fun delete(
        postId: Int,
        ownerId: Int?
    ): VkApiRequest<BooleanInt> =
        Methods.delete.httpGet(BooleanInt.serializer()) {
            append("post_id", postId)
            append("owner_id", ownerId)
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
        postId: Int,
        ownerId: Int?,
        friendsOnly: Boolean?,
        message: String?,
        attachments: List<MessageAttachment>?,
        attachmentLink: String?,
        servicesForExport: List<String>?,
        signed: Boolean?,
        publishDate: GMTDate?,
        latitude: Double?,
        longitude: Double?,
        placeId: Int?,
        markAsAds: Boolean?,
        closeComments: Boolean?,
        posterBackgroundId: Int?
    ): VkApiRequest<BooleanInt> =
        Methods.edit.httpPost(BooleanInt.serializer()) {
            append("post_id", postId)
            append("owner_id", ownerId)
            append("friends_only", friendsOnly?.asInt())
            append("message", message)
            append("attachments", attachments?.joinToString(",", transform = MessageAttachment::attachmentString).append(attachmentLink))
            append("services", servicesForExport?.joinToString(","))
            append("signed", signed?.asInt())
            append("publish_date", publishDate?.unixtime)
            append("lat", latitude)
            append("long", longitude)
            append("place_id", placeId)
            append("mark_as_ads", markAsAds?.asInt())
            append("close_comments", closeComments?.asInt())
            append("poster_bkg_id", posterBackgroundId)
        }

    override fun editAdsStealth(
        postId: Int,
        ownerId: Int?,
        message: String?,
        attachments: List<MessageAttachment>?,
        attachmentLink: String?,
        signed: Boolean,
        latitude: Double?,
        longitude: Double?,
        placeId: Int?,
        linkButton: LinkButtonType?,
        linkTitle: String?,
        linkImage: String?
    ): VkApiRequest<BooleanInt> =
        Methods.editAdsStealth.httpPost(BooleanInt.serializer()) {
            append("post_id", postId)
            append("owner_id", ownerId)
            append("message", message)
            append("attachments", attachments?.joinToString(",", transform = MessageAttachment::attachmentString).append(attachmentLink))
            append("signed", signed.asInt())
            append("lat", latitude)
            append("long", longitude)
            append("place_id", placeId)
            append("link_button", linkButton?.value)
            append("link_title", linkTitle)
            append("link_image", linkImage)
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
        offset: Int,
        count: Int,
        filter: WallPostFilter,
        extended: Boolean,
        fields: List<ObjectField>
    ): VkApiRequest<ExtendedListResponse<WallPost>> =
        Methods.get.httpGet(extendedList(WallPost.serializer())) {
            append("owner_id", ownerId)
            append("offset", offset)
            append("count", count)
            append("filter", filter.value)
            append("extended", extended.asInt())
            append("fields", fields.joinToString(",") { it.value })
        }

    override fun getById(
        posts: Map<Int, List<Int>>,
        copyHistoryDepth: Int
    ): VkApiRequest<List<WallPost>> =
        Methods.getById.httpGet(WallPost.serializer().list) {
            append("posts", posts.toList().joinToString(",") { it.posts })
            append("copy_history_depth", copyHistoryDepth)
        }

    override fun getByIdExtended(
        posts: Map<Int, List<Int>>,
        copyHistoryDepth: Int,
        fields: List<ObjectField>
    ): VkApiRequest<ExtendedListResponse<WallPost>> =
        Methods.getById.httpGet(extendedList(WallPost.serializer())) {
            append("posts", posts.toList().joinToString(",") { it.posts })
            append("copy_history_depth", copyHistoryDepth)
            append("extended", 1)
            append("fields", fields.joinToString(",") { it.value })
        }

    override fun getComment(
        commentId: Int,
        ownerId: Int?,
        extended: Boolean,
        fields: List<ObjectField>
    ): VkApiRequest<ExtendedListResponse<WallComment>> =
        Methods.getComment.httpGet(extendedList(WallComment.serializer())) {
            append("comment_id", commentId)
            append("owner_id", ownerId)
            append("extended", extended.asInt())
            append("fields", fields.joinToString(",") { it.value })
        }

    override fun getComments(
        postId: Int,
        ownerId: Int?,
        commentId: Int?,
        needLikes: Boolean,
        startCommentId: Int?,
        threadItemsCount: Int,
        offset: Int,
        count: Int,
        sort: CommentsSort,
        previewLength: Int,
        extended: Boolean,
        fields: List<ObjectField>
    ): VkApiRequest<CommentsListResponse> =
        Methods.getComments.httpGet(CommentsListResponse.serializer()) {
            append("post_id", postId)
            append("owner_id", ownerId)
            append("comment_id", commentId)
            append("need_likes", needLikes.asInt())
            append("start_comment_id", startCommentId)
            append("thread_items_count", threadItemsCount)
            append("offset", offset)
            append("count", count)
            append("sort", sort.value)
            append("preview_length", previewLength)
            append("extended", extended.asInt())
            append("fields", fields.joinToString(",") { it.value })
        }

    override fun getReposts(
        postId: Int,
        ownerId: Int?,
        offset: Int,
        count: Int
    ): VkApiRequest<ExtendedListResponse<WallPost>> =
        Methods.getReposts.httpGet(extendedList(WallPost.serializer())) {
            append("post_id", postId)
            append("owner_id", ownerId)
            append("offset", offset)
            append("count", count)
        }

    override fun openComments(
        ownerId: Int,
        postId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.openComments.httpGet(BooleanInt.serializer()) {
            append("owner_id", ownerId)
            append("post_id", postId)
        }

    override fun pin(
        postId: Int,
        ownerId: Int?
    ): VkApiRequest<BooleanInt> =
        Methods.pin.httpGet(BooleanInt.serializer()) {
            append("post_id", postId)
            append("owner_id", ownerId)
        }

    override fun post(
        ownerId: Int?,
        friendsOnly: Boolean,
        fromGroup: Boolean,
        message: String?,
        attachments: List<MessageAttachment>?,
        attachmentLink: String?,
        servicesForExport: List<String>?,
        signed: Boolean,
        publishDate: GMTDate?,
        latitude: Double?,
        longitude: Double?,
        placeId: Int?,
        postId: Int?,
        guid: String?,
        markAsAds: Boolean,
        closeComments: Boolean
    ): VkApiRequest<PostResponse> =
        Methods.post.httpPost(PostResponse.serializer()) {
            append("owner_id", ownerId)
            append("friends_only", friendsOnly.asInt())
            append("from_group", fromGroup.asInt())
            append("message", message)
            append("attachments", attachments?.joinToString(",", transform = MessageAttachment::attachmentString).append(attachmentLink))
            append("services", servicesForExport?.joinToString(","))
            append("signed", signed.asInt())
            append("publish_date", publishDate?.unixtime)
            append("lat", latitude)
            append("long", longitude)
            append("place_id", placeId)
            append("post_id", postId)
            append("guid", guid)
            append("mark_as_ads", markAsAds.asInt())
            append("close_comments", closeComments.asInt())
        }

    override fun postAdsStealth(
        ownerId: Int,
        message: String?,
        attachments: List<MessageAttachment>?,
        attachmentLink: String?,
        signed: Boolean,
        latitude: Double?,
        longitude: Double?,
        placeId: Int?,
        guid: String?,
        linkButton: LinkButtonType?,
        linkTitle: String?,
        linkImage: String?
    ): VkApiRequest<PostResponse> =
        Methods.postAdsStealth.httpPost(PostResponse.serializer()) {
            append("owner_id", ownerId)
            append("message", message)
            append("attachments", attachments?.joinToString(",", transform = MessageAttachment::attachmentString).append(attachmentLink))
            append("signed", signed.asInt())
            append("lat", latitude)
            append("long", longitude)
            append("place_id", placeId)
            append("guid", guid)
            append("link_button", linkButton?.value)
            append("link_title", linkTitle)
            append("link_image", linkImage)
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

    override fun reportPost(
        ownerId: Int,
        postId: Int,
        reason: PostReportComplaintType
    ): VkApiRequest<BooleanInt> =
        Methods.reportPost.httpGet(BooleanInt.serializer()) {
            append("owner_id", ownerId)
            append("post_id", postId)
            append("reason", reason.value)
        }

    override fun repost(
        repostObject: MessageAttachment,
        message: String?,
        groupId: Int?,
        markAsAds: Boolean
    ): VkApiRequest<RepostResponse> =
        Methods.repost.httpPost(RepostResponse.serializer()) {
            append("object", repostObject.attachmentString)
            append("message", message)
            append("group_id", groupId)
            append("mark_as_ads", markAsAds.asInt())
        }

    override fun restore(
        postId: Int,
        ownerId: Int?
    ): VkApiRequest<BooleanInt> =
        Methods.restore.httpGet(BooleanInt.serializer()) {
            append("post_id", postId)
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

    override fun search(
        query: String,
        ownerId: Int?,
        ownersOnly: Boolean,
        count: Int,
        offset: Int,
        extended: Boolean,
        fields: List<ObjectField>
    ): VkApiRequest<ExtendedListResponse<WallPost>> =
        Methods.search.httpGet(extendedList(WallPost.serializer())) {
            append("query", query)
            append("owner_id", ownerId)
            append("owners_only", ownersOnly.asInt())
            append("count", count)
            append("offset", offset)
            append("extended", extended.asInt())
            append("fields", fields.joinToString(",") { it.value })
        }

    override fun unpin(
        postId: Int,
        ownerId: Int?
    ): VkApiRequest<BooleanInt> =
        Methods.unpin.httpGet(BooleanInt.serializer()) {
            append("post_id", postId)
            append("owner_id", ownerId)
        }

    companion object {

        private fun String?.append(other: String?): String? =
            when {
                this == null -> other
                other == null -> this
                else -> "$this,$other"
            }

        private inline val Pair<Int, List<Int>>.posts: String
            get() = second.joinToString(",") { "${first}_$it" }

    }

    private object Methods {
        private const val it = "wall."
        const val closeComments = it + "closeComments"
        const val createComment = it + "createComment"
        const val delete = it + "delete"
        const val deleteComment = it + "deleteComment"
        const val edit = it + "edit"
        const val editAdsStealth = it + "editAdsStealth"
        const val editComment = it + "editComment"
        const val get = it + "get"
        const val getById = it + "getById"
        const val getComment = it + "getComment"
        const val getComments = it + "getComments"
        const val getReposts = it + "getReposts"
        const val openComments = it + "openComments"
        const val pin = it + "pin"
        const val post = it + "post"
        const val postAdsStealth = it + "postAdsStealth"
        const val reportComment = it + "reportComment"
        const val reportPost = it + "reportPost"
        const val repost = it + "repost"
        const val restore = it + "restore"
        const val restoreComment = it + "restoreComment"
        const val search = it + "search"
        const val unpin = it + "unpin"
    }

}