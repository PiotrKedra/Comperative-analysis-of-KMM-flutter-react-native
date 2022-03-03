package com.example.jetpackcompose

import com.example.jetpackcompose.data.FearAndGreedIndex
import retrofit2.http.GET

interface Api {
    @GET("https://api.alternative.me/fng/")
    suspend fun getIndex():FearAndGreedIndex
}