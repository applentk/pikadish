package com.applentk.pickadish.core.di

import android.content.Context
import androidx.room.Room
import com.applentk.pickadish.core.local.FavoriteFoodDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideFavoriteFoodDao(appDatabase: AppDatabase): FavoriteFoodDao {
        return appDatabase.favoriteFoodDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "wichfud"
        ).build()
    }
}