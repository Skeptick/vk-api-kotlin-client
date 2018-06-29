package tk.skeptick.vk.apiclient.methods.uploads

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UploadFileError(
    @SerialName("error") val error: String,
    @SerialName("error_descr") val errorDescription: String
) : Exception()

@Serializable
data class UploadDocumentResponse(
    @SerialName("file") val file: String)

@Serializable
data class UploadPhotoIntoMessageResponse(
    @SerialName("server") val server: Int,
    @SerialName("photo") val photo: String,
    @SerialName("hash") val hash: String)