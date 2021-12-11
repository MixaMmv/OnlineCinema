package com.example.onlinecinema.features.movies_card_screen.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlinecinema.R
import com.example.onlinecinema.base.Screens
import com.example.onlinecinema.base.loadImage
import com.example.onlinecinema.databinding.FragmentMoviesCardBinding
import com.example.onlinecinema.domain.model.MoviesDomainModel
import com.example.onlinecinema.features.movies_card_screen.ui.adapter.MoviesCardActorsAdapter
import com.example.onlinecinema.features.movies_list_screen.ui.UiEvent
import com.example.onlinecinema.features.movies_list_screen.ui.adapter.MoviesAdapter
import com.example.onlinecinema.features.movies_player_screen.ui.MoviesExoPlayerFragment
import com.example.onlinecinema.features.movies_player_screen.ui.MoviesYoutubePlayerFragment
import com.github.terrakok.cicerone.Router
import org.koin.android.ext.android.inject

class MoviesCardFragment : Fragment(R.layout.fragment_movies_card) {

    private val viewBinding: FragmentMoviesCardBinding by viewBinding(FragmentMoviesCardBinding::bind)
    private val router by inject<Router>()

    companion object {
        private const val MOVIE_KEY = "movie"
        fun newInstance(movie: MoviesDomainModel) = MoviesCardFragment().apply {
            arguments = bundleOf(MOVIE_KEY to movie)
        }
    }

    private val movie: MoviesDomainModel by lazy {
        requireArguments().getParcelable(MOVIE_KEY)!!
    }

    private val moviesCardActorsAdapter: MoviesCardActorsAdapter by lazy {
        MoviesCardActorsAdapter(actors = movie.actors)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            ivCard.loadImage(movie.poster)
            tvCardGenres.text =
                movie.genres?.joinToString(separator = ", ")
            tvCardCountries.text = movie.countries?.joinToString(separator = ", ")
            tvCardDescription.text = movie.description
            tvCardPremierRussia.text = getString(R.string.premier_russia, movie.premiere_russia ?: "")
            tvCardPremierWorld.text = getString(R.string.premier, movie.premiere_world ?: "")
            tvCardRating.text = movie.rating
            tvCardTitleAlternative.text = movie.title_alternative
            tvCardYear.text = movie.year.toString()
            tbCardTitle.title = movie.title
            rvActors.adapter = moviesCardActorsAdapter



            fabCard.setOnClickListener {
                if (movie.trailer?.contains("youtube") == true) {
                    router.navigateTo(Screens.moviesYoutubePlayerScreen(movie))
                } else {
                    router.navigateTo(Screens.moviesExoPlayerScreen(movie))
                }
            }
        }


    }


}