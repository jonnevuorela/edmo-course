package com.example.lopputehtava

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import androidx.compose.runtime.getValue
import com.example.lopputehtava.models.RestaurantReviewsState
import com.example.lopputehtava.models.RestaurantWithReviewDto
import com.example.lopputehtava.vm.RestaurantViewModel
import androidx.compose.foundation.lazy.items
@Composable
fun RestaurantsWithReviewsScreenRoot(
    modifier: Modifier = Modifier,
    onNavigate: (Int) -> Unit,
    viewModel: RestaurantViewModel
) {

    val state by viewModel.restaurantReviewsState.collectAsStateWithLifecycle()
    RestaurantsWithReviewsScreen(state = state, onNavigate = onNavigate)

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantsWithReviewsScreen(
    modifier: Modifier = Modifier,
    state: RestaurantReviewsState,
    onNavigate: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Restaurants")
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Menu, contentDescription = "Open Menu")
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
            else -> {
                state.error?.let { err ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues), contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(err)
                            Button(onClick = {}) {
                                Text("Retry")
                            }
                        }
                    }
                } ?: LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    items(state.restaurantWithReview, key = { restaurant ->
                        restaurant.id
                    }) { restaurant ->
                        RestaurantItem(restaurant = restaurant, onNavigate = onNavigate, inReviews = false)
                    }

                }
            }
        }
    }
}

@Composable
fun RestaurantItem(modifier: Modifier = Modifier, restaurant: RestaurantWithReviewDto, onNavigate: (Int) -> Unit, inReviews: Boolean) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable{
                if(!inReviews){
                    onNavigate(restaurant.id)
                }else{}
                },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ){
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            AsyncImage(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp)),
                model = R.drawable.doublequarterpoundercheese,
                contentDescription = "Product image",
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier
                .weight(1f)
                .padding(16.dp)) {
                Text(
                    text = restaurant.name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                RatingBar(rating = restaurant.rating ?: 0f, reviewCount = restaurant.reviewCount)
                Row {
                    Text(
                        text = restaurant.cuisine,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.W400
                    )
                    Text(
                        text = " • ",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.Gray
                    )
                    Text(
                        text = restaurant.priceRange,
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold
                    )
                }
                Text(
                    text = restaurant.address,
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = restaurant.openStatus,
                    style = MaterialTheme.typography.labelMedium,
                    color = Color(0xFF009900),
                    fontWeight = FontWeight.Bold
                )


            }

        }


    }
}
@Composable
fun RatingBar(modifier: Modifier = Modifier, rating: Float, reviewCount: Int?) {
    val filledStars = rating.toInt()
    val hasHalfStar = rating - filledStars >= 0.5
    val emptyStars = 5 - filledStars - if (hasHalfStar) 1 else 0

    Row {
        repeat(filledStars) {
            Icon(Icons.Filled.Star, contentDescription = "Star", tint = Color(0xFFFFD700))

        }

        if (hasHalfStar) {
            Icon(
                painterResource(R.drawable.star_half),
                contentDescription = "Half Star",
                tint = Color(0xFFFFD700)
            )
        }

        repeat(emptyStars) {
            Icon(Icons.Filled.Star, contentDescription = "Star", tint = Color.LightGray)
        }
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = rating.toString(),
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.W400
            )
        Spacer(modifier = Modifier.width(8.dp))
        if(reviewCount != null){
            Text(
                text ="($reviewCount)",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.SemiBold,
                color = Color.Gray
            )
        }else {

        }

}
}