package com.ningi.poc.ui.composables.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ningi.poc.enums.BottomNavigationBarItem
import com.ningi.poc.ui.composables.elements.NingiBottomNavigationBar
import com.ningi.poc.ui.composables.elements.NingiTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    selectedItem: BottomNavigationBarItem,
    items: Array<BottomNavigationBarItem>,
    onItemClick: (BottomNavigationBarItem) -> Unit
) {
    Scaffold(
        topBar = {
            NingiTopAppBar(title = stringResource(id = selectedItem.text))
        },
        bottomBar = {
            NingiBottomNavigationBar(
                selectedItem = selectedItem,
                items = items,
                onItemClick = onItemClick
            )
        }) {
        Surface(modifier = Modifier.padding(it)) {
            when (selectedItem) {
                BottomNavigationBarItem.Account -> ProfileScreen()
                BottomNavigationBarItem.Documents -> DocumentsScreen()
                BottomNavigationBarItem.Chat -> ChatScreen()
                BottomNavigationBarItem.Settings -> SettingsScreen()
            }
        }
    }
}