package com.applentk.pickadish.core.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_food")
data class FavoriteFood (
    @PrimaryKey val id: String,
    val name: String,
    val imgUrl: String
)