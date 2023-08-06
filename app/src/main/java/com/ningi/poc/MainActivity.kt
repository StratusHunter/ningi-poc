package com.ningi.poc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.ningi.poc.enums.Route
import com.ningi.poc.ui.composables.screens.MainScreen
import com.ningi.poc.ui.theme.NingiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen()

        super.onCreate(savedInstanceState)
        setContent {
            NingiTheme {
                val navController = rememberNavController()

                MainScreen(
                    navController = navController,
                    startDestination = Route.Profile,
                    navigationBarItems = listOf(Route.Profile, Route.Documents, Route.Chat, Route.Settings),
                    allRoutes = Route.values().toList(),
                    onItemClick = {
                        navController.navigate(it.identifier) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}

