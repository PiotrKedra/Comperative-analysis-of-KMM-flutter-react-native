package com.example.jetpackcompose

import com.example.jetpackcompose.data.FearAndGreedIndex
import javax.inject.Inject

class Repo @Inject constructor(
    private val api: Api
) {
    suspend fun getIndex(): FearAndGreedIndex {
        return api.getIndex()
    }
}