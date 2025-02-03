package com.example.domain.repo

import com.example.domain.model.ChatMessage

interface ChatRepo {
    fun getCurrentUserId(): String
    fun generateUserId(): String
    fun connect(onReceivedMessage:(msg: ChatMessage)->Unit)
    fun sendMessage(message: String)
    fun disconnect()
}