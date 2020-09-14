package tk.skeptick.vk.apiclient.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tk.skeptick.vk.apiclient.domain.Keyboard

@Serializable
data class Conversation(
    @SerialName("peer") val peer: Peer,
    @SerialName("in_read") val inReadMessageId: Int,
    @SerialName("out_read") val outReadMessageId: Int,
    @SerialName("can_write") val canWrite: CanWrite,
    @SerialName("last_message_id") val lastMessageId: Int,
    @SerialName("can_send_money") val canSendMoney: Boolean? = null,
    @SerialName("can_receive_money") val canReceiveMoney: Boolean? = null,
    @SerialName("unread_count") val unreadCount: Int = 0,
    @SerialName("important") val isImportant: Boolean? = null,
    @SerialName("unanswered") val isUnanswered: Boolean? = null,
    @SerialName("current_keyboard") val currentKeyboard: Keyboard? = null,
    @SerialName("chat_settings") val chatSettings: ChatSettings? = null,
    @SerialName("push_settings") val pushSettings: PushSettings? = null) {

    @Serializable
    data class Peer(
        @SerialName("id") val id: Int,
        @SerialName("type") val type: Type,
        @SerialName("local_id") val localId: Int) {

        @Serializable
        enum class Type(val value: String) {
            @SerialName("user") USER("user"),
            @SerialName("chat") CHAT("chat"),
            @SerialName("group") GROUP("group"),
            @SerialName("email") EMAIL("email")
        }

    }

    @Serializable
    data class CanWrite(
        @SerialName("allowed") val isAllowed: Boolean,
        @SerialName("reason") val reasonErrorCode: Int? = null)

    @Serializable
    data class ChatSettings(
        @SerialName("owner_id") val ownerId: Int,
        @SerialName("title") val title: String,
        @SerialName("state") val state: State,
        @SerialName("admin_ids") val adminIds: List<Int>,
        @SerialName("active_ids") val activeUserIds: List<Int>,
        @SerialName("acl") val acl: AccessControlList,
        @SerialName("members_count") val membersCount: Int? = null,
        @SerialName("is_group_channel") val isGroupChannel: Boolean? = null,
        @SerialName("is_disappearing") val isDisappearing: Boolean? = null,
        @SerialName("pinned_message") val pinnedMessage: Message.Pinned? = null,
        @SerialName("photo") val photo: SimplePhoto? = null,
        @SerialName("theme") val theme: String? = null) {

        @Serializable
        enum class State(val value: String) {
            @SerialName("in") IN("in"),
            @SerialName("kicked") KICKED("kicked"),
            @SerialName("left") LEFT("left")
        }

        @Serializable
        data class AccessControlList(
            @SerialName("can_invite") val canInvite: Boolean,
            @SerialName("can_change_info") val canChangeInfo: Boolean,
            @SerialName("can_change_pin") val canChangePin: Boolean,
            @SerialName("can_promote_users") val canPromoteUsers: Boolean,
            @SerialName("can_see_invite_link") val canSeeInviteLink: Boolean,
            @SerialName("can_change_invite_link") val canChangeInviteLink: Boolean,
            @SerialName("can_moderate") val canModerate: Boolean,
            @SerialName("can_copy_chat") val canCopyChat: Boolean)

    }

    @Serializable
    data class PushSettings(
        @SerialName("no_sound") val isNoSound: Boolean,
        @SerialName("disabled_forever") val isDisabledForever: Boolean,
        @SerialName("disabled_until") val disabledUntil: Int? = null)

}