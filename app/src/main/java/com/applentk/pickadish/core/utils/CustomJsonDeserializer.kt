package com.applentk.pickadish.core.utils

import com.applentk.pickadish.core.remote.FoodRecipeDto
import com.applentk.pickadish.core.remote.FoodRecipeResponseDto
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class FoodRecipeResponseDeserializer : JsonDeserializer<FoodRecipeResponseDto> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): FoodRecipeResponseDto {
        val jsonObject = json.asJsonObject
        val mealsElement = jsonObject.get("meals")

        val mealsList: List<FoodRecipeDto> = if (mealsElement != null && mealsElement.isJsonArray) {
            context.deserialize(mealsElement, object : TypeToken<List<FoodRecipeDto>>() {}.type)
        } else {
            emptyList()
        }

        return FoodRecipeResponseDto(meals = mealsList)
    }
}