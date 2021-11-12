package com.example.onlinecinema.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoviesDomainModel(
    val genres: List<MoviesDomainGenresModel>,
    val originalTitle: String,
    val overview: String,
    val releaseDate: String,
    val posterPath: String,
    val title: String,
    val video: String,
    val voteAverage: Double
): Parcelable