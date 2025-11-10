package com.applentk.pickadish.feature.fooddetail

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.applentk.pickadish.feature.home.HomeActivity
import com.applentk.pickadish.feature.ui.theme.WichFudTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mealId = intent.getStringExtra("meal_id")

        if (mealId == null) {
            val intent = Intent(this, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()

            return
        }

        setContent {
            WichFudTheme {
                FoodDetailScreen(mealId)
            }
        }
    }
}