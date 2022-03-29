package com.example.preappkmm.android.di

import com.example.preappkmm.datasource.RecipeService
import com.example.preappkmm.interactors.recipe_details.GetRecipe
import com.example.preappkmm.interactors.recipe_list.SearchRecipes
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
    fun provideSearchRecipes(recipeService: RecipeService): SearchRecipes {
        return SearchRecipes(recipeService = recipeService)
    }

    @Singleton
    @Provides
    fun provideGetRecipe(recipeService: RecipeService): GetRecipe {
        return GetRecipe(recipeService = recipeService)
    }
}