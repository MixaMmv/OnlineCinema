package com.example.onlinecinema.domain

import com.example.onlinecinema.base.attempt
import com.example.onlinecinema.data.api.MoviesRepository

class MoviesInteractor(private val moviesRepository: MoviesRepository) {
    suspend fun getMovies() = attempt {
        moviesRepository.getMovies()
    }
}