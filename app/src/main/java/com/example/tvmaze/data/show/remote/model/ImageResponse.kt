package com.example.tvmaze.data.show.remote.model

import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("medium") val medium: String?
)