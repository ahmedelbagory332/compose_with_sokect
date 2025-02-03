package com.example.presentation

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(innerPadding: PaddingValues, viewModel: ChatViewModel) {
    val state by viewModel.chatState.collectAsState()
    val interactionSource = remember { MutableInteractionSource() }
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f),
            reverseLayout = true
        ) {
            items(state.messages.reversed()) { msg ->
                if (msg.userId == viewModel.currentUserId()) {
                    Text("Me: ${msg.message}", modifier = Modifier.padding(4.dp))
                } else {
                    Text(
                        "User ${msg.userId.takeLast(4)}: ${msg.message}",
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }
        }


        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            BasicTextField(
                value = state.message,
                onValueChange = { viewModel.updateMessage(it) },
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
            ) { innerTextField ->
                TextFieldDefaults.DecorationBox(
                    value = state.message,
                    innerTextField = innerTextField,
                    enabled = true,
                    placeholder = { Text("write your message...") },
                    singleLine = true,
                    shape = RoundedCornerShape(8.dp),
                    visualTransformation = VisualTransformation.None,
                    interactionSource = interactionSource,
                    leadingIcon = {},
                    contentPadding = OutlinedTextFieldDefaults.contentPadding(
                        top = 0.dp,
                        bottom = 0.dp
                    )
                )
            }
            Button(onClick = {
                if (state.message.isNotEmpty()) {
                    viewModel.sendMessage(state.message)
                }
            }) {
                Text("Send")
            }
        }
    }
}