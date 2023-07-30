package com.ningi.poc.classes.notifications

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.ningi.poc.MainActivity
import com.ningi.poc.R

class NotificationHelper {

    private var launcher: ManagedActivityResultLauncher<String, Boolean>? = null

    @SuppressLint("ComposableNaming")
    @Composable
    fun setupPermissionResponseListener(onGranted: () -> Unit) {
        launcher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) {
                onGranted()
            }
        }
    }

    fun requestNotificationPermissionOrPerform(context: Context) {
        val launcher = launcher ?: run {
            Log.e(javaClass.simpleName, "Permission launcher has not been initialised")
            return@requestNotificationPermissionOrPerform
        }

        requestPermissionOrPerform(
            context = context,
            launcher = launcher
        ) {
            showNotification(context)
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

    @SuppressLint("MissingPermission")
    private fun showNotification(
        context: Context,
        notificationId: Int = 1234,
        channelId: String = "NINGI_DOCUMENT_NOTIFICATIONS",
        channelName: Int = R.string.channel_name,
        notificationTitle: Int = R.string.notification_title,
        notificationMessage: Int = R.string.notification_message
    ) {

        val notificationManager = NotificationManagerCompat.from(context)
        setupChannel(channelId = channelId, channelName = channelName, manager = notificationManager, context = context)

        val notifyIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val notifyPendingIntent = PendingIntent.getActivity(
            context, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        notificationManager.notify(
            notificationId,
            buildNotification(
                context = context,
                title = notificationTitle,
                message = notificationMessage,
                channelId = channelId,
                pendingIntent = notifyPendingIntent,
            )
        )
    }

    private fun buildNotification(context: Context, @StringRes title: Int, @StringRes message: Int, channelId: String, pendingIntent: PendingIntent) =
        NotificationCompat
            .Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(context.getString(title))
            .setContentText(context.getString(message))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .build()

    private fun setupChannel(context: Context, channelId: String, @StringRes channelName: Int, manager: NotificationManagerCompat) {

        manager.createNotificationChannel(
            NotificationChannel(
                channelId,
                context.getString(R.string.channel_name),
                NotificationManager.IMPORTANCE_HIGH
            )
        )
    }
}