package tk.skeptick.vk.apiclient.methods

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

interface GetUploadServerResponse {
    val uploadUrl: String
}

@Serializable
data class DefaultUploadServerResponse(
    @SerialName("upload_url") override val uploadUrl: String
) : GetUploadServerResponse