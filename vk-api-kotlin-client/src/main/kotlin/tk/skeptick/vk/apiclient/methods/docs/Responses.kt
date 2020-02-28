package tk.skeptick.vk.apiclient.methods.docs

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tk.skeptick.vk.apiclient.domain.models.AudioMessage
import tk.skeptick.vk.apiclient.domain.models.Document
import tk.skeptick.vk.apiclient.domain.models.Graffiti

@Serializable
data class DocumentType(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("count") val count: Int)

@Serializable
data class DocumentSaveResponse(
    @SerialName("type") val type: Type,
    @SerialName("graffiti") val graffiti: Graffiti? = null,
    @SerialName("audio_message") val audioMessage: AudioMessage? = null,
    @SerialName("doc") val document: Document? = null) {

    @Serializable
    enum class Type(val value: String) {
        @SerialName("graffiti") GRAFFITI("graffiti"),
        @SerialName("audio_message") AUDIO_MESSAGE("audio_message"),
        @SerialName("doc") DOC("doc")
    }

}