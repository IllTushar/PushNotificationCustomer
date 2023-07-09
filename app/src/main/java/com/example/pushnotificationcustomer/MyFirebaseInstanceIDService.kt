package com.example.pushnotificationcustomer

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlin.random.Random


@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class MyFirebaseInstanceIDService : FirebaseMessagingService(){
    private val channelID="popupnotification"

    fun onNewToken(token: String?) {
        super.onNewToken(token!!)
        // Handle token refresh or other operations

    }
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        // Handle the received message
        if (remoteMessage.notification != null) {
            val title = remoteMessage.notification?.title
            val body = remoteMessage.notification?.body

            // Display the notification
            if (title != null && body != null) {
                showHeadsUpNotification(title, body)
            }
        }
    }

    private fun showHeadsUpNotification(title: String, body: String) {
        // Create a notification builder
        val notificationBuilder = NotificationCompat.Builder(this, "your_channel_id")
            .setContentTitle(title)
            .setContentText(body)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
        // Customize other notification settings as needed

        // Show the heads-up notification
        val notificationManager = NotificationManagerCompat.from(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        notificationManager.notify(123, notificationBuilder.build())
    }
}