package tk.skeptick.vk.apiclient.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tk.skeptick.vk.apiclient.EnumStringSerializer
import tk.skeptick.vk.apiclient.SerializableEnum

@Serializable
data class Address(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String? = null,
    @SerialName("address") val address: String? = null,
    @SerialName("additional_address") val additionalAddress: String? = null,
    @SerialName("country_id") val countryId: Int? = null,
    @SerialName("city_id") val cityId: Int? = null,
    @SerialName("latitude") val latitude: Double? = null,
    @SerialName("longitude") val longitude: Double? = null,
    @SerialName("work_info_status") val workInfoStatus: WorkInfoStatus? = null,
    @SerialName("timetable") val timetable: Timetable? = null,
    @SerialName("phone") val phone: String? = null,
    @SerialName("time_offset") val timeOffset: Int? = null) {

    @Serializable(with = WorkInfoStatus.Companion::class)
    enum class WorkInfoStatus(override val value: String) : SerializableEnum<String> {
        NO_INFORMATION("no_information"),
        TEMPORARILY_CLOSED("temporarily_closed"),
        TIMETABLE("timetable"),
        ALWAYS_OPENED("always_opened"),
        FOREVER_CLOSED("forever_closed");

        companion object : EnumStringSerializer<WorkInfoStatus>(WorkInfoStatus::class)
    }

    @Serializable
    data class Timetable(
        @SerialName("mon") val monday: Times? = null,
        @SerialName("tue") val tuesday: Times? = null,
        @SerialName("wed") val wednesday: Times? = null,
        @SerialName("thu") val thursday: Times? = null,
        @SerialName("fri") val friday: Times? = null,
        @SerialName("sat") val saturday: Times? = null,
        @SerialName("sun") val sunday: Times? = null) {

        @Serializable
        data class Times(
            @SerialName("open_time") val openTime: Int,
            @SerialName("close_time") val closeTime: Int,
            @SerialName("break_open_time") val breakOpenTime: Int,
            @SerialName("break_close_time") val breakCloseTime: Int)

    }

}