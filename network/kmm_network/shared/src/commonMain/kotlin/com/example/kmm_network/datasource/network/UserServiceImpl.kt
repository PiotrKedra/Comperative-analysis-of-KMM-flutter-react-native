package com.example.kmm_network.datasource.network

import com.example.kmm_network.datasource.network.model.SingleUserDto
import com.example.kmm_network.datasource.network.model.UserListDto
import com.example.kmm_network.datasource.toUser
import com.example.kmm_network.datasource.toUserList
import com.example.kmm_network.domain.model.User
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class UserServiceImpl(
    private val client: HttpClient,
    private val baseUrl: String,
): UserService {
    override suspend fun getUsers(page: Int): List<User> {
        val userListDto: UserListDto = client.get("${baseUrl}users/?page=$page").body()
        return userListDto.userDtoList.toUserList()
    }

    override suspend fun get(id: Int): User {
        val singleUserDto: SingleUserDto = client.get("${baseUrl}users/$id").body()
        return singleUserDto.userDto.toUser()
    }

    companion object {
        const val BASE_URL = "https://reqres.in/api/"
    }
}

