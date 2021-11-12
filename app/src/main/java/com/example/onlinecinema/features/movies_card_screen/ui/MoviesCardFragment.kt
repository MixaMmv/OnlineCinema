package com.example.onlinecinema.features.movies_card_screen.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlinecinema.R
import com.example.onlinecinema.base.loadImage
import com.example.onlinecinema.data.api.model.Genres
import com.example.onlinecinema.databinding.FragmentMoviesCardBinding
import com.example.onlinecinema.domain.model.MoviesDomainModel
import com.example.onlinecinema.features.movies_player_screen.ui.MoviesPlayerFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesCardFragment : Fragment(R.layout.fragment_movies_card) {

    private val viewBinding: FragmentMoviesCardBinding by viewBinding(FragmentMoviesCardBinding::bind)

    companion object {
        private const val MOVIE_KEY = "movie"
        fun newInstance(movie: MoviesDomainModel) = MoviesCardFragment().apply {
            arguments = bundleOf(Pair(MOVIE_KEY, movie))
        }
    }

    private val movie: MoviesDomainModel by lazy {
        requireArguments().getParcelable(MOVIE_KEY)!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.apply {
            ivCard.loadImage(movie.posterPath)
            tvCardGenres.text =
                movie.genres.joinToString(separator = ", ", transform = { it -> it.name })
            tvCardOverview.text = movie.overview
            tvCardReleaseDate.text = movie.releaseDate
            tvCardTitle.text = movie.title
            tvCardVotes.text = movie.voteAverage.toString()

            fabCard.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(android.R.id.content, MoviesPlayerFragment.newInstance(movie))
                    .addToBackStack("movies")
                    .commit()
            }

        }


    }


}