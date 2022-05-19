package com.example.kmm_network.datasource.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateUserDto(

    @SerialName("name")
    val name: String,

    @SerialName("job")
    val job: String,
)
