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

    fun toRequestString(): String {
        val result = mutableListOf<String>()
        if (category != null) result.add(category.value)
        if (owners?.allowed != null) for (id in owners.allowed) result.add("$id")
        if (owners?.excluded != null) for (id in owners.excluded) result.add("-$id")
        if (lists?.allowed != null) for (id in lists.allowed) result.add("list$id")
        if (lists?.excluded != null) for (id in lists.excluded) result.add("-list$id")
        return result.joinToString(",")
    }

}