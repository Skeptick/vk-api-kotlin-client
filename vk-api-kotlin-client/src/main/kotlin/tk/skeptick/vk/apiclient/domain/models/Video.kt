package tk.skeptick.vk.apiclient.domain.models

import kotlinx.serialization.*
import tk.skeptick.vk.apiclient.BooleanInt
import tk.skeptick.vk.apiclient.domain.AttachmentType
import tk.skeptick.vk.apiclient.domain.MessageAttachment

@Serializable
data class Video(
    @SerialName("id") override val id: Int,
    @SerialName("owner_id") override val ownerId: Int,
    @SerialName("title") val title: String,
    @SerialName("description") val description: String,
    @SerialName("duration") val duration: Int, // sec
    @SerialName("date") val date: Int,
    @SerialName("views") val viewsCount: Int,
    @SerialName("comments") val commentsCount: Int,
    @SerialName("player") val playerUrl: String,
    @SerialName("photo_130") val photo130: String,
    @SerialName("photo_320") val photo320: String,
    @Optional @SerialName("photo_640") val photo640: String? = null,
    @Optional @SerialName("photo_800") val photo800: String? = null,
    @Optional @SerialName("platform") val platform: String? = null,
    @Optional @SerialName("adding_date") val addingDate: Int? = null,
    @Optional @SerialName("can_edit") val canEdit: BooleanInt = BooleanInt(false),
    @Optional @SerialName("can_add") val canAdd: BooleanInt = BooleanInt(false),
    @Optional @SerialName("is_private") val isPrivate: BooleanInt = BooleanInt(false),
    @Optional @SerialName("processing") val isInProcessing: BooleanInt = BooleanInt(false),
    @Optional @SerialName("live") val isLive: BooleanInt = BooleanInt(false),
    @Optional @SerialName("upcoming") val isUpcoming: BooleanInt = BooleanInt(false),
    @Optional @SerialName("access_key") override val accessKey: String? = null
) : MessageAttachment {

    @Transient override val typeAttachment get() = AttachmentType.VIDEO.value
    @Transient val photoMax: String get() = photo800 ?: photo640 ?: photo320

}