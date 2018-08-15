package tk.skeptick.vk.apiclient.methods.wall

import kotlinx.serialization.internal.IntSerializer
import tk.skeptick.vk.apiclient.*
import tk.skeptick.vk.apiclient.domain.MessageAttachment

class MessagesApi(override val client: VkApiClient)
    : WallApiUser, WallApiCommunity, MethodsContext {

    override fun closeComments(
        ownerId: Int,
        postId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.closeComments.httpGet(
            "owner_id" to ownerId,
            "post_id" to postId
        ).withSerializer(BooleanInt.serializer())

    override fun createComment(
        postId: Int,
        ownerId: Int?,
        fromGroup: Int,
        message: String?,
        replyToCommentId: Int?,
        attachments: List<MessageAttachment>?,
        stickerId: Int?,
        guid: String?
    ): VkApiRequest<Int> =
        Methods.createComment.httpPost(
            "post_id" to postId,
            "owner_id" to ownerId,
            "from_group" to fromGroup,
            "message" to message,
            "reply_to_comment" to replyToCommentId,
            "attachments" to attachments?.let(::prepareAttachments),
            "sticker_id" to stickerId,
            "guid" to guid
        ).withSerializer(IntSerializer)

    override fun delete(
        postId: Int,
        ownerId: Int?
    ): VkApiRequest<BooleanInt> =
        Methods.delete.httpGet(
            "post_id" to postId,
            "owner_id" to ownerId
        ).withSerializer(BooleanInt.serializer())

    override fun deleteComment(
        commentId: Int,
        ownerId: Int?
    ): VkApiRequest<BooleanInt> =
        Methods.deleteComment.httpGet(
            "comment_id" to commentId,
            "owner_id" to ownerId
        ).withSerializer(BooleanInt.serializer())

    override fun edit(
        postId: Int,
        ownerId: Int?,
        friendsOnly: Boolean?,
        message: String?,
        attachments: List<MessageAttachment>?,
        servicesForExport: List<String>?,
        signed: Boolean?,
        publishDate: Int?,
        lat: Double?,
        long: Double?,
        placeId: Int?,
        markAsAds: Boolean?,
        closeComments: Boolean?
    ): VkApiRequest<BooleanInt> =
        Methods.edit.httpPost(
            "post_id" to postId,
            "owner_id" to ownerId,
            "friends_only" to friendsOnly?.asInt(),
            "message" to message,
            "attachments" to attachments?.let(::prepareAttachments),
            "services" to servicesForExport?.joinToString(","),
            "signed" to signed?.asInt(),
            "publish_date" to publishDate,
            "lat" to lat,
            "long" to long,
            "place_id" to placeId,
            "mark_as_ads" to markAsAds?.asInt(),
            "close_comments" to closeComments?.asInt()
        ).withSerializer(BooleanInt.serializer())

    private object Methods {
        private const val it = "wall."
        const val closeComments = it + "closeComments"
        const val createComment = it + "createComment"
        const val delete = it + "delete"
        const val deleteComment = it + "deleteComment"
        const val edit = it + "edit"
    }

}