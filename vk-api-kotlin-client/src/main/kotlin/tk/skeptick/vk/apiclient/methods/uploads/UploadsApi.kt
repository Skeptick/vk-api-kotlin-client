package tk.skeptick.vk.apiclient.methods.uploads

import io.ktor.http.Parameters
import io.ktor.http.parametersOf
import kotlinx.serialization.builtins.serializer
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
            files = listOf(UploadableFile(DEFAULT_FILE_FIELD, file)),
            parameters = Parameters.Empty,
            serializer = UploadDocumentResponse.serializer()
        )

    override fun photoForAlbum(
        uploadUrl: String,
        files: List<FileContent>
    ): UploadFilesRequest<UploadAlbumPhotoResponse> =
        UploadFilesRequest(
            client = client,
            uploadUrl = uploadUrl,
            files = prepareFiles(files),
            parameters = Parameters.Empty,
            serializer = UploadAlbumPhotoResponse.serializer()
        )

    override fun photoForChat(
        uploadUrl: String,
        file: FileContent
    ): UploadFilesRequest<String> =
        UploadFilesRequest(
            client = client,
            uploadUrl = uploadUrl,
            files = listOf(UploadableFile(DEFAULT_FILE_FIELD, file)),
            parameters = Parameters.Empty,
            serializer = String.serializer()
        )

    override fun photoForMarketAlbum(
        uploadUrl: String,
        file: FileContent
    ): UploadFilesRequest<UploadMarketAlbumPhotoResponse> =
        UploadFilesRequest(
            client = client,
            uploadUrl = uploadUrl,
            files = listOf(UploadableFile(DEFAULT_FILE_FIELD, file)),
            parameters = Parameters.Empty,
            serializer = UploadMarketAlbumPhotoResponse.serializer()
        )

    override fun photoForMarket(
        uploadUrl: String,
        files: List<FileContent>
    ): UploadFilesRequest<UploadMarketPhotoResponse> =
        UploadFilesRequest(
            client = client,
            uploadUrl = uploadUrl,
            files = prepareFiles(files),
            parameters = Parameters.Empty,
            serializer = UploadMarketPhotoResponse.serializer()
        )

    override fun photoForMessage(
        uploadUrl: String,
        files: List<FileContent>
    ): UploadFilesRequest<UploadPhotoResponse> =
        UploadFilesRequest(
            client = client,
            uploadUrl = uploadUrl,
            files = prepareFiles(files),
            parameters = Parameters.Empty,
            serializer = UploadPhotoResponse.serializer()
        )

    override fun photoForOwnerCover(
        uploadUrl: String,
        file: FileContent
    ): UploadFilesRequest<UploadOwnerCoverPhotoResponse> =
        UploadFilesRequest(
            client = client,
            uploadUrl = uploadUrl,
            files = listOf(UploadableFile(DEFAULT_FILE_FIELD, file)),
            parameters = Parameters.Empty,
            serializer = UploadOwnerCoverPhotoResponse.serializer()
        )

    override fun photoForOwner(
        uploadUrl: String,
        file: FileContent,
        previewX: Int?,
        previewY: Int?,
        previewWidth: Int?
    ): UploadFilesRequest<UploadOwnerPhotoResponse> =
        UploadFilesRequest(
            client = client,
            uploadUrl = uploadUrl,
            files = listOf(UploadableFile(DEFAULT_FILE_FIELD, file)),
            parameters = parametersOf("_square_crop", "$previewX,$previewY,$previewWidth"),
            serializer = UploadOwnerPhotoResponse.serializer()
        )

    override fun photoForWall(
        uploadUrl: String,
        files: List<FileContent>
    ): UploadFilesRequest<UploadPhotoResponse> =
        UploadFilesRequest(
            client = client,
            uploadUrl = uploadUrl,
            files = prepareFiles(files),
            parameters = Parameters.Empty,
            serializer = UploadPhotoResponse.serializer()
        )

    override fun videoByLink(
        uploadUrl: String
    ): UploadFilesRequest<BooleanInt> =
        UploadFilesRequest(
            client = client,
            uploadUrl = uploadUrl,
            files = emptyList(),
            parameters = Parameters.Empty,
            serializer = BooleanInt.serializer()
        )

    override fun video(
        uploadUrl: String,
        file: FileContent
    ): UploadFilesRequest<UploadVideoResponse> =
        UploadFilesRequest(
            client = client,
            uploadUrl = uploadUrl,
            files = listOf(UploadableFile(DEFAULT_FILE_FIELD, file)),
            parameters = Parameters.Empty,
            serializer = UploadVideoResponse.serializer()
        )

    companion object {

        private const val DEFAULT_FILE_FIELD = "file"

        private fun prepareFiles(files: List<FileContent>): List<UploadableFile> =
            files.mapIndexed { i, file -> UploadableFile("$DEFAULT_FILE_FIELD$i", file) }

    }

}