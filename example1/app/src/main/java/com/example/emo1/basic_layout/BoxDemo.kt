package com.example.emo1.basic_layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.emo1.R

@Composable
fun BoxDemo(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier,
        contentAlignment = Alignment.BottomCenter
    ){
        Image(
            painter = painterResource(R.drawable.siru_koira),
            contentDescription = null,
        )
        Box(
            modifier = Modifier
                .background(
                    brush =  Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black)
                    )
                )
                .matchParentSize()
        )
            IconButton(
                onClick = {
                    
                },
                modifier = Modifier.align(Alignment.BottomEnd)
            ) {
                Icon(imageVector = Icons.Default.Star, contentDescription = null, tint = Color.Yellow )
            }
    }
}

@Preview
@Composable
private fun BoxDemoPreview() {
    BoxDemo()

}