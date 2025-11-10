package com.applentk.pickadish.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.applentk.pickadish.core.remote.FoodRecipe
import com.applentk.pickadish.core.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: FoodRepository
): ViewModel() {
    private val _state = MutableStateFlow(HomeUiState())
    val state: StateFlow<HomeUiState> = _state.asStateFlow()

    fun setSearchQuery(query: String) {
        _state.update {
            it.copy(searchQuery = query)
        }
    }

    fun searchFoodFromQuery() {
        if (state.value.searchQuery.isBlank()) {
            _state.update {
                it.copy(foodRecipesFromQuery = emptyList())
            }
            return
        }

        viewModelScope.launch {
            val recipes = repository.searchFoodRecipeByName(state.value.searchQuery)
            _state.update {
                it.copy(foodRecipesFromQuery = recipes)
            }
        }
    }
}

data class HomeUiState(
    val searchQuery: String = "",
    val foodRecipesFromQuery: List<FoodRecipe> = emptyList()
)