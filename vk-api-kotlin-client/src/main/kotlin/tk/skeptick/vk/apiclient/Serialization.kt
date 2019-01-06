package tk.skeptick.vk.apiclient

import kotlinx.serialization.*
import kotlinx.serialization.internal.EnumDescriptor
import kotlinx.serialization.internal.IntSerializer
import kotlin.reflect.KClass

@Serializable
data class BooleanInt(val value: Boolean) {

    @Serializer(forClass = BooleanInt::class)
    companion object : KSerializer<BooleanInt> {

        override fun serialize(output: Encoder, obj: BooleanInt) =
            output.encodeInt(if (obj.value) 1 else 0)

        override fun deserialize(input: Decoder): BooleanInt =
            BooleanInt(input.decodeNullable(IntSerializer) == 1)
    }
}

interface SerializableEnum<out T> {
    val value: T
}

abstract class EnumIntSerializer<E>(clazz: KClass<E>)
    : CustomEnumSerializer<E, Int>(clazz) where E : Enum<E>, E : SerializableEnum<Int> {

    override fun serialize(output: Encoder, obj: E) = output.encodeInt(values[members.indexOf(obj)])
    override fun deserialize(input: Decoder): E = members[values.indexOf(input.decodeInt())]
}

abstract class EnumStringSerializer<E>(clazz: KClass<E>)
    : CustomEnumSerializer<E, String>(clazz) where E : Enum<E>, E : SerializableEnum<String> {

    override fun serialize(output: Encoder, obj: E) = output.encodeString(values[members.indexOf(obj)])
    override fun deserialize(input: Decoder): E = members[values.indexOf(input.decodeString())]
}

abstract class CustomEnumSerializer<E, T>(clazz: KClass<E>)
    : KSerializer<E> where E : Enum<E>, E : SerializableEnum<T> {

    protected val members = clazz.enumMembers()
    protected val values = members.map(SerializableEnum<T>::value)
    private val names = members.map(Enum<E>::name).toTypedArray()
    override val descriptor: EnumDescriptor = EnumDescriptor(clazz.enumClassName(), names)
}