package tk.skeptick.vk.apiclient.domain

import kotlinx.serialization.*
import tk.skeptick.vk.apiclient.json

@Serializable
data class MessagePayload(val value: String) {

    fun <T> to(loader: DeserializationStrategy<T>): T =
        json.parse(loader, value)

    @ImplicitReflectionSerializer
    inline fun <reified T : Any> to(): T =
        to(T::class.serializer())

    @Serializer(forClass = MessagePayload::class)
    companion object : KSerializer<MessagePayload> {

        override fun serialize(output: Encoder, obj: MessagePayload) =
            output.encodeString(obj.value)

        override fun deserialize(input: Decoder): MessagePayload =
            MessagePayload(input.decodeString())

        fun <T> from(strategy: SerializationStrategy<T>, obj: T): MessagePayload =
            MessagePayload(json.stringify(strategy, obj))

        @ImplicitReflectionSerializer
        inline fun <reified T : Any> from(obj: T): MessagePayload =
            from(T::class.serializer(), obj)

    }

}