package com.example.onlinecinema.data.api

import com.example.onlinecinema.data.api.model.Movies
import com.example.onlinecinema.data.toDomain
import com.example.onlinecinema.domain.model.MoviesDomainModel

class MoviesRepositoryImpl(private val moviesRemoteSource: MoviesRemoteSource): MoviesRepository {
    override suspend fun getMovies(): List<MoviesDomainModel> {
        return moviesRemoteSource.getMovies().results.map { it.toDomain() }
    }

}