package com.example.domain.use_case

import com.example.domain.repo.ChatRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(private val chatRepo: ChatRepo) {
    operator fun invoke(message: String) {
        CoroutineScope(Dispatchers.IO).launch { chatRepo.sendMessage(message) }
    }
}