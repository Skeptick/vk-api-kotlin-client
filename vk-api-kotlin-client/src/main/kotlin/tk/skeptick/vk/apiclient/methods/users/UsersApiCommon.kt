package tk.skeptick.vk.apiclient.methods.users

import tk.skeptick.vk.apiclient.DefaultMethodsParams
import tk.skeptick.vk.apiclient.VkApiRequest
import tk.skeptick.vk.apiclient.domain.models.User
import tk.skeptick.vk.apiclient.methods.NameCase
import tk.skeptick.vk.apiclient.methods.UserOptionalField

interface UsersApiCommon {

    /**
     * @param[userNames] maximum number of elements allowed is 1000
     * @see <a href="https://vk.com/dev/users.get">VK API</a>
     */
    fun get(
        userNames: List<String>? = null,
        userFields: List<UserOptionalField> = DefaultMethodsParams.userFields,
        nameCase: NameCase = NameCase.NOM
    ): VkApiRequest<List<User>>

    /**
     * @param[userIds] maximum number of elements allowed is 1000
     * @see <a href="https://vk.com/dev/users.get">VK API</a>
     */
    fun getById(
        userIds: List<Int>,
        userFields: List<UserOptionalField> = DefaultMethodsParams.userFields,
        nameCase: NameCase = NameCase.NOM
    ): VkApiRequest<List<User>>

}