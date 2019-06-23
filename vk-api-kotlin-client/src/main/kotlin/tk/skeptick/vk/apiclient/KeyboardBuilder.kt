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

    fun locationButton(payload: MessagePayload? = null, block: LocationButtonBuilder.() -> Unit = { }) {
        rows += listOf(LocationButtonBuilder().apply { this.payload = payload }.apply(block).build())
    }

    fun vkPayButton(hash: String, payload: MessagePayload? = null, block: VkPayButton.() -> Unit = { }) {
        rows += listOf(VkPayButton(hash).apply { this.payload = payload }.apply(block).build())
    }

    fun vkAppButton(
        label: String,
        appId: Int,
        ownerId: Int? = null,
        hash: String? = null,
        payload: MessagePayload? = null,
        block: VkAppButton.() -> Unit = { }
    ) {
        rows += listOf(VkAppButton(label, appId).apply {
            this.ownerId = ownerId
            this.hash = hash
            this.payload = payload
            this.block()
        }.build())
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
            buttons += TextButtonBuilder(label, color).apply { this.payload = payload }.apply(block).build()
        }

        @KeyboardDsl
        class TextButtonBuilder(val label: String, val color: Keyboard.Button.ButtonColor) {
            var payload: MessagePayload? = null
            internal fun build(): Keyboard.Button = Keyboard.Button(
                action = Keyboard.Button.Action(Keyboard.Button.Action.Type.TEXT, label = label, payload = payload),
                color = color
            )
        }

    }

    @KeyboardDsl
    class LocationButtonBuilder {
        var payload: MessagePayload? = null
        internal fun build(): Keyboard.Button = Keyboard.Button(
            action = Keyboard.Button.Action(Keyboard.Button.Action.Type.LOCATION, payload = payload)
        )
    }

    @KeyboardDsl
    class VkPayButton(val hash: String) {
        var payload: MessagePayload? = null
        internal fun build(): Keyboard.Button = Keyboard.Button(
            action = Keyboard.Button.Action(Keyboard.Button.Action.Type.VK_PAY, payload = payload, hash = hash)
        )
    }

    @KeyboardDsl
    class VkAppButton(val label: String, val appId: Int) {
        var ownerId: Int? = null
        var hash: String? = null
        var payload: MessagePayload? = null
        internal fun build(): Keyboard.Button = Keyboard.Button(
            action = Keyboard.Button.Action(
                type = Keyboard.Button.Action.Type.VK_APP,
                label = label,
                appId = appId,
                ownerId = ownerId,
                payload = payload,
                hash = hash
            )
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