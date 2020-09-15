package tk.skeptick.vk.apiclient

import io.ktor.client.HttpClient
import io.ktor.client.request.forms.*
import io.ktor.client.request.post
import io.ktor.client.request.request
import io.ktor.client.statement.HttpStatement
import io.ktor.http.*
import io.ktor.util.flattenEntries
import tk.skeptick.vk.apiclient.domain.Language
import tk.skeptick.vk.apiclient.oauth.OAuth
import tk.skeptick.vk.apiclient.oauth.OAuthResponse

class VkApiClient(
    accessToken: String,
    val httpClient: HttpClient
) : ApiClient {

    init {
        httpClient.configure()
    }

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
    ): VkResult<T, Exception> = try {
        parseMethodResponse(request(additionalParameters).receive(), request.serializer)
    } catch (exception: Exception) {
        VkResult.error(exception)
    }

    override suspend fun <T : Any> uploadFile(
        request: UploadFilesRequest<T>
    ): VkResult<T, Exception> = try {
        parseUploadResponse(request().receive(), request.serializer)
    } catch (exception: Exception) {
        VkResult.error(exception)
    }

    private suspend inline operator fun <T : Any> VkApiRequest<T>.invoke(
        additionalParameters: Parameters? = null
    ): HttpStatement = httpClient.request(DefaultApiParams.API_URL + path) {
        method = httpMethod
        val parameters = baseParams + parameters + (additionalParameters ?: Parameters.Empty)
        if (method == HttpMethod.Post) body = FormDataContent(parameters) else url.parameters.appendAll(parameters)
    }

    private suspend inline operator fun <T : Any> UploadFilesRequest<T>.invoke(): HttpStatement =
        httpClient.submitFormWithBinaryData(uploadUrl, formData {
            parameters.flattenEntries().map(Pair<String, String>::formPart).forEach(::append)
            files.map(UploadableFile::formPart).forEach(::append)
        })

    companion object {

        suspend fun byOAuth(
            httpClient: HttpClient,
            authData: OAuth,
            captchaResponse: CaptchaResponse? = null
        ): VkApiClient = when (val response = authorize(httpClient, authData, captchaResponse)) {
            is VkResult.Success -> VkApiClient(response.value.accessToken, httpClient)
            is VkResult.Failure -> throw response.error
        }

        suspend fun authorize(
            httpClient: HttpClient,
            authData: OAuth,
            captchaResponse: CaptchaResponse? = null
        ): VkResult<OAuthResponse, Exception> = httpClient.configure().post<String>(DefaultApiParams.OAUTH_URL) {
            body = FormDataContent(authData.parameters + (captchaResponse?.parameters ?: Parameters.Empty))
        }.let(::parseOAuthResponse)

        private fun HttpClient.configure(): HttpClient = config {
            expectSuccess = false
        }

    }

}