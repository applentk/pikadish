package com.applentk.pickadish.core.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface FoodApiService {
    @GET("search.php")
    suspend fun getFoodRecipesByName(@Query("s") name: String): FoodRecipeResponseDto

    @GET("lookup.php")
    suspend fun getFoodRecipeById(@Query("i") id: String): FoodRecipeResponseDto
}