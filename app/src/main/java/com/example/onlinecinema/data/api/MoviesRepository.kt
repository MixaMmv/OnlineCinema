package com.example.onlinecinema.data.api

import com.example.onlinecinema.data.api.model.Movies
import com.example.onlinecinema.domain.model.MoviesDomainModel

interface MoviesRepository {
    suspend fun getMovies(): List<MoviesDomainModel>
//    suspend fun getMoviesLocal(): List<MoviesDomainModel>
}