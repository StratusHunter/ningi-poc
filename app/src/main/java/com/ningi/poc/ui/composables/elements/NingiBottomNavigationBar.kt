package com.ningi.poc.ui.composables.elements

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.ningi.poc.enums.Route

@Composable
fun NingiBottomNavigationBar(
    selectedItem: Route,
    items: List<Route>,
    onItemClick: (Route) -> Unit
) {
    NavigationBar {
        items.forEach { item ->
                NavigationBarItem(
                    colors = NavigationBarItemDefaults.colors(),
                    icon = {
                        item.tabIcon?.let {
                            Icon(it, contentDescription = null)
                        }
                    },
                    label = {
                        item.title()?.let {
                            Text(it)
                        }
                    },
                    selected = selectedItem == item,
                    onClick = { onItemClick(item) }
                )
        }
    }
}