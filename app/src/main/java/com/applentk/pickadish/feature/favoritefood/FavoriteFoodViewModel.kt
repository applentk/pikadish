package com.applentk.pickadish.feature.favoritefood

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.applentk.pickadish.core.local.FavoriteFood
import com.applentk.pickadish.core.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteFoodViewModel @Inject constructor(
    private val repository: FoodRepository
): ViewModel() {

    private val _state = MutableStateFlow(FavoriteFoodUiState())
    val state: StateFlow<FavoriteFoodUiState> = _state.asStateFlow()

    init {
        getAllFavoriteFood()
    }

    fun getAllFavoriteFood() {
        viewModelScope.launch {
            _state.update { it.copy(isFavoriteFoodsLoading = true) }

            repository.getAllFavoriteFood()
                .collect { data ->
                    _state.update { it.copy(favoriteFoods = data) }
                }

            _state.update { it.copy(isFavoriteFoodsLoading = false) }
        }
    }
}

data class FavoriteFoodUiState(
    val favoriteFoods: List<FavoriteFood> = emptyList(),
    val isFavoriteFoodsLoading: Boolean = false
)