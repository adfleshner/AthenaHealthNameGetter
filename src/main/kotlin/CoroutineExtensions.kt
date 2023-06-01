import kotlinx.coroutines.*

suspend fun <A, B> Iterable<A>.pmap(dispatcher: CoroutineDispatcher = Dispatchers.IO, f: suspend (A) -> B): List<B> =
    coroutineScope {
        map { async(dispatcher) { f(it) } }.awaitAll()
    }

