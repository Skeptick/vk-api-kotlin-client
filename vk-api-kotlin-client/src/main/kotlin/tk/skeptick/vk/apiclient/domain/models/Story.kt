package tk.skeptick.vk.apiclient.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tk.skeptick.vk.apiclient.BooleanInt
import tk.skeptick.vk.apiclient.EnumStringSerializer
import tk.skeptick.vk.apiclient.SerializableEnum
import tk.skeptick.vk.apiclient.domain.AttachmentType
import tk.skeptick.vk.apiclient.domain.MessageAttachment

@Serializable
data class Story(
    @SerialName("id") override val id: Int,
    @SerialName("owner_id") override val ownerId: Int,
    @SerialName("date") val date: Int? = null,
    @SerialName("expires_at") val expiresAt: Int? = null,
    @SerialName("is_expired") val isExpired: Boolean? = null,
    @SerialName("is_deleted") val isDeleted: Boolean? = null,
    @SerialName("can_see") val canSee: BooleanInt? = null,
    @SerialName("seen") val seen: BooleanInt? = null,
    @SerialName("type") val type: Type? = null,
    @SerialName("photo") val photo: Photo? = null,
    @SerialName("video") val video: Video? = null,
    @SerialName("link") val link: Link? = null,
    @SerialName("parent_story_owner_id") val parentStoryOwnerId: Int? = null,
    @SerialName("parent_story_id") val parentStoryId: Int? = null,
    @SerialName("parent_story") val parentStory: Story? = null,
    @SerialName("replies") val replies: Replies? = null,
    @SerialName("can_reply") val canReply: BooleanInt? = null,
    @SerialName("can_share") val canShare: BooleanInt? = null,
    @SerialName("can_hide") val canHide: BooleanInt? = null,
    @SerialName("can_comment") val canComment: BooleanInt? = null,
    @SerialName("can_ask") val canAsk: BooleanInt? = null,
    @SerialName("can_ask_anonymous") val canAskAnonymous: BooleanInt? = null,
    @SerialName("views") val views: Int? = null,
    @SerialName("track_code") val trackCode: String? = null,
    @SerialName("access_key") override val accessKey: String? = null
) : MessageAttachment {

    override val typeAttachment: AttachmentType get() = AttachmentType.STORY

    @Serializable(with = Type.Companion::class)
    enum class Type(override val value: String) : SerializableEnum<String> {
        PHOTO("photo"),
        VIDEO("video");

        companion object : EnumStringSerializer<Type>(Type::class)
    }

    @Serializable
    data class Link(
        @SerialName("text") val text: String,
        @SerialName("url") val url: String)

    @Serializable
    data class Replies(
        @SerialName("count") val count: Int,
        @SerialName("new") val new: Int)

}