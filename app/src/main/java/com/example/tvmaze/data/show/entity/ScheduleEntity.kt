package com.example.tvmaze.data.show.entity

data class ScheduleEntity(
    var id: Int,
    var airdate: String?,
    var airtime: String?,
    var show: ShowEntity
){
    val airDateTime: String
        get() = if(!(airdate.isNullOrEmpty() && airtime.isNullOrEmpty())) "$airdate | $airtime" else ""
}
