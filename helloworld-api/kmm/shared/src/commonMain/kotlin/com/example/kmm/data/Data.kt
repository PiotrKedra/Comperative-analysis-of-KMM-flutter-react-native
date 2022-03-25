package com.example.kmm.data

import kotlinx.serialization.Serializable

@Serializable
data class Data(
    val time_until_update: String,
    val timestamp: String,
    val value: String,
    val value_classification: String
)