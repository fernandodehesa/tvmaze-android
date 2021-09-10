package com.example.tvmaze.data.show.remote.model

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("show") val show: ShowResponse
)