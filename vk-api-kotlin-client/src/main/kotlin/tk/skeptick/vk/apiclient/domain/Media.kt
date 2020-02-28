package tk.skeptick.vk.apiclient.domain

interface Media {
    val id: Int
    val ownerId: Int
    val accessKey: String?
}

interface Attachment : Media {
    val typeAttachment: AttachmentType
    override val accessKey: String? get() = null
}

interface MessageAttachment : Attachment
interface WallAttachment : Attachment
interface CommentAttachment : Attachment

data class BaseMedia(
    override val id: Int,
    override val ownerId: Int,
    override val accessKey: String?
) : Media

data class BaseAttachment(
    override val id: Int,
    override val ownerId: Int,
    override val accessKey: String?,
    override val typeAttachment: AttachmentType
) : CommentAttachment, WallAttachment, MessageAttachment