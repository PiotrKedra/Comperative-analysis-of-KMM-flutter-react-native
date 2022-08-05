package com.example.kmm_performance

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SingleUserDto(
    @SerialName("data")
    val userDto: UserDto
)