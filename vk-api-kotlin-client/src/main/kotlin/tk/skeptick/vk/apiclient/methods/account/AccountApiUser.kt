package tk.skeptick.vk.apiclient.methods.account

import tk.skeptick.vk.apiclient.*
import tk.skeptick.vk.apiclient.domain.AccessPermissionsUser
import tk.skeptick.vk.apiclient.domain.models.User
import tk.skeptick.vk.apiclient.methods.AccountInfoField
import tk.skeptick.vk.apiclient.methods.CounterFilter
import tk.skeptick.vk.apiclient.methods.DefaultListResponse
import tk.skeptick.vk.apiclient.methods.ExtendedListResponse

interface AccountApiUser : AccountApiCommon {

    /**
     * @see <a href="https://vk.com/dev/account.ban">VK API</a>
     */
    fun ban(
        ownerId: Int
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/account.changePassword">VK API</a>
     */
    fun changePassword(
        oldPassword: String,
        newPassword: String,
        restoreSid: String? = null,
        changePasswordHash: String? = null
    ): VkApiRequest<ChangePasswordResponse>

    /**
     * @param[count] maximum value 100
     * @see <a href="https://vk.com/dev/account.getActiveOffers">VK API</a>
     */
    fun getActiveOffers(
        offset: Int = 0,
        count: Int = 100
    ): VkApiRequest<DefaultListResponse<ActiveOffer>>

    /**
     * @see <a href="https://vk.com/dev/account.getAppPermissions">VK API</a>
     */
    fun getAppPermissions(
        userId: Int? = null
    ): VkApiRequest<AccessPermissionsUser>

    /**
     * @param[count] maximum value 200
     * @see <a href="https://vk.com/dev/account.getBanned">VK API</a>
     */
    fun getBanned(
        offset: Int = 0,
        count: Int = 20
    ): VkApiRequest<ExtendedListResponse<Int>>

    /**
     * @see <a href="https://vk.com/dev/account.getCounters">VK API</a>
     */
    fun getCounters(
        filter: List<CounterFilter>? = null
    ): VkApiRequest<AccountCounters>

    /**
     * @see <a href="https://vk.com/dev/account.getInfo">VK API</a>
     */
    fun getInfo(
        fields: List<AccountInfoField>? = null
    ): VkApiRequest<AccountInfo>

    /**
     * @see <a href="https://vk.com/dev/account.getProfileInfo">VK API</a>
     */
    fun getProfileInfo(): VkApiRequest<AccountProfileInfo>

    /**
     * @see <a href="https://vk.com/dev/account.getPushSettings">VK API</a>
     * TODO find how to parse the <settings> field from response
     */
    fun getPushSettings(
        deviceId: String
    ): VkApiRequest<PushSettings>

    /**
     * @see <a href="https://vk.com/dev/account.registerDevice">VK API</a>
     * TODO find how to parse the <settings> field from response
     */
    fun registerDevice(
        token: String,
        deviceId: String,
        deviceModel: String? = null,
        deviceYear: Int? = null,
        systemVersion: String? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/account.saveProfileInfo">VK API</a>
     */
    fun saveProfileInfo(
        firstName: String? = null,
        lastName: String? = null,
        maidenName: String? = null,
        screenName: String? = null,
        cancelRequestId: Int? = null,
        sex: User.Sex? = null,
        relation: User.RelationStatus? = null,
        relationPartnerId: Int? = null,
        birthDate: String? = null,
        birthDateVisibility: AccountProfileInfo.BirthDateVisibility? = null,
        homeTown: String? = null,
        countryId: Int? = null,
        cityId: Int? = null,
        status: String? = null
    ): VkApiRequest<SaveAccountProfileInfoResponse>

    /**
     * @see <a href="https://vk.com/dev/account.setInfo">VK API</a>
     */
    fun setInfo(
        ownPostsDefault: Boolean? = null,
        noWallReplies: Boolean? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/account.setNameInMenu">VK API</a>
     */
    fun setNameInMenu(
        userId: Int? = null,
        name: String? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/account.setOffline">VK API</a>
     */
    fun setOffline(): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/account.setOnline">VK API</a>
     */
    fun setOnline(
        isVoipSupported: Boolean? = null
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/account.setSilenceMode">VK API</a>
     */
    fun setSilenceMode(
        peerId: Int,
        time: Int,
        enableSound: Boolean?
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/account.setSilenceMode">VK API</a>
     */
    fun setSilenceModeForever(
        peerId: Int,
        enableSound: Boolean?
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/account.unban">VK API</a>
     */
    fun unban(
        ownerId: Int
    ): VkApiRequest<BooleanInt>

    /**
     * @see <a href="https://vk.com/dev/account.unregisterDevice">VK API</a>
     */
    fun unregisterDevice(
        deviceId: String
    ): VkApiRequest<BooleanInt>

}