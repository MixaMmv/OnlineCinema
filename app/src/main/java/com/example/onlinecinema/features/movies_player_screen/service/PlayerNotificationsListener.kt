package com.example.onlinecinema.features.movies_player_screen.service

import android.app.Notification
import android.content.Intent
import androidx.core.content.ContextCompat
import com.example.onlinecinema.base.Constants.VIDEO_NOTIFICATION_ID
import com.google.android.exoplayer2.ui.PlayerNotificationManager

class PlayerNotificationsListener(
    private val playerService: PlayerService
) : PlayerNotificationManager.NotificationListener {

    override fun onNotificationCancelled(notificationId: Int, dismissedByUser: Boolean) {
        super.onNotificationCancelled(notificationId, dismissedByUser)
        playerService.apply {
            stopForeground(true)
            isForegroundService = false
            stopSelf()
        }
    }

    override fun onNotificationPosted(
        notificationId: Int,
        notification: Notification,
        ongoing: Boolean
    ) {
        super.onNotificationPosted(notificationId, notification, ongoing)
        playerService.apply {
            if (ongoing && !isForegroundService) {
                ContextCompat.startForegroundService(
                    this,
                    Intent(applicationContext, this::class.java)
                )
                startForeground(VIDEO_NOTIFICATION_ID, notification)
                isForegroundService = true
            }
        }
    }
}