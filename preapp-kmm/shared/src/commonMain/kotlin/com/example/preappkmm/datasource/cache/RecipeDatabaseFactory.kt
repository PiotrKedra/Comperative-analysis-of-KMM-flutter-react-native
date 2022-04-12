package com.example.preappkmm.datasource.cache

import com.example.preappkmm.domain.model.Recipe
import com.example.preappkmm.domain.util.DateTimeUtil
import com.squareup.sqldelight.db.SqlDriver

class RecipeDatabaseFactory(
    private val driverFactory: DriverFactory,
) {
    fun createDatabase(): RecipeDatabase {
        return RecipeDatabase(driverFactory.createDriver())
    }
}

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

fun Recipe_Entity.toRecipe(): Recipe {
    val datetimeUtil = DateTimeUtil()
    return Recipe(
        id = id.toInt(),
        title = title,
        publisher = publisher,
        featuredImage = featured_image,
        rating = rating.toInt(),
        sourceUrl = source_url,
        ingredients = ingredients.convertIngredientsToList(),
        dateAdded = datetimeUtil.toLocalDate(date_added),
        dateUpdated = datetimeUtil.toLocalDate(date_updated),
    )
}

fun List<Recipe_Entity>.toRecipeList(): List<Recipe> {
    return map{it.toRecipe()}
}

fun List<String>.convertIngredientsToString(): String {
    val ingredientsString = StringBuilder()
    for (ingredient in this){
        ingredientsString.append("$ingredient,")
    }
    return ingredientsString.toString()
}

fun String.convertIngredientsToList(): List<String> {
    val list: ArrayList<String> = ArrayList()
    for (ingredient in split(",")) {
        list.add(ingredient)
    }
    return list
}