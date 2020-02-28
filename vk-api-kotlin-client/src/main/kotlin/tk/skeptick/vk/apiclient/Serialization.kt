package tk.skeptick.vk.apiclient

import kotlinx.serialization.*
import kotlinx.serialization.internal.EnumDescriptor
import kotlinx.serialization.internal.IntSerializer
import kotlinx.serialization.internal.nullable
import kotlin.reflect.KClass

@Serializable
data class BooleanInt(val value: Boolean) {

    @Serializer(forClass = BooleanInt::class)
    companion object : KSerializer<BooleanInt> {

        override fun serialize(encoder: Encoder, obj: BooleanInt) =
            encoder.encodeInt(if (obj.value) 1 else 0)

        override fun deserialize(decoder: Decoder): BooleanInt =
            BooleanInt(decoder.decodeNullableSerializableValue(IntSerializer.nullable) == 1)

    }
}

interface IntEnum {
    val value: Int
}

abstract class EnumIntSerializer<E>(clazz: KClass<E>, cases: Array<E>)
    : KSerializer<E> where E : Enum<E>, E : IntEnum {

    private val caseByInt = cases.associateBy(IntEnum::value)
    private val names = cases.map(Enum<E>::name).toTypedArray()

    @InternalSerializationApi override val descriptor = EnumDescriptor(clazz.enumClassName(), names)
    override fun serialize(encoder: Encoder, obj: E) = encoder.encodeInt(obj.value)
    override fun deserialize(decoder: Decoder): E = caseByInt.getValue(decoder.decodeInt())
}