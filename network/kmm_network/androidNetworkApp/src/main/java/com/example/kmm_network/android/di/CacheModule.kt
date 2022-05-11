package com.example.kmm_network.android.di

import com.example.kmm_network.android.BaseApplication
import com.example.kmm_network.datasource.cache.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideUserDatabase(context: BaseApplication): UserDatabase {
        return UserDatabaseFactory(driverFactory = DriverFactory(context = context)).createDatabase()
    }

    @Singleton
    @Provides
    fun provideRecipeCache(userDatabase: UserDatabase): UserCache {
        return UserCacheImpl(userDatabase = userDatabase)
    }
}