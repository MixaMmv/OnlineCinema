package com.example.onlinecinema.base

import com.example.onlinecinema.domain.model.MoviesDomainModel
import com.example.onlinecinema.features.movies_card_screen.ui.MoviesCardFragment
import com.example.onlinecinema.features.movies_list_screen.ui.MoviesListFragment
import com.example.onlinecinema.features.movies_player_screen.ui.MoviesExoPlayerFragment
import com.example.onlinecinema.features.movies_player_screen.ui.MoviesYoutubePlayerFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun moviesListScreen() = FragmentScreen {
        MoviesListFragment.newInstance()
    }

    fun moviesCardScreen(movie: MoviesDomainModel) = FragmentScreen {
        MoviesCardFragment.newInstance(movie)
    }

    fun moviesExoPlayerScreen(movie: MoviesDomainModel) = FragmentScreen {
        MoviesExoPlayerFragment.newInstance(movie)
    }

    fun moviesYoutubePlayerScreen(movie: MoviesDomainModel) = FragmentScreen {
        MoviesYoutubePlayerFragment.newInstance(movie)
    }



}