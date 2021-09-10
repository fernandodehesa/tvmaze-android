package com.example.tvmaze.data.common.remote

import com.example.tvmaze.utils.AppContants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiModule {

    companion object {

        fun provideTvMazeRetrofit(): Retrofit {
            return Retrofit.Builder().apply {
                addConverterFactory(GsonConverterFactory.create())
                baseUrl(AppContants.TV_MAZE_ENDPOINT)
            }.build()
        }

        fun provideCountriesRetrofit(): Retrofit {
            return Retrofit.Builder().apply {
                addConverterFactory(GsonConverterFactory.create())
                baseUrl(AppContants.COUNTRIES_ENDPOINT)
            }.build()
        }
    }
}