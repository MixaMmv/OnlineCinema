package com.example.onlinecinema.features.movies_list_screen.ui

import com.example.onlinecinema.base.Event
import com.example.onlinecinema.data.api.model.Movies
import com.example.onlinecinema.domain.model.MoviesDomainModel

data class ViewState(
    val movies: List<MoviesDomainModel>,
    val moviesSorted: List<MoviesDomainModel>,
    val errorMessage: String?,
    val isLoading: Boolean,
    val spanCount: Int
) {
    val isInErrorState: Boolean = errorMessage != null
}

sealed class UiEvent : Event {
    object GetMovies : UiEvent()
    data class OnCardClick(val movie: MoviesDomainModel) : UiEvent()
    object OnSpanCountCLick: UiEvent()
    data class OnSortButtonClick(val index: Int) : UiEvent()
}

sealed class DataEvent : Event {
    object OnLoadMovies : DataEvent()
    data class SuccessMoviesRequest(val movies: List<MoviesDomainModel>) : DataEvent()
    data class ErrorMoviesRequest(val errorMessage: String) : DataEvent()
}

