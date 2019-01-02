package tk.skeptick.vk.apiclient.methods.docs

import tk.skeptick.vk.apiclient.VkApiRequest
import tk.skeptick.vk.apiclient.domain.models.Document
import tk.skeptick.vk.apiclient.methods.DefaultListResponse
import tk.skeptick.vk.apiclient.methods.DefaultUploadServerResponse

interface DocsApiCommon {

    /**
     * @see <a href="https://vk.com/dev/docs.getMessagesUploadServer">VK API</a>
     */
    fun getMessagesUploadServer(
        peerId: Int,
        forAudioMessage: Boolean = false
    ): VkApiRequest<DefaultUploadServerResponse>

    /**
     * @see <a href="https://vk.com/dev/docs.getWallUploadServer">VK API</a>
     */
    fun getWallUploadServer(
        groupId: Int? = null
    ): VkApiRequest<DefaultUploadServerResponse>

    /**
     * @see <a href="https://vk.com/dev/docs.save">VK API</a>
     */
    fun save(
        file: String,
        title: String? = null,
        tags: List<String>? = null
    ): VkApiRequest<DocumentSaveResponse>

    /**
     * @param[count] maximum value 1000
     * @param[offset] maximum value 999
     * @see <a href="https://vk.com/dev/docs.search">VK API</a>
     */
    fun search(
        query: String,
        withOwn: Boolean = false,
        count: Int = 1000,
        offset: Int = 0
    ): VkApiRequest<DefaultListResponse<Document>>

}