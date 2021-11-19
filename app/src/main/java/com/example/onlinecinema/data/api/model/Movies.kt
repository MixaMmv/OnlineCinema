package com.example.onlinecinema.data.api.model

import com.google.gson.annotations.SerializedName

//data class Movies(
//    @SerializedName("genres") val genres: List<Genres>,
//    @SerializedName("original_title") val originalTitle: String,
//    @SerializedName("overview") val overview: String,
//    @SerializedName("release_date") val releaseDate: String,
//    @SerializedName("poster_path") val posterPath: String,
//    @SerializedName("title") val title: String,
//    @SerializedName("video") val video: String,
//    @SerializedName("vote_average") val voteAverage: Double
//)


data class Movies(
    @SerializedName("id_kinopoisk") val id: Int,
    @SerializedName("genres") val genres: List<String>?,
    @SerializedName("actors") val actors: List<String>?,
    @SerializedName("title_alternative") val title_alternative: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("year") val year: Int?,
    @SerializedName("countries") val countries: List<String>?,
    @SerializedName("description") val description: String?,
    @SerializedName("premiere_world") val premiere_world: String?,
    @SerializedName("premiere_russia") val premiere_russia: String?,
    @SerializedName("poster") val poster: String?,
    @SerializedName("trailer") val trailer: String?,
    @SerializedName("rating_kinopoisk") val rating: String?
)