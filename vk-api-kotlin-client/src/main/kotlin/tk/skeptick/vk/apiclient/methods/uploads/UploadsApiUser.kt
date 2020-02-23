package tk.skeptick.vk.apiclient.methods.uploads

import tk.skeptick.vk.apiclient.BooleanInt
import tk.skeptick.vk.apiclient.FileContent
import tk.skeptick.vk.apiclient.UploadFilesRequest

interface UploadsApiUser : UploadsApiCommon {

    /**
     * @param[files] can contain a maximum of 10 items, the rest will be ignored during the uploading
     * @see <a href="https://vk.com/dev/upload_files">VK API</a>
     */
    fun photoForAlbum(
        uploadUrl: String,
        files: List<FileContent>
    ): UploadFilesRequest<UploadAlbumPhotoResponse>

    /**
     * @see <a href="https://vk.com/dev/upload_files">VK API</a>
     */
    fun photoForChat(
        uploadUrl: String,
        file: FileContent
    ): UploadFilesRequest<String>

    /**
     * @see <a href="https://vk.com/dev/upload_files">VK API</a>
     */
    fun photoForMarketAlbum(
        uploadUrl: String,
        file: FileContent
    ): UploadFilesRequest<UploadMarketAlbumPhotoResponse>

    /**
     * @param[files] can contain a maximum of 10 items, the rest will be ignored during the uploading
     * @see <a href="https://vk.com/dev/upload_files">VK API</a>
     */
    fun photoForMarket(
        uploadUrl: String,
        files: List<FileContent>
    ): UploadFilesRequest<UploadMarketPhotoResponse>

    /**
     * @see <a href="https://vk.com/dev/upload_files">VK API</a>
     */
    fun photoForOwner(
        uploadUrl: String,
        file: FileContent,
        previewX: Int? = null,
        previewY: Int? = null,
        previewWidth: Int? = null
    ): UploadFilesRequest<UploadOwnerPhotoResponse>

    /**
     * @param[files] can contain a maximum of 10 items, the rest will be ignored during the uploading
     * @see <a href="https://vk.com/dev/upload_files">VK API</a>
     */
    fun photoForWall(
        uploadUrl: String,
        files: List<FileContent>
    ): UploadFilesRequest<UploadPhotoResponse>

    /**
     * @see <a href="https://vk.com/dev/upload_files">VK API</a>
     */
    fun videoByLink(
        uploadUrl: String
    ): UploadFilesRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/upload_files">VK API</a>
     */
    fun video(
        uploadUrl: String,
        file: FileContent
    ): UploadFilesRequest<UploadVideoResponse>

}