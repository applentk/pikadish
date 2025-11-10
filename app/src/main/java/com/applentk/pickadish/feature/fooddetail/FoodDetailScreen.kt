package com.applentk.pickadish.feature.fooddetail

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.applentk.pickadish.R
import com.applentk.pickadish.feature.ui.composes.IconButton
import com.applentk.pickadish.feature.ui.theme.ColorScheme
import kotlinx.coroutines.delay

@Composable
fun FoodDetailScreen(
    mealId: String,
    foodDetailViewModel: FoodDetailViewModel = viewModel()
) {
    val context = LocalContext.current
    val state by foodDetailViewModel.state.collectAsState()
    val listState = rememberLazyListState()

    val isLoading = state.foodRecipe == null

    LaunchedEffect(Unit) {
        foodDetailViewModel.initRecipe(mealId)
    }

    LaunchedEffect(isLoading) {
        delay(800)
        listState.animateScrollToItem(0)
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
        else {
            Box(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier
                        .padding(paddingValues)
                        .padding(14.dp)
                        .align(Alignment.TopStart)
                        .zIndex(1f)
                ) {
                    IconButton(
                        painter = painterResource(R.drawable.baseline_arrow_back_24),
                        onClick = {
                            (context as Activity).finish()
                        },
                    )
                }

                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(ColorScheme.background)
                ) {
                    item {
                        AsyncImage(
                            model = state.foodRecipe?.imageUrl,
                            contentDescription = "Food Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxHeight(0.4f)
                                .fillMaxWidth()
                        )
                    }

                    item {
                        Column(
                            modifier = Modifier
                                .padding(horizontal = 22.dp)
                                .padding(top = 16.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = state.foodRecipe?.name ?: "Loading...",
                                    fontSize = 32.sp,
                                    lineHeight = 32.sp,
                                    modifier = Modifier
                                        .weight(0.8f)
                                )
                                Icon(
                                    painter =
                                        if (state.isUserFavorite) {
                                            painterResource(R.drawable.baseline_star_24)
                                        }
                                        else {
                                            painterResource(R.drawable.baseline_star_outline_24)
                                        },
                                    contentDescription = "Favorite",
                                    tint = Color.Unspecified,
                                    modifier = Modifier
                                        .size(40.dp)
                                        .align(Alignment.CenterVertically)
                                        .clickable {
                                            if (state.isUserFavorite) {
                                                foodDetailViewModel.removeFromFavorite()
                                            }
                                            else {
                                                foodDetailViewModel.addToFavorite()
                                            }
                                        }
                                )
                            }

                            Text(
                                text = state.foodRecipe?.category ?: "Category",
                                color = ColorScheme.primary
                            )

                            Text(
                                text = "Ingredient",
                                style = LocalTextStyle.current.copy(
                                    fontSize = 20.sp,
                                    textDecoration = TextDecoration.Underline
                                ),
                                modifier = Modifier.padding(top = 24.dp)
                            )

                            Column(
                                modifier = Modifier
                                    .padding(top = 8.dp)
                            ) {
                                (state.foodRecipe?.ingredients ?: listOf()).forEach {
                                    Row(modifier = Modifier.fillMaxWidth()) {
                                        Text(
                                            text = it.first,
                                            modifier = Modifier.weight(0.5f)
                                        )
                                        Text(
                                            text = it.second,
                                            textAlign = TextAlign.End,
                                            modifier = Modifier.weight(0.5f)
                                        )
                                    }
                                }
                            }

                            Text(
                                text = "Instruction",
                                style = LocalTextStyle.current.copy(
                                    fontSize = 20.sp,
                                    textDecoration = TextDecoration.Underline
                                ),
                                modifier = Modifier.padding(top = 24.dp)
                            )

                            Text(
                                text = state.foodRecipe?.instructions ?: "Loading...",
                                modifier = Modifier.padding(bottom = 32.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}