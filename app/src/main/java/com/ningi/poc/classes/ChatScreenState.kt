package com.ningi.poc.classes

import com.ningi.poc.sealed.ChatItem

data class ChatScreenState(
    val chatList: List<ChatItem>,
)
