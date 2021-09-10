package com.example.tvmaze.data.country.remote.api

import com.example.tvmaze.data.common.remote.ApiModule
import com.example.tvmaze.data.country.remote.model.CountryResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CountryService {

    private val api = ApiModule.provideCountriesRetrofit().create(CountryApi::class.java)

    suspend fun getCountries(fields: String): List<CountryResponse> {
        return withContext(Dispatchers.IO) {
            val response = api.getCountries(fields)
            response.body() ?: emptyList()
        }
    }
}