package tk.skeptick.vk.apiclient.domain

interface Media {
    val id: Int
    val ownerId: Int
    val accessKey: String?
}

data class SimpleMedia(
    override val id: Int,
    override val ownerId: Int,
    override val accessKey: String?
) : Media

interface MessageAttachment : Media {
    override val id: Int
    override val ownerId: Int
    override val accessKey: String? get() = null
    val typeAttachment: String
}