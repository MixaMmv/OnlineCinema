package com.example.onlinecinema.data.api

import com.example.onlinecinema.data.toDomain
import com.example.onlinecinema.domain.model.MoviesDomainModel

class MoviesRepositoryImpl(private val moviesRemoteSource: MoviesRemoteSource,
//                           private val moviesDAO:
): MoviesRepository {
    override suspend fun getMovies(): List<MoviesDomainModel> {
        return moviesRemoteSource.getMovies().results.map { it.toDomain() }
    }

//    override suspend fun getMoviesLocal(): List<MoviesDomainModel> {
////        return moviesDAO.read().map { it.toDomain()}
//    }




}