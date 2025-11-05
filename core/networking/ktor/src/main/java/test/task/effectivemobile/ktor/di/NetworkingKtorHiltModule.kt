package test.task.effectivemobile.ktor.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.serialization.kotlinx.json.json
import io.ktor.websocket.WebSocketSession
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.WebSocket
import okhttp3.logging.HttpLoggingInterceptor

object KtorNetworkSettings{
    var networkingUrl: String = ""
}

fun createHttpClient(engine: HttpClientEngine) : HttpClient {
    return HttpClient(engine) {
        install(WebSockets){

        }
        install(ContentNegotiation){
            json(
                json = Json{
                    ignoreUnknownKeys = true
                    this.explicitNulls = true
                }
            )
        }
    }
}

@Module
@InstallIn(SingletonComponent::class)
class NetworkingKtorHiltModule {
    @Provides
    fun provideHttpClient(engine: HttpClientEngine) : HttpClient{
        return createHttpClient(engine)
    }

    @Provides
    fun provideHttpClientEngine(interceptors: Set<@JvmSuppressWildcards Interceptor>) : HttpClientEngine {
        return OkHttp.create{
            interceptors.forEach {
                addInterceptor(it)
            }
        }
    }

    @Provides
    @IntoSet
    fun provideLoggingInterceptor() : Interceptor{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }
}