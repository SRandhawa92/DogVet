package com.dog.dogvet.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.dog.dogvet.ui.viewmodels.ChatBotViewModel
import com.dog.dogvet.ui.viewmodels.State

@Composable
fun MessageList(viewModel: ChatBotViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        viewModel.getHistory()
        when (val screenState = state) {
            is State.Loading -> CircularProgressIndicator()
            is State.Error -> Text(text = screenState.message)
            is State.Result -> {
                val messageItems = screenState.messageList
                    .filter { it.data.isNotBlank() }
                    .asReversed()

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    reverseLayout = true,
                ) { items(messageItems) { message -> Message(message) } }
            }
        }
    }
}
