package com.example.preappkmm.datasource.network.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeSearchResponseDto(

    @SerialName("count")
    var count: Int,

    @SerialName("results")
    var results: List<RecipeDto>,
)