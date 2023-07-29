package com.ningi.poc.ui.composables.elements

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.ningi.poc.enums.BottomNavigationBarItem

@Composable
fun BottomNavigationBar(
    selectedItem: BottomNavigationBarItem,
    items: Array<BottomNavigationBarItem>,
    onItemClick: (BottomNavigationBarItem) -> Unit
) {
    NavigationBar() {
        items.forEach { item ->
            val text = stringResource(id = item.text)
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(),
                icon = { Icon(item.icon, contentDescription = text) },
                label = { Text(text) },
                selected = selectedItem == item,
                onClick = { onItemClick(item) }
            )
        }
    }
}