package tk.skeptick.vk.apiclient.methods.docs

import tk.skeptick.vk.apiclient.BooleanInt
import tk.skeptick.vk.apiclient.VkApiRequest
import tk.skeptick.vk.apiclient.domain.models.Document
import tk.skeptick.vk.apiclient.domain.Media
import tk.skeptick.vk.apiclient.methods.DefaultListResponse
import tk.skeptick.vk.apiclient.methods.DefaultUploadServerResponse

interface DocsApiUser : DocsApiCommon {

    /**
     * @return ID of the created document
     * @see <a href="https://vk.com/dev/docs.add">VK API</a>
     */
    fun add(
        ownerId: Int,
        docId: Int,
        accessKey: String? = null
    ): VkApiRequest<Int>

    /**
     * @see <a href="https://vk.com/dev/docs.delete">VK API</a>
     */
    fun delete(
        ownerId: Int,
        docId: Int
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/docs.edit">VK API</a>
     */
    fun edit(
        docId: Int,
        title: String,
        ownerId: Int? = null,
        tags: List<String>? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @param[count] maximum value 2000
     * @param[offset] maximum value 1999
     * @see <a href="https://vk.com/dev/docs.get">VK API</a>
     */
    fun get(
        ownerId: Int? = null,
        count: Int = 2000,
        offset: Int = 0,
        type: Document.Type? = null
    ): VkApiRequest<DefaultListResponse<Document>>

    /**
     * @see <a href="https://vk.com/dev/docs.getById">VK API</a>
     */
    fun getById(
        docs: List<Media>
    ): VkApiRequest<List<Document>>

    /**
     * @see <a href="https://vk.com/dev/docs.getTypes">VK API</a>
     */
    fun getTypes(
        ownerId: Int? = null
    ): VkApiRequest<DefaultListResponse<DocumentType>>

    /**
     * @see <a href="https://vk.com/dev/docs.getUploadServer">VK API</a>
     */
    fun getUploadServer(
        groupId: Int? = null
    ): VkApiRequest<DefaultUploadServerResponse>

}