package com.example.kmm_network.di

import com.example.kmm_network.datasource.network.KtorClientFactory
import com.example.kmm_network.datasource.network.UserService
import com.example.kmm_network.datasource.network.UserServiceImpl

class NetworkModule {

    val userService: UserService by lazy {
        UserServiceImpl(
            client = KtorClientFactory().build(),
            baseUrl = UserServiceImpl.BASE_URL
        )
    }
}