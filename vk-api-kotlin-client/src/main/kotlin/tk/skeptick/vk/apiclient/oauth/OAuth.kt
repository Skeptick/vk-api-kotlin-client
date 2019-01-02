package tk.skeptick.vk.apiclient.oauth

import tk.skeptick.vk.apiclient.DefaultApiParams

sealed class OAuth {

    sealed class User : OAuth() {

        class CodeFlow(
            val clientId: Int,
            val clientSecret: String,
            val code: String,
            val redirectUri: String = DefaultApiParams.REDIRECT_URL
        ) : OAuth.User()

        class PasswordFlow(
            val clientId: Int,
            val clientSecret: String,
            val username: String,
            val password: String
        ) : OAuth.User()

    }

    sealed class Community : OAuth() {

        class CodeFlow(
            val clientId: Int,
            val clientSecret: String,
            val code: String,
            val redirectUri: String = DefaultApiParams.REDIRECT_URL
        ) : OAuth.Community()

    }
}