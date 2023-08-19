package com.ningi.poc.ui.composables.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import com.ningi.poc.classes.Document

@Composable
fun DocumentList(
    documents: List<Document>,
    openDocument: (Document) -> Unit,
    signDocument: (Document) -> Unit,
) {

    LazyColumn {
        items(items = documents) {
            Column {
                DocumentSummary(
                    document = it,
                    openDocument = openDocument,
                    signDocument = signDocument
                )
                Divider()
            }
        }
    }
}