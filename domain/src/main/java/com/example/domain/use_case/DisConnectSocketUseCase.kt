package com.example.domain.use_case

import com.example.domain.repo.ChatRepo
import javax.inject.Inject

class DisConnectSocketUseCase @Inject constructor(private val chatRepo: ChatRepo) {
    operator fun invoke() {
        chatRepo.disconnect()
    }
}