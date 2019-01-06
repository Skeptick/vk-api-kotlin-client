package tk.skeptick.vk.apiclient.domain.models

import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tk.skeptick.vk.apiclient.EnumStringSerializer
import tk.skeptick.vk.apiclient.SerializableEnum

@Serializable
data class Address(
    @SerialName("id") val id: Int,
    @Optional @SerialName("title") val title: String? = null,
    @Optional @SerialName("address") val address: String? = null,
    @Optional @SerialName("additional_address") val additionalAddress: String? = null,
    @Optional @SerialName("country_id") val countryId: Int? = null,
    @Optional @SerialName("city_id") val cityId: Int? = null,
    @Optional @SerialName("latitude") val latitude: Double? = null,
    @Optional @SerialName("longitude") val longitude: Double? = null,
    @Optional @SerialName("work_info_status") val workInfoStatus: WorkInfoStatus? = null,
    @Optional @SerialName("timetable") val timetable: Timetable? = null,
    @Optional @SerialName("phone") val phone: String? = null,
    @Optional @SerialName("time_offset") val timeOffset: Int? = null) {

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
        @Optional @SerialName("mon") val monday: Times? = null,
        @Optional @SerialName("tue") val tuesday: Times? = null,
        @Optional @SerialName("wed") val wednesday: Times? = null,
        @Optional @SerialName("thu") val thursday: Times? = null,
        @Optional @SerialName("fri") val friday: Times? = null,
        @Optional @SerialName("sat") val saturday: Times? = null,
        @Optional @SerialName("sun") val sunday: Times? = null) {

        @Serializable
        data class Times(
            @SerialName("open_time") val openTime: Int,
            @SerialName("close_time") val closeTime: Int,
            @SerialName("break_open_time") val breakOpenTime: Int,
            @SerialName("break_close_time") val breakCloseTime: Int)

    }

}