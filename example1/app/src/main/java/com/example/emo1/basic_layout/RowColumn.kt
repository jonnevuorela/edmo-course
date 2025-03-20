package com.example.emo1.basic_layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.provider.FontsContractCompat.Columns
@Composable
fun RowColumnDemo(modifier: Modifier = Modifier) {
    Column(Modifier.fillMaxSize()) {
        Text("Terve")

    }
}


@Preview(showBackground = true)
@Composable
private fun RowColumnDemoPreview() {
    RowColumnDemo()
}