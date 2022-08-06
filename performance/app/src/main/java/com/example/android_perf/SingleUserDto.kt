package com.example.android_perf

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SingleUserDto(
    @SerialName("data")
    val userDto: UserDto
)