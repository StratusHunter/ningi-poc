package com.ningi.poc.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ningi.poc.DI
import com.ningi.poc.classes.Document
import com.ningi.poc.repository.document.DocumentRepository

class DocumentViewerViewModel(
    documentId: String,
    documentRepository: DocumentRepository = DI.documentRepository
) : ViewModel() {

    var document = documentRepository.getDocument(documentId)

    var showSignDocumentView by mutableStateOf(false)
    fun signDocument(document: Document) {
        showSignDocumentView = true
    }
}