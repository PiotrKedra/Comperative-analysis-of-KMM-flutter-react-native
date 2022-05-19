package com.example.kmm_network.datasource

import com.example.kmm_network.datasource.network.model.CreateUserDto
import com.example.kmm_network.datasource.network.model.UserDto
import com.example.kmm_network.domain.model.User
import com.example.kmmnetwork.datasource.cache.UserEntity

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

fun User.toUserDto(): UserDto {
    return UserDto(
        id = id,
        email = email,
        firstName = firstName,
        lastName = lastName,
        avatar = avatar
    )
}

fun User.toCreateUserDto(): CreateUserDto {
    return CreateUserDto(
        name = firstName,
        job = lastName,
    )
}

fun UserEntity.toUser(): User {
    return User(
        id = id.toInt(),
        email = email.orEmpty(),
        firstName = first_name.orEmpty(),
        lastName = last_name.orEmpty(),
        avatar = avatar.orEmpty()
    )
}

fun List<UserEntity>.fromDBtoUserList(): List<User> {
    return map{it.toUser()}
}