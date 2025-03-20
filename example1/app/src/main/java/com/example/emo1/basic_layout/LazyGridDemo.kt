package com.example.emo1.basic_layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.emo1.ui.theme.Emo1Theme
import kotlin.random.Random


@Composable
fun LazyGridDemo(modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(40.dp)

    ) {
        items(100) {
            Box(
                modifier = Modifier
                    .height(50.dp)
                    .background(Color(Random.nextInt()))
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LazyGridDemoPreview() {
    Emo1Theme  {
        LazyGridDemo()
    }
}
