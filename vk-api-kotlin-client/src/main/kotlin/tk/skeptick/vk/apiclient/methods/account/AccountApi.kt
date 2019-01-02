package tk.skeptick.vk.apiclient.methods.account

import kotlinx.serialization.internal.IntSerializer
import tk.skeptick.vk.apiclient.*
import tk.skeptick.vk.apiclient.domain.AccessPermissionsUser
import tk.skeptick.vk.apiclient.domain.models.User
import tk.skeptick.vk.apiclient.methods.AccountInfoField
import tk.skeptick.vk.apiclient.methods.CounterFilter
import tk.skeptick.vk.apiclient.methods.DefaultListResponse
import tk.skeptick.vk.apiclient.methods.ExtendedListResponse

class AccountApi(override val client: VkApiClient)
    : AccountApiUser, MethodsContext {

    override fun ban(
        ownerId: Int
    ): VkApiRequest<BooleanInt> =
        Methods.ban.httpGet(
            "owner_id" to ownerId
        ).withSerializer(BooleanInt.serializer())

    override fun changePassword(
        oldPassword: String,
        newPassword: String,
        restoreSid: String?,
        changePasswordHash: String?
    ): VkApiRequest<ChangePasswordResponse> =
        Methods.changePassword.httpPost(
            "old_password" to oldPassword,
            "new_password" to newPassword,
            "restore_sid" to restoreSid,
            "change_password_hash" to changePasswordHash
        ).withSerializer(ChangePasswordResponse.serializer())

    override fun getActiveOffers(
        offset: Int,
        count: Int
    ): VkApiRequest<DefaultListResponse<ActiveOffer>> =
        Methods.getActiveOffers.httpGet(
            "offset" to offset,
            "count" to count
        ).withSerializer(list(ActiveOffer.serializer()))

    override fun getAppPermissions(
        userId: Int?
    ): VkApiRequest<AccessPermissionsUser> =
        Methods.getAppPermissions.httpGet(
            "user_id" to userId
        ).withSerializer(AccessPermissionsUser.Companion)

    override fun getBanned(
        offset: Int,
        count: Int
    ): VkApiRequest<ExtendedListResponse<Int>> =
        Methods.getBanned.httpGet(
            "offset" to offset,
            "count" to count
        ).withSerializer(extendedList(IntSerializer))

    override fun getCounters(
        filter: List<CounterFilter>?
    ): VkApiRequest<AccountCounters> =
        Methods.getCounters.httpGet(
            "filter" to filter?.joinToString(",") { it.value }
        ).withSerializer(AccountCounters.serializer())

    override fun getInfo(
        fields: List<AccountInfoField>?
    ): VkApiRequest<AccountInfo> =
        Methods.getInfo.httpGet(
            "fields" to fields?.joinToString(",") { it.value }
        ).withSerializer(AccountInfo.serializer())

    override fun getProfileInfo(): VkApiRequest<AccountProfileInfo> =
        Methods.getProfileInfo.httpGet()
            .withSerializer(AccountProfileInfo.serializer())

    override fun getPushSettings(
        deviceId: String
    ): VkApiRequest<PushSettings> =
        Methods.getPushSettings.httpGet(
            "device_id" to deviceId
        ).withSerializer(PushSettings.serializer())

    override fun registerDevice(
        token: String,
        deviceId: String,
        deviceModel: String?,
        deviceYear: Int?,
        systemVersion: String?
    ): VkApiRequest<BooleanInt> =
        Methods.registerDevice.httpGet(
            "token" to token,
            "device_id" to deviceId,
            "device_model" to deviceModel,
            "device_year" to deviceYear,
            "system_version" to systemVersion
        ).withSerializer(BooleanInt.serializer())

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
        Methods.saveProfileInfo.httpPost(
            "first_name" to firstName,
            "last_name" to lastName,
            "maiden_name" to maidenName,
            "screen_name" to screenName,
            "cancel_request_id" to cancelRequestId,
            "sex" to sex?.value,
            "relation" to relation?.value,
            "relation_partner_id" to relationPartnerId,
            "bdate" to birthDate,
            "bdate_visibility" to birthDateVisibility?.value,
            "home_town" to homeTown,
            "country_id" to countryId,
            "city_id" to cityId,
            "status" to status
        ).withSerializer(SaveAccountProfileInfoResponse.serializer())

    override fun setInfo(
        ownPostsDefault: Boolean?,
        noWallReplies: Boolean?
    ): VkApiRequest<BooleanInt> =
        Methods.setInfo.httpGet(
            "own_posts_default" to ownPostsDefault?.asInt(),
            "no_wall_replies" to noWallReplies?.asInt()
        ).withSerializer(BooleanInt.serializer())

    override fun setNameInMenu(
        userId: Int?,
        name: String?
    ): VkApiRequest<BooleanInt> =
        Methods.setNameInMenu.httpGet(
            "user_id" to userId,
            "name" to name
        ).withSerializer(BooleanInt.serializer())

    override fun setOffline(): VkApiRequest<BooleanInt> =
        Methods.setOffline.httpGet()
            .withSerializer(BooleanInt.serializer())

    override fun setOnline(
        isVoipSupported: Boolean?
    ): VkApiRequest<BooleanInt> =
        Methods.setOnline.httpGet(
            "voip" to isVoipSupported?.asInt()
        ).withSerializer(BooleanInt.serializer())

    override fun setSilenceMode(
        peerId: Int,
        time: Int,
        enableSound: Boolean?
    ): VkApiRequest<BooleanInt> =
        Methods.setSilenceMode.httpGet(
            "peer_id" to peerId,
            "time" to time,
            "sound" to enableSound?.asInt()
        ).withSerializer(BooleanInt.serializer())

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
        Methods.unban.httpGet(
            "owner_id" to ownerId
        ).withSerializer(BooleanInt.serializer())

    override fun unregisterDevice(
        deviceId: String
    ): VkApiRequest<BooleanInt> =
        Methods.unregisterDevice.httpGet(
            "device_id" to deviceId
        ).withSerializer(BooleanInt.serializer())

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