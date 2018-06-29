package tk.skeptick.vk.apiclient.domain

import kotlinx.serialization.*
import kotlinx.serialization.internal.StringSerializer
import tk.skeptick.vk.apiclient.json

@Serializable
data class MessagePayload(val value: String) {

    fun <T> to(loader: KSerialLoader<T>): T =
        json.parse(loader, value)

    inline fun <reified T : Any> to(): T =
        to(T::class.serializer())

    @Serializer(forClass = MessagePayload::class)
    companion object : KSerializer<MessagePayload> {

        override fun save(output: KOutput, obj: MessagePayload) =
            output.writeStringValue(obj.value)

        override fun load(input: KInput): MessagePayload =
            MessagePayload(input.read(StringSerializer))

        fun <T> from(saver: KSerialSaver<T>, obj: T): MessagePayload =
            MessagePayload(json.stringify(saver, obj))

        inline fun <reified T : Any> from(obj: T): MessagePayload =
            from(T::class.serializer(), obj)

    }

}