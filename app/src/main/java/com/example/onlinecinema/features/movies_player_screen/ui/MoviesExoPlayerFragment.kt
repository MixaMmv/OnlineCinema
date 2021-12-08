package com.example.onlinecinema.features.movies_player_screen.ui

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlinecinema.R
import com.example.onlinecinema.base.Constants.VIDEO_FILE
import com.example.onlinecinema.databinding.FragmentMoviesPlayerBinding
import com.example.onlinecinema.domain.model.MoviesDomainModel
import com.example.onlinecinema.features.movies_player_screen.service.PlayerService
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.util.Util

class MoviesExoPlayerFragment : Fragment(R.layout.fragment_movies_player) {

    private val viewBinding: FragmentMoviesPlayerBinding by viewBinding(FragmentMoviesPlayerBinding::bind)

    companion object {
        private const val MOVIE_KEY = "movie"
        fun newInstance(movie: MoviesDomainModel) = MoviesExoPlayerFragment().apply {
            arguments = bundleOf(MOVIE_KEY to movie)
        }
    }

    private val movie: MoviesDomainModel by lazy {
        requireArguments().getParcelable(MOVIE_KEY)!!
    }

    private val connection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {

            when (service) {
                is PlayerService.PlayerServiceBinder -> {
                    viewBinding.pvMoviePlayer.player = service.getService().exoPlayer
                }
            }
        }

        override fun onServiceDisconnected(p0: ComponentName?) {}

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val intent = Intent(requireActivity(), PlayerService::class.java)
        intent.putExtra(VIDEO_FILE, movie)
        requireActivity().bindService(intent, connection, Context.BIND_AUTO_CREATE)

        super.onViewCreated(view, savedInstanceState)
    }







}