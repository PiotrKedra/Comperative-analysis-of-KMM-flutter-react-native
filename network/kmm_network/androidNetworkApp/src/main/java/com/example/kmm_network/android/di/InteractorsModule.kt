package com.example.kmm_network.android.di

import com.example.kmm_network.datasource.cache.UserCache
import com.example.kmm_network.datasource.network.UserService
import com.example.kmm_network.interactors.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InteractorsModule {

    @Singleton
    @Provides
    fun provideGetUserList(userService: UserService, userCache: UserCache): GetUserList {
        return GetUserList(userService = userService, userCache = userCache)
    }

    @Singleton
    @Provides
    fun provideGetUser(userCache: UserCache): GetUser {
        return GetUser(userCache = userCache)
    }

    @Singleton
    @Provides
    fun provideCreateUser(userService: UserService, userCache: UserCache): CreateUser {
        return CreateUser(userService = userService, userCache = userCache)
    }

    @Singleton
    @Provides
    fun provideUpdateUser(userService: UserService, userCache: UserCache): UpdateUser {
        return UpdateUser(userService = userService, userCache = userCache)
    }

    @Singleton
    @Provides
    fun provideDeleteUser(userService: UserService, userCache: UserCache): DeleteUser {
        return DeleteUser(userService = userService, userCache = userCache)
    }
}