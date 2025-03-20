package com.example.emo1.state_management

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.emo1.ui.theme.Emo1Theme


private var count = mutableStateOf(0)


@Composable
fun Counter(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center


    ) {
        // tämä onClick-funktio kasvattaa napin klikillä
        // integerin arvoa ja logittaa konsoliin uuden arvon
        // tagilla juhanicounter
        Button(onClick = {
            count.value++
        }) {
            Text("count: ${count.value}")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CounterPreview() {
   Emo1Theme {
       Counter()
   }

}
