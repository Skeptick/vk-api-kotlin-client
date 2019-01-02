package tk.skeptick.vk.apiclient

import com.github.kittinunf.result.Result
import kotlinx.coroutines.Deferred
import java.io.File

interface TransportClient {

    fun <T : Any> execute(
        httpMethod: HttpMethod,
        url: String,
        parameters: List<Pair<String, String>>,
        mapper: (String) -> Result<T, Exception>
    ): Deferred<Result<T, Exception>>

    fun <T : Any> uploadFile(
        url: String,
        files: List<Triple<String, String, File>>,
        mapper: (String) -> Result<T, Exception>
    ): Deferred<Result<T, Exception>>

}