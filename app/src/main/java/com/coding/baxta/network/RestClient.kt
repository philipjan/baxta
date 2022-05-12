package com.coding.baxta.network

import android.util.Log
import com.coding.baxta.BuildConfig
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object RestClient {

    private const val TAG = "RestClient"

    fun provideHttpClient() =
        HttpClient(
            Android
        ) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }

            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.d(TAG, "Response: \n$message\n")
                    }
                }
                level = LogLevel.ALL
            }

            defaultRequest {
                url {
                    this.protocol = URLProtocol.HTTPS
                    this.host = BuildConfig.BASE_URL
                }
                header("Content-Type", "application/json")
            }

        }
}