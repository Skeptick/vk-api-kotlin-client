package tk.skeptick.vk.apiclient.domain.models

import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import tk.skeptick.vk.apiclient.EnumStringSerializer
import tk.skeptick.vk.apiclient.SerializableEnum
import tk.skeptick.vk.apiclient.domain.AttachmentType
import tk.skeptick.vk.apiclient.domain.MessageAttachment
import tk.skeptick.vk.apiclient.domain.models.Photo.Size as PhotoSize

@Serializable
data class Poll(
    @SerialName("id") override val id: Int,
    @SerialName("owner_id") override val ownerId: Int,
    @SerialName("created") val createdDate: Int,
    @SerialName("question") val question: String,
    @SerialName("votes") val votesCount: Int,
    @SerialName("answers") val answers: List<Answer>,
    @SerialName("anonymous") val isAnonymous: Boolean,
    @Optional @SerialName("answer_id") val selectedAnswerId: Int? = null,
    @Optional @SerialName("answer_ids") val selectedAnswerIds: List<Int>? = null,
    @Optional @SerialName("multiple") val isMultiple: Boolean? = null,
    @Optional @SerialName("end_date") val endDate: Int? = null,
    @Optional @SerialName("closed") val isClosed: Boolean? = null,
    @Optional @SerialName("is_board") val isBoard: Boolean? = null,
    @Optional @SerialName("can_edit") val canEdit: Boolean? = null,
    @Optional @SerialName("can_vote") val canVote: Boolean? = null,
    @Optional @SerialName("can_report") val canReport: Boolean? = null,
    @Optional @SerialName("can_share") val canShare: Boolean? = null,
    @Optional @SerialName("author_id") val authorId: Int? = null,
    @Optional @SerialName("photo") val photo: Photo? = null,
    @Optional @SerialName("background") val background: Background? = null,
    @Optional @SerialName("friends") val friends: List<Friend>? = null
) : MessageAttachment {

    @Transient override val typeAttachment: String get() = AttachmentType.POLL.value
    @Transient val isUnlimited: Boolean get() = endDate == 0

    @Serializable
    data class Answer(
        @SerialName("id") val id: Int,
        @SerialName("text") val text: String,
        @SerialName("votes") val votes: Int,
        @SerialName("rate") val rate: Double)

    @Serializable
    data class Photo(
        @SerialName("id") val id: Int,
        @SerialName("color") val color: String,
        @SerialName("images") val images: List<PhotoSize>)

    @Serializable
    data class Background(
        @SerialName("id") val id: Int,
        @SerialName("type") val type: Type,
        @SerialName("color") val color: String,
        @Optional @SerialName("angle") val angle: Int? = null,
        @Optional @SerialName("width") val width: Int? = null,
        @Optional @SerialName("height") val height: Int? = null,
        @Optional @SerialName("images") val images: List<PhotoSize>? = null,
        @Optional @SerialName("points") val points: List<Point>? = null) {

        @Serializable(with = Type.Companion::class)
        enum class Type(override val value: String) : SerializableEnum<String> {
            GRADIENT("gradient"),
            TILE("tile");

            companion object : EnumStringSerializer<Type>(Type::class)
        }

        @Serializable
        data class Point(
            @SerialName("position") val position: Double,
            @SerialName("color") val color: String)

    }

    @Serializable
    data class Friend(
        @SerialName("id") val id: Int)

}