package tk.skeptick.vk.apiclient

import tk.skeptick.vk.apiclient.methods.docs.DocsApi
import tk.skeptick.vk.apiclient.methods.docs.DocsApiCommunity
import tk.skeptick.vk.apiclient.methods.execute.ExecuteApi
import tk.skeptick.vk.apiclient.methods.execute.ExecuteApiCommunity
import tk.skeptick.vk.apiclient.methods.groups.GroupsApi
import tk.skeptick.vk.apiclient.methods.groups.GroupsApiCommunity
import tk.skeptick.vk.apiclient.methods.messages.MessagesApi
import tk.skeptick.vk.apiclient.methods.messages.MessagesApiCommunity
import tk.skeptick.vk.apiclient.methods.photos.PhotosApi
import tk.skeptick.vk.apiclient.methods.photos.PhotosApiCommunity
import tk.skeptick.vk.apiclient.methods.photos.PhotosApiUser
import tk.skeptick.vk.apiclient.methods.uploads.UploadsApi
import tk.skeptick.vk.apiclient.methods.uploads.UploadsApiCommunity
import tk.skeptick.vk.apiclient.methods.users.UsersApi
import tk.skeptick.vk.apiclient.methods.users.UsersApiCommunity
import tk.skeptick.vk.apiclient.methods.utils.UtilsApi
import tk.skeptick.vk.apiclient.methods.utils.UtilsApiCommunity
import tk.skeptick.vk.apiclient.methods.wall.WallApi
import tk.skeptick.vk.apiclient.methods.wall.WallApiCommunity

open class VkApiCommunity(val client: ApiClient) {

    val docs: DocsApiCommunity = DocsApi(client)
    val execute: ExecuteApiCommunity = ExecuteApi(client)
    val groups: GroupsApiCommunity = GroupsApi(client)
    val messages: MessagesApiCommunity = MessagesApi(client)
    val photos: PhotosApiCommunity = PhotosApi(client)
    val users: UsersApiCommunity = UsersApi(client)
    val utils: UtilsApiCommunity = UtilsApi(client)
    val wall: WallApiCommunity = WallApi(client)
    val upload: UploadsApiCommunity = UploadsApi(client)

}