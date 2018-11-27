package tk.skeptick.vk.apiclient.domain.models

import kotlinx.serialization.*
import tk.skeptick.vk.apiclient.BooleanInt
import tk.skeptick.vk.apiclient.EnumIntSerializer
import tk.skeptick.vk.apiclient.SerializableEnum
import tk.skeptick.vk.apiclient.domain.AttachmentType
import tk.skeptick.vk.apiclient.domain.MessageAttachment

@Serializable
data class Market(
    @SerialName("id") override val id: Int,
    @SerialName("owner_id") override val ownerId: Int,
    @SerialName("title") val title: String,
    @SerialName("description") val description: String,
    @SerialName("date") val date: Int,
    @SerialName("availability") val availability: AvailabilityType,
    @SerialName("price") val price: Price,
    @SerialName("category") val category: Category,
    @SerialName("thumb_photo") val thumbPhoto: String,
    @Optional @SerialName("photos") val photos: List<Photo>? = null,
    @Optional @SerialName("can_comment") val canComment: BooleanInt? = null,
    @Optional @SerialName("can_repost") val canRepost: BooleanInt? = null,
    @Optional @SerialName("likes") val likes: Likes? = null
) : MessageAttachment {

    @Transient override val typeAttachment: String get() = AttachmentType.MARKET.value

    @Serializable(with = AvailabilityType.Companion::class)
    enum class AvailabilityType(override val value: Int) : SerializableEnum<Int> {
        AVAILABLE(0),
        DELETED(1),
        UNAVAILABLE(2);

        companion object : EnumIntSerializer<AvailabilityType>(AvailabilityType::class)
    }

    @Serializable
    data class Album(
        @SerialName("id") val id: Int,
        @SerialName("owner_id") val ownerId: Int,
        @SerialName("title") val title: String,
        @SerialName("photo") val photo: Photo,
        @SerialName("count") val count: Int,
        @SerialName("updated_time") val updateTime: Int)

    @Serializable
    data class Price(
        @SerialName("amount") val amount: Int, // price * 100
        @SerialName("currency") val currency: Currency,
        @SerialName("text") val text: String) {

        @Serializable
        data class Currency(
            @SerialName("id") val id: Int,
            @SerialName("name") val name: String)

    }

    @Serializable
    data class Category(
        @SerialName("id") val id: Int,
        @SerialName("name") val name: String,
        @Optional @SerialName("section") val section: Section? = null) {

        @Serializable
        data class Section(
            @SerialName("id") val id: Int,
            @SerialName("name") val name: String)

    }

    @Serializable
    data class Likes(
        @SerialName("user_likes") val isUserLikes: BooleanInt,
        @SerialName("count") val count: Int)

}







