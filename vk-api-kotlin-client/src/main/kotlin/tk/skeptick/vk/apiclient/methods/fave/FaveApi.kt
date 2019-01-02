package tk.skeptick.vk.apiclient.methods.fave

import tk.skeptick.vk.apiclient.*
import tk.skeptick.vk.apiclient.domain.models.*
import tk.skeptick.vk.apiclient.methods.DefaultListResponse
import tk.skeptick.vk.apiclient.methods.ExtendedListResponse

class FaveApi(override val client: VkApiClient)
    : FaveApiUser, MethodsContext {

    override fun addGroup(
        groupId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.addGroup.httpGet(
            "group_id" to groupId
        ).withSerializer(BooleanInt.serializer())

    override fun addLink(
        link: String,
        description: String?
    ): VkApiRequest<BooleanInt> =
        Methods.addLink.httpPost(
            "link" to link,
            "text" to description
        ).withSerializer(BooleanInt.serializer())

    override fun addUser(
        userId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.addUser.httpGet(
            "user_id" to userId
        ).withSerializer(BooleanInt.serializer())

    override fun getLinks(
        count: Int,
        offset: Int
    ): VkApiRequest<DefaultListResponse<FaveLink>> =
        Methods.getLinks.httpGet(
            "count" to count,
            "offset" to offset
        ).withSerializer(list(FaveLink.serializer()))

    override fun getMarketItems(
        count: Int,
        offset: Int,
        extended: Boolean
    ): VkApiRequest<DefaultListResponse<Market>> =
        Methods.getMarketItems.httpGet(
            "count" to count,
            "offset" to offset,
            "extended" to extended.asInt()
        ).withSerializer(list(Market.serializer()))

    override fun getPhotos(
        count: Int,
        offset: Int,
        withPhotoSizes: Boolean
    ): VkApiRequest<DefaultListResponse<Photo>> =
        Methods.getPhotos.httpGet(
            "count" to count,
            "offset" to offset,
            "photo_sizes" to withPhotoSizes.asInt()
        ).withSerializer(list(Photo.serializer()))

    override fun getPosts(
        count: Int,
        offset: Int,
        extended: Boolean
    ): VkApiRequest<ExtendedListResponse<WallPost>> =
        Methods.getPosts.httpGet(
            "count" to count,
            "offset" to offset,
            "extended" to extended.asInt()
        ).withSerializer(extendedList(WallPost.serializer()))

    override fun getUsers(
        count: Int,
        offset: Int
    ): VkApiRequest<DefaultListResponse<User>> =
        Methods.getUsers.httpGet(
            "count" to count,
            "offset" to offset
        ).withSerializer(list(User.serializer()))

    override fun getVideos(
        count: Int,
        offset: Int,
        extended: Boolean
    ): VkApiRequest<ExtendedListResponse<Video>> =
        Methods.getVideos.httpGet(
            "count" to count,
            "offset" to offset,
            "extended" to extended.asInt()
        ).withSerializer(extendedList(Video.serializer()))

    override fun removeGroup(
        groupId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.removeGroup.httpGet(
            "group_id" to groupId
        ).withSerializer(BooleanInt.serializer())

    override fun removeLink(
        linkId: String
    ): VkApiRequest<BooleanInt> =
        Methods.removeLink.httpGet(
            "link_id" to linkId
        ).withSerializer(BooleanInt.serializer())

    override fun removeUser(
        userId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.removeUser.httpGet(
            "user_id" to userId
        ).withSerializer(BooleanInt.serializer())

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