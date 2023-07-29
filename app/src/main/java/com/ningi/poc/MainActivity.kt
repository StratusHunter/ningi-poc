package com.ningi.poc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ningi.poc.enums.BottomNavigationBarItem
import com.ningi.poc.ui.composables.screens.MainScreen
import com.ningi.poc.ui.theme.NingiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen()

        super.onCreate(savedInstanceState)
        setContent {
            NingiTheme {

                val selectedItem = remember { mutableStateOf(BottomNavigationBarItem.Account) }

                MainScreen(
                    selectedItem = selectedItem.value,
                    items = BottomNavigationBarItem.values()
                ) { item -> selectedItem.value = item }
            }
        }
    }
}

