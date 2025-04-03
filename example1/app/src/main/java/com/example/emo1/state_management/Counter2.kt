package com.example.emo1.state_management

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier


@Composable
fun Counter2(modifier: Modifier = Modifier, count: Int, onButtonClick: ()-> Unit) {

    Button(onClick = {
        onButtonClick()
    }){
        Text("$count")
    }

}