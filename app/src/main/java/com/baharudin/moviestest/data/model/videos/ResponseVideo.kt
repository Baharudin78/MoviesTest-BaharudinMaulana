package com.baharudin.moviestest.data.model.videos

import com.google.gson.annotations.SerializedName

data class ResponseVideo(
    @SerializedName("id") val id: Int,
    @SerializedName("results") val results: List<DataVideo>
)