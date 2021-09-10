package com.example.tvmaze.data.show.remote.model

import com.google.gson.annotations.SerializedName

data class ShowResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("summary") val summary: String?,
    @SerializedName("genres") val genres: List<String> = emptyList(),
    @SerializedName("officialSite") val officialSite: String?,
    @SerializedName("schedule") val schedule: Schedule?,
    @SerializedName("rating") val rating: Rating?,
    @SerializedName("network") val network: Network?,
    @SerializedName("image") val image: ImageResponse?,
)

