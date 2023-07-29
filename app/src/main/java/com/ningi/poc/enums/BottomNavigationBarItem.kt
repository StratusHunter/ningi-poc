package com.ningi.poc.enums

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.ningi.poc.R


enum class BottomNavigationBarItem(
    @StringRes val text: Int,
    val icon: ImageVector,
) {
    Account(text = R.string.account, icon = Icons.Filled.Person),
    Documents(text = R.string.documents, icon = Icons.Filled.List),
    Chat(text = R.string.chat, icon = Icons.Filled.Email),
    Settings(text = R.string.settings, icon = Icons.Filled.Settings),
}
