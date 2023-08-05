package com.ningi.poc.ui.composables.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ningi.poc.ui.composables.elements.ChatBubble
import com.ningi.poc.viewmodels.ChatViewModel
import com.ningi.poc.viewmodels.MessageOwner

@Composable
fun ChatScreen(
    viewModel: ChatViewModel = viewModel()
) {
    val context = LocalContext.current
    LaunchedEffect(true) {
        viewModel.startChatDemo(context)
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(items = viewModel.state.chatList) {

            val backgroundColor = when (it.owner) {
                MessageOwner.Send -> MaterialTheme.colorScheme.secondary
                MessageOwner.Response -> MaterialTheme.colorScheme.tertiary
            }

            val textColor = when (it.owner) {
                MessageOwner.Send -> MaterialTheme.colorScheme.onSecondary
                MessageOwner.Response -> MaterialTheme.colorScheme.onTertiary
            }

            val arrangement = when (it.owner) {
                MessageOwner.Send -> Arrangement.Start
                MessageOwner.Response -> Arrangement.End
            }

            val alignment = when (it.owner) {
                MessageOwner.Send -> Alignment.Start
                MessageOwner.Response -> Alignment.End
            }

            ChatBubble(
                arrangement = arrangement,
                alignment = alignment,
                backgroundColor = backgroundColor,
                message = it.message,
                textColor = textColor
            )
        }
    }
}