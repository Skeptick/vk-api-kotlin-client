package tk.skeptick.vk.apiclient

import com.github.kittinunf.result.coroutines.SuspendableResult
import io.ktor.http.Parameters

interface ApiClient {

    suspend fun <T : Any> executeMethod(
        request: VkApiRequest<T>,
        additionalParameters: Parameters? = null
    ): SuspendableResult<T, Exception>

    suspend fun <T : Any> uploadFile(
        request: UploadFilesRequest<T>
    ): SuspendableResult<T, Exception>

}