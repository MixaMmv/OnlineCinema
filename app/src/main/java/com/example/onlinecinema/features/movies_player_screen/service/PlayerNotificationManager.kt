package com.example.onlinecinema.features.movies_player_screen.service

import android.app.PendingIntent
import android.content.Context
import android.graphics.Bitmap
import android.support.v4.media.session.MediaControllerCompat
import android.support.v4.media.session.MediaSessionCompat
import com.example.onlinecinema.R
import com.example.onlinecinema.base.Constants.VIDEO_NOTIFICATION_ID
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerNotificationManager

class PlayerNotificationManager(
    context: Context,
    sessionToken: MediaSessionCompat.Token,
    notificationListener: PlayerNotificationManager.NotificationListener,
    private val newPlayerCallback: () -> Unit
) {
    private val playerNotificationManager: PlayerNotificationManager
    private val channelId = context.resources.getString(R.string.video_channel_id)


    init {
        val mediaController = MediaControllerCompat(context, sessionToken)

        playerNotificationManager = PlayerNotificationManager.Builder(
            context, VIDEO_NOTIFICATION_ID, channelId
        )
            .setChannelNameResourceId(R.string.channel_name)
            .setSmallIconResourceId(R.drawable.ic_baseline_play_arrow_24)
            .setMediaDescriptionAdapter(DescriptionAdapter(mediaController))
            .setNotificationListener(notificationListener)
            .build().apply {
                setMediaSessionToken(sessionToken)
            }
    }

    fun showNotification(player: Player) {
        playerNotificationManager.setPlayer(player)
    }

    private inner class DescriptionAdapter(private val mediaController: MediaControllerCompat) :
        PlayerNotificationManager.MediaDescriptionAdapter {

        override fun getCurrentContentTitle(player: Player): CharSequence {
            newPlayerCallback()
            return mediaController.metadata.description.title ?: ""
        }

        override fun createCurrentContentIntent(player: Player): PendingIntent? {
            return mediaController.sessionActivity

        }

        override fun getCurrentContentText(player: Player): CharSequence? {
            return mediaController.metadata.description.subtitle
        }

        override fun getCurrentLargeIcon(
            player: Player,
            callback: PlayerNotificationManager.BitmapCallback
        ): Bitmap? {
            return null
        }


    }


}