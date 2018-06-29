package tk.skeptick.vk.apiclient.methods.uploads

import tk.skeptick.vk.apiclient.UploadFilesRequest
import java.io.File

interface UploadsApiCommon {

    /**
     * @see <a href="https://vk.com/dev/upload_files">VK API</a>
     */
    fun document(
        uploadUrl: String,
        file: Pair<String, File>
    ): UploadFilesRequest<UploadDocumentResponse>

    /**
     * @param[files] can contain a maximum of six items,
     * the rest will be ignored during the uploading
     *
     * @see <a href="https://vk.com/dev/upload_files">VK API</a>
     */
    fun photoIntoMessage(
        uploadUrl: String,
        files: List<Pair<String, File>>
    ): UploadFilesRequest<UploadPhotoIntoMessageResponse>

}