package tk.skeptick.vk.apiclient.domain

import kotlinx.serialization.*
import kotlinx.serialization.json.JSON
import kotlinx.serialization.json.content
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

        override fun load(input: KInput): EntityWrapper {
            val jsonObject = (input as JSON.JsonInput).readAsTree().jsonObject
            val type = jsonObject["type"].content
            return when (type) {
                Type.PAGE.value -> EntityWrapper(
                    type = Type.PAGE,
                    page = json.parse(jsonObject.toString())
                )
                Type.PROFILE.value -> EntityWrapper(
                    type = Type.PROFILE,
                    profile = json.parse(jsonObject.toString())
                )
                else -> throw IllegalArgumentException("Type \"$type\" is not defined.")
            }
        }

    }

}