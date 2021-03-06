package tk.skeptick.vk.apiclient

import tk.skeptick.vk.apiclient.methods.account.AccountApi
import tk.skeptick.vk.apiclient.methods.account.AccountApiUser
import tk.skeptick.vk.apiclient.methods.docs.DocsApi
import tk.skeptick.vk.apiclient.methods.docs.DocsApiUser
import tk.skeptick.vk.apiclient.methods.execute.ExecuteApi
import tk.skeptick.vk.apiclient.methods.execute.ExecuteApiUser
import tk.skeptick.vk.apiclient.methods.fave.FaveApi
import tk.skeptick.vk.apiclient.methods.fave.FaveApiUser
import tk.skeptick.vk.apiclient.methods.friends.FriendsApi
import tk.skeptick.vk.apiclient.methods.friends.FriendsApiUser
import tk.skeptick.vk.apiclient.methods.gifts.GiftsApi
import tk.skeptick.vk.apiclient.methods.gifts.GiftsApiUser
import tk.skeptick.vk.apiclient.methods.groups.GroupsApi
import tk.skeptick.vk.apiclient.methods.groups.GroupsApiUser
import tk.skeptick.vk.apiclient.methods.likes.LikesApi
import tk.skeptick.vk.apiclient.methods.likes.LikesApiUser
import tk.skeptick.vk.apiclient.methods.messages.MessagesApi
import tk.skeptick.vk.apiclient.methods.messages.MessagesApiUser
import tk.skeptick.vk.apiclient.methods.photos.PhotosApi
import tk.skeptick.vk.apiclient.methods.photos.PhotosApiUser
import tk.skeptick.vk.apiclient.methods.uploads.UploadsApi
import tk.skeptick.vk.apiclient.methods.uploads.UploadsApiUser
import tk.skeptick.vk.apiclient.methods.users.UsersApi
import tk.skeptick.vk.apiclient.methods.users.UsersApiUser
import tk.skeptick.vk.apiclient.methods.utils.UtilsApi
import tk.skeptick.vk.apiclient.methods.utils.UtilsApiUser
import tk.skeptick.vk.apiclient.methods.video.VideoApi
import tk.skeptick.vk.apiclient.methods.video.VideoApiUser
import tk.skeptick.vk.apiclient.methods.wall.WallApi
import tk.skeptick.vk.apiclient.methods.wall.WallApiUser

open class VkApiUser(val client: ApiClient) {

    val account: AccountApiUser = AccountApi(client)
    val docs: DocsApiUser = DocsApi(client)
    val execute: ExecuteApiUser = ExecuteApi(client)
    val fave: FaveApiUser = FaveApi(client)
    val friends: FriendsApiUser = FriendsApi(client)
    val gifts: GiftsApiUser = GiftsApi(client)
    val groups: GroupsApiUser = GroupsApi(client)
    val likes: LikesApiUser = LikesApi(client)
    val messages: MessagesApiUser = MessagesApi(client)
    val photos: PhotosApiUser = PhotosApi(client)
    val users: UsersApiUser = UsersApi(client)
    val utils: UtilsApiUser = UtilsApi(client)
    val video: VideoApiUser = VideoApi(client)
    val wall: WallApiUser = WallApi(client)
    val upload: UploadsApiUser = UploadsApi(client)

}