@file:Suppress("UNUSED")

package tk.skeptick.vk.apiclient

import tk.skeptick.vk.apiclient.domain.Keyboard
import tk.skeptick.vk.apiclient.domain.MessagePayload

@DslMarker
annotation class KeyboardDsl

@KeyboardDsl
class KeyboardBuilder {

    internal val rows: MutableList<List<Keyboard.Button>> = mutableListOf()

    fun buttonsRow(block: RowBuilder.() -> Unit) {
        rows += RowBuilder().apply(block).buttons
    }

    fun locationButton(payload: MessagePayload? = null, block: LocationButtonBuilder.() -> Unit = { }) {
        rows += listOf(LocationButtonBuilder(payload).apply(block).build())
    }

    fun vkPayButton(hash: String, payload: MessagePayload? = null, block: VkPayButton.() -> Unit = { }) {
        rows += listOf(VkPayButton(hash, payload).apply(block).build())
    }

    fun vkAppButton(label: String, appId: Int, ownerId: Int? = null, hash: String? = null, payload: MessagePayload? = null, block: VkAppButton.() -> Unit = { }) {
        rows += listOf(VkAppButton(label, appId, ownerId, hash, payload).apply(block).build())
    }

    fun openLinkButton(label: String, link: String, payload: MessagePayload? = null, block: OpenLinkButton.() -> Unit = { }) {
        rows += listOf(OpenLinkButton(label, link, payload).apply(block).build())
    }

    @KeyboardDsl
    class RowBuilder {

        internal val buttons: MutableList<Keyboard.Button> = mutableListOf()

        fun primaryButton(label: String, payload: MessagePayload? = null, block: TextButtonBuilder.() -> Unit = { }) {
            addButton(label, payload, block, Keyboard.Button.ButtonColor.PRIMARY)
        }

        fun defaultButton(label: String, payload: MessagePayload? = null, block: TextButtonBuilder.() -> Unit = { }) {
            addButton(label, payload, block, Keyboard.Button.ButtonColor.DEFAULT)
        }

        fun negativeButton(label: String, payload: MessagePayload? = null, block: TextButtonBuilder.() -> Unit = { }) {
            addButton(label, payload, block, Keyboard.Button.ButtonColor.NEGATIVE)
        }

        fun positiveButton(label: String, payload: MessagePayload? = null, block: TextButtonBuilder.() -> Unit = { }) {
            addButton(label, payload, block, Keyboard.Button.ButtonColor.POSITIVE)
        }

        private fun addButton(label: String, payload: MessagePayload?, block: TextButtonBuilder.() -> Unit, color: Keyboard.Button.ButtonColor) {
            buttons += TextButtonBuilder(label, color, payload).apply(block).build()
        }

        @KeyboardDsl
        class TextButtonBuilder(val label: String, val color: Keyboard.Button.ButtonColor, var payload: MessagePayload?) {
            internal fun build(): Keyboard.Button = Keyboard.Button(
                action = Keyboard.Button.Action(Keyboard.Button.Action.Type.TEXT, label = label, payload = payload),
                color = color
            )
        }

    }

    @KeyboardDsl
    class LocationButtonBuilder(var payload: MessagePayload?) {
        internal fun build(): Keyboard.Button = Keyboard.Button(
            action = Keyboard.Button.Action(Keyboard.Button.Action.Type.LOCATION, payload = payload)
        )
    }

    @KeyboardDsl
    class VkPayButton(val hash: String, var payload: MessagePayload?) {
        internal fun build(): Keyboard.Button = Keyboard.Button(
            action = Keyboard.Button.Action(Keyboard.Button.Action.Type.VK_PAY, payload = payload, hash = hash)
        )
    }

    @KeyboardDsl
    class VkAppButton(val label: String, val appId: Int, var ownerId: Int?, var hash: String?, var payload: MessagePayload?) {
        internal fun build(): Keyboard.Button = Keyboard.Button(
            action = Keyboard.Button.Action(Keyboard.Button.Action.Type.VK_APP, label = label, appId = appId, ownerId = ownerId, payload = payload, hash = hash)
        )
    }

    @KeyboardDsl
    class OpenLinkButton(val label: String, val link: String, var payload: MessagePayload?) {
        internal fun build(): Keyboard.Button = Keyboard.Button(
            action = Keyboard.Button.Action(Keyboard.Button.Action.Type.OPEN_LINK, label = label, link = link, payload = payload)
        )
    }

}

fun keyboard(
    isOneTime: Boolean = false,
    builder: KeyboardBuilder.() -> Unit
): Keyboard = Keyboard(
    isOneTime = isOneTime,
    buttons = KeyboardBuilder().apply(builder).rows
)

fun inlineKeyboard(
    builder: KeyboardBuilder.() -> Unit
): Keyboard = Keyboard(
    isInline = true,
    buttons = KeyboardBuilder().apply(builder).rows
)