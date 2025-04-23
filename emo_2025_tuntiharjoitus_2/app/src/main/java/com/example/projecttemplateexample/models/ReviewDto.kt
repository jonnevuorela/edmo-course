package com.example.projecttemplateexample.models

import com.google.gson.annotations.SerializedName

data class ReviewDto(
    val id: Int,
    val rating: Float,
    @SerializedName("date_reviewed")
    val dateReviewed : String,
    val message: String?
)

data class ReviewsState(
    val loading: Boolean = false,
    val error: String? = null,
    val product: GetProductResponseDto? = null
)

data class GetProductResponseDto(
    val id: Int,
    val name: String,
    val review: List<ReviewDto>
)