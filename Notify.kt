package com.example.pairtrader.alerts

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.pairtrader.R

object Notify {
    fun send(context: Context, title: String, text: String) {
        val builder = NotificationCompat.Builder(context, "alerts")
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(title)
            .setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
        with(NotificationManagerCompat.from(context)) {
            notify((0..9999).random(), builder.build())
        }
    }
}
