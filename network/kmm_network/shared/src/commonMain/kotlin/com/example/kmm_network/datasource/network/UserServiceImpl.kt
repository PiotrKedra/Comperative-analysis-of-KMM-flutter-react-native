package com.example.kmm_network.datasource.network

import com.example.kmm_network.datasource.network.model.SingleUserDto
import com.example.kmm_network.datasource.network.model.UserListDto
import com.example.kmm_network.datasource.toUser
import com.example.kmm_network.datasource.toUserDto
import com.example.kmm_network.datasource.toUserList
import com.example.kmm_network.domain.model.User
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

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

    override suspend fun create(user: User): User {
        val userDto = user.toUserDto()
        val singleUserDto: SingleUserDto = client.post("${baseUrl}users") {
            setBody(userDto)
        }.body()
        return singleUserDto.userDto.toUser()
    }

    override suspend fun update(user: User): User {
        val userDto = user.toUserDto()
        val singleUserDto: SingleUserDto = client.put("${baseUrl}users/${user.id}") {
            setBody(userDto)
        }.body()
        return singleUserDto.userDto.toUser()
    }

    override suspend fun delete(userId: Int) {
        val response: HttpResponse = client.get("${baseUrl}users/$userId")
        println("Dupa Delete status: ${response.status}")
    }

    companion object {
        const val BASE_URL = "https://reqres.in/api/"
    }
}

