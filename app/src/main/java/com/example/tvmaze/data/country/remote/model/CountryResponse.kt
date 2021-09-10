package com.example.tvmaze.data.country.remote.model

import com.google.gson.annotations.SerializedName

data class CountryResponse(
    @SerializedName("name") val name: String,
    @SerializedName("alpha2Code") val alpha2Code: String,
)
