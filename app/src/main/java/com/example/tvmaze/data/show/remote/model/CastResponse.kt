package com.example.tvmaze.data.show.remote.model

import com.google.gson.annotations.SerializedName

data class CastResponse(
    @SerializedName("person") val person: PersonResponse
)
