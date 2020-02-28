package tk.skeptick.vk.apiclient.methods.fave

import tk.skeptick.vk.apiclient.*
import tk.skeptick.vk.apiclient.methods.DefaultListResponse
import tk.skeptick.vk.apiclient.methods.ExtendedListResponse
import tk.skeptick.vk.apiclient.methods.FavePagesType
import tk.skeptick.vk.apiclient.methods.ObjectField

class FaveApi(override val client: ApiClient)
    : FaveApiUser, MethodsContext {

    override fun addArticle(
        url: String,
        ref: String?,
        trackCode: String?,
        source: String?
    ): VkApiRequest<BooleanInt> =
        Methods.addArticle.httpGet(BooleanInt.serializer()) {
            append("url", url)
            append("ref", ref)
            append("track_code", trackCode)
            append("source", source)
        }

    override fun addGroup(
        groupId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.addPage.httpGet(BooleanInt.serializer()) {
            append("group_id", groupId)
        }

    override fun addLink(
        link: String
    ): VkApiRequest<BooleanInt> =
        Methods.addLink.httpPost(BooleanInt.serializer()) {
            append("link", link)
        }

    override fun addUser(
        userId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.addPage.httpGet(BooleanInt.serializer()) {
            append("user_id", userId)
        }

    override fun addPost(
        id: Int,
        ownerId: Int,
        accessKey: String?,
        ref: String?,
        trackCode: String?,
        source: String?
    ): VkApiRequest<BooleanInt> =
        Methods.addPost.httpGet(BooleanInt.serializer()) {
            append("id", id)
            append("owner_id", ownerId)
            append("access_key", accessKey)
            append("ref", ref)
            append("track_code", trackCode)
            append("source", source)
        }

    override fun addProduct(
        id: Int,
        ownerId: Int,
        accessKey: String?,
        ref: String?,
        source: String?
    ): VkApiRequest<BooleanInt> =
        Methods.addProduct.httpGet(BooleanInt.serializer()) {
            append("id", id)
            append("owner_id", ownerId)
            append("access_key", accessKey)
            append("ref", ref)
            append("source", source)
        }

    override fun addTag(
        name: String
    ): VkApiRequest<FaveTag> =
        Methods.addTag.httpGet(FaveTag.serializer()) {
            append("name", name)
        }

    override fun addVideo(
        id: Int,
        ownerId: Int,
        accessKey: String?,
        ref: String?
    ): VkApiRequest<BooleanInt> =
        Methods.addVideo.httpGet(BooleanInt.serializer()) {
            append("id", id)
            append("owner_id", ownerId)
            append("access_key", accessKey)
            append("ref", ref)
        }

    override fun editTag(
        id: Int,
        name: String
    ): VkApiRequest<BooleanInt> =
        Methods.editTag.httpGet(BooleanInt.serializer()) {
            append("id", id)
            append("name", name)
        }

    override fun get(
        itemType: FaveItem.Type?,
        tagId: Int?,
        offset: Int,
        count: Int,
        isFromSnackbar: Boolean?,
        extended: Boolean,
        fields: List<ObjectField>
    ): VkApiRequest<ExtendedListResponse<FaveItem>> =
        Methods.get.httpGet(extendedList(FaveItem.serializer())) {
            append("item_type", itemType?.value)
            append("tag_id", tagId)
            append("offset", offset)
            append("count", count)
            append("is_from_snackbar", isFromSnackbar?.asInt())
            append("extended", extended.asInt())
            append("fields", fields.joinToString(",") { it.value })
        }

    override fun getPages(
        type: FavePagesType?,
        tagId: Int?,
        offset: Int,
        count: Int,
        fields: List<ObjectField>
    ): VkApiRequest<DefaultListResponse<FavePage>> =
        Methods.getPages.httpGet(list(FavePage.serializer())) {
            append("type", type?.value)
            append("tag_id", tagId)
            append("offset", offset)
            append("count", count)
            append("fields", fields.joinToString(",") { it.value })
        }

    override fun getTags(): VkApiRequest<DefaultListResponse<FaveTag>> =
        Methods.getTags.httpGet(list(FaveTag.serializer()))

    override fun markSeen(): VkApiRequest<BooleanInt> =
        Methods.markSeen.httpGet(BooleanInt.serializer())

    override fun removeArticle(
        articleId: Int,
        ownerId: Int,
        ref: String?
    ): VkApiRequest<BooleanInt> =
        Methods.removeArticle.httpGet(BooleanInt.serializer()) {
            append("article_id", articleId)
            append("owner_id", ownerId)
            append("ref", ref)
        }

    override fun removeGroup(
        groupId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.removePage.httpGet(BooleanInt.serializer()) {
            append("group_id", groupId)
        }

    override fun removeLink(
        linkId: String
    ): VkApiRequest<BooleanInt> =
        Methods.removeLink.httpGet(BooleanInt.serializer()) {
            append("link_id", linkId)
        }

    override fun removePost(
        id: Int,
        ownerId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.removePost.httpGet(BooleanInt.serializer()) {
            append("id", id)
            append("owner_id", ownerId)
        }

    override fun removeProduct(
        id: Int,
        ownerId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.removeProduct.httpGet(BooleanInt.serializer()) {
            append("id", id)
            append("owner_id", ownerId)
        }

    override fun removeTag(
        id: Int
    ): VkApiRequest<BooleanInt> =
        Methods.removeTag.httpGet(BooleanInt.serializer()) {
            append("id", id)
        }

    override fun removeUser(
        userId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.removePage.httpGet(BooleanInt.serializer()) {
            append("user_id", userId)
        }

    override fun removeVideo(
        id: Int,
        ownerId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.removeVideo.httpGet(BooleanInt.serializer()) {
            append("id", id)
            append("owner_id", ownerId)
        }

    override fun reorderTags(
        ids: List<Int>
    ): VkApiRequest<BooleanInt> =
        Methods.reorderTags.httpGet(BooleanInt.serializer()) {
            append("ids", ids.joinToString(","))
        }

    override fun setGroupTags(
        groupId: Int,
        tagIds: List<Int>?,
        ref: String?
    ): VkApiRequest<BooleanInt> =
        Methods.setPageTags.httpGet(BooleanInt.serializer()) {
            append("group_id", groupId)
            append("tag_ids", tagIds?.joinToString(","))
            append("ref", ref)
        }

    override fun setItemTags(
        itemType: FaveItem.Type,
        itemId: Int,
        itemOwnerId: Int,
        tagIds: List<Int>?,
        ref: String?
    ): VkApiRequest<BooleanInt> =
        Methods.setTags.httpGet(BooleanInt.serializer()) {
            append("item_type", itemType.value)
            append("item_id", itemId)
            append("item_owner_id", itemOwnerId)
            append("tag_ids", tagIds?.joinToString(","))
            append("ref", ref)
        }

    override fun setLinkTags(
        linkId: String,
        linkUrl: String,
        tagIds: List<Int>?,
        ref: String?
    ): VkApiRequest<BooleanInt> =
        Methods.setTags.httpGet(BooleanInt.serializer()) {
            append("item_type", FaveItem.Type.LINK.value)
            append("link_id", linkId)
            append("link_url", linkUrl)
            append("tag_ids", tagIds?.joinToString(","))
            append("ref", ref)
        }

    override fun setUserTags(
        userId: Int,
        tagIds: List<Int>?,
        ref: String?
    ): VkApiRequest<BooleanInt> =
        Methods.setPageTags.httpGet(BooleanInt.serializer()) {
            append("user_id", userId)
            append("tag_ids", tagIds?.joinToString(","))
            append("ref", ref)
        }

    override fun trackGroupInteraction(
        groupId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.trackPageInteraction.httpGet(BooleanInt.serializer()) {
            append("group_id", groupId)
        }

    override fun trackUserInteraction(
        userId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.trackPageInteraction.httpGet(BooleanInt.serializer()) {
            append("user_id", userId)
        }

    private object Methods {
        private const val it = "fave."
        const val addArticle = it + "addArticle"
        const val addLink = it + "addLink"
        const val addPage = it + "addPage"
        const val addPost = it + "addPost"
        const val addProduct = it + "addProduct"
        const val addTag = it + "addTag"
        const val addVideo = it + "addVideo"
        const val editTag = it + "editTag"
        const val get = it + "get"
        const val getPages = it + "getPages"
        const val getTags = it + "getTags"
        const val markSeen = it + "markSeen"
        const val removeArticle = it + "removeArticle"
        const val removeLink = it + "removeLink"
        const val removePage = it + "removePage"
        const val removePost = it + "removePost"
        const val removeProduct = it + "removeProduct"
        const val removeTag = it + "removeTag"
        const val removeVideo = it + "removeVideo"
        const val reorderTags = it + "reorderTags"
        const val setPageTags = it + "setPageTags"
        const val setTags = it + "setTags"
        const val trackPageInteraction = it + "trackPageInteraction"
    }

}