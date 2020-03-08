package tk.skeptick.vk.apiclient

import io.ktor.http.Parameters

interface ApiClient {

    suspend fun <T : Any> executeMethod(
        request: VkApiRequest<T>,
        additionalParameters: Parameters? = null
    ): VkResult<T, Exception>

    suspend fun <T : Any> uploadFile(
        request: UploadFilesRequest<T>
    ): VkResult<T, Exception>

}