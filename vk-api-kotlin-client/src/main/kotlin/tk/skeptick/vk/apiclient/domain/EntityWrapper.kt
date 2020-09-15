package tk.skeptick.vk.apiclient.domain

import kotlinx.serialization.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.json.*
import tk.skeptick.vk.apiclient.domain.models.Community
import tk.skeptick.vk.apiclient.domain.models.User
import tk.skeptick.vk.apiclient.json

@Serializable
data class EntityWrapper(
    val type: Type,
    val page: Community? = null,
    val profile: User? = null) {

    enum class Type(val value: String) {
        PAGE("page"),
        PROFILE("profile")
    }

    @Serializer(forClass = EntityWrapper::class)
    companion object : KSerializer<EntityWrapper> {
        override fun deserialize(decoder: Decoder): EntityWrapper {
            val jsonObject = (decoder as JsonDecoder).decodeJsonElement().jsonObject
            return when (val type = jsonObject["type"]?.jsonPrimitive?.content) {
                Type.PAGE.value -> EntityWrapper(Type.PAGE, page = json.decodeFromJsonElement(Community.serializer(), jsonObject))
                Type.PROFILE.value -> EntityWrapper(Type.PROFILE, profile = json.decodeFromJsonElement(User.serializer(), jsonObject))
                else -> throw SerializationException("Type '$type' is not defined.")
            }
        }
    }

}