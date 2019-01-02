package tk.skeptick.vk.apiclient.methods.docs

import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tk.skeptick.vk.apiclient.EnumStringSerializer
import tk.skeptick.vk.apiclient.SerializableEnum
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
    @Optional @SerialName("graffiti") val graffiti: Graffiti? = null,
    @Optional @SerialName("audio_message") val audioMessage: AudioMessage? = null,
    @Optional @SerialName("doc") val document: Document? = null) {

    @Serializable(with = Type.Companion::class)
    enum class Type(override val value: String) : SerializableEnum<String> {
        GRAFFITI("graffiti"),
        AUDIO_MESSAGE("audio_message"),
        DOC("doc");

        companion object : EnumStringSerializer<Type>(Type::class)
    }

}