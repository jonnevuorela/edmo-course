package com.example.projecttemplateexample

import com.example.projecttemplateexample.models.GetProductResponseDto
import com.example.projecttemplateexample.models.ProductWithReviewDto
import com.example.projecttemplateexample.models.UserDto
import retrofit2.http.GET
import retrofit2.http.Path

interface DataApi {
    @GET("product_reviews")
    suspend fun getProductsWithReviews() : List<ProductWithReviewDto>

    @GET("products/{productId}")
    suspend fun getProduct(@Path("productId") productId: Int): GetProductResponseDto
}