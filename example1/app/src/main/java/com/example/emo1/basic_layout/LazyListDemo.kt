package com.example.emo1.basic_layout

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LazyListDemo(modifier: Modifier = Modifier) {
LazyColumn {
    items(50) { itemIndex ->
        Text("item $itemIndex")
    }
    item {
        Text("Pääsit loppuun")
    }
}

}

@Preview
@Composable
private fun LazyListDemoPreview(){
LazyListDemo()
}