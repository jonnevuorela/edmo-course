package com.example.emo1.state_management

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
fun StateHoistingDemo(modifier: Modifier = Modifier) {

    val count = remember {
        mutableStateOf(0)
    }

    Box(modifier=Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Counter2(count = count.value, onButtonClick = {
            count.value++
        })
    }
}