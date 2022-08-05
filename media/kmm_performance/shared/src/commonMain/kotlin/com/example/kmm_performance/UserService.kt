package com.example.kmm_performance

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