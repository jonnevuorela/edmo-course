package com.example.lopputehtava

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.lopputehtava.models.ReviewDto
import com.example.lopputehtava.models.ReviewsState
import com.example.lopputehtava.vm.RestaurantViewModel

import androidx.compose.runtime.getValue
import com.example.lopputehtava.models.RestaurantState

@Composable
fun ReviewsOfRestaurantScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: RestaurantViewModel,
    navController: NavController,
    onNavigate: (Int) -> Unit
) {


    val reviewsState by viewModel.reviewsState.collectAsStateWithLifecycle()
    val restaurantState by viewModel.restaurantState.collectAsStateWithLifecycle()
    ReviewsOfRestaurantScreen(
        restaurantState = restaurantState,
        reviewState = reviewsState,
        navController = navController,
        onNavigate = onNavigate
    )



}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewsOfRestaurantScreen(
    modifier: Modifier = Modifier,
    restaurantState: RestaurantState,
    reviewState: ReviewsState,
    navController: NavController,
    onNavigate: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = restaurantState.restaurant?.name ?: "Restaurant")
                },
                navigationIcon = {
                    IconButton(onClick= { navController.navigateUp() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Navigate back")
                    }
                }
            )
        }
    ) { paddingValues ->
        when {
            restaurantState.loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            restaurantState.error != null -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Column {
                        Text(restaurantState.error)
                        Button(onClick = {}) {
                            Text("Retry")
                        }
                    }
                }
            }
            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    if(restaurantState.restaurant!=null){

                        item {
                            RestaurantItem(restaurant = restaurantState.restaurant, onNavigate = onNavigate, inReviews = true)
                        }
                        items(reviewState.reviews){ review ->
                            ReviewItem(review=review)
                        }

                    }else{
                        // Ei varmaan parhaan käytännö mukainen
                        navController.navigateUp()

                    }
                }
            }
        }
    }
}
@Composable
fun ReviewItem(modifier: Modifier = Modifier, review: ReviewDto) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ){
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Column(modifier = Modifier
                .weight(1f)
                .padding(16.dp)) {

                Row( modifier = Modifier
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween ){

                    RatingBar(rating = review.value.toFloat(), reviewCount = null)
                    IconButton(onClick = {}){
                        Icon(Icons.Filled.Delete, contentDescription = "delete review")
                    }
                }
                Text(text = review.description ?: "", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(16.dp))
                val _date = review.dateRated
                Text(
                    text = "Date: $_date",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold
                )
                }
            }

        }


    }
