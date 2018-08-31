package tk.skeptick.vk.apiclient.domain.models

import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tk.skeptick.vk.apiclient.EnumStringSerializer
import tk.skeptick.vk.apiclient.domain.Keyboard
import tk.skeptick.vk.apiclient.SerializableEnum
import tk.skeptick.vk.apiclient.domain.models.SimplePhoto

@Serializable
data class Conversation(
    @SerialName("peer") val peer: Peer,
    @SerialName("in_read") val inReadMessageId: Int,
    @SerialName("out_read") val outReadMessageId: Int,
    @SerialName("can_write") val canWrite: CanWrite,
    @SerialName("last_message_id") val lastMessageId: Int,
    @Optional @SerialName("unread_count") val unreadCount: Int = 0,
    @Optional @SerialName("important") val isImportant: Boolean? = null,
    @Optional @SerialName("unanswered") val isUnanswered: Boolean? = null,
    @Optional @SerialName("current_keyboard") val currentKeyboard: Keyboard? = null,
    @Optional @SerialName("chat_settings") val chatSettings: ChatSettings? = null,
    @Optional @SerialName("push_settings") val pushSettings: PushSettings? = null) {

    @Serializable
    data class Peer(
        @SerialName("id") val id: Int,
        @SerialName("type") val type: Type,
        @SerialName("local_id") val localId: Int) {

        @Serializable(with = Type.Companion::class)
        enum class Type(override val value: String) : SerializableEnum<String> {
            USER("user"),
            CHAT("chat"),
            GROUP("group"),
            EMAIL("email");

            companion object : EnumStringSerializer<Type>(
                clazz = Type::class,
                values = enumValues()
            )
        }

    }

    @Serializable
    data class CanWrite(
        @SerialName("allowed") val isAllowed: Boolean,
        @Optional @SerialName("reason") val reasonErrorCode: Int? = null)

    @Serializable
    data class ChatSettings(
        @SerialName("title") val title: String,
        @SerialName("members_count") val membersCount: Int,
        @SerialName("state") val state: State,
        @SerialName("active_ids") val activeUserIds: List<Int>,
        @SerialName("acl") val acl: AccessControlList,
        @SerialName("is_group_channel") val isGroupChannel: Boolean,
        @SerialName("owner_id") val ownerId: Int,
        @Optional @SerialName("pinned_message") val pinnedMessage: Message.Pinned? = null,
        @Optional @SerialName("photo") val photo: SimplePhoto? = null) {

        @Serializable(with = State.Companion::class)
        enum class State(override val value: String) : SerializableEnum<String> {
            IN("in"),
            KICKED("kicked"),
            LEFT("left");

            companion object : EnumStringSerializer<State>(
                clazz = State::class,
                values = enumValues()
            )
        }

        @Serializable
        data class AccessControlList(
            @SerialName("can_invite") val canInvite: Boolean,
            @SerialName("can_change_info") val canChangeInfo: Boolean,
            @SerialName("can_change_pin") val canChangePin: Boolean,
            @SerialName("can_promote_users") val canPromoteUsers: Boolean,
            @SerialName("can_see_invite_link") val canSeeInviteLink: Boolean,
            @SerialName("can_change_invite_link") val canChangeInviteLink: Boolean)

    }

    @Serializable
    data class PushSettings(
        @SerialName("no_sound") val isNoSound: Boolean,
        @SerialName("disabled_until") val disabledUntil: Int,
        @SerialName("disabled_forever") val isDisabledForever: Boolean)

}