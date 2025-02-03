package com.example.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.use_case.ConnectSocketUseCase
import com.example.domain.use_case.DisConnectSocketUseCase
import com.example.domain.use_case.GetCurrentUserIdUseCase
import com.example.domain.use_case.SendMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    connectSocketUseCase: ConnectSocketUseCase,
    private val disConnectSocketUseCase: DisConnectSocketUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    private val getCurrentUserIdUseCase: GetCurrentUserIdUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow<ChatState>(ChatState())
    val chatState: StateFlow<ChatState>
        get() = _state

    init {
        connectSocketUseCase.invoke { chatMessage ->
            viewModelScope.launch {
                _state.update {
                    it.copy(
                        messages = it.messages + chatMessage
                    )
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        disConnectSocketUseCase.invoke()
    }

    fun sendMessage(message: String) {
        viewModelScope.launch {
            sendMessageUseCase.invoke(message)
            _state.update {
                it.copy(
                    message = ""
                )
            }
        }
    }

    fun updateMessage(message: String) {
        _state.update {
            it.copy(
                message = message
            )
        }
    }
    fun currentUserId() = getCurrentUserIdUseCase.invoke()
}