package com.example.preappkmm.android.di

import com.example.preappkmm.datasource.RecipeService
import com.example.preappkmm.datasource.RecipeServiceImpl
import com.example.preappkmm.datasource.RecipeServiceImpl.Companion.BASE_URL
import com.example.preappkmm.datasource.network.KtorClientFactory
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
    fun provideRecipeService(
        httpClient: HttpClient,
    ): RecipeService {
        return RecipeServiceImpl(
            httpClient=httpClient,
            baseUrl = BASE_URL
        )
    }
}