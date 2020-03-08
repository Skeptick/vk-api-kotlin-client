package tk.skeptick.vk.apiclient

import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.builtins.nullable
import kotlin.reflect.KClass

@Serializable
data class BooleanInt(val value: Boolean) {
    @Serializer(forClass = BooleanInt::class)
    companion object : KSerializer<BooleanInt> {
        override fun serialize(encoder: Encoder, value: BooleanInt) = encoder.encodeInt(if (value.value) 1 else 0)
        override fun deserialize(decoder: Decoder): BooleanInt = BooleanInt(decoder.decodeNullableSerializableValue(Int.serializer().nullable) == 1)
    }
}

interface IntEnum {
    val value: Int
}

abstract class EnumIntSerializer<E>(clazz: KClass<E>, cases: Array<E>) : KSerializer<E> where E : Enum<E>, E : IntEnum {
    private val caseByInt: Map<Int, E> = cases.associateBy(IntEnum::value)
    override val descriptor: SerialDescriptor = EnumDescriptor(clazz, cases)
    override fun serialize(encoder: Encoder, value: E) = encoder.encodeInt(value.value)
    override fun deserialize(decoder: Decoder): E = caseByInt.getValue(decoder.decodeInt())
}

@Suppress("FunctionName")
private fun <E : Enum<E>> EnumDescriptor(clazz: KClass<E>, cases: Array<E>): SerialDescriptor {
    return SerialDescriptor(clazz.simpleName ?: "", UnionKind.ENUM_KIND) {
        for (case in cases) element(case.name, descriptor(case))
    }
}

@Suppress("NOTHING_TO_INLINE")
private inline fun <E : Enum<E>> SerialDescriptorBuilder.descriptor(case: E): SerialDescriptor {
    return SerialDescriptor("$serialName.${case.name}", StructureKind.OBJECT)
}