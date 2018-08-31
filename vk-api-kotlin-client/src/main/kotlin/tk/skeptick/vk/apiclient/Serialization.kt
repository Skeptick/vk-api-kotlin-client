package tk.skeptick.vk.apiclient

import kotlinx.serialization.*
import kotlinx.serialization.internal.IntSerializer
import kotlin.reflect.KClass

@Serializable
data class BooleanInt(val value: Boolean) {
    @Serializer(forClass = BooleanInt::class)
    companion object : KSerializer<BooleanInt> {
        override fun save(output: KOutput, obj: BooleanInt) =
            output.writeIntValue(if (obj.value) 1 else 0)

        override fun load(input: KInput): BooleanInt =
            BooleanInt(input.readNullable(IntSerializer) == 1)
    }
}

interface SerializableEnum<out T> {
    val value: T
}

abstract class EnumIntSerializer<E>(clazz: KClass<E>, values: Array<E>)
    : EnumSerializer<E, Int>(clazz, values)
    where E : Enum<E>, E : SerializableEnum<Int> {

    override fun save(output: KOutput, obj: E) = output.writeIntValue(obj.value)
    override fun load(input: KInput): E = types[input.readIntValue()]!!
}

abstract class EnumStringSerializer<E>(clazz: KClass<E>, values: Array<E>)
    : EnumSerializer<E, String>(clazz, values)
    where E : Enum<E>, E : SerializableEnum<String> {

    override fun save(output: KOutput, obj: E) = output.writeStringValue(obj.value)
    override fun load(input: KInput): E = types[input.readStringValue()]!!
}

abstract class EnumSerializer<E, T>(clazz: KClass<E>, values: Array<E>)
    : KSerializer<E> where E : Enum<E>, E : SerializableEnum<T> {

    protected val types: Map<T, E> = values.map { it.value to it }.toMap()
    override var serialClassDesc: KSerialClassDesc = EnumDesc(clazz.enumClassName())
}

class EnumDesc(override val name: String) : KSerialClassDesc {
    override val kind: KSerialClassKind = KSerialClassKind.ENUM
    override fun getElementName(index: Int) = throw IllegalStateException("Primitives does not have fields")
    override fun getElementIndex(name: String) = throw IllegalStateException("Primitives does not have fields")
}