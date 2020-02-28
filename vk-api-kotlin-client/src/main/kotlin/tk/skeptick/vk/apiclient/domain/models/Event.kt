package tk.skeptick.vk.apiclient.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tk.skeptick.vk.apiclient.EnumIntSerializer
import tk.skeptick.vk.apiclient.IntEnum

@Serializable
data class Event(
    @SerialName("id") val id: Int,
    @SerialName("time") val time: Int,
    @SerialName("member_status") val memberStatus: MemberStatus,
    @SerialName("is_favorite") val isFavorite: Boolean,
    @SerialName("text") val text: String,
    @SerialName("button_text") val buttonText: String,
    @SerialName("friends") val friends: List<Int>,
    @SerialName("address") val address: String? = null) {

    @Serializable(with = MemberStatus.Companion::class)
    enum class MemberStatus(override val value: Int) : IntEnum {
        NOT_SELECTED(0),
        DEFINITELY_GOING(1),
        MAYBE_GOING(2),
        NOT_GOING(3);

        companion object : EnumIntSerializer<MemberStatus>(MemberStatus::class, values())
    }

}