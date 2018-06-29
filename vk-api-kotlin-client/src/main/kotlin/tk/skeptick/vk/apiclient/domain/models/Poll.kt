package tk.skeptick.vk.apiclient.domain.models

import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tk.skeptick.vk.apiclient.BooleanInt

@Serializable
data class Poll(
    @SerialName("id") val id: Int,
    @SerialName("owner_id") val ownerId: Int,
    @SerialName("created") val createdDate: Int,
    @SerialName("question") val question: String,
    @SerialName("votes") val votesCount: Int,
    @SerialName("answers") val answers: List<Answer>,
    @Optional @SerialName("answer_id") val selectedAnswerId: Int? = null,
    @Optional @SerialName("anonymous") val isAnonymous: BooleanInt? = null) {

    @Serializable
    data class Answer(
        @SerialName("id") val id: Int,
        @SerialName("text") val text: String,
        @SerialName("votes") val votes: Int,
        @SerialName("rate") val rate: Double)

}