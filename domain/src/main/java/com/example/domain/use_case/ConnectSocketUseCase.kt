package com.example.domain.use_case

import com.example.domain.model.ChatMessage
import com.example.domain.repo.ChatRepo
import javax.inject.Inject

class ConnectSocketUseCase @Inject constructor(private val chatRepo: ChatRepo) {
    operator fun invoke(onReceivedMessage:(msg: ChatMessage)->Unit) {
        chatRepo.connect(onReceivedMessage)
    }
}