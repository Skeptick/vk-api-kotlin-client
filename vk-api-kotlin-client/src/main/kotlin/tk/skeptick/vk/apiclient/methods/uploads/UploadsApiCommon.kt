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
     * @param[files] can contain a maximum of six items,
     * the rest will be ignored during the uploading
     *
     * @see <a href="https://vk.com/dev/upload_files">VK API</a>
     */
    fun photoIntoMessage(
        uploadUrl: String,
        files: List<FileContent>
    ): UploadFilesRequest<UploadPhotoIntoMessageResponse>

}