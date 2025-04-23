package com.example.projecttemplateexample

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
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.projecttemplateexample.models.ProductReviewsState
import com.example.projecttemplateexample.models.ProductWithReviewDto
import com.example.projecttemplateexample.ui.theme.ProjectTemplateExampleTheme
import com.example.projecttemplateexample.vm.ProductReviewsViewModel

@Composable
fun ProductsWithReviewsScreenRoot(
    modifier: Modifier = Modifier,
    viewmodel: ProductReviewsViewModel,
    onNavigate: (Int) -> Unit
) {

    val state by viewmodel.productReviewsState.collectAsStateWithLifecycle()
    ProductsWithReviewsScreen(state = state, onNavigate = onNavigate)

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsWithReviewsScreen(
    modifier: Modifier = Modifier,
    state: ProductReviewsState,
    onNavigate: (Int) -> Unit
) {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text("Products & Reviews")
            },
            navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(Icons.Default.Menu, contentDescription = "Open Menu")
                }
            }
        )
    }) { paddingValues ->
        when {
            state.loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues), contentAlignment = Alignment.Center
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
                    items(state.productsWithReview, key = { product ->
                        product.id
                    }) { product ->
                        ProductItem(product = product, onNavigate = onNavigate)
                    }
                }
            }
        }
    }
}

@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    product: ProductWithReviewDto,
    onNavigate: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                onNavigate(product.id)
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp)),
                model = R.drawable.review, contentDescription = "Product image",
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier
                .weight(1f)
                .padding(16.dp)) {
                Text(
                    product.productName, style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                RatingBar(rating = product.rating ?: 0f, reviewCount = product.reviewCount)
                Text(product.categoryName)

            }
        }
    }
}

@Composable
fun RatingBar(modifier: Modifier = Modifier, rating: Float, reviewCount: Int) {
    val filledStars = rating.toInt()
    val hasHalfStars = rating - filledStars >= 0.5
    val emptyStars = 5 - filledStars - if(hasHalfStars) 1 else 0

    Row {
    repeat(filledStars){
        Icon(Icons.Filled.Star, contentDescription = "Star", tint = Color(0xFFFFD700))

    }
         if (hasHalfStars){
            Icon(painterResource(R.drawable.star_half), contentDescription = "Half Star", tint = Color(0xFFFFD700) )
        }
    repeat(emptyStars){
        Icon(Icons.Filled.Star, contentDescription = "Empty Star", tint = Color.LightGray)
    }
        Spacer(modifier = Modifier.width(4.dp))
        Text(rating.toString(), style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.width(8.dp))
        Text("($reviewCount)", style = MaterialTheme.typography.bodyLarge)


    }


}

@Preview
@Composable
private fun ProductsWithReviewsPreview() {
    val productsWithReviews = listOf(
        ProductWithReviewDto(
            id = 1,
            productName = "Loistava tuote",
            categoryName = "Siisti kategoria",
            rating = 5f,
            reviewCount = 1000
        ),
        ProductWithReviewDto(
            id = 2,
            productName = "Loistava tuote 2",
            categoryName = "Siisti kategoria",
            rating = 4.8f,
            reviewCount = 1000
        ),
        ProductWithReviewDto(
            id = 3,
            productName = "Aivan paska",
            categoryName = "Siisti kategoria",
            rating = 2.8f,
            reviewCount = 1000
        )
    )
    val state = ProductReviewsState(productsWithReview = productsWithReviews)
    ProjectTemplateExampleTheme {
        ProductsWithReviewsScreen(state = state, onNavigate = {})
    }
}
