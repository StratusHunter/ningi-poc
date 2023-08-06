package com.ningi.poc.sealed

import com.ningi.poc.enums.MessageOwner

sealed class ChatItem(val message: String, val owner: MessageOwner) {

    class Typing(owner: MessageOwner) : ChatItem(message = "...", owner = owner)
    class Message(message: String, owner: MessageOwner) : ChatItem(message = message, owner = owner)
}
