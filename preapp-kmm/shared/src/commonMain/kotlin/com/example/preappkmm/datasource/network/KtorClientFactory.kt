package com.example.preappkmm.datasource.network

import com.example.preappkmm.datasource.network.network.model.RecipeDto
import com.example.preappkmm.domain.model.Recipe
import com.example.preappkmm.domain.util.DateTimeUtil
import io.ktor.client.*

expect class KtorClientFactory() {
    fun build(): HttpClient
}


// this is extension function
fun RecipeDto.toRecipe(): Recipe {
    val dateTimeUtil = DateTimeUtil()
    return Recipe(
        id = pk,
        title = title,
        featuredImage = featuredImage,
        rating = rating,
        publisher = publisher,
        sourceUrl = sourceUrl,
        ingredients = ingredients,
        dateAdded = dateTimeUtil.toLocalDate(longDateAdded.toDouble()),
        dateUpdated = dateTimeUtil.toLocalDate(longDateUpdated.toDouble()),
    )
}

fun List<RecipeDto>.toRecipeList(): List<Recipe>{
    return map{it.toRecipe()}
}