package com.example.tvmaze.data.show.remote.api

import com.example.tvmaze.data.show.remote.model.CastResponse
import com.example.tvmaze.data.show.remote.model.ScheduleResponse
import com.example.tvmaze.data.show.remote.model.SearchResponse
import com.example.tvmaze.data.show.remote.model.ShowResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ShowApi {

    @GET("/schedule")
    suspend fun getSchedule(
        @Query("country") country: String,
        @Query("date") date: String
    ): Response<List<ScheduleResponse>>

    @GET("/search/shows")
    suspend fun getSearch(
        @Query("q") query: String,
    ): Response<List<SearchResponse>>

    @GET("/shows/{id}")
    suspend fun getShow(
        @Path("id") id: Int,
    ): Response<ShowResponse>

    @GET("/shows/{id}/cast")
    suspend fun getCast(
        @Path("id") showId: Int,
    ): Response<List<CastResponse>>
}