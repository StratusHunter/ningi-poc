package com.ningi.poc.enums

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.ningi.poc.R
import com.ningi.poc.ui.composables.screens.ChatScreen
import com.ningi.poc.ui.composables.screens.DocumentViewerScreen
import com.ningi.poc.ui.composables.screens.DocumentsScreen
import com.ningi.poc.ui.composables.screens.ProfileScreen
import com.ningi.poc.ui.composables.screens.SettingsScreen

private const val DOCUMENTVIEWER_DOCUMENTID_ARG = "documentId"

fun NavController.navigateToProfile() {
    navigate(Route.Profile.identifier)
}

fun NavController.navigateToDocuments() {
    navigate(Route.Documents.identifier)
}

fun NavController.navigateToChat() {
    navigate(Route.Chat.identifier)
}

fun NavController.navigateToSettings() {
    navigate(Route.Settings.identifier)
}

fun NavController.navigateToDocumentViewer(documentId: String) {
    navigate(Route.DocumentViewer.identifier.replace("{$DOCUMENTVIEWER_DOCUMENTID_ARG}", documentId))
}

enum class Route(
    val title: @Composable () -> String? = { null }, //TODO: Mulling on how to pull out a custom title based on arguments
    val tabIcon: ImageVector? = null,
    val identifier: String,
    val arguments: List<NamedNavArgument> = emptyList(),
    val screen: @Composable (NavController, NavBackStackEntry) -> Unit,
) {
    Profile(
        title = { stringResource(R.string.account) },
        tabIcon = Icons.Filled.Person,
        identifier = "ProfileScreen",
        screen = { _, _ -> ProfileScreen() }),
    Documents(
        title = { stringResource(R.string.documents) },
        tabIcon = Icons.Filled.List,
        identifier = "DocumentsScreen",
        screen = { controller, _ -> DocumentsScreen(controller) }),
    Chat(
        title = { stringResource(R.string.chat) },
        tabIcon = Icons.Filled.Email,
        identifier = "ChatScreen",
        screen = { _, _ -> ChatScreen() }),
    Settings(
        title = { stringResource(R.string.settings) },
        tabIcon = Icons.Filled.Settings,
        identifier = "SettingsScreen",
        screen = { _, _ -> SettingsScreen() }),
    DocumentViewer(
        title = { stringResource(R.string.view_document) },
        identifier = "DocumentViewer/{$DOCUMENTVIEWER_DOCUMENTID_ARG}",
        arguments = listOf(
            navArgument(
                name = DOCUMENTVIEWER_DOCUMENTID_ARG,
                builder = { type = NavType.StringType })
        ),
        screen = { controller, backStackEntry ->

            val documentId = backStackEntry.arguments!!.getString(DOCUMENTVIEWER_DOCUMENTID_ARG)!!

            DocumentViewerScreen(
                documentId = documentId
            )
        });
}