package tk.skeptick.vk.apiclient.methods.uploads

import tk.skeptick.vk.apiclient.*

class UploadsApi(override val client: ApiClient)
    : UploadsApiUser, UploadsApiCommunity, MethodsContext {

    override fun document(
        uploadUrl: String,
        file: FileContent
    ): UploadFilesRequest<UploadDocumentResponse> =
        UploadFilesRequest(
            client = client,
            uploadUrl = uploadUrl,
            files = listOf(UploadableFile("file", file)),
            serializer = UploadDocumentResponse.serializer()
        )

    override fun photoIntoMessage(
        uploadUrl: String,
        files: List<FileContent>
    ): UploadFilesRequest<UploadPhotoIntoMessageResponse> =
        UploadFilesRequest(
            client = client,
            uploadUrl = uploadUrl,
            files = prepareFiles(files, "file"),
            serializer = UploadPhotoIntoMessageResponse.serializer()
        )

    companion object {

        private fun prepareFiles(
            files: List<FileContent>,
            namePrefix: String
        ): List<UploadableFile> =
            files.mapIndexed { i, file ->
                UploadableFile("$namePrefix${i + 1}", file)
            }

    }

}