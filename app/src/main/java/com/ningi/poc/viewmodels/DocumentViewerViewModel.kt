package com.ningi.poc.viewmodels

import androidx.lifecycle.ViewModel
import com.ningi.poc.DI
import com.ningi.poc.repository.document.DocumentRepository

class DocumentViewerViewModel(
    documentId: String,
    documentRepository: DocumentRepository = DI.documentRepository
) : ViewModel() {

    var document = documentRepository.getDocument(documentId)
}