package com.ningi.poc.viewmodels

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ningi.poc.R
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

enum class MessageOwner {
    Send, Response
}

sealed class ChatItem(val message: String, val owner: MessageOwner) {

    class Typing(owner: MessageOwner) : ChatItem(message = "...", owner = owner)
    class Message(message: String, owner: MessageOwner) : ChatItem(message = message, owner = owner)
}

data class ChatScreenState(
    val chatList: List<ChatItem>,
)

class ChatViewModel : ViewModel() {
    var state by mutableStateOf(
        ChatScreenState(
            chatList = emptyList()
        )
    )
        private set

    private var chatJob = Job()
    fun startChatDemo(context: Context) {

        state = state.copy(chatList = emptyList())

        chatJob.cancel()
        chatJob = Job()

        viewModelScope.launch(chatJob) {

            showMessage(
                owner = MessageOwner.Response,
                message = context.getString(R.string.chat_1)
            )
            showMessage(
                owner = MessageOwner.Send,
                message = context.getString(R.string.chat_2)
            )
            showMessage(
                owner = MessageOwner.Response,
                message = context.getString(R.string.chat_3)
            )
        }
    }

    private suspend fun showMessage(owner: MessageOwner, message: String) {
        state = state.copy(chatList = state.chatList.plus(ChatItem.Typing(owner = owner)))
        delay(2000L)
        state = state.copy(
            chatList = state.chatList.filterIsInstance<ChatItem.Message>().plus(
                ChatItem.Message(
                    message = message,
                    owner = owner
                )
            )
        )
        delay(1000L)
    }
}