@file:Suppress("NOTHING_TO_INLINE")

package tk.skeptick.vk.apiclient

import com.github.kittinunf.result.Result
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.request.forms.*
import io.ktor.client.request.post
import io.ktor.client.request.request
import io.ktor.client.response.HttpResponse
import io.ktor.client.response.readText
import io.ktor.http.*
import tk.skeptick.vk.apiclient.domain.Language
import tk.skeptick.vk.apiclient.oauth.OAuth
import tk.skeptick.vk.apiclient.oauth.OAuthResponse

class VkApiClient(
    accessToken: String,
    engine: HttpClientEngine,
    config: HttpClientConfig<*>.() -> Unit = {}
) : ApiClient {

    private val httpClient: HttpClient = buildClient(engine, config)

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

    override suspend fun <T : Any> executeMethod(
        request: VkApiRequest<T>,
        additionalParameters: Parameters?
    ): Result<T, Exception> = try {
        parseMethodResponse(request(additionalParameters).readText(), request.serializer)
    } catch (exception: Exception) {
        Result.error(exception)
    }

    override suspend fun <T : Any> uploadFile(
        request: UploadFilesRequest<T>
    ): Result<T, Exception> = try {
        parseUploadResponse(request().readText(), request.serializer)
    } catch (exception: Exception) {
        Result.error(exception)
    }

    private suspend inline operator fun <T : Any> VkApiRequest<T>.invoke(
        additionalParameters: Parameters? = null
    ): HttpResponse = httpClient.request(DefaultApiParams.API_URL + path) {
        method = httpMethod
        val parameters = baseParams + parameters + additionalParameters
        if (method == HttpMethod.Post) body = FormDataContent(parameters)
        else url.parameters.appendAll(parameters)
    }

    private suspend inline operator fun <T : Any> UploadFilesRequest<T>.invoke(): HttpResponse =
        httpClient.submitFormWithBinaryData(uploadUrl, formData {
            files.map(UploadableFile::formPart).forEach(::append)
        })

    companion object {

        suspend fun byOAuth(
            engine: HttpClientEngine,
            authData: OAuth,
            captchaResponse: CaptchaResponse? = null,
            config: HttpClientConfig<*>.() -> Unit = {}
        ): VkApiClient = buildClient(engine, config).use {
            when (val response = it.authorize(authData, captchaResponse)) {
                is Result.Success -> VkApiClient(response.value.accessToken, engine, config)
                is Result.Failure -> throw response.error
            }
        }

        suspend fun authorize(
            engine: HttpClientEngine,
            authData: OAuth,
            captchaResponse: CaptchaResponse? = null,
            config: HttpClientConfig<*>.() -> Unit = {}
        ): Result<OAuthResponse, Exception> = buildClient(engine, config).use {
            it.authorize(authData, captchaResponse)
        }

        private suspend inline fun HttpClient.authorize(
            authData: OAuth,
            captchaResponse: CaptchaResponse? = null
        ): Result<OAuthResponse, Exception> = post<String>(DefaultApiParams.OAUTH_URL) {
            body = FormDataContent(authData.parameters + captchaResponse?.parameters)
        }.let(::parseOAuthResponse)

        private fun buildClient(
            engine: HttpClientEngine,
            config: HttpClientConfig<*>.() -> Unit
        ): HttpClient = HttpClient(engine).config {
            config()
            expectSuccess = false
        }

    }

}