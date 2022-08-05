package com.example.kmm_performance

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.http.websocket.*
import io.ktor.serialization.kotlinx.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

// https://ktor.io/docs/serialization-client.html#register_json
@ExperimentalSerializationApi
actual class KtorClientFactory actual constructor() {
    actual fun build(): HttpClient {
        return HttpClient(Android) {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                        prettyPrint = true
                        ignoreUnknownKeys = true
                        explicitNulls = false
                    }
                )
            }
        }
    }
}
