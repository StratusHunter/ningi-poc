package com.ningi.poc.ui.composables.elements

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ningi.poc.enums.DocumentTab

@Composable
fun DocumentTabs(
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