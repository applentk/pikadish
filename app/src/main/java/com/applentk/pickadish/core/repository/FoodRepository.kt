package com.applentk.pickadish.core.repository

import android.util.Log
import com.applentk.pickadish.core.local.FavoriteFood
import com.applentk.pickadish.core.local.FavoriteFoodDao
import com.applentk.pickadish.core.remote.FoodApiService
import com.applentk.pickadish.core.remote.FoodRecipe
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FoodRepository @Inject constructor(
    private val apiService: FoodApiService,
    private val favoriteFoodDao: FavoriteFoodDao
) {
    // Local
    fun getAllFavoriteFood(): Flow<List<FavoriteFood>> {
        return favoriteFoodDao.getAll()
    }

    suspend fun addToFavoriteFood(food: FavoriteFood) {
        favoriteFoodDao.add(food)
    }

    suspend fun removeFavoriteFood(id: String) {
        favoriteFoodDao.delete(id)
    }

    // Remote
    suspend fun searchFoodRecipeByName(name: String): List<FoodRecipe> {
        try {
            val res = apiService.getFoodRecipesByName(name)
            val recipes = res.meals?.map { FoodRecipe.fromDto(it) }

            return recipes ?: emptyList()
        }
        catch (e: Exception) {
            Log.e("FoodRepository:searchFoodRecipeByName", e.message ?: "")
            return listOf()
        }
    }

    suspend fun getFoodRecipeFromId(id: String): FoodRecipe? {
        try {
            val res = apiService.getFoodRecipeById(id)
            if (res.meals == null) {
                return null
            }

            val recipe = FoodRecipe.fromDto(res.meals[0])
            return recipe
        }
        catch (e: Exception) {
            Log.e("FoodRepository:getFoodRecipeFromId", e.message ?: "")
            return null
        }
    }
}