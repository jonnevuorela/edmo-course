package com.example.lopputehtava.models


import com.google.gson.annotations.SerializedName

data class RestaurantWithReviewDto(
    val id: Int,
    val name: String,
    val cuisine: String,
    @SerializedName("price_range")
    val priceRange: String,
    val address: String,
    @SerializedName("open_status")
    val openStatus: String,
    val rating: Float?,
    @SerializedName("review_count")
    val reviewCount: Int,

    )

data class RestaurantReviewsState(
    val loading: Boolean = false,
    val error: String? = null,
    val restaurantWithReview : List<RestaurantWithReviewDto> = emptyList(),
)