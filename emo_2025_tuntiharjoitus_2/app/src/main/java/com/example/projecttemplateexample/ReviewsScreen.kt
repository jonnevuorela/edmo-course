package com.example.projecttemplateexample

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.projecttemplateexample.models.ReviewsState
import com.example.projecttemplateexample.vm.ProductReviewsViewModel

@Composable
fun ReviewsScreenRoot(modifier: Modifier = Modifier, viewmodel: ProductReviewsViewModel) {

    LaunchedEffect(true) {
        viewmodel.getProduct()
    }

    val state by viewmodel.reviewsState.collectAsStateWithLifecycle()
    ReviewsScreen(state = state)

}

@Composable
fun ReviewsScreen(modifier: Modifier = Modifier, state: ReviewsState) {

}