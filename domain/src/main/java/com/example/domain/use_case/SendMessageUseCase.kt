package com.example.domain.use_case

import com.example.domain.model.ChatMessage
import com.example.domain.repo.ChatRepo
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(private val chatRepo: ChatRepo) {
    operator fun invoke(message: String,onReceivedMessage:(msg: ChatMessage)->Unit) {
        chatRepo.sendMessage(message,onReceivedMessage)
    }
}