package com.ningi.poc.ui.composables.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.ningi.poc.R

@Composable
fun SettingsScreen() {

    Column() {
        Text(text = stringResource(id = R.string.basic_notification))
        Text(text = stringResource(id = R.string.advanced_notification))
    }
}