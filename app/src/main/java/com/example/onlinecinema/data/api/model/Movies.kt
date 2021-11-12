package com.example.onlinecinema.data.api.model

import com.google.gson.annotations.SerializedName

data class Movies(
    @SerializedName("genres") val genres: List<Genres>,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("title") val title: String,
    @SerializedName("video") val video: String,
    @SerializedName("vote_average") val voteAverage: Double
)