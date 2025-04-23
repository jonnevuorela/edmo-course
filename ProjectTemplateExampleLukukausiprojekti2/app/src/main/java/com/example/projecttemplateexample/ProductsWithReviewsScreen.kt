package com.example.projecttemplateexample

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.projecttemplateexample.models.ProductReviewsState
import com.example.projecttemplateexample.models.ProductWithReviewDto
import com.example.projecttemplateexample.ui.theme.ProjectTemplateExampleTheme

@Composable
fun ProductsWithReviewsScreenRoot(modifier: Modifier = Modifier) {
    val productsWithReviews = listOf(
        ProductWithReviewDto()
    )
    val _state = ProductReviewsState(
    )
    ProductsWithReviewsScreen(state = _state)

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsWithReviewsScreen(modifier: Modifier = Modifier, state: ProductReviewsState) {
    Scaffold(
        topBar = {
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
                    items(state.productWithReview) { product ->
                        ProductItem(product = product)
                    }
                }
            }
        }
    }
}

    @Composable
    fun ProductItem(modifier: Modifier = Modifier, product: ProductWithReviewDto) {
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
                AsyncImage(
                    modifier = Modifier.size(100.dp).clip(RoundedCornerShape(8.dp)),
                    model = R.drawable.review,
                    contentDescription = "Product image",
                    contentScale = ContentScale.Crop
                )
                Column(modifier = Modifier.weight(1f).padding(16.dp)) {
                    Text(product.productName), style = MaterialTheme.typography.titleMedium,
                    fontWeight = fontWeight.Bold

                }

            }


        }
    }

    }
@Preview
@Composable
private fun ProductsWithReviewPreview() {
    val productsWithReview = listOf(
        ProductWithReviewsDto(
        id = 1,
        productName = "Loistava tuote",
        categoryName = "Siisti kategoria",
        rating = 5f,
        reviewCount = 1000,
    )
    )
    val state = ProductReviewsState(productsWithReview = productsWithReview)
    ProjectTemplateExampleTheme {

    }

}
