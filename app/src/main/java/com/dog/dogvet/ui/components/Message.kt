package com.dog.dogvet.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.dog.dogvet.data.entity.MessageItem
import com.dog.dogvet.ui.theme.Purple200
import com.dog.dogvet.ui.theme.Teal200

@Composable
fun Message(messageItem: MessageItem) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalAlignment = if (messageItem.isBot) Alignment.Start else Alignment.End,
    ) {
        Card(
            modifier = Modifier.widthIn(max = 340.dp),
            shape = cardShapeFor(messageItem),
            backgroundColor = if (messageItem.isBot) Purple200 else Teal200,
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = messageItem.data,
            )
        }
    }
}

@Composable
fun cardShapeFor(message: MessageItem): Shape {
    val roundedCorners = RoundedCornerShape(16.dp)
    return when {
        message.isBot -> roundedCorners.copy(bottomStart = CornerSize(0))
        else -> roundedCorners.copy(bottomEnd = CornerSize(0))
    }
}
