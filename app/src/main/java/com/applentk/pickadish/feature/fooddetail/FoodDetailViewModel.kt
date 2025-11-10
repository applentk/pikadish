package com.applentk.pickadish.feature.fooddetail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.applentk.pickadish.core.local.FavoriteFood
import com.applentk.pickadish.core.remote.FoodRecipe
import com.applentk.pickadish.core.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodDetailViewModel @Inject constructor(
    private val repository: FoodRepository,
): ViewModel() {
    private val _state = MutableStateFlow(FoodDetailUiState())
    val state: StateFlow<FoodDetailUiState> = _state.asStateFlow()

    fun initRecipe(mealId: String) {
        viewModelScope.launch {
            val recipe = repository.getFoodRecipeFromId(mealId)
            val favoriteRecipes = repository.getAllFavoriteFood().first()

            val isRecipeAUserFavorite = favoriteRecipes.any { favoriteRecipe ->
                favoriteRecipe.id == recipe?.id
            }

            _state.update {
                it.copy(
                    foodRecipe = recipe,
                    isUserFavorite = isRecipeAUserFavorite
                )
            }
        }
    }

    fun addToFavorite() {
        if (state.value.foodRecipe != null) {
            viewModelScope.launch {
                repository.addToFavoriteFood(
                    FavoriteFood(
                        id = state.value.foodRecipe?.id!!,
                        name = state.value.foodRecipe?.name!!,
                        imgUrl = state.value.foodRecipe?.imageUrl!!
                    )
                )
                _state.update {
                    it.copy(isUserFavorite = true)
                }
            }
        }
    }

    fun removeFromFavorite() {
        if (state.value.foodRecipe != null) {
            viewModelScope.launch {
                try {
                    repository.removeFavoriteFood(state.value.foodRecipe?.id!!)
                    _state.update {
                        it.copy(isUserFavorite = false)
                    }
                }
                catch (e: Exception) {
                    Log.e("FavError", e.message ?: "")
                }
            }
        }
    }
}

data class FoodDetailUiState(
    var foodRecipe: FoodRecipe? = null,
    var isUserFavorite: Boolean = false
)