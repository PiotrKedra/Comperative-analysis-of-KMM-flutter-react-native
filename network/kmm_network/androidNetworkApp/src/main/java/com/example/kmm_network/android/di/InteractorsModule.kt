package com.example.kmm_network.android.di

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
    fun provideGetUserList(userService: UserService): GetUserList {
        return GetUserList(userService = userService)
    }

    @Singleton
    @Provides
    fun provideGetUser(userService: UserService): GetUser {
        return GetUser(userService = userService)
    }

    @Singleton
    @Provides
    fun provideCreateUser(userService: UserService): CreateUser {
        return CreateUser(userService = userService)
    }

    @Singleton
    @Provides
    fun provideUpdateUser(userService: UserService): UpdateUser {
        return UpdateUser(userService = userService)
    }

    @Singleton
    @Provides
    fun provideDeleteUser(userService: UserService): DeleteUser {
        return DeleteUser(userService = userService)
    }
}