package tk.skeptick.vk.apiclient.methods.photos

import tk.skeptick.vk.apiclient.VkApiRequest
import tk.skeptick.vk.apiclient.domain.models.Community
import tk.skeptick.vk.apiclient.domain.models.Photo
import tk.skeptick.vk.apiclient.methods.DefaultUploadServerResponse

interface PhotosApiCommon {

    /**
     * @see <a href="https://vk.com/dev/photos.getMessagesUploadServer">VK API</a>
     */
    fun getMessagesUploadServer(
        peerId: Int
    ): VkApiRequest<PhotosUploadServerResponse>

    /**
     * @see <a href="https://vk.com/dev/photos.getOwnerCoverPhotoUploadServer">VK API</a>
     */
    fun getOwnerCoverPhotoUploadServer(
        groupId: Int,
        cropX: Int = 0,
        cropY: Int = 0,
        cropX2: Int = 795,
        cropY2: Int = 200
    ): VkApiRequest<DefaultUploadServerResponse>

    /**
     * @see <a href="https://vk.com/dev/photos.saveMessagesPhoto">VK API</a>
     */
    fun saveMessagesPhoto(
        server: Int,
        photo: String,
        hash: String
    ): VkApiRequest<List<Photo>>

    /**
     * @see <a href="https://vk.com/dev/photos.saveOwnerCoverPhoto">VK API</a>
     */
    fun saveOwnerCoverPhoto(
        photo: String,
        hash: String
    ): VkApiRequest<List<Community.Cover.Image>>

}