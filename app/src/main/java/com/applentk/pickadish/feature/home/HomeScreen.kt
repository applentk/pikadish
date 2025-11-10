package com.applentk.pickadish.feature.home

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.applentk.pickadish.R
import com.applentk.pickadish.feature.favoritefood.FavoriteFoodActivity
import com.applentk.pickadish.feature.fooddetail.FoodDetailActivity
import com.applentk.pickadish.feature.ui.composes.IconButton
import com.applentk.pickadish.feature.ui.composes.TextInputWithTextScreen
import com.applentk.pickadish.feature.ui.theme.ColorScheme

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel()
) {
    val context = LocalContext.current
    val state by homeViewModel.state.collectAsState()

    Scaffold(Modifier.fillMaxSize()) { paddingValue ->
        Box(
            Modifier
                .fillMaxSize()
                .background(ColorScheme.background)
        ) {
            Box(
                Modifier
                    .padding(paddingValue)
                    .padding(14.dp)
                    .align(Alignment.TopEnd)
            ) {
                IconButton(
                    painter = painterResource(R.drawable.baseline_star_24),
                    onClick = {
                        val intent = Intent(context, FavoriteFoodActivity::class.java)
                        context.startActivity(intent)
                    }
                )
            }
            Text(
                text = stringResource(R.string.app_name),
                fontSize = 32.sp,
                modifier = Modifier.align(Alignment.Center)
            )
            TextInputWithTextScreen(
                placeholder = "PikaDish that you are like !",
                texts = state.foodRecipesFromQuery.map { it.name },
                onInputChange = {
                    homeViewModel.setSearchQuery(it)
                    homeViewModel.searchFoodFromQuery()
                },
                onClickEachText = { text ->
                    val meal = state.foodRecipesFromQuery.find { it.name == text }
                    if (meal !== null) {
                        val intent = Intent(context, FoodDetailActivity::class.java)
                        intent.putExtra("meal_id", meal.id)
                        context.startActivity(intent)
                    }
                },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(top = paddingValue.calculateTopPadding())
            )
        }
    }
}
