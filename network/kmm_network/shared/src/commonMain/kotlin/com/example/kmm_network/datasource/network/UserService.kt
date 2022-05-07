package com.example.kmm_network.datasource.network

import com.example.kmm_network.domain.model.User

interface UserService {

    suspend fun getUsers(
        page: Int
    ) : List<User>

    suspend fun get(
        id: Int
    ) : User
}