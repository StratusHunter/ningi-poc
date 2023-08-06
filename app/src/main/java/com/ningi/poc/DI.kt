package com.ningi.poc

import com.ningi.poc.repository.document.DocumentRepository
import com.ningi.poc.repository.document.MockDocumentRepository

//Just making something basic for DI right now.
object DI {

    val documentRepository: DocumentRepository = MockDocumentRepository()
}