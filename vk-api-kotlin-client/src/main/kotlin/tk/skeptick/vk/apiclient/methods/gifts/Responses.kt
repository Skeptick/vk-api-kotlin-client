package tk.skeptick.vk.apiclient.methods.gifts

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tk.skeptick.vk.apiclient.EnumIntSerializer
import tk.skeptick.vk.apiclient.IntEnum
import tk.skeptick.vk.apiclient.domain.models.Gift

@Serializable
data class UserGift(
    @SerialName("id") val id: Int,
    @SerialName("from_id") val fromId: Int = 0,
    @SerialName("message") val message: String,
    @SerialName("date") val date: Int,
    @SerialName("gift") val gift: Gift,
    @SerialName("privacy") val privacy: PrivacyType? = null) {

    val isAnonymous: Boolean get() = fromId == 0

    @Serializable(with = PrivacyType.Companion::class)
    enum class PrivacyType(override val value: Int) : IntEnum {
        SEEN_ALL(0),
        MESSAGE_SEEN_OWNER(1),
        NAME_AND_MESSAGE_SEEN_OWNER(2);

        companion object : EnumIntSerializer<PrivacyType>(PrivacyType::class, values())
    }

}