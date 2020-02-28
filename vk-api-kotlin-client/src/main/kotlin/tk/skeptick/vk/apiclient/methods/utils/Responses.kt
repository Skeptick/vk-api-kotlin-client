package tk.skeptick.vk.apiclient.methods.utils

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CheckLinkResponse(
    @SerialName("status") val status: Status,
    @SerialName("link") val link: String) {

    @Serializable
    enum class Status(val value: String) {
        @SerialName("not_banned") NOT_BANNED("not_banned"),
        @SerialName("banned") BANNED("banned"),
        @SerialName("processing") PROCESSING("processing")
    }

}

@Serializable
data class ShortLink(
    @SerialName("short_url") val shortUrl: String,
    @SerialName("url") val url: String,
    @SerialName("key") val key: String,
    @SerialName("access_key") val accessKey: String? = null,
    @SerialName("timestamp") val timestamp: Int? = null,
    @SerialName("views") val views: Int? = null)

@Serializable
data class ShortLinkStats(
    @SerialName("key") val key: String,
    @SerialName("stats") val stats: List<Stats>) {

    @Serializable
    data class Stats(
        @SerialName("timestamp") val timestamp: Int,
        @SerialName("views") val views: Int,
        @SerialName("sex_age") val sexAge: List<SexAge>? = null,
        @SerialName("countries") val countries: List<Country>? = null,
        @SerialName("cities") val cities: List<City>? = null) {

        @Serializable
        data class SexAge(
            @SerialName("age_range") val ageRange: String,
            @SerialName("female") val female: Int,
            @SerialName("male") val male: Int)

        @Serializable
        data class Country(
            @SerialName("country_id") val countryId: Int,
            @SerialName("views") val views: Int)

        @Serializable
        data class City(
            @SerialName("city_id") val cityId: Int,
            @SerialName("views") val views: Int)

    }

}

@Serializable
data class ResolveScreenNameResponse(
    @SerialName("type") val type: Type,
    @SerialName("object_id") val objectId: Int) {

    @Serializable
    enum class Type(val value: String) {
        @SerialName("user") USER("user"),
        @SerialName("group") GROUP("group"),
        @SerialName("page") PAGE("page"),
        @SerialName("vk_app") VK_APP("vk_app"),
        @SerialName("application") APPLICATION("application")
    }

}