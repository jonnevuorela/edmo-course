package com.example.emo1.basic_layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun ModifierDemo(modifier: Modifier = Modifier) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Spacer(Modifier.weight(2f))
        Box(modifier = Modifier
            .size(100.dp)
            .background(Color.Red)
            .weight(30f)
        )
        Spacer(Modifier.weight(2f))
        Box(modifier = Modifier
            .size(100.dp)
            .background(Color.Blue)
            .weight(30f)
        )
        Spacer(Modifier.weight(2f))
        Box(modifier = Modifier
            .size(100.dp)
            .background(Color.Green)
            .weight(30f)
        )
        Spacer(Modifier.weight(2f))
    }

}

@Preview(showBackground = true)
@Composable
private fun ModifierDemoPreiew() {
    ModifierDemo()

}