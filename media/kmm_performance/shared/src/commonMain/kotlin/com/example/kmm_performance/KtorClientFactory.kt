package com.example.kmm_performance

import io.ktor.client.*

expect class KtorClientFactory() {
    fun build(): HttpClient
}