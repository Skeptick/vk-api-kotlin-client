@file:Suppress("NOTHING_TO_INLINE")

package tk.skeptick.vk.apiclient

import io.ktor.http.Parameters
import io.ktor.http.ParametersBuilder
import tk.skeptick.vk.apiclient.domain.Attachment
import tk.skeptick.vk.apiclient.domain.Media
import tk.skeptick.vk.apiclient.oauth.OAuth

internal inline fun ParametersBuilder.append(first: String, second: Any?) {
    if (second != null) append(first, second.toString())
}

val CaptchaResponse.parameters get() = Parameters.build {
    append("captcha_sid", sid)
    append("captcha_key", key)
}

val OAuth.parameters get() = when (this) {
    is OAuth.User.CodeFlow -> Parameters.build {
        append("client_id", clientId.toString())
        append("client_secret", clientSecret)
        append("code", code)
        append("redirect_uri", redirectUri)
    }
    is OAuth.User.PasswordFlow -> Parameters.build {
        append("grant_type", "password")
        append("client_id", clientId.toString())
        append("client_secret", clientSecret)
        append("username", username)
        append("password", password)
    }
    is OAuth.Community.CodeFlow -> Parameters.build {
        append("client_id", clientId.toString())
        append("client_secret", clientSecret)
        append("code", code)
        append("redirect_uri", redirectUri)
    }
}

inline val Media.mediaString: String get() = buildString {
    append(ownerId)
    append("_").append(id)
    if (accessKey != null) append("_").append(accessKey!!)
}

inline val Attachment.attachmentString: String get() = buildString {
    append(typeAttachment.value)
    append(mediaString)
}