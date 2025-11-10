package com.applentk.pickadish.core.di

import com.applentk.pickadish.core.remote.FoodApiService
import com.applentk.pickadish.core.remote.FoodRecipeResponseDto
import com.applentk.pickadish.core.utils.FoodRecipeResponseDeserializer
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val gson = GsonBuilder()
            // 🎯 Register the custom deserializer for the *container* class
            .registerTypeAdapter(
                FoodRecipeResponseDto::class.java,
                FoodRecipeResponseDeserializer()
            )
            .create()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideFoodApiService(retrofit: Retrofit): FoodApiService {
        return retrofit.create(FoodApiService::class.java)
    }
}