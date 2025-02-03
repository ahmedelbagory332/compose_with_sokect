package com.example.data.repo_impl

import android.content.SharedPreferences
import android.util.Log
import com.example.data.Constant
import com.example.domain.model.ChatMessage
import com.example.domain.repo.ChatRepo
import io.socket.client.Socket
import org.json.JSONObject
import javax.inject.Inject

class ChatRepoImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val sharedPreferencesEditor: SharedPreferences.Editor,
    private val socket: Socket
) : ChatRepo {
    override fun getCurrentUserId(): String {
        return sharedPreferences.getString(Constant.USER_ID_KEY, null) ?: generateUserId()
    }

    override fun generateUserId(): String {
        val newId = "user_${System.currentTimeMillis()}"
        sharedPreferencesEditor.putString(Constant.USER_ID_KEY, newId).apply()
        return newId
    }

    override fun connect(onReceivedMessage: (msg: ChatMessage) -> Unit) {
        try {
            socket.connect()
            Log.d("SocketManager", "Connecting to server")

            socket.on(Socket.EVENT_CONNECT) {
                Log.d("SocketManager", "Socket connected")
            }
            socket.on(Socket.EVENT_CONNECT_ERROR) { args ->
                Log.e("SocketManager", "Socket connection error: ${args.joinToString()}")
            }
            socket.on(Socket.EVENT_DISCONNECT) {
                Log.d("SocketManager", "Socket disconnected")
            }
            socket.on("message") { args ->
                val data = args[0] as JSONObject
                val receivedUserId = data.getString("userId")
                val receivedMessage = data.getString("message")
                onReceivedMessage(ChatMessage(receivedUserId, receivedMessage))
                Log.d("SocketManager", "data Received: $data")
                Log.d("SocketManager", "args Received: $args")
            }
        } catch (e: Exception) {
            Log.e("SocketManager", "Error connecting: ${e.message}")
        }
    }


    override fun sendMessage(message: String) {
        val json = JSONObject()
        json.put("userId", getCurrentUserId())
        json.put("message", message)
        socket.emit("message", json)
    }

    override fun disconnect() {
        socket.disconnect()
    }
}