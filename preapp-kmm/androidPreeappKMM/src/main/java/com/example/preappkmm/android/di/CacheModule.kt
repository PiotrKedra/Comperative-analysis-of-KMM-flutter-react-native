package com.example.preappkmm.android.di

import com.example.preappkmm.android.BaseApplication
import com.example.preappkmm.datasource.cache.*
import com.example.preappkmm.domain.util.DateTimeUtil
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
    fun provideRecipeDatabase(context: BaseApplication): RecipeDatabase {
        return RecipeDatabaseFactory(driverFactory = DriverFactory(context)).createDatabase()
    }

    @Singleton
    @Provides
    fun provideRecipeCache(
        recipeDatabase: RecipeDatabase
    ): RecipeCache{
        return RecipeCacheImpl(
            recipeDatabase = recipeDatabase,
            datetimeUtil = DateTimeUtil()
        )
    }
}