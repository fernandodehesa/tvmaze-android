package com.example.tvmaze.data.show.repository

import com.example.tvmaze.data.show.entity.CastEntity
import com.example.tvmaze.data.show.entity.ScheduleEntity
import com.example.tvmaze.data.show.entity.ShowEntity
import com.example.tvmaze.data.show.remote.api.ShowService

class ShowRepository {

    private val api = ShowService()

    suspend fun getSchedule(country: String, date: String): List<ScheduleEntity> {

        val response = api.getSchedule(country, date)

        return response.map {
            ScheduleEntity(
                it.id,
                it.airdate,
                it.airtime,
                ShowEntity(
                    it.show.id,
                    it.show.name,
                    it.show.summary,
                    it.show.genres,
                    it.show.officialSite,
                    it.show.schedule?.time,
                    it.show.schedule?.days,
                    it.show.rating?.average,
                    it.show.network?.name,
                    it.show.image?.medium
                )
            )
        }
    }

    suspend fun getSearch(query: String): List<ShowEntity> {

        val response = api.getSearch(query)

        return response.map {
            ShowEntity(
                it.show.id,
                it.show.name,
                it.show.summary,
                it.show.genres,
                it.show.officialSite,
                it.show.schedule?.time,
                it.show.schedule?.days,
                it.show.rating?.average,
                it.show.network?.name,
                it.show.image?.medium
            )
        }
    }

    suspend fun getShow(id: Int): ShowEntity? {

        val response = api.getShow(id)

        if(response != null)
            return ShowEntity(
                response.id,
                response.name,
                response.summary,
                response.genres,
                response.officialSite,
                response.schedule?.time,
                response.schedule?.days,
                response.rating?.average,
                response.network?.name,
                response.image?.medium
            )

        return null
    }

    suspend fun getCast(showId: Int): List<CastEntity> {

        val response = api.getCast(showId)

        return response.map {
            CastEntity(
                it.person.id,
                it.person.name,
                it.person.image?.medium
            )
        }
    }
}