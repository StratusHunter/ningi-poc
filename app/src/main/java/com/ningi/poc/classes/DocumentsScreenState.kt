package com.ningi.poc.classes

import com.ningi.poc.enums.DocumentTab

data class DocumentsScreenState(
    val selectedTab: DocumentTab,
    val tabs: List<DocumentTab>,
    val allDocuments: List<Document>,
) {
    val documentsToSign: List<Document>
        get() = allDocuments.filter { it.needsSigning }
}
