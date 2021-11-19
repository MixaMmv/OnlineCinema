package com.example.onlinecinema.data.api

import com.example.onlinecinema.data.api.model.MoviesModel
import retrofit2.http.GET

interface MoviesApi {
    @GET("movies/all/page/1/token/a2374a7fa973c7757cd665c16294006e")
    suspend fun getMovies(): MoviesModel
}

