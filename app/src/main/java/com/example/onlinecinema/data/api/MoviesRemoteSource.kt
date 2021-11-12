package com.example.onlinecinema.data.api

import com.example.onlinecinema.data.api.model.Movies
import com.example.onlinecinema.data.api.model.MoviesModel

class MoviesRemoteSource(private val moviesApi: MoviesApi) {
    suspend fun getMovies(): MoviesModel = moviesApi.getMovies()
}