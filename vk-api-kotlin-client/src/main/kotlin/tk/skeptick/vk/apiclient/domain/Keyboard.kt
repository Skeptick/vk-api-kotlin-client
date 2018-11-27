package tk.skeptick.vk.apiclient.domain

import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.serializer
import tk.skeptick.vk.apiclient.EnumStringSerializer
import tk.skeptick.vk.apiclient.SerializableEnum
import tk.skeptick.vk.apiclient.json

@Serializable
data class Keyboard(
    @SerialName("one_time") val isOneTime: Boolean = false,
    @SerialName("buttons") val buttons: List<List<Button>> = emptyList()) {

    @Serializable
    class Button(
        @SerialName("action") val action: Action,
        @SerialName("color") val color: ButtonColor = ButtonColor.DEFAULT) {

        @Serializable
        data class Action(
            @SerialName("type") val type: Type = Type.TEXT,
            @SerialName("label") val label: String,
            @Optional @SerialName("payload") val payload: MessagePayload? = null) {

            @Serializable(with = Type.Companion::class)
            enum class Type(override val value: String) : SerializableEnum<String> {
                TEXT("text");

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

internal fun Keyboard.serialize(): String =
    json.stringify(Keyboard.serializer(), this)