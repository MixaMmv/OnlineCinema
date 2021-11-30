package com.example.onlinecinema.data

import com.example.onlinecinema.data.api.model.Movies
import com.example.onlinecinema.domain.model.MoviesDomainModel

fun Movies.toDomain() = MoviesDomainModel(
    id = id,
    genres = genres,
    actors = actors,
    title_alternative = title_alternative,
    title = title,
    year = year,
    countries = countries,
    description = description,
    premiere_world = premiere_world,
    premiere_russia = premiere_russia,
    poster = "https:$poster",
    trailer = "http://techslides.com/demos/sample-videos/small.mp4",
//    trailer = trailer,
    rating = rating
)



//    http://techslides.com/demos/sample-videos/small.mp4
//    https://www.youtube.com/embed/5snjgB9PO48