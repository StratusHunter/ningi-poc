package com.ningi.poc.ui.composables.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ningi.poc.viewmodels.DocumentViewerViewModel

@Composable
fun DocumentViewerScreen(
    documentId: String,
    viewModel: DocumentViewerViewModel = viewModel {
        DocumentViewerViewModel(documentId = documentId)
    }
) {

}