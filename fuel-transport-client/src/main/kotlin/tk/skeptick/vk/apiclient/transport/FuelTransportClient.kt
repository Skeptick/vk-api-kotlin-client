package tk.skeptick.vk.apiclient.transport

import com.github.kittinunf.fuel.core.*
import com.github.kittinunf.result.Result
import com.github.kittinunf.result.flatMap
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import tk.skeptick.vk.apiclient.HttpMethod
import tk.skeptick.vk.apiclient.TransportClient
import java.io.File

open class FuelTransportClient : TransportClient {

    open val client: FuelManager = FuelManager().apply {
        removeAllResponseInterceptors()
    }

    override fun <T : Any> execute(
        httpMethod: HttpMethod,
        url: String,
        parameters: List<Pair<String, String>>,
        mapper: (String) -> Result<T, Exception>
    ): Deferred<Result<T, Exception>> =
        client.request(
            method = httpMethod.fuelMethod,
            path = url,
            param = parameters
        ).asDeferred(mapper)

    override fun <T : Any> uploadFile(
        url: String,
        files: List<Triple<String, String, File>>,
        mapper: (String) -> Result<T, Exception>
    ): Deferred<Result<T, Exception>> =
        client.upload(url)
            .blobs { _, _ -> files.map { it.blob } }
            .also { it.names.addAll(files.map { it.first }) }
            .asDeferred(mapper)

    private fun <T : Any> Request.asDeferred(
        mapper: (String) -> Result<T, Exception>
    ): Deferred<Result<T, Exception>> {
        val deferred = CompletableDeferred<Result<T, Exception>>()

        deferred.invokeOnCompletion {
            if (deferred.isCancelled) {
                cancel()
            }
        }

        responseString { _, _, result ->
            when (result) {
                is Result.Success -> deferred.complete(result.flatMap(mapper))
                is Result.Failure -> deferred.complete(Result.error(result.error))
            }
        }

        return deferred
    }

    private val HttpMethod.fuelMethod get() = when(this) {
        HttpMethod.GET -> Method.GET
        HttpMethod.POST -> Method.POST
    }

    private val Triple<String, String, File>.blob get() = Blob(
        name = second,
        length = third.length(),
        inputStream = third::inputStream
    )


}