package com.example.presentation

import com.example.domain.model.ChatMessage

data class ChatState(
    val messages: List<ChatMessage> = emptyList(),
    val message: String = ""
)