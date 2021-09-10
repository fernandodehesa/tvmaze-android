package com.example.tvmaze.data.country.repository

import com.example.tvmaze.data.country.entity.CountryEntity
import com.example.tvmaze.data.country.remote.api.CountryService

class CountryRepository {

    private val api = CountryService()

    suspend fun getCountries(fields: String = "name;alpha2Code"): List<CountryEntity>{

        val response = api.getCountries(fields)

        return response.map { CountryEntity(it.name, it.alpha2Code) }
    }

}