package com.example.onlinecinema.features.movies_player_screen.service

import android.app.PendingIntent
import android.content.Intent
import android.os.Binder
import android.os.Bundle
import android.os.IBinder
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaSessionCompat
import androidx.media.MediaBrowserServiceCompat
import com.example.onlinecinema.base.Constants.MEDIA_ROOT_ID
import com.example.onlinecinema.base.Constants.NETWORK_ERROR
import com.example.onlinecinema.base.Constants.VIDEO_FILE
import com.example.onlinecinema.base.Constants.VIDEO_SERVICE_TAG
import com.example.onlinecinema.domain.model.MoviesDomainModel
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector
import kotlinx.coroutines.*
import org.koin.android.ext.android.inject


class PlayerService : MediaBrowserServiceCompat() {

    companion object {
        var currentVideoDuration = 0L
            private set
    }

    val exoPlayer by inject<ExoPlayer>()

    private val videoSource by inject<PlayerSource>()

    lateinit var playerNotificationManager: PlayerNotificationManager

    private val serviceJob = Job()
    private val serviceScope = CoroutineScope(Dispatchers.Main + serviceJob)

    private lateinit var mediaSession: MediaSessionCompat
    private lateinit var mediaSessionConnector: MediaSessionConnector

    var isForegroundService = false

    private var currentPlayingVideo: MediaMetadataCompat? = null
    private lateinit var playerListener: PlayerListener
    private var isPlayerInitialized = false

    override fun onCreate() {
        super.onCreate()

        serviceScope.launch {
            videoSource.fetchMediaData()
        }

        val activityIntent = packageManager.getLaunchIntentForPackage(packageName)?.let { intent ->
            PendingIntent.getActivity(
                this, 0, intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )
        }

        mediaSession = MediaSessionCompat(this, VIDEO_SERVICE_TAG).apply {
            setSessionActivity(activityIntent)
            isActive = true
        }

        sessionToken = mediaSession.sessionToken

        playerNotificationManager = PlayerNotificationManager(
            this,
            mediaSession.sessionToken,
            PlayerNotificationsListener(this)
        ) {
            currentVideoDuration = exoPlayer.duration
        }

        val playerPlaybackPreparer = PlayerPlaybackPreparer(videoSource) {
            currentPlayingVideo = it
        }


        mediaSessionConnector = MediaSessionConnector(mediaSession).apply {
            setPlaybackPreparer(playerPlaybackPreparer)
            setPlayer(exoPlayer)
        }

        playerListener = PlayerListener(this)

        exoPlayer.addListener(playerListener)

        playerNotificationManager.showNotification(exoPlayer)

    }


    override fun onBind(intent: Intent?): IBinder {
        intent?.getParcelableExtra<MoviesDomainModel>(VIDEO_FILE)?.let {
            preparePlayer(it, true)
        }
        return PlayerServiceBinder()
    }

    override fun onUnbind(intent: Intent?): Boolean {
        stopSelf()

        return super.onUnbind(intent)
    }

    private fun preparePlayer(movie: MoviesDomainModel, playNow: Boolean) {
        exoPlayer.apply {
            setMediaItem(MediaItem.fromUri(movie.trailer.toString()))
            prepare()
            playWhenReady = playNow
        }
    }

    override fun onGetRoot(
        clientPackageName: String,
        clientUid: Int,
        rootHints: Bundle?
    ): BrowserRoot? {
        return BrowserRoot(MEDIA_ROOT_ID, null)
    }

    override fun onLoadChildren(
        parentId: String,
        result: Result<MutableList<MediaBrowserCompat.MediaItem>>
    ) {
        when (parentId) {
            MEDIA_ROOT_ID -> {
                val resultSent = videoSource.whenReady { isInitialized ->
                    if (isInitialized) {
                        result.sendResult(videoSource.asMediaItems())
                        if (!isPlayerInitialized && videoSource.movies.isNotEmpty()) {
                            isPlayerInitialized = true
                        }
                    } else {
                        mediaSession.sendSessionEvent(NETWORK_ERROR, null)
                        result.sendResult(null)
                    }
                }
                if (!resultSent) {
                    result.detach()
                }
            }
        }
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        exoPlayer.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
        exoPlayer.removeListener(playerListener)
        exoPlayer.release()
    }

    inner class PlayerServiceBinder : Binder() {
        fun getService() = this@PlayerService
    }

}