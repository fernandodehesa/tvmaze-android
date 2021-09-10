package com.example.tvmaze.data.show.remote.model

import com.google.gson.annotations.SerializedName

data class PersonResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String?,
    @SerializedName("image") val image: ImageResponse?,
)