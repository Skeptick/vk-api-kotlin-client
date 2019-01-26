package tk.skeptick.vk.apiclient.methods.fave

import tk.skeptick.vk.apiclient.*
import tk.skeptick.vk.apiclient.domain.models.*
import tk.skeptick.vk.apiclient.methods.DefaultListResponse
import tk.skeptick.vk.apiclient.methods.ExtendedListResponse

class FaveApi(override val client: ApiClient)
    : FaveApiUser, MethodsContext {

    override fun addGroup(
        groupId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.addGroup.httpGet(BooleanInt.serializer()) {
            append("group_id", groupId)
        }

    override fun addLink(
        link: String,
        description: String?
    ): VkApiRequest<BooleanInt> =
        Methods.addLink.httpPost(BooleanInt.serializer()) {
            append("link", link)
            append("text", description)
        }

    override fun addUser(
        userId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.addUser.httpGet(BooleanInt.serializer()) {
            append("user_id", userId)
        }

    override fun getLinks(
        count: Int,
        offset: Int
    ): VkApiRequest<DefaultListResponse<FaveLink>> =
        Methods.getLinks.httpGet(list(FaveLink.serializer())) {
            append("count", count)
            append("offset", offset)
        }

    override fun getMarketItems(
        count: Int,
        offset: Int,
        extended: Boolean
    ): VkApiRequest<DefaultListResponse<Market>> =
        Methods.getMarketItems.httpGet(list(Market.serializer())) {
            append("count", count)
            append("offset", offset)
            append("extended", extended.asInt())
        }

    override fun getPhotos(
        count: Int,
        offset: Int,
        withPhotoSizes: Boolean
    ): VkApiRequest<DefaultListResponse<Photo>> =
        Methods.getPhotos.httpGet(list(Photo.serializer())) {
            append("count", count)
            append("offset", offset)
            append("photo_sizes", withPhotoSizes.asInt())
        }

    override fun getPosts(
        count: Int,
        offset: Int,
        extended: Boolean
    ): VkApiRequest<ExtendedListResponse<WallPost>> =
        Methods.getPosts.httpGet(extendedList(WallPost.serializer())) {
            append("count", count)
            append("offset", offset)
            append("extended", extended.asInt())
        }

    override fun getUsers(
        count: Int,
        offset: Int
    ): VkApiRequest<DefaultListResponse<User>> =
        Methods.getUsers.httpGet(list(User.serializer())) {
            append("count", count)
            append("offset", offset)
        }

    override fun getVideos(
        count: Int,
        offset: Int,
        extended: Boolean
    ): VkApiRequest<ExtendedListResponse<Video>> =
        Methods.getVideos.httpGet(extendedList(Video.serializer())) {
            append("count", count)
            append("offset", offset)
            append("extended", extended.asInt())
        }

    override fun removeGroup(
        groupId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.removeGroup.httpGet(BooleanInt.serializer()) {
            append("group_id", groupId)
        }

    override fun removeLink(
        linkId: String
    ): VkApiRequest<BooleanInt> =
        Methods.removeLink.httpGet(BooleanInt.serializer()) {
            append("link_id", linkId)
        }

    override fun removeUser(
        userId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.removeUser.httpGet(BooleanInt.serializer()) {
            append("user_id", userId)
        }

    private object Methods {
        private const val it = "fave."
        const val addGroup = it + "addGroup"
        const val addLink = it + "addLink"
        const val addUser = it + "addUser"
        const val getLinks = it + "getLinks"
        const val getMarketItems = it + "getMarketItems"
        const val getPhotos = it + "getPhotos"
        const val getPosts = it + "getPosts"
        const val getUsers = it + "getUsers"
        const val getVideos = it + "getVideos"
        const val removeGroup = it + "removeGroup"
        const val removeLink = it + "removeLink"
        const val removeUser = it + "removeUser"
    }

}