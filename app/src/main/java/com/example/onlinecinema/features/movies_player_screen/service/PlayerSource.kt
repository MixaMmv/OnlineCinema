package com.example.onlinecinema.features.movies_player_screen.service

import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaBrowserCompat.MediaItem.FLAG_PLAYABLE
import android.support.v4.media.MediaDescriptionCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.MediaMetadataCompat.*
import com.example.onlinecinema.data.api.MoviesRepository
import androidx.core.net.toUri
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.ConcatenatingMediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PlayerSource(
    private val moviesRepository: MoviesRepository
) {

    var movies = emptyList<MediaMetadataCompat>()

    private val onReadyListeners = mutableListOf<(Boolean) -> Unit>()

    private var state: VideoSourceState = VideoSourceState.STATE_CREATED

        set(value) {
            if (value == VideoSourceState.STATE_INITIALIZED || value == VideoSourceState.STATE_ERROR) {
                synchronized(onReadyListeners) {
                    field = value
                    onReadyListeners.forEach { listener ->
                        listener(state == VideoSourceState.STATE_INITIALIZED)
                    }
                }
            } else {
                field = value
            }
        }

    fun whenReady(action: (Boolean) -> Unit): Boolean =
        if (state == VideoSourceState.STATE_CREATED || state == VideoSourceState.STATE_INITIALIZING) {
            onReadyListeners += action
            false
        } else {
            action(state == VideoSourceState.STATE_INITIALIZED)
            true
        }

    suspend fun fetchMediaData() = withContext(Dispatchers.IO) {
        state = VideoSourceState.STATE_INITIALIZING
        val getMovies = moviesRepository.getMovies()
        movies = getMovies.map { movie ->
            MediaMetadataCompat.Builder()
                .putString(METADATA_KEY_TITLE, movie.title)
                .putString(METADATA_KEY_DISPLAY_TITLE, movie.title)
                .putString(METADATA_KEY_MEDIA_URI, movie.trailer)
                .putString(METADATA_KEY_MEDIA_ID, movie.id.toString()) // ?
                .build()
        }
        state = VideoSourceState.STATE_INITIALIZED
    }

    fun asMediaSource(dataSourceFactory: DefaultDataSource.Factory): ConcatenatingMediaSource {
        val concatenatingMediaSource = ConcatenatingMediaSource()
        movies.forEach { movie ->
            val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(MediaItem.fromUri(movie.getString(METADATA_KEY_MEDIA_URI)))
            concatenatingMediaSource.addMediaSource(mediaSource)
        }
        return concatenatingMediaSource
    }

    fun asMediaItems() = movies.map { movie ->
        val description = MediaDescriptionCompat.Builder()
            .setMediaUri(movie.getString(METADATA_KEY_MEDIA_URI).toUri())
            .setTitle(movie.description.title)
            .setMediaId(movie.description.mediaId)
            .build()
        MediaBrowserCompat.MediaItem(description, FLAG_PLAYABLE)
    }.toMutableList()
}

enum class VideoSourceState {
    STATE_CREATED,
    STATE_INITIALIZING,
    STATE_INITIALIZED,
    STATE_ERROR
}
