package com.example.kmm.data

import kotlinx.serialization.Serializable

@Serializable
data class FearAndGreedIndex(
    val `data`: List<Data>,
//    val metadata: Metadata,
    val name: String
)