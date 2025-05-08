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
import androidx.navigation.NavController
import com.example.lopputehtava.models.GetRestaurantResponseDto
import com.example.lopputehtava.models.RestaurantWithReviewsDto
import com.example.lopputehtava.models.ReviewDto
import com.example.lopputehtava.models.ReviewsState

@Composable
fun ReviewsOfRestaurantScreenRoot(modifier: Modifier = Modifier, id: Int, navController: NavController, onNavigate: (Int) -> Unit) {
    val reviewsOfRestaurant = listOf(
        ReviewDto(
            id = 1,
            userId = null,
            value = 5,
            description = "Ruoka oli hyvää ja sitä oli riittävästi",
            dateRated = "2025-05-06 14:45:02"
        ),
        ReviewDto(
            id = 2,
            userId = null,
            value = 1,
            description = "En tykkää",
            dateRated = "2025-05-05 14:45:02"
        ),
        ReviewDto(
            id = 3,
            userId = null,
            value = 5,
            description = "Ruoka oli hyvää ja sitä oli riittävästi. Ruoka oli hyvää ja sitä oli riittävästi. Ruoka oli hyvää ja sitä oli riittävästi. Ruoka oli hyvää ja sitä oli riittävästi. Ruoka oli hyvää ja sitä oli riittävästi. Ruoka oli hyvää ja sitä oli riittävästi. Ruoka oli hyvää ja sitä oli riittävästi. Ruoka oli hyvää ja sitä oli riittävästi. Ruoka oli hyvää ja sitä oli riittävästi. Ruoka oli hyvää ja sitä oli riittävästi. Ruoka oli hyvää ja sitä oli riittävästi. Ruoka oli hyvää ja sitä oli riittävästi. Ruoka oli hyvää ja sitä oli riittävästi. Ruoka oli hyvää ja sitä oli riittävästi. Ruoka oli hyvää ja sitä oli riittävästi. Ruoka oli hyvää ja sitä oli riittävästi. Ruoka oli hyvää ja sitä oli riittävästi. Ruoka oli hyvää ja sitä oli riittävästi. Ruoka oli hyvää ja sitä oli riittävästi. Ruoka oli hyvää ja sitä oli riittävästi. Ruoka oli hyvää ja sitä oli riittävästi. Ruoka oli hyvää ja sitä oli riittävästi. Ruoka oli hyvää ja sitä oli riittävästi. Ruoka oli hyvää ja sitä oli riittävästi. Ruoka oli hyvää ja sitä oli riittävästi. Ruoka oli hyvää ja sitä oli riittävästi. Ruoka oli hyvää ja sitä oli riittävästi. Ruoka oli hyvää ja sitä oli riittävästi. Ruoka oli hyvää ja sitä oli riittävästi. Ruoka oli hyvää ja sitä oli riittävästi. Ruoka oli hyvää ja sitä oli riittävästi. Ruoka oli hyvää ja sitä oli riittävästi. Ruoka oli hyvää ja sitä oli riittävästi. Ruoka oli hyvää ja sitä oli riittävästi. Ruoka oli hyvää ja sitä oli riittävästi. Ruoka oli hyvää ja sitä oli riittävästi. Ruoka oli hyvää ja sitä oli riittävästi. Ruoka oli hyvää ja sitä oli riittävästi. ",
            dateRated = "2025-05-07 14:45:02"
    ),
    )

    val restaurantsWithReviews = listOf(
        RestaurantWithReviewsDto(
            id = 1,
            name = "Ravintola",
            cuisine = "Ruokaa",
            priceRange = "$$$",
            address = "Katu 123, Tuntsa",
            openStatus = "Aina auki",
            rating = 5f,
            reviewCount = 1000,
        ),
        RestaurantWithReviewsDto(
            id = 2,
            name = "Toinen Ravintola",
            cuisine = "Eri ruokaa",
            priceRange = "$$",
            address = "Kuja 777, Tulppio",
            openStatus = "Suljetaan kohta",
            rating = 4.5f,
            reviewCount = 100,
        ),
        RestaurantWithReviewsDto(
            id = 3,
            name = "Äteritsiputeritsipuolilautatsibaari",
            cuisine = "Väärää ruokaa",
            priceRange = "$",
            address = "Polku 2, Värriö",
            openStatus = "Suljettu pysyvästi",
            rating = 1f,
            reviewCount = 1,
        )
    )
    val selectedRestaurant = restaurantsWithReviews.first { it.id == id }
    val response = GetRestaurantResponseDto(
        id = selectedRestaurant.id,
        name = selectedRestaurant.name,
        reviews = reviewsOfRestaurant
    )
    val _state = ReviewsState(
        restaurant = response
    )

        ReviewsOfRestaurantScreen(
            restaurant = selectedRestaurant,
            state = _state,
            navController = navController,
            onNavigate = onNavigate
        )


}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewsOfRestaurantScreen(modifier: Modifier = Modifier, restaurant: RestaurantWithReviewsDto, state: ReviewsState, navController: NavController, onNavigate: (Int) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = restaurant.name)
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
            state.loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            state.error != null -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Column {
                        Text(state.error)
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
                    item {
                        RestaurantItem(restaurant = restaurant, onNavigate = onNavigate)
                    }
                    items(state.restaurant?.reviews ?: emptyList()){ review ->
                        ReviewItem(review = review)

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
