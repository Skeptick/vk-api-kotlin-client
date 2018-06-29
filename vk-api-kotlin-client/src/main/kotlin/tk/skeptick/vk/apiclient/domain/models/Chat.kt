package tk.skeptick.vk.apiclient.domain.models

import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import tk.skeptick.vk.apiclient.BooleanInt

@Serializable
data class Chat(
    @SerialName("id") val id: Int,
    @SerialName("type") val type: String,
    @SerialName("title") val title: String,
    @SerialName("admin_id") val adminId: Int,
    @SerialName("users") val users: List<Int>,
    @Optional @SerialName("push_settings") val pushSettings: PushSettings? = null,
    @Optional @SerialName("photo_50") val photo50: String? = null,
    @Optional @SerialName("photo_100") val photo100: String? = null,
    @Optional @SerialName("photo_200") val photo200: String? = null,
    @Optional @SerialName("left") private val wasLeft: BooleanInt = BooleanInt(false),
    @Optional @SerialName("kicked") private val wasKicked: BooleanInt = BooleanInt(false)) {

    @Transient val photoMax: String? get() = photo200 ?: photo100 ?: photo50

    @Serializable
    data class PushSettings(
        @Optional @SerialName("sound") val isSoundEnabled: BooleanInt? = null,
        @Optional @SerialName("disabled_until") val disabledUntil: Int? = null) {

        @Transient val isNotificationDisabledForever: Boolean?
            get() = disabledUntil?.let { it == -1 }

    }

}