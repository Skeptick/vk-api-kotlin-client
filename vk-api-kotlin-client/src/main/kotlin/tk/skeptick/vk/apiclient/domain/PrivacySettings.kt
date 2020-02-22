package tk.skeptick.vk.apiclient.domain

import kotlinx.serialization.*
import tk.skeptick.vk.apiclient.EnumStringSerializer
import tk.skeptick.vk.apiclient.SerializableEnum

@Serializable
data class PrivacySettings(
    @SerialName("category") val category: Category? = null,
    @SerialName("owners") val owners: Ids? = null,
    @SerialName("lists") val lists: Ids? = null) {

    @Serializable(with = Category.Companion::class)
    enum class Category(override val value: String) : SerializableEnum<String> {
        ALL("all"),
        FRIENDS("friends"),
        FRIENDS_OF_FRIENDS("friends_of_friends"),
        FRIENDS_OF_FRIENDS_ONLY("friends_of_friends_only"),
        ONLY_ME("only_me"),
        NOBODY("nobody");

        companion object : EnumStringSerializer<Category>(Category::class)
    }

    @Serializable
    data class Ids(
        @SerialName("allowed") val allowed: List<Int>? = null,
        @SerialName("excluded") val excluded: List<Int>? = null)

    @Serializer(forClass = PrivacySettings::class)
    companion object : SerializationStrategy<PrivacySettings> {

        override fun serialize(encoder: Encoder, obj: PrivacySettings) {
            val result = mutableListOf<String>()
            obj.category?.value?.let(result::add)
            obj.owners?.allowed?.map { "$it" }?.let(result::addAll)
            obj.owners?.excluded?.map { "-$it" }?.let(result::addAll)
            obj.lists?.allowed?.map { "list$it" }?.let(result::addAll)
            obj.lists?.excluded?.map { "-list$it" }?.let(result::addAll)
            encoder.encodeString(result.joinToString(","))
        }

    }
}