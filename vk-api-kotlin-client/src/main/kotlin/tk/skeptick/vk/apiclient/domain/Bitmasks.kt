@file:Suppress("unused")

package tk.skeptick.vk.apiclient.domain

import kotlinx.serialization.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

open class Bitmask(open var mask: Int = 0) {
    fun getValue(bit: Int): Boolean = mask and bit != 0
    fun setValue(bit: Int) { mask = mask or bit }
    fun clearValue(bit: Int) { mask = mask xor bit }

    fun bitmask(bit: Int): BitmaskDelegate = BitmaskDelegate(bit)

    class BitmaskDelegate(private val bit: Int) : ReadWriteProperty<Bitmask, Boolean> {
        override fun getValue(thisRef: Bitmask, property: KProperty<*>): Boolean = thisRef.getValue(bit)
        override fun setValue(thisRef: Bitmask, property: KProperty<*>, value: Boolean) = if (value) thisRef.setValue(bit) else thisRef.clearValue(bit)
    }
}

/**
 * @see <a href="https://vk.com/dev/permissions">VK API</a>
 */
@Serializable(AccessPermissionsUser.Companion::class)
class AccessPermissionsUser(
    override var mask: Int = 0
) : Bitmask(mask) {
    var notify by bitmask(1)
    var friends by bitmask(2)
    var photos by bitmask(4)
    var audio by bitmask(8)
    var video by bitmask(16)
    var stories by bitmask(64)
    var pages by bitmask(128)
    var status by bitmask(1024)
    var notes by bitmask(2048)
    var messages by bitmask(4096)
    var wall by bitmask(8192)
    var ads by bitmask(32768)
    var offline by bitmask(65536)
    var docs by bitmask(131072)
    var groups by bitmask(262144)
    var notifications by bitmask(524288)
    var stats by bitmask(1048576)
    var email by bitmask(4194304)
    var market by bitmask(134217728)

    @Serializer(forClass = AccessPermissionsUser::class)
    companion object : KSerializer<AccessPermissionsUser> {
        override fun serialize(encoder: Encoder, value: AccessPermissionsUser) = encoder.encodeInt(value.mask)
        override fun deserialize(decoder: Decoder): AccessPermissionsUser = AccessPermissionsUser(decoder.decodeInt())
    }
}

/**
 * @see <a href="https://vk.com/dev/permissions">VK API</a>
 */
@Serializable(AccessPermissionsCommunity.Companion::class)
class AccessPermissionsCommunity(
    override var mask: Int = 0
) : Bitmask(mask) {
    var stories by bitmask(1)
    var photos by bitmask(4)
    var messages by bitmask(4096)
    var wall by bitmask(8192)
    var docs by bitmask(131072)
    var manage by bitmask(262144)

    @Serializer(forClass = AccessPermissionsCommunity::class)
    companion object : KSerializer<AccessPermissionsCommunity> {
        override fun serialize(encoder: Encoder, value: AccessPermissionsCommunity) = encoder.encodeInt(value.mask)
        override fun deserialize(decoder: Decoder): AccessPermissionsCommunity = AccessPermissionsCommunity(decoder.decodeInt())
    }
}