package com.applentk.pickadish.feature.favoritefood

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.applentk.pickadish.R
import com.applentk.pickadish.feature.fooddetail.FoodDetailActivity
import com.applentk.pickadish.feature.ui.composes.IconButton
import com.applentk.pickadish.feature.ui.theme.ColorScheme

@Composable
fun FavoriteFoodScreen(
    favoriteFoodViewModel: FavoriteFoodViewModel = viewModel()
) {
    val context = LocalContext.current
    val state by favoriteFoodViewModel.state.collectAsState()

    Scaffold{ paddingValues ->
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier.fillMaxSize().background(ColorScheme.background)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 96.dp)
                    .background(ColorScheme.background)
            ) {
                LazyColumn(
                    modifier = Modifier.padding(bottom = 32.dp)
                ) {
                    items(items = state.favoriteFoods, itemContent = {
                        Column(
                            modifier = Modifier
                                .padding(horizontal = 26.dp)
                                .padding(top = 16.dp)
                                .clickable {
                                    val intent = Intent(context, FoodDetailActivity::class.java)
                                    intent.putExtra("meal_id", it.id)
                                    context.startActivity(intent)
                                }
                        ) {
                            AsyncImage(
                                model = it.imgUrl,
                                contentDescription = "Favorite Image",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(225.dp)
                                    .clip(RoundedCornerShape(16.dp))
                            )
                            Text(
                                text = it.name,
                                fontSize = 24.sp,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                    })
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
                    .background(ColorScheme.background)
            ) {
                Box(Modifier.padding(14.dp)) {
                    IconButton(
                        painter = painterResource(R.drawable.baseline_arrow_back_24),
                        onClick = {
                            (context as Activity).finish()
                        }
                    )
                }
                Text(
                    text = "My favorite recipe",
                    fontSize = 26.sp
                )
            }
        }
    }
}