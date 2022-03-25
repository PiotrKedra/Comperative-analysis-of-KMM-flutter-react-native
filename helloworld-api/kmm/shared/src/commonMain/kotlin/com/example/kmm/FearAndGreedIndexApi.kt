package com.example.kmm

import com.example.kmm.data.FearAndGreedIndex
import io.ktor.client.*
import io.ktor.client.plugins.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json


val client = HttpClient() {
}


class FearAndGreedIndexApi {
    private val apiUrl = "https://api.alternative.me/fng/"

    suspend fun getFearAndGreedIndex(success: (FearAndGreedIndex) -> Unit, failure: (Throwable?) -> Unit) {

        val response: HttpResponse = client.get(apiUrl)

        print(response)
    }

}