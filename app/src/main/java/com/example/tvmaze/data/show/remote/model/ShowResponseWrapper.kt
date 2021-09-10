package com.example.tvmaze.data.show.remote.model

import com.google.gson.annotations.SerializedName

data class Schedule(
    @SerializedName("time") val time: String?,
    @SerializedName("days") val days: List<String>?,
)

data class Rating(
    @SerializedName("average") val average: Float?,
)

data class Network(
    @SerializedName("name") val name: String?,
)