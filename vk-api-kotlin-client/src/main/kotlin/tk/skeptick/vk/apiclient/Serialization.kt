@file:Suppress("NOTHING_TO_INLINE")

package tk.skeptick.vk.apiclient

import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.builtins.nullable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import tk.skeptick.vk.apiclient.domain.Keyboard
import tk.skeptick.vk.apiclient.domain.models.Address
import tk.skeptick.vk.apiclient.methods.DefaultListResponse
import tk.skeptick.vk.apiclient.methods.ExtendedListResponse
import kotlin.reflect.KClass

internal val json = Json(
    JsonConfiguration.Stable.copy(
        encodeDefaults = false,
        ignoreUnknownKeys = true,
        isLenient = false,
        serializeSpecialFloatingPointValues = false,
        allowStructuredMapKeys = true,
        prettyPrint = false,
        unquotedPrint = false,
        useArrayPolymorphism = false
    )
)

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

private inline fun <E : Enum<E>> SerialDescriptorBuilder.descriptor(case: E): SerialDescriptor {
    return SerialDescriptor("$serialName.${case.name}", StructureKind.OBJECT)
}

internal fun Keyboard.serialize(): String = json.stringify(Keyboard.serializer(), this)

internal fun Address.Timetable.serialize(): String = json.stringify(Address.Timetable.serializer(), this)

internal inline fun <T : Any> list(nestedSerializer: KSerializer<T>) = DefaultListResponse.serializer(nestedSerializer)

internal inline fun <T : Any> extendedList(nestedSerializer: KSerializer<T>) = ExtendedListResponse.serializer(nestedSerializer)