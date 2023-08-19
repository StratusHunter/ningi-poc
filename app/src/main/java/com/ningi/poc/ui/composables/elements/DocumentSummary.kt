package com.ningi.poc.ui.composables.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ningi.poc.R
import com.ningi.poc.classes.Document

@Composable
fun DocumentSummary(
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
                SignButton { signDocument(document) }
            }
        }
        Icon(Icons.Filled.KeyboardArrowRight, contentDescription = stringResource(R.string.open_document))
    }
}