package com.example.pushnotificationcustomer

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
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
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        val intent = Intent(this,Registration::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val manager= getSystemService(Context.NOTIFICATION_SERVICE)
        createNotificationChannel(manager as NotificationManager)
        val inte1 = PendingIntent.getActivities(this,0,
        arrayOf(intent),PendingIntent.FLAG_IMMUTABLE)
        val notification = NotificationCompat.Builder(this,channelID)
            .setContentTitle(message.data["title"])
            .setContentTitle(message.data["message"])
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setAutoCancel(true)
            .setContentIntent(inte1)
            .build()
        manager.notify(Random.nextInt(),notification)

    }

    private fun createNotificationChannel(manager: NotificationManager){
        val channel = NotificationChannel(channelID,"Tushar Gupta",
        NotificationManager.IMPORTANCE_HIGH)
        channel.description="New chat"
        channel.enableLights(true)
        manager.createNotificationChannel(channel)

    }
}