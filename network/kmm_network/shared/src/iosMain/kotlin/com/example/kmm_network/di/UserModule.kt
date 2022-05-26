package com.example.kmm_network.di

import com.example.kmm_network.interactors.GetUserList

class UserModule(
    private val networkModule: NetworkModule,
    private val cacheModule: CacheModule,
) {

    val getUserList: GetUserList by lazy {
        GetUserList(
            userService = networkModule.userService,
            userCache = cacheModule.userCache
        )
    }
}