package com.ningi.poc.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ningi.poc.DI
import com.ningi.poc.classes.Document
import com.ningi.poc.classes.DocumentsScreenState
import com.ningi.poc.enums.DocumentTab

class DocumentsViewModel(
    documents: List<Document> = DI.documentRepository.allDocuments()
) : ViewModel() {

    var state by mutableStateOf(
        DocumentsScreenState(
            selectedTab = DocumentTab.MyDocuments,
            tabs = DocumentTab.values().toList(),
            allDocuments = documents,
        )
    )
        private set

    fun selectTab(tab: DocumentTab) {
        state = state.copy(selectedTab = tab)
    }
}