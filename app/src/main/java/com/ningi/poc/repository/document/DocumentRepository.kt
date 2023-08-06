package com.ningi.poc.repository.document

import com.ningi.poc.classes.Document

interface DocumentRepository {
    fun allDocuments(): List<Document>
    fun getDocument(id: String): Document?
}