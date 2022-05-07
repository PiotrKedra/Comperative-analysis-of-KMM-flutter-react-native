package com.example.kmm_network.android.di

import com.example.kmm_network.datasource.network.UserService
import com.example.kmm_network.interactors.GetUserList
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
}