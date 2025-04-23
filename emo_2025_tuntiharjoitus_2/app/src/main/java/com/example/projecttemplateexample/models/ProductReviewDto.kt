package com.example.projecttemplateexample.models

import com.google.gson.annotations.SerializedName

data class ProductWithReviewDto(
    val id: Int,
    @SerializedName("product_name")
    val productName: String,
    @SerializedName("category_name")
    val categoryName: String,
    val rating: Float?,
    @SerializedName("review_count")
    val reviewCount: Int
)

data class ProductReviewsState(
    val loading: Boolean = false,
    val error: String? = null,
    val productsWithReview: List<ProductWithReviewDto> = emptyList()
)
