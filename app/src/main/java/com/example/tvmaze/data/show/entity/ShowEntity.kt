package com.example.tvmaze.data.show.entity

import java.io.Serializable

data class ShowEntity(
    var id: Int,
    var name: String?,
    var summary: String?,
    var genres: List<String> = emptyList(),
    var officialSite: String?,
    var scheduleTime: String?,
    var scheduleDays: List<String>?,
    var ratingAverage: Float?,
    var networkName: String?,
    var image: String?
): Serializable {

    val scheduleTimeDays: String
        get(){
            val time = if(!scheduleTime.isNullOrEmpty()) scheduleTime else "-"
            val days = if(!scheduleDays.isNullOrEmpty()) scheduleDays?.joinToString(separator = ", ") else "-"
            return "$time | $days"
        }

    val genresLine: String
        get() = genres.joinToString(separator = ", ")

    val ratingFormatted: String
        get() = if(ratingAverage != null) "${ratingAverage}" else "?"
}
