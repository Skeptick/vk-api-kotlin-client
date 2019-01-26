package tk.skeptick.vk.apiclient.methods.account

import kotlinx.serialization.internal.IntSerializer
import tk.skeptick.vk.apiclient.*
import tk.skeptick.vk.apiclient.domain.AccessPermissionsUser
import tk.skeptick.vk.apiclient.domain.models.User
import tk.skeptick.vk.apiclient.methods.AccountInfoField
import tk.skeptick.vk.apiclient.methods.CounterFilter
import tk.skeptick.vk.apiclient.methods.DefaultListResponse
import tk.skeptick.vk.apiclient.methods.ExtendedListResponse

class AccountApi(override val client: ApiClient)
    : AccountApiUser, MethodsContext {

    override fun ban(
        ownerId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.ban.httpGet(BooleanInt.serializer()) {
            append("owner_id", ownerId)
        }

    override fun changePassword(
        oldPassword: String,
        newPassword: String,
        restoreSid: String?,
        changePasswordHash: String?
    ): VkApiRequest<ChangePasswordResponse> =
        Methods.changePassword.httpPost(ChangePasswordResponse.serializer()) {
            append("old_password", oldPassword)
            append("new_password", newPassword)
            append("restore_sid", restoreSid)
            append("change_password_hash", changePasswordHash)
        }

    override fun getActiveOffers(
        offset: Int,
        count: Int
    ): VkApiRequest<DefaultListResponse<ActiveOffer>> =
        Methods.getActiveOffers.httpGet(list(ActiveOffer.serializer())) {
            append("offset", offset)
            append("count", count)
        }

    override fun getAppPermissions(
        userId: Int?
    ): VkApiRequest<AccessPermissionsUser> =
        Methods.getAppPermissions.httpGet(AccessPermissionsUser.Companion) {
            append("user_id", userId)
        }

    override fun getBanned(
        offset: Int,
        count: Int
    ): VkApiRequest<ExtendedListResponse<Int>> =
        Methods.getBanned.httpGet(extendedList(IntSerializer)) {
            append("offset", offset)
            append("count", count)
        }

    override fun getCounters(
        filter: List<CounterFilter>?
    ): VkApiRequest<AccountCounters> =
        Methods.getCounters.httpGet(AccountCounters.serializer()) {
            append("filter", filter?.joinToString(",") { it.value })
        }

    override fun getInfo(
        fields: List<AccountInfoField>?
    ): VkApiRequest<AccountInfo> =
        Methods.getInfo.httpGet(AccountInfo.serializer()) {
            append("fields", fields?.joinToString(",") { it.value })
        }

    override fun getProfileInfo(): VkApiRequest<AccountProfileInfo> =
        Methods.getProfileInfo.httpGet(AccountProfileInfo.serializer())

    override fun getPushSettings(
        deviceId: String
    ): VkApiRequest<PushSettings> =
        Methods.getPushSettings.httpGet(PushSettings.serializer()) {
            append("device_id", deviceId)
        }

    override fun registerDevice(
        token: String,
        deviceId: String,
        deviceModel: String?,
        deviceYear: Int?,
        systemVersion: String?
    ): VkApiRequest<BooleanInt> =
        Methods.registerDevice.httpGet(BooleanInt.serializer()) {
            append("token", token)
            append("device_id", deviceId)
            append("device_model", deviceModel)
            append("device_year", deviceYear)
            append("system_version", systemVersion)
        }

    override fun saveProfileInfo(
        firstName: String?,
        lastName: String?,
        maidenName: String?,
        screenName: String?,
        cancelRequestId: Int?,
        sex: User.Sex?,
        relation: User.RelationStatus?,
        relationPartnerId: Int?,
        birthDate: String?,
        birthDateVisibility: AccountProfileInfo.BirthDateVisibility?,
        homeTown: String?,
        countryId: Int?,
        cityId: Int?,
        status: String?
    ): VkApiRequest<SaveAccountProfileInfoResponse> =
        Methods.saveProfileInfo.httpPost(SaveAccountProfileInfoResponse.serializer()) {
            append("first_name", firstName)
            append("last_name", lastName)
            append("maiden_name", maidenName)
            append("screen_name", screenName)
            append("cancel_request_id", cancelRequestId)
            append("sex", sex?.value)
            append("relation", relation?.value)
            append("relation_partner_id", relationPartnerId)
            append("bdate", birthDate)
            append("bdate_visibility", birthDateVisibility?.value)
            append("home_town", homeTown)
            append("country_id", countryId)
            append("city_id", cityId)
            append("status", status)
        }

    override fun setInfo(
        ownPostsDefault: Boolean?,
        noWallReplies: Boolean?
    ): VkApiRequest<BooleanInt> =
        Methods.setInfo.httpGet(BooleanInt.serializer()) {
            append("own_posts_default", ownPostsDefault?.asInt())
            append("no_wall_replies", noWallReplies?.asInt())
        }

    override fun setNameInMenu(
        userId: Int?,
        name: String?
    ): VkApiRequest<BooleanInt> =
        Methods.setNameInMenu.httpGet(BooleanInt.serializer()) {
            append("user_id", userId)
            append("name", name)
        }

    override fun setOffline(): VkApiRequest<BooleanInt> =
        Methods.setOffline.httpGet(BooleanInt.serializer())

    override fun setOnline(
        isVoipSupported: Boolean?
    ): VkApiRequest<BooleanInt> =
        Methods.setOnline.httpGet(BooleanInt.serializer()) {
            append("voip", isVoipSupported?.asInt())
        }

    override fun setSilenceMode(
        peerId: Int,
        time: Int,
        enableSound: Boolean?
    ): VkApiRequest<BooleanInt> =
        Methods.setSilenceMode.httpGet(BooleanInt.serializer()) {
            append("peer_id", peerId)
            append("time", time)
            append("sound", enableSound?.asInt())
        }

    override fun setSilenceModeForever(
        peerId: Int,
        enableSound: Boolean?
    ): VkApiRequest<BooleanInt> =
        setSilenceMode(
            peerId = peerId,
            time = -1,
            enableSound = enableSound
        )

    override fun unban(
        ownerId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.unban.httpGet(BooleanInt.serializer()) {
            append("owner_id", ownerId)
        }

    override fun unregisterDevice(
        deviceId: String
    ): VkApiRequest<BooleanInt> =
        Methods.unregisterDevice.httpGet(BooleanInt.serializer()) {
            append("device_id", deviceId)
        }

    private object Methods {
        private const val it = "account."
        const val ban = it + "ban"
        const val changePassword = it + "changePassword"
        const val getActiveOffers = it + "getActiveOffers"
        const val getAppPermissions = it + "getAppPermissions"
        const val getBanned = it + "getBanned"
        const val getCounters = it + "getCounters"
        const val getInfo = it + "getInfo"
        const val getProfileInfo = it + "getProfileInfo"
        const val getPushSettings = it + "getPushSettings"
        const val registerDevice = it + "registerDevice"
        const val saveProfileInfo = it + "saveProfileInfo"
        const val setInfo = it + "setInfo"
        const val setNameInMenu = it + "setNameInMenu"
        const val setOffline = it + "setOffline"
        const val setOnline = it + "setOnline"
        const val setSilenceMode = it + "setSilenceMode"
        const val unban = it + "unban"
        const val unregisterDevice = it + "unregisterDevice"

        // skipped
        // const val setPushSettings = it + "setPushSettings"
    }

}