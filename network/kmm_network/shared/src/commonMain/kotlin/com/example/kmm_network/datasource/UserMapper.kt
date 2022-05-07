package com.example.kmm_network.datasource

import com.example.kmm_network.datasource.network.model.UserDto
import com.example.kmm_network.domain.model.User

fun UserDto.toUser(): User {
    return User(
        id = id,
        email = email,
        firstName = firstName,
        lastName = lastName,
        avatar = avatar
    )
}

fun List<UserDto>.toUserList(): List<User> {
    return map{it.toUser()}
}