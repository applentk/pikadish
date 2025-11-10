package com.applentk.pickadish.feature.favoritefood

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.applentk.pickadish.feature.ui.theme.WichFudTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFoodActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WichFudTheme {
                FavoriteFoodScreen()
            }
        }
    }
}