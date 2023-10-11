package com.dog.dogvet.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dog.dogvet.ui.viewmodels.ChatBotViewModel

@Composable
fun InputField(viewModel: ChatBotViewModel = hiltViewModel()) {
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.Bottom,
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            value = viewModel.inputMessage.value,
            onValueChange = { viewModel.inputMessage.value = it },
            placeholder = { Text("Ask something...") },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.Send,
                    contentDescription = "Send Button",
                    modifier = Modifier.clickable {
                        viewModel.send(viewModel.inputMessage.value.text)
                        viewModel.inputMessage.value = TextFieldValue("")
                    },
                )
            },
        )
    }
}
