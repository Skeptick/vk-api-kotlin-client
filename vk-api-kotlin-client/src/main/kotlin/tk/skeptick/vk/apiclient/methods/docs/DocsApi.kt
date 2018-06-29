package tk.skeptick.vk.apiclient.methods.docs

import kotlinx.serialization.internal.IntSerializer
import kotlinx.serialization.list
import tk.skeptick.vk.apiclient.*
import tk.skeptick.vk.apiclient.domain.models.Document
import tk.skeptick.vk.apiclient.domain.Media
import tk.skeptick.vk.apiclient.methods.DefaultListResponse
import tk.skeptick.vk.apiclient.methods.DefaultUploadServerResponse

class DocsApi(override val client: VkApiClient)
    : DocsApiUser, DocsApiCommunity, MethodsContext {

    override fun add(
        ownerId: Int,
        docId: Int,
        accessKey: String?
    ): VkApiRequest<Int> =
        Methods.add.httpGet(
            "owner_id" to ownerId,
            "doc_id" to docId,
            "access_key" to accessKey
        ).withSerializer(IntSerializer)

    override fun delete(
        ownerId: Int,
        docId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.delete.httpGet(
            "owner_id" to ownerId,
            "doc_id" to docId
        ).withSerializer(BooleanInt.serializer())

    override fun edit(
        docId: Int,
        title: String,
        ownerId: Int?,
        tags: List<String>?
    ): VkApiRequest<BooleanInt> =
        Methods.edit.httpGet(
            "doc_id" to docId,
            "title" to title,
            "owner_id" to ownerId,
            "tags" to tags?.joinToString(",")
        ).withSerializer(BooleanInt.serializer())

    override fun get(
        ownerId: Int?,
        count: Int,
        offset: Int,
        type: Document.Type?
    ): VkApiRequest<DefaultListResponse<Document>> =
        Methods.get.httpGet(
            "owner_id" to ownerId,
            "count" to count,
            "offset" to offset,
            "type" to type?.value
        ).withSerializer(list(Document.serializer()))

    override fun getById(
        docs: List<Media>
    ): VkApiRequest<List<Document>> =
        Methods.getById.httpGet(
            "docs" to prepareDocs(docs)
        ).withSerializer(Document.serializer().list)

    override fun getMessagesUploadServer(
        peerId: Int,
        forAudioMessage: Boolean
    ): VkApiRequest<DefaultUploadServerResponse> =
        Methods.getMessagesUploadServer.httpGet(
            "peer_id" to peerId,
            "type" to if (forAudioMessage) "audio_message" else "doc"
        ).withSerializer(DefaultUploadServerResponse.serializer())

    override fun getTypes(
        ownerId: Int?
    ): VkApiRequest<DefaultListResponse<DocumentType>> =
        Methods.getTypes.httpGet(
            "owner_id" to ownerId
        ).withSerializer(list(DocumentType.serializer()))

    override fun getUploadServer(
        groupId: Int?
    ): VkApiRequest<DefaultUploadServerResponse> =
        Methods.getUploadServer.httpGet(
            "group_id" to groupId
        ).withSerializer(DefaultUploadServerResponse.serializer())

    override fun getWallUploadServer(
        groupId: Int?
    ): VkApiRequest<DefaultUploadServerResponse> =
        Methods.getWallUploadServer.httpGet(
            "group_id" to groupId
        ).withSerializer(DefaultUploadServerResponse.serializer())

    override fun save(
        file: String,
        title: String?,
        tags: List<String>?
    ): VkApiRequest<List<Document>> =
        Methods.save.httpGet(
            "file" to file,
            "title" to title,
            "tags" to tags?.joinToString(",")
        ).withSerializer(Document.serializer().list)

    override fun search(
        query: String,
        withOwn: Boolean,
        count: Int,
        offset: Int
    ): VkApiRequest<DefaultListResponse<Document>> =
        Methods.search.httpGet(
            "q" to query,
            "search_own" to withOwn.asInt(),
            "count" to count,
            "offset" to offset
        ).withSerializer(list(Document.serializer()))

    private companion object {

        fun prepareDocs(
            docs: List<Media>
        ): String = docs.joinToString(",") {
            buildString {
                append(it.ownerId)
                append('_')
                append(it.id)
                it.accessKey?.let { append(it) }
            }
        }

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