package com.applentk.pickadish.core.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteFoodDao {
    @Query("SELECT * FROM favorite_food")
    fun getAll(): Flow<List<FavoriteFood>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(food: FavoriteFood)


    @Query("DELETE FROM favorite_food WHERE id = :id")
    suspend fun delete(id: String)
}