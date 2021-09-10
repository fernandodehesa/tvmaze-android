package com.example.tvmaze.data.country.remote.api

import com.example.tvmaze.data.country.remote.model.CountryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CountryApi {

    @GET("all")
    suspend fun getCountries(
        @Query("fields") fields: String
    ): Response<List<CountryResponse>>
}