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
        rows.add(RowBuilder().apply(block).buttons)
    }

    @KeyboardDsl
    class RowBuilder {

        internal val buttons: MutableList<Keyboard.Button> = mutableListOf()

        fun primaryButton(
            label: String,
            payload: MessagePayload? = null,
            block: ButtonBuilder.() -> Unit = { }
        ) = addButton(label, payload, block, Keyboard.Button.ButtonColor.PRIMARY)

        fun defaultButton(
            label: String,
            payload: MessagePayload? = null,
            block: ButtonBuilder.() -> Unit = { }
        ) = addButton(label, payload, block, Keyboard.Button.ButtonColor.DEFAULT)

        fun negativeButton(
            label: String,
            payload: MessagePayload? = null,
            block: ButtonBuilder.() -> Unit = { }
        ) = addButton(label, payload, block, Keyboard.Button.ButtonColor.NEGATIVE)

        fun positiveButton(
            label: String,
            payload: MessagePayload? = null,
            block: ButtonBuilder.() -> Unit = { }
        ) = addButton(label, payload, block, Keyboard.Button.ButtonColor.POSITIVE)

        private fun addButton(
            label: String,
            payload: MessagePayload?,
            block: ButtonBuilder.() -> Unit,
            color: Keyboard.Button.ButtonColor
        ) = ButtonBuilder().apply {
            this.color = color
            this.label = label
            this.payload = payload
            this.block()
        }.also { buttons.add(it.build()) }

        @KeyboardDsl
        class ButtonBuilder {

            internal var color = Keyboard.Button.ButtonColor.DEFAULT

            var label = "Label"
            var type = Keyboard.Button.Action.Type.TEXT
            var payload: MessagePayload? = null

            internal fun build(): Keyboard.Button = Keyboard.Button(
                action = Keyboard.Button.Action(type, label, payload),
                color = color
            )

        }

    }

}

fun keyboard(
    isOneTime: Boolean = false,
    builder: KeyboardBuilder.() -> Unit
): Keyboard = Keyboard(
    isOneTime = isOneTime,
    buttons = KeyboardBuilder().apply(builder).rows
)