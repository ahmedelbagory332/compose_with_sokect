package com.example.domain.use_case

import com.example.domain.repo.ChatRepo
import javax.inject.Inject

class GetCurrentUserIdUseCase @Inject constructor(private val chatRepo: ChatRepo) {
    operator fun invoke(): String {
        return chatRepo.getCurrentUserId()
    }
}