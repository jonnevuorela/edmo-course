package com.example.lopputehtava

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.lopputehtava.ui.theme.LopputehtavaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LopputehtavaTheme {
                ReviewsOfRestaurantScreenRoot(id = 1)
            }
        }
    }
}