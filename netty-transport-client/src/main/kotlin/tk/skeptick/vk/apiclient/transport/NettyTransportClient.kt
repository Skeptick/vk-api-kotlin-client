package tk.skeptick.vk.apiclient.transport

import com.github.kittinunf.result.Result
import kotlinx.coroutines.experimental.CompletableDeferred
import kotlinx.coroutines.experimental.Deferred
import org.asynchttpclient.*
import org.asynchttpclient.request.body.multipart.FilePart
import io.netty.handler.codec.http.HttpMethod as NettyHttpMethod
import tk.skeptick.vk.apiclient.HttpMethod
import tk.skeptick.vk.apiclient.TransportClient
import java.io.File
import kotlin.concurrent.thread

open class NettyTransportClient : TransportClient {

    private val client = Dsl.config()
        .setThreadFactory(::createThread)
        .let(Dsl::asyncHttpClient)

    override fun <T : Any> execute(
        httpMethod: HttpMethod,
        url: String,
        parameters: List<Pair<String, String>>,
        mapper: (String) -> Result<T, Exception>
    ): Deferred<Result<T, Exception>> =
        client.prepare(httpMethod.nettyMethod.name(), url)
            .addQueryParams(parameters.map { it.param })
            .asDeferred(mapper)

    override fun <T : Any> uploadFile(
        url: String,
        files: List<Triple<String, String, File>>,
        mapper: (String) -> Result<T, Exception>
    ): Deferred<Result<T, Exception>> =
        client.preparePost(url)
            .setBodyParts(files.map { it.filePart })
            .asDeferred(mapper)

    private fun <T : Any> BoundRequestBuilder.asDeferred(
        mapper: (String) -> Result<T, Exception>
    ): Deferred<Result<T, Exception>> {
        val deferred = CompletableDeferred<Result<T, Exception>>()
        val future = execute().toCompletableFuture()

        deferred.invokeOnCompletion {
            if (deferred.isCancelled) {
                future.cancel(true)
            }
        }

        future.whenComplete { response, throwable ->
            when {
                response != null -> deferred.complete(mapper(response.responseBody))
                throwable != null -> deferred.complete(Result.error(Exception(throwable)))
                else -> deferred.complete(Result.error(IllegalStateException("Response is null")))
            }
        }

        return deferred
    }

    private fun createThread(runnable: Runnable): Thread = thread(
        start = false,
        isDaemon = true,
        name = "NettyTransportClient",
        block = runnable::run
    )

    private val HttpMethod.nettyMethod get() = when(this) {
        HttpMethod.GET -> NettyHttpMethod.GET
        HttpMethod.POST -> NettyHttpMethod.POST
    }

    private val Pair<String, String>.param get() =
        Param(first, second)

    private val Triple<String, String, File>.filePart get() =
        FilePart(first, third)

}