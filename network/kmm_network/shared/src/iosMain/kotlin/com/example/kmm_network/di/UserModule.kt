package com.example.kmm_network.di

import com.example.kmm_network.interactors.CreateUser
import com.example.kmm_network.interactors.DeleteUser
import com.example.kmm_network.interactors.GetUserList
import com.example.kmm_network.interactors.UpdateUser

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

    val createUser: CreateUser by lazy {
        CreateUser(
            userService = networkModule.userService,
            userCache = cacheModule.userCache
        )
    }

    val deleteUser: DeleteUser by lazy {
        DeleteUser(
            userService = networkModule.userService,
            userCache = cacheModule.userCache
        )
    }

    val updateUser: UpdateUser by lazy {
        UpdateUser(
            userService = networkModule.userService,
            userCache = cacheModule.userCache
        )
    }
}