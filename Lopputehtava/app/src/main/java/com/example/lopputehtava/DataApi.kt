package com.example.lopputehtava

import com.example.lopputehtava.models.GetRestaurantResponseDto
import com.example.lopputehtava.models.RestaurantWithReviewDto
import retrofit2.http.GET
import retrofit2.http.Path

interface DataApi {
    @GET("restaurants/ratings/")
    suspend fun getRestaurantWithReviews() : List<RestaurantWithReviewDto>

    @GET("restaurants/{restaurantId}")
    suspend fun getRestaurant(@Path("restaurantId") restaurantId: Int): GetRestaurantResponseDto
}