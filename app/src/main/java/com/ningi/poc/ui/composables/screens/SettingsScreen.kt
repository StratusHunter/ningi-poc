package com.ningi.poc.ui.composables.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ningi.poc.R
import com.ningi.poc.classes.NotificationHelper

@Composable
fun SettingsScreen() {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { }
    val notificationHelper = NotificationHelper()

    LazyColumn() {
        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = {

                        notificationHelper.requestNotificationPermissionOrPerform(
                            context = context,
                            launcher = launcher,
                            notificationId = 1234,
                        )
                    })
                    .padding(16.dp),
                text = stringResource(id = R.string.basic_notification)
            )
            Divider()
        }
    }
}