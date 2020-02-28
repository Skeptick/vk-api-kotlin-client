package tk.skeptick.vk.apiclient.domain

import kotlinx.serialization.*

@Serializable
data class PrivacySettings(
    @SerialName("category") val category: Category? = null,
    @SerialName("owners") val owners: Ids? = null,
    @SerialName("lists") val lists: Ids? = null) {

    @Serializable
    enum class Category(val value: String) {
        @SerialName("all") ALL("all"),
        @SerialName("friends") FRIENDS("friends"),
        @SerialName("friends_of_friends") FRIENDS_OF_FRIENDS("friends_of_friends"),
        @SerialName("friends_of_friends_only") FRIENDS_OF_FRIENDS_ONLY("friends_of_friends_only"),
        @SerialName("only_me") ONLY_ME("only_me"),
        @SerialName("nobody") NOBODY("nobody")
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