@file:Suppress("NOTHING_TO_INLINE")

package tk.skeptick.vk.apiclient

import com.github.kittinunf.result.Result
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.request.forms.*
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.response.HttpResponse
import io.ktor.client.response.readText
import io.ktor.http.*
import tk.skeptick.vk.apiclient.domain.Language
import tk.skeptick.vk.apiclient.oauth.OAuth
import tk.skeptick.vk.apiclient.oauth.OAuthResponse

class VkApiClient(engine: HttpClientEngine, accessToken: String) {

    val httpClient: HttpClient = buildClient(engine)

    private var baseParams = Parameters.build {
        append("access_token", accessToken)
        append("v", DefaultApiParams.API_VERSION)
    }

    var language: Language? = null
        set(lang) {
            field = lang
            baseParams = Parameters.build {
                appendAll(baseParams)
                remove("lang")
                append("lang", lang?.value)
            }
        }

    suspend fun <T : Any> executeMethod(
        request: VkApiRequest<T>,
        additionalParameters: Parameters? = null
    ): Result<T, Exception> = try {
        val response = request.execute(additionalParameters)
        parseMethodResponse(response.readText(), request.serializer)
    } catch (exception: Exception) {
        Result.error(exception)
    }

    suspend fun <T : Any> uploadFile(
        request: UploadFilesRequest<T>
    ): Result<T, Exception> = try {
        val response = request.upload()
        parseUploadResponse(response.readText(), request.serializer)
    } catch (exception: Exception) {
        Result.error(exception)
    }

    private suspend inline fun <T : Any> VkApiRequest<T>.execute(
        additionalParameters: Parameters? = null
    ): HttpResponse = when (httpMethod) {
        HttpMethod.Post -> httpClient.post(DefaultApiParams.API_URL + path) {
            body = FormDataContent(baseParams + parameters + additionalParameters)
        }
        HttpMethod.Get -> httpClient.get(DefaultApiParams.API_URL + path) {
            url.parameters.appendAll(baseParams + parameters + additionalParameters)
        }
        else -> throw UnsupportedOperationException()
    }

    private suspend inline fun <T : Any> UploadFilesRequest<T>.upload(): HttpResponse =
        httpClient.submitFormWithBinaryData(uploadUrl, formData {
            files.map(UploadableFile::formPart).forEach(::append)
        })

    companion object {

        suspend fun byOAuth(
            engine: HttpClientEngine,
            authData: OAuth,
            captchaResponse: CaptchaResponse? = null
        ): VkApiClient = buildClient(engine).use {
            when (val response = it.authorize(authData, captchaResponse)) {
                is Result.Success -> VkApiClient(engine, response.value.accessToken)
                is Result.Failure -> throw response.error
            }
        }

        suspend fun authorize(
            engine: HttpClientEngine,
            authData: OAuth,
            captchaResponse: CaptchaResponse? = null
        ): Result<OAuthResponse, Exception> = buildClient(engine).use {
            it.authorize(authData, captchaResponse)
        }

        private suspend inline fun HttpClient.authorize(
            authData: OAuth,
            captchaResponse: CaptchaResponse? = null
        ): Result<OAuthResponse, Exception> = post<String>(DefaultApiParams.OAUTH_URL) {
            body = FormDataContent(authData.parameters + captchaResponse?.parameters)
        }.let(::parseOAuthResponse)

        private fun buildClient(engine: HttpClientEngine): HttpClient =
            HttpClient(engine).config { expectSuccess = false }

    }

}