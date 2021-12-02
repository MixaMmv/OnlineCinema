package com.example.onlinecinema.features.movies_player_screen.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.onlinecinema.R
import com.example.onlinecinema.domain.model.MoviesDomainModel
import kr.co.prnd.YouTubePlayerView


class MoviesYoutubePlayerFragment: Fragment(R.layout.fragment_movies_player_youtube)
{

    companion object {
        private const val MOVIE_KEY = "movie"
        fun newInstance(movie: MoviesDomainModel) = MoviesYoutubePlayerFragment().apply {
            arguments = bundleOf(MOVIE_KEY to movie)
        }
    }

    private val movie: MoviesDomainModel by lazy {
        requireArguments().getParcelable(MOVIE_KEY)!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val videoId = movie.trailer?.substringAfterLast('/') ?: ""

        view.findViewById<YouTubePlayerView>(R.id.pvYoutube).play(videoId)

    }


}




