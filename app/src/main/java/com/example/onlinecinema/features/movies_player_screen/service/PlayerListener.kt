package com.example.onlinecinema.features.movies_player_screen.service

import com.google.android.exoplayer2.Player

class PlayerListener (
    private val playerService: PlayerService
) : Player.Listener {

    override fun onPlaybackStateChanged(playbackState: Int) {
        super.onPlaybackStateChanged(playbackState)
        if (playbackState == Player.STATE_READY)
            playerService.stopForeground(false)
    }

    override fun onPlayWhenReadyChanged(playWhenReady: Boolean, reason: Int) {
        super.onPlayWhenReadyChanged(playWhenReady, reason)
        if (!playWhenReady)
            playerService.stopForeground(false)
    }
}