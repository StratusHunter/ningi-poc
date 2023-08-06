package com.ningi.poc.ui.composables.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ningi.poc.R
import com.ningi.poc.classes.Document
import com.ningi.poc.enums.DocumentTab
import com.ningi.poc.enums.navigateToDocumentViewer
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

@Composable
private fun DocumentTabs(
    selectedTab: DocumentTab,
    allTabs: List<DocumentTab>,
    onClick: (DocumentTab) -> Unit
) {
    TabRow(
        selectedTabIndex = selectedTab.ordinal
    ) {
        allTabs.forEach {

            val isSelected = selectedTab == it

            Tab(
                selected = isSelected,
                onClick = { onClick(it) }
            ) {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = stringResource(id = it.text),
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                )
            }
        }
    }
}

@Composable
private fun DocumentList(
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

@Composable
private fun DocumentSummary(
    document: Document,
    openDocument: (Document) -> Unit,
    signDocument: (Document) -> Unit,
) {

    Row(
        modifier = Modifier
            .clickable { openDocument(document) }
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = document.name,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = document.description
            )
            Text(
                text = "[${document.fileType}]"
            )
            if (document.needsSigning) {
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { signDocument(document) }) {

                    val needsSigning = stringResource(id = R.string.needs_signing)
                    Icon(Icons.Filled.Edit, contentDescription = needsSigning)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = needsSigning)
                }
            }
        }
        Icon(Icons.Filled.KeyboardArrowRight, contentDescription = stringResource(R.string.open_document))
    }
}