package com.ningi.poc.ui.composables.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ningi.poc.enums.Route
import com.ningi.poc.ui.composables.elements.NingiBottomNavigationBar
import com.ningi.poc.ui.composables.elements.NingiTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavHostController,
    startDestination: Route,
    navigationBarItems: List<Route>,
    allRoutes: List<Route>,
    onItemClick: (Route) -> Unit
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestinationHierarchy = navBackStackEntry?.destination?.hierarchy

    val currentTab = navigationBarItems.firstOrNull { item -> currentDestinationHierarchy?.any { it.route == item.identifier } == true }
    val currentScreen = allRoutes.firstOrNull { item -> currentDestinationHierarchy?.any { it.route == item.identifier } == true }

    Scaffold(
        topBar = {
            currentScreen?.title?.invoke()?.let {
                NingiTopAppBar(title = it)
            }
        },
        bottomBar = {
            currentTab?.let {
                NingiBottomNavigationBar(
                    selectedItem = it,
                    items = navigationBarItems,
                    onItemClick = onItemClick
                )
            }
        }
    ) { innerPadding ->

        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = startDestination.identifier
        ) {

            Route.values().forEach { route ->
                composable(
                    route = route.identifier,
                    arguments = route.arguments
                ) {
                    route.screen(navController, it)
                }
            }
        }
    }
}