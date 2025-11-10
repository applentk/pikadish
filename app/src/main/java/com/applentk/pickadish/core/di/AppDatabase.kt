package com.applentk.pickadish.core.di

import androidx.room.Database
import androidx.room.RoomDatabase
import com.applentk.pickadish.core.local.FavoriteFood
import com.applentk.pickadish.core.local.FavoriteFoodDao

@Database(
    entities = [FavoriteFood::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteFoodDao(): FavoriteFoodDao
}