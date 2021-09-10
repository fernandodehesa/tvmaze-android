package com.example.tvmaze.data.show.remote.model

import com.google.gson.annotations.SerializedName

data class ScheduleResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("airdate") val airdate: String?,
    @SerializedName("airtime") val airtime: String?,
    @SerializedName("show") val show: ShowResponse,
)
