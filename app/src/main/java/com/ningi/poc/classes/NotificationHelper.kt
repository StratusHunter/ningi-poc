package com.ningi.poc.classes

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.ningi.poc.R

class NotificationHelper {

    @SuppressLint("MissingPermission")
    fun requestNotificationPermissionOrPerform(
        context: Context,
        launcher: ManagedActivityResultLauncher<String, Boolean>,
        notificationId: Int,
        channelId: String = "TEST_NOTIFICATIONS",
    ) {
        val notificationManager = NotificationManagerCompat.from(context)
        setupChannel(channelId = channelId, manager = notificationManager)

        requestPermissionOrPerform(
            context = context,
            launcher = launcher
        ) {
            notificationManager.notify(
                notificationId,
                buildNotification(
                    context = context,
                    channelId = channelId
                )
            )
        }

    }

    private fun requestPermissionOrPerform(
        context: Context,
        launcher: ManagedActivityResultLauncher<String, Boolean>,
        action: () -> Unit
    ) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            action()
        } else {
            when (PackageManager.PERMISSION_GRANTED) {
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) -> {
                    action()
                }

                else -> {
                    // Asking for permission
                    launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }
        }
    }

    private fun buildNotification(context: Context, channelId: String) = NotificationCompat
        .Builder(context, channelId)
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setContentTitle("Test Notification")
        .setContentText("Test Content")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .build()

    private fun setupChannel(channelId: String, manager: NotificationManagerCompat) {

        manager.createNotificationChannel(
            NotificationChannel(
                channelId,
                "Test Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
        )
    }
}