package tk.skeptick.vk.apiclient.methods.friends

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tk.skeptick.vk.apiclient.BooleanInt
import tk.skeptick.vk.apiclient.EnumIntSerializer
import tk.skeptick.vk.apiclient.IntEnum
import tk.skeptick.vk.apiclient.domain.models.User

@Serializable(with = AddFriendResponse.Companion::class)
enum class AddFriendResponse(override val value: Int) : IntEnum {
    REQUEST_SENT(1),
    REQUEST_APPROVED(2),
    REQUEST_RESENDING(4);

    companion object : EnumIntSerializer<AddFriendResponse>(AddFriendResponse::class, values())
}

@Serializable
data class AreFriendResponse(
    @SerialName("user_id") val userId: Int,
    @SerialName("friend_status") val status: User.FriendStatus,
    @SerialName("request_message") val requestMessage: String? = null,
    @SerialName("read_state") val isRead: BooleanInt? = null,
    @SerialName("sign") val sign: String? = null)

@Serializable
data class DeleteFriendResponse(
    @SerialName("success") val isSuccess: BooleanInt,
    @SerialName("friend_deleted") val isFriendDeleted: BooleanInt? = null,
    @SerialName("out_request_deleted") val isOutRequestDeleted: BooleanInt? = null,
    @SerialName("in_request_deleted") val isInRequestDeleted: BooleanInt? = null,
    @SerialName("suggestion_deleted") val isSuggestionDeleted: BooleanInt? = null)

@Serializable
data class FriendsList(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String)

@Serializable
data class MutualFriendsResponse(
    @SerialName("id") val id: Int,
    @SerialName("common_friends") val commonFriends: List<Int>,
    @SerialName("common_count") val commonCount: Int)

@Serializable
data class OnlineFriendsResponse(
    @SerialName("online") val online: List<Int>,
    @SerialName("online_mobile") val onlineMobile: List<Int>)

@Serializable
data class FriendRequest(
    @SerialName("user_id") val userId: Int,
    @SerialName("from") val from: Int? = null,
    @SerialName("message") val message: String? = null,
    @SerialName("mutual") val mutual: Mutual? = null) {

    @Serializable
    data class Mutual(
        @SerialName("count") val count: Int,
        @SerialName("users") val users: List<Int>)

}