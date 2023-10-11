package com.dog.dogvet.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dog.dogvet.R
import com.dog.dogvet.ui.components.InputField
import com.dog.dogvet.ui.components.MessageList

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ChatBot() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Image(painter = painterResource(R.drawable.dog_face), contentDescription = "logo")
                        Spacer(modifier = Modifier.padding(10.dp))
                        Text(text = "DogVet")
                    }
                },
            )
        },
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.weight(1.0f)) {
                MessageList()
            }
            InputField()
        }
    }
}
