package com.example.onlinecinema.features.movies_card_screen.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlinecinema.R
import com.example.onlinecinema.base.loadImage
import com.example.onlinecinema.databinding.FragmentMoviesCardBinding
import com.example.onlinecinema.domain.model.MoviesDomainModel
import com.example.onlinecinema.features.movies_player_screen.ui.MoviesPlayerFragment

class MoviesCardFragment : Fragment(R.layout.fragment_movies_card) {

    private val viewBinding: FragmentMoviesCardBinding by viewBinding(FragmentMoviesCardBinding::bind)

    companion object {
        private const val MOVIE_KEY = "movie"
        fun newInstance(movie: MoviesDomainModel) = MoviesCardFragment().apply {
            arguments = bundleOf(MOVIE_KEY to movie)
        }
    }

    private val movie: MoviesDomainModel by lazy {
        requireArguments().getParcelable(MOVIE_KEY)!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            ivCard.loadImage(movie.poster)
            tvCardGenres.text =
                movie.genres?.joinToString(separator = ", ")
            tvCardCountries.text = movie.countries?.joinToString(separator = ", ")
            tvCardDescription.text = movie.description
            tvCardPremierRussia.text = movie.premiere_russia
            tvCardPremierWorld.text = movie.premiere_world
            tvCardRating.text = movie.rating
            tvCardTitleAlternative.text = movie.title_alternative
            tvCardYear.text = movie.year.toString()
            tbCardTitle.title = movie.title
//            tbCardTitle2.text = movie.title

            fabCard.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(android.R.id.content, MoviesPlayerFragment.newInstance(movie))
                    .addToBackStack("movies")
                    .commit()
            }

        }


    }


}