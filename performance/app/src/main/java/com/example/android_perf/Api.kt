package com.example.android_perf

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class UserServiceImpl(
    private val client: HttpClient,
) {
    suspend fun getUsers(): List<User> {
        val userListDto: UserListDto = client.get("https://reqres.in/api/users").body()
        return userListDto.userDtoList.toUserList()
    }
}

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