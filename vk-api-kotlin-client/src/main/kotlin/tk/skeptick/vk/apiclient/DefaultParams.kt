package tk.skeptick.vk.apiclient

import tk.skeptick.vk.apiclient.methods.NameCase
import tk.skeptick.vk.apiclient.methods.UserOptionalField

object DefaultApiParams {
    const val OAUTH_URL = "https://oauth.vk.com/access_token"
    const val REDIRECT_URL = "https://oauth.vk.com/blank.html"
    const val API_URL = "https://api.vk.com/method/"
    const val API_VERSION = "5.100"
    const val LONG_POLL_VERSION = 3
}

object DefaultMethodsParams {
    val userFields = listOf(UserOptionalField.SCREEN_NAME)
    val userNameCase = NameCase.NOM
}