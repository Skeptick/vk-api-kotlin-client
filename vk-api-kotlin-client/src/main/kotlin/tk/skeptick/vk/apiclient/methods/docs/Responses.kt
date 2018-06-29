package tk.skeptick.vk.apiclient.methods.docs

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DocumentType(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("count") val count: Int)