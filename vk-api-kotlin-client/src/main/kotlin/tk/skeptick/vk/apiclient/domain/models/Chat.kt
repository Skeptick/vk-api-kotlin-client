package tk.skeptick.vk.apiclient.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tk.skeptick.vk.apiclient.BooleanInt

@Serializable
data class Chat(
    @SerialName("id") val id: Int,
    @SerialName("type") val type: String,
    @SerialName("title") val title: String,
    @SerialName("admin_id") val adminId: Int,
    @SerialName("users") val users: List<Int>,
    @SerialName("push_settings") val pushSettings: PushSettings? = null,
    @SerialName("photo_50") val photo50: String? = null,
    @SerialName("photo_100") val photo100: String? = null,
    @SerialName("photo_200") val photo200: String? = null,
    @SerialName("left") private val wasLeft: BooleanInt = BooleanInt(false),
    @SerialName("kicked") private val wasKicked: BooleanInt = BooleanInt(false)) {

    val photoMax: String? get() = photo200 ?: photo100 ?: photo50

    @Serializable
    data class PushSettings(
        @SerialName("sound") val isSoundEnabled: BooleanInt? = null,
        @SerialName("disabled_until") val disabledUntil: Int? = null) {

        val isNotificationDisabledForever: Boolean? get() = disabledUntil?.let { it == -1 }

    }

}