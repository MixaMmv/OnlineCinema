package com.example.onlinecinema.data.api.model

import com.google.gson.annotations.SerializedName

data class MoviesModel(
    @SerializedName("results") val results: List<Movies>
)