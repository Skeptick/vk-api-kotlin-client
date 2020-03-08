package tk.skeptick.vk.apiclient.methods.docs

import kotlinx.serialization.builtins.list
import kotlinx.serialization.builtins.serializer
import tk.skeptick.vk.apiclient.*
import tk.skeptick.vk.apiclient.domain.models.Document
import tk.skeptick.vk.apiclient.domain.Media
import tk.skeptick.vk.apiclient.methods.DefaultListResponse
import tk.skeptick.vk.apiclient.methods.DefaultUploadServerResponse

class DocsApi(override val client: ApiClient)
    : DocsApiUser, DocsApiCommunity, MethodsContext {

    override fun add(
        ownerId: Int,
        docId: Int,
        accessKey: String?
    ): VkApiRequest<Int> =
        Methods.add.httpGet(Int.serializer()) {
            append("owner_id", ownerId)
            append("doc_id", docId)
            append("access_key", accessKey)
        }

    override fun delete(
        ownerId: Int,
        docId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.delete.httpGet(BooleanInt.serializer()) {
            append("owner_id", ownerId)
            append("doc_id", docId)
        }

    override fun edit(
        docId: Int,
        title: String,
        ownerId: Int?,
        tags: List<String>?
    ): VkApiRequest<BooleanInt> =
        Methods.edit.httpPost(BooleanInt.serializer()) {
            append("doc_id", docId)
            append("title", title)
            append("owner_id", ownerId)
            append("tags", tags?.joinToString(","))
        }

    override fun get(
        ownerId: Int?,
        count: Int,
        offset: Int,
        type: Document.Type?
    ): VkApiRequest<DefaultListResponse<Document>> =
        Methods.get.httpGet(list(Document.serializer())) {
            append("owner_id", ownerId)
            append("count", count)
            append("offset", offset)
            append("type", type?.value)
        }

    override fun getById(
        docs: List<Media>,
        returnTags: Boolean
    ): VkApiRequest<List<Document>> =
        Methods.getById.httpPost(Document.serializer().list) {
            append("docs", docs.joinToString(",", transform = Media::media))
            append("return_tags", returnTags.asInt())
        }

    override fun getMessagesUploadServer(
        peerId: Int,
        forAudioMessage: Boolean
    ): VkApiRequest<DefaultUploadServerResponse> =
        Methods.getMessagesUploadServer.httpGet(DefaultUploadServerResponse.serializer()) {
            append("peer_id", peerId)
            append("type", if (forAudioMessage) "audio_message" else "doc")
        }

    override fun getTypes(
        ownerId: Int?
    ): VkApiRequest<DefaultListResponse<DocumentType>> =
        Methods.getTypes.httpGet(list(DocumentType.serializer())) {
            append("owner_id", ownerId)
        }

    override fun getUploadServer(
        groupId: Int?
    ): VkApiRequest<DefaultUploadServerResponse> =
        Methods.getUploadServer.httpGet(DefaultUploadServerResponse.serializer()) {
            append("group_id", groupId)
        }

    override fun getWallUploadServer(
        groupId: Int?
    ): VkApiRequest<DefaultUploadServerResponse> =
        Methods.getWallUploadServer.httpGet(DefaultUploadServerResponse.serializer()) {
            append("group_id", groupId)
        }

    override fun save(
        file: String,
        title: String?,
        tags: List<String>?
    ): VkApiRequest<DocumentSaveResponse> =
        Methods.save.httpPost(DocumentSaveResponse.serializer()) {
            append("file", file)
            append("title", title)
            append("tags", tags?.joinToString(","))
        }

    override fun search(
        query: String,
        withOwn: Boolean,
        count: Int,
        offset: Int
    ): VkApiRequest<DefaultListResponse<Document>> =
        Methods.search.httpGet(list(Document.serializer())) {
            append("q", query)
            append("search_own", withOwn.asInt())
            append("count", count)
            append("offset", offset)
        }

    private object Methods {
        private const val it = "docs."
        const val add = it + "add"
        const val delete = it + "delete"
        const val edit = it + "edit"
        const val get = it + "get"
        const val getById = it + "getById"
        const val getMessagesUploadServer = it + "getMessagesUploadServer"
        const val getTypes = it + "getTypes"
        const val getUploadServer = it + "getUploadServer"
        const val getWallUploadServer = it + "getWallUploadServer"
        const val save = it + "save"
        const val search = it + "search"
    }

}