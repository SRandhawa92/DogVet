package com.dog.dogvet.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dog.dogvet.data.MessagesRepository
import com.dog.dogvet.data.entity.MessageItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class State {
    object Loading : State()
    data class Error(val message: String) : State()
    data class Result(val messageList: List<MessageItem>) : State()
}

@HiltViewModel
class ChatBotViewModel @Inject constructor(private val repository: MessagesRepository) : ViewModel() {

    var inputMessage = mutableStateOf(TextFieldValue(""))
    val state: StateFlow<State>
        get() = _state

    private val _state = MutableStateFlow<State>(State.Loading)

    fun getHistory() {
        viewModelScope.launch {
            repository.getConversation().collect { messages -> _state.value = State.Result(messages) }
        }
    }

    fun send(msg: String) {
        viewModelScope.launch {
            repository.addMsg(msg).collect { messages -> _state.value = State.Result(messages) }
            repository.getReply(msg).collect { messages -> _state.value = State.Result(messages) }
        }
    }
}
