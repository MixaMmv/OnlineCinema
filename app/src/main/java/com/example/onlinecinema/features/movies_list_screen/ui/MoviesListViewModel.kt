package com.example.onlinecinema.features.movies_list_screen.ui

import com.example.onlinecinema.base.BaseViewModel
import com.example.onlinecinema.base.Event
import com.example.onlinecinema.base.Screens
import com.example.onlinecinema.base.SingleLiveEvent
import com.example.onlinecinema.domain.MoviesInteractor
import com.github.terrakok.cicerone.Router

class MoviesListViewModel(private val moviesInteractor: MoviesInteractor, private val router: Router) :
    BaseViewModel<ViewState>() {

    init {
        processUiEvent(UiEvent.GetMovies)
    }

    override fun initialViewState(): ViewState = ViewState(
        movies = emptyList(),
        moviesSorted = emptyList(),
        errorMessage = null,
        isLoading = false,
        spanCount = 2
    )

    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {

        when (event) {
            is UiEvent.GetMovies -> {
                processDataEvent(DataEvent.OnLoadMovies)
                moviesInteractor.getMovies().fold(
                    onSuccess = {
                        processDataEvent(DataEvent.SuccessMoviesRequest(it))
                    },
                    onError = {
                        processDataEvent(
                            DataEvent.ErrorMoviesRequest(
                                it.localizedMessage ?: ""
                            )
                        )
                    }
                )
            }

            is DataEvent.OnLoadMovies -> {
                return previousState.copy(isLoading = !previousState.isLoading)
            }

            is UiEvent.OnCardClick -> {
                router.navigateTo(Screens.moviesCardScreen(event.movie))
            }

            is UiEvent.OnSpanCountCLick -> {
                var count = previousState.spanCount + 1
                if (count > 3) count = 1
                return previousState.copy(spanCount = count)
            }

            is UiEvent.OnSortButtonClick -> {
                when (event.index) {
                    0 -> return previousState.copy(moviesSorted = previousState.movies.sortedBy { it.title } )
                    1 -> return previousState.copy(moviesSorted = previousState.movies.sortedByDescending { it.year } )
                    2 -> return previousState.copy(moviesSorted = previousState.movies.sortedByDescending { it.rating} )
                }
            }



            is DataEvent.SuccessMoviesRequest -> {
                return previousState.copy(
                    movies = event.movies,
                    moviesSorted = event.movies,
                    isLoading = false,
                    errorMessage = null
                )
            }
            is DataEvent.ErrorMoviesRequest -> {
                return previousState.copy(
                    isLoading = false,
                    errorMessage = event.errorMessage
                )
            }
        }
        return null

    }
}