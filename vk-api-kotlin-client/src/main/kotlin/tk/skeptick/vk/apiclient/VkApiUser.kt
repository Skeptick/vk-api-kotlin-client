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
import tk.skeptick.vk.apiclient.methods.messages.MessagesApi
import tk.skeptick.vk.apiclient.methods.messages.MessagesApiUser
import tk.skeptick.vk.apiclient.methods.uploads.UploadsApi
import tk.skeptick.vk.apiclient.methods.uploads.UploadsApiUser

class VkApiUser(client: VkApiClient) {

    val account: AccountApiUser = AccountApi(client)
    val docs: DocsApiUser = DocsApi(client)
    val execute: ExecuteApiUser = ExecuteApi(client)
    val fave: FaveApiUser = FaveApi(client)
    val friends: FriendsApiUser = FriendsApi(client)
    val gifts: GiftsApiUser = GiftsApi(client)
    val groups: GroupsApiUser = GroupsApi(client)
    val messages: MessagesApiUser = MessagesApi(client)
    val upload: UploadsApiUser = UploadsApi(client)

}