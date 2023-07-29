package com.ningi.poc.ui.composables.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.ningi.poc.R
import com.ningi.poc.enums.BottomNavigationBarItem
import com.ningi.poc.ui.composables.elements.BottomNavigationBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    selectedItem: BottomNavigationBarItem,
    items: Array<BottomNavigationBarItem>,
    onItemClick: (BottomNavigationBarItem) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_topbar_foreground),
                            contentDescription = null,
                            modifier = Modifier.size(30.dp)
                        )
                        Text(text = stringResource(id = selectedItem.text))
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
                )
            )
        },
        bottomBar = {
            BottomNavigationBar(
                selectedItem = selectedItem,
                items = items,
                onItemClick = onItemClick
            )
        }) {
        Surface(modifier = Modifier.padding(it)) {
            when (selectedItem) {
                BottomNavigationBarItem.Account -> AccountScreen()
                BottomNavigationBarItem.Documents -> DocumentsScreen()
                BottomNavigationBarItem.Chat -> ChatScreen()
                BottomNavigationBarItem.Settings -> SettingsScreen()
            }
        }
    }
}