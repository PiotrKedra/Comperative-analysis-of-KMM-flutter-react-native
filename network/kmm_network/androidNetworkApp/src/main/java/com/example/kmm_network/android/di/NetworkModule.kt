package com.example.kmm_network.android.di

import com.example.kmm_network.android.BASE_URL
import com.example.kmm_network.datasource.network.KtorClientFactory
import com.example.kmm_network.datasource.network.UserService
import com.example.kmm_network.datasource.network.UserServiceImpl
import com.example.kmm_network.interactors.GetUserList
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient {
        return KtorClientFactory().build()
    }

    @Singleton
    @Provides
    fun provideUserService(httpClient: HttpClient): UserService {
        return UserServiceImpl(httpClient, BASE_URL)
    }
}