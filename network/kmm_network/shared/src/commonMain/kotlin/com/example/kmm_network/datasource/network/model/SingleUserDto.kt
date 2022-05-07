package com.example.kmm_network.datasource.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SingleUserDto(
    @SerialName("data")
    val userDto: UserDto
)
