package com.ningi.poc.ui.composables.screens

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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ningi.poc.R
import com.ningi.poc.viewmodels.SettingsViewModel

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = viewModel()
) {
    val context = LocalContext.current
    viewModel.setupPermissionResponseListener(context)

    LazyColumn() {
        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = { viewModel.performNotificationRequest(context) })
                    .padding(16.dp),
                text = stringResource(id = R.string.basic_notification)
            )
            Divider()
        }
    }
}