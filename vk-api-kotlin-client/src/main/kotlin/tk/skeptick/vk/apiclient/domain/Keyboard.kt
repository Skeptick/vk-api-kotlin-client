package tk.skeptick.vk.apiclient.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
            @SerialName("link") val link: String? = null,
            @SerialName("app_id") val appId: Int? = null,
            @SerialName("owner_id") val ownerId: Int? = null) {

            @Serializable
            enum class Type(val value: String) {
                @SerialName("text") TEXT("text"),
                @SerialName("location") LOCATION("location"),
                @SerialName("vkpay") VK_PAY("vkpay"),
                @SerialName("open_app") VK_APP("open_app"),
                @SerialName("open_link") OPEN_LINK("open_link"),
                @SerialName("open_photo") OPEN_PHOTO("open_photo")
            }

        }

        @Serializable
        enum class ButtonColor(val value: String) {
            @SerialName("primary") PRIMARY("primary"),
            @SerialName("default") DEFAULT("default"),
            @SerialName("negative") NEGATIVE("negative"),
            @SerialName("positive") POSITIVE("positive")
        }

    }

}