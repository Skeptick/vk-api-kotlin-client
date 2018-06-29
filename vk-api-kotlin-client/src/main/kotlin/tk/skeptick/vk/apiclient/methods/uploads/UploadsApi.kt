package tk.skeptick.vk.apiclient.methods.uploads

import tk.skeptick.vk.apiclient.MethodsContext
import tk.skeptick.vk.apiclient.UploadFilesRequest
import tk.skeptick.vk.apiclient.VkApiClient
import java.io.File

class UploadsApi(override val client: VkApiClient)
    : UploadsApiUser, UploadsApiCommunity, MethodsContext {

    override fun document(
        uploadUrl: String,
        file: Pair<String, File>
    ): UploadFilesRequest<UploadDocumentResponse> =
        UploadFilesRequest(
            client = client,
            uploadUrl = uploadUrl,
            files = listOf(Triple("file", file.first, file.second)),
            serializer = UploadDocumentResponse.serializer()
        )

    override fun photoIntoMessage(
        uploadUrl: String,
        files: List<Pair<String, File>>
    ): UploadFilesRequest<UploadPhotoIntoMessageResponse> =
        UploadFilesRequest(
            client = client,
            uploadUrl = uploadUrl,
            files = prepareFiles(files, "file"),
            serializer = UploadPhotoIntoMessageResponse.serializer()
        )

    companion object {

        private fun prepareFiles(
            files: List<Pair<String, File>>,
            namePrefix: String
        ): List<Triple<String, String, File>> =
            files.mapIndexed { i, pair ->
                Triple("$namePrefix${i + 1}", pair.first, pair.second)
            }

    }

}