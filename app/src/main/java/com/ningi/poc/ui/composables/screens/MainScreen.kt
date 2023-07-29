package com.ningi.poc.ui.composables.screens

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.ningi.poc.enums.BottomNavigationBarItem
import com.ningi.poc.ui.composables.elements.BottomNavigationBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    selectedItem: BottomNavigationBarItem,
    items: Array<BottomNavigationBarItem>,
    onItemClick: (BottomNavigationBarItem) -> Unit
) {
    Scaffold(bottomBar = {
        BottomNavigationBar(
            selectedItem = selectedItem,
            items = items,
            onItemClick = onItemClick
        )
    }) {
        when (selectedItem) {
            BottomNavigationBarItem.Account -> AccountScreen()
            BottomNavigationBarItem.Documents -> DocumentsScreen()
            BottomNavigationBarItem.Chat -> ChatScreen()
            BottomNavigationBarItem.Settings -> SettingsScreen()
        }
    }
}