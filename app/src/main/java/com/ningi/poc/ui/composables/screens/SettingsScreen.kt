package com.ningi.poc.ui.composables.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ningi.poc.R
import com.ningi.poc.ui.composables.elements.SettingItem
import com.ningi.poc.viewmodels.SettingsViewModel

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = viewModel()
) {
    val context = LocalContext.current
    viewModel.setupPermissionResponseListener(context)

    LazyColumn {
        item {
            SettingItem(
                text = stringResource(id = R.string.basic_notification),
                onClick = { viewModel.performNotificationRequest(context) })
        }
    }
}