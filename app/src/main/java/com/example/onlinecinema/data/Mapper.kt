package com.example.onlinecinema.data

import com.example.onlinecinema.data.api.model.Genres
import com.example.onlinecinema.data.api.model.Movies
import com.example.onlinecinema.data.api.model.MoviesModel
import com.example.onlinecinema.domain.model.MoviesDomainGenresModel
import com.example.onlinecinema.domain.model.MoviesDomainModel

fun Movies.toDomain(): MoviesDomainModel {
    val genres: List<MoviesDomainGenresModel> = genres.map { genres -> genres.toDomain() }

    return MoviesDomainModel(
        genres = genres,
        originalTitle = originalTitle,
        overview = overview,
        releaseDate = releaseDate,
        posterPath = posterPath,
        title = title,
        video = video,
        voteAverage = voteAverage
    )

}

fun Genres.toDomain() = MoviesDomainGenresModel(
    name = name
)