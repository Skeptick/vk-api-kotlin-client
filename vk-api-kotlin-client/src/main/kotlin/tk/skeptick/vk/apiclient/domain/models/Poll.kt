package tk.skeptick.vk.apiclient.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tk.skeptick.vk.apiclient.domain.AttachmentType
import tk.skeptick.vk.apiclient.domain.MessageAttachment
import tk.skeptick.vk.apiclient.domain.WallAttachment
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
    @SerialName("answer_id") val selectedAnswerId: Int? = null,
    @SerialName("answer_ids") val selectedAnswerIds: List<Int>? = null,
    @SerialName("multiple") val isMultiple: Boolean? = null,
    @SerialName("end_date") val endDate: Int? = null,
    @SerialName("closed") val isClosed: Boolean? = null,
    @SerialName("is_board") val isBoard: Boolean? = null,
    @SerialName("can_edit") val canEdit: Boolean? = null,
    @SerialName("can_vote") val canVote: Boolean? = null,
    @SerialName("can_report") val canReport: Boolean? = null,
    @SerialName("can_share") val canShare: Boolean? = null,
    @SerialName("author_id") val authorId: Int? = null,
    @SerialName("photo") val photo: Photo? = null,
    @SerialName("background") val background: Background? = null,
    @SerialName("friends") val friends: List<Friend>? = null
) : WallAttachment, MessageAttachment {

    override val accessKey: String? = null
    override val typeAttachment: AttachmentType get() = AttachmentType.POLL
    val isUnlimited: Boolean get() = endDate == 0

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
        @SerialName("angle") val angle: Int? = null,
        @SerialName("width") val width: Int? = null,
        @SerialName("height") val height: Int? = null,
        @SerialName("images") val images: List<PhotoSize>? = null,
        @SerialName("points") val points: List<Point>? = null) {

        @Serializable
        enum class Type(val value: String) {
            @SerialName("gradient") GRADIENT("gradient"),
            @SerialName("tile") TILE("tile")
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