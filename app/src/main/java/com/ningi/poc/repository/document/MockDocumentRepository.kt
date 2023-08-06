package com.ningi.poc.repository.document

import com.ningi.poc.classes.Document

class MockDocumentRepository : DocumentRepository {

    private val documents = listOf(
        Document(
            name = "Super important",
            description = "This is a very important file for you",
            fileType = "PDF",
            needsSigning = true,
        ),
        Document(
            name = "Super important 2",
            description = "This is a very important file for you",
            fileType = "PDF",
            needsSigning = false,
        ),
        Document(
            name = "Super important 3",
            description = "This is a very important file for you",
            fileType = "PDF",
            needsSigning = true,
        ),
        Document(
            name = "Super important 4",
            description = "This is a very important file for you",
            fileType = "PDF",
            needsSigning = false,
        ),
        Document(
            name = "Super important 5",
            description = "This is a very important file for you",
            fileType = "PDF",
            needsSigning = true,
        )
    )

    override fun allDocuments() = documents

    override fun getDocument(id: String) = documents.firstOrNull { it.id == id }
}