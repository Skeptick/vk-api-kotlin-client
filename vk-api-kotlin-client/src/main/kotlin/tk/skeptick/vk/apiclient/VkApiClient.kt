package tk.skeptick.vk.apiclient

import com.github.kittinunf.result.Result
import kotlinx.coroutines.experimental.Deferred
import tk.skeptick.vk.apiclient.domain.Language
import tk.skeptick.vk.apiclient.oauth.OAuth

class VkApiClient(accessToken: String, private val transportClient: TransportClient) {

    private var baseParams = listOf<Pair<String, Any?>>(
        "access_token" to accessToken,
        "v" to DefaultApiParams.API_VERSION
    )

    var language: Language? = null
        set(lang) {
            field = lang
            baseParams = baseParams
                .filterNot { it.first == "lang" }
                .plus("lang" to lang?.value)
        }

    fun <T : Any> executeMethod(
        request: VkApiRequest<T>,
        additionalParameters: List<Pair<String, Any?>>? = null
    ): Deferred<Result<T, Exception>> =
        transportClient.execute(
            httpMethod = request.httpMethod,
            url = DefaultApiParams.API_URL + request.path,
            parameters = combineParameters(
                request.parameters,
                baseParams,
                additionalParameters ?: emptyList()
            ),
            mapper = { parseMethodResponse(it, request.serializer) }
        )

    fun <T : Any> uploadFile(
        request: UploadFilesRequest<T>
    ): Deferred<Result<T, Exception>> =
        transportClient.uploadFile(
            url = request.uploadUrl,
            files = request.files,
            mapper = { parseUploadResponse(it, request.serializer) }
        )

    companion object {

        suspend fun byOAuth(
            transportClient: TransportClient,
            authData: OAuth,
            captchaResponse: CaptchaResponse? = null
        ): VkApiClient {
            val response = transportClient.authorize(authData, captchaResponse).await()
            return when (response) {
                is Result.Success -> VkApiClient(
                    accessToken = response.value.accessToken,
                    transportClient = transportClient)
                is Result.Failure -> throw response.error
            }
        }

    }

}