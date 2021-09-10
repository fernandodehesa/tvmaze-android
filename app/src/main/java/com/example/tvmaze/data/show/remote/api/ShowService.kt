package com.example.tvmaze.data.show.remote.api

import com.example.tvmaze.data.common.remote.ApiModule
import com.example.tvmaze.data.show.remote.model.CastResponse
import com.example.tvmaze.data.show.remote.model.ScheduleResponse
import com.example.tvmaze.data.show.remote.model.SearchResponse
import com.example.tvmaze.data.show.remote.model.ShowResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ShowService {

    private val api = ApiModule.provideTvMazeRetrofit().create(ShowApi::class.java)

    suspend fun getSchedule(country: String, date: String): List<ScheduleResponse> {
        return withContext(Dispatchers.IO) {
            val response = api.getSchedule(country, date)
            response.body() ?: emptyList()
        }
    }

    suspend fun getSearch(query: String): List<SearchResponse> {
        return withContext(Dispatchers.IO) {
            val response = api.getSearch(query)
            response.body() ?: emptyList()
        }
    }

    suspend fun getShow(id: Int): ShowResponse? {
        return withContext(Dispatchers.IO) {
            val response = api.getShow(id)
            response.body()
        }
    }

    suspend fun getCast(showId: Int): List<CastResponse> {
        return withContext(Dispatchers.IO) {
            val response = api.getCast(showId)
            response.body() ?: emptyList()
        }
    }
}