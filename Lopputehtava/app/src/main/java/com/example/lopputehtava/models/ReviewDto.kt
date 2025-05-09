package com.example.lopputehtava.models

import com.google.gson.annotations.SerializedName

data class ReviewDto(
    val id: Int,
    @SerializedName("user_id")
    val userId: Int?,
    val value: Int,
    val description: String?,
    @SerializedName("date_rated")
    val dateRated: String
)


data class ReviewsState(
    val loading: Boolean = false,
    val error: String? = null,

    val restaurant: GetRestaurantResponseDto? = null
)
data class GetRestaurantResponseDto(
    val id: Int,
    val name: String,

    val review: List<ReviewDto>
)
