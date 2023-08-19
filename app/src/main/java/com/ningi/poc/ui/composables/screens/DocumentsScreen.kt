package com.ningi.poc.ui.composables.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ningi.poc.enums.DocumentTab
import com.ningi.poc.enums.navigateToDocumentViewer
import com.ningi.poc.ui.composables.elements.DocumentList
import com.ningi.poc.ui.composables.elements.DocumentTabs
import com.ningi.poc.viewmodels.DocumentsViewModel

@Composable
fun DocumentsScreen(
    navController: NavController,
    viewModel: DocumentsViewModel = viewModel()
) {
    val state = viewModel.state
    val selectedTab = state.selectedTab

    Column {
        DocumentTabs(
            selectedTab = selectedTab,
            allTabs = viewModel.state.tabs,
            onClick = { viewModel.selectTab(tab = it) },
        )

        DocumentList(
            documents = when (selectedTab) {
                DocumentTab.MyDocuments -> state.allDocuments
                DocumentTab.ToSign -> state.documentsToSign
            },
            openDocument = {
                navController.navigateToDocumentViewer(documentId = it.id)
            },
            signDocument = {
                navController.navigateToDocumentViewer(documentId = it.id)
            }
        )
    }
}