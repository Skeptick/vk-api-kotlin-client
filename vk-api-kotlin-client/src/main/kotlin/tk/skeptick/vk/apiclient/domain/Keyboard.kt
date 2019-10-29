package tk.skeptick.vk.apiclient.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tk.skeptick.vk.apiclient.EnumStringSerializer
import tk.skeptick.vk.apiclient.SerializableEnum

@Serializable
data class Keyboard(
    @SerialName("one_time") val isOneTime: Boolean = false,
    @SerialName("inline") val isInline: Boolean = false,
    @SerialName("buttons") val buttons: List<List<Button>> = emptyList(),
    @SerialName("author_id") val authorId: Int? = null) {

    @Serializable
    class Button(
        @SerialName("action") val action: Action,
        @SerialName("color") val color: ButtonColor? = null) {

        @Serializable
        data class Action(
            @SerialName("type") val type: Type,
            @SerialName("label") val label: String? = null,
            @SerialName("payload") val payload: MessagePayload? = null,
            @SerialName("hash") val hash: String? = null,
            @SerialName("app_id") val appId: Int? = null,
            @SerialName("owner_id") val ownerId: Int? = null) {

            @Serializable(with = Type.Companion::class)
            enum class Type(override val value: String) : SerializableEnum<String> {
                TEXT("text"),
                LOCATION("location"),
                VK_PAY("vkpay"),
                VK_APP("open_app");

                companion object : EnumStringSerializer<Type>(Type::class)
            }

        }

        @Serializable(with = ButtonColor.Companion::class)
        enum class ButtonColor(override val value: String) : SerializableEnum<String> {
            PRIMARY("primary"),
            DEFAULT("default"),
            NEGATIVE("negative"),
            POSITIVE("positive");

            companion object : EnumStringSerializer<ButtonColor>(ButtonColor::class)
        }

    }

}