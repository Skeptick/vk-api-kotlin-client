package tk.skeptick.vk.apiclient.methods.uploads

import tk.skeptick.vk.apiclient.FileContent
import tk.skeptick.vk.apiclient.UploadFilesRequest

interface UploadsApiCommon {

    /**
     * @see <a href="https://vk.com/dev/upload_files">VK API</a>
     */
    fun document(
        uploadUrl: String,
        file: FileContent
    ): UploadFilesRequest<UploadDocumentResponse>

    /**
     * @param[files] can contain a maximum of 10 items, the rest will be ignored during the uploading
     * @see <a href="https://vk.com/dev/upload_files">VK API</a>
     */
    fun photoForMessage(
        uploadUrl: String,
        files: List<FileContent>
    ): UploadFilesRequest<UploadPhotoResponse>

    /**
     * @see <a href="https://vk.com/dev/upload_files">VK API</a>
     */
    fun photoForOwnerCover(
        uploadUrl: String,
        file: FileContent
    ): UploadFilesRequest<UploadOwnerCoverPhotoResponse>

}