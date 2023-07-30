package com.ningi.poc.viewmodels

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import com.ningi.poc.classes.notifications.NotificationHelper

class SettingsViewModel : ViewModel() {

    private val notificationHelper = NotificationHelper()

    @SuppressLint("ComposableNaming")
    @Composable
    fun setupPermissionResponseListener(context: Context) {
        notificationHelper.setupPermissionResponseListener {
            performNotificationRequest(context)
        }
    }

    fun performNotificationRequest(context: Context) {
        notificationHelper.requestNotificationPermissionOrPerform(context = context)
    }
}