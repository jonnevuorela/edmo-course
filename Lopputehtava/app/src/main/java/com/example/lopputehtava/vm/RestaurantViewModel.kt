package com.example.lopputehtava.vm

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lopputehtava.DataApi
import com.example.lopputehtava.models.RestaurantReviewsState
import com.example.lopputehtava.models.RestaurantState
import com.example.lopputehtava.models.ReviewsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val api: DataApi,
    private val savedState: SavedStateHandle
): ViewModel(){
    private val _restaurantReviewState = MutableStateFlow(RestaurantReviewsState())
    val restaurantReviewsState = _restaurantReviewState.asStateFlow()

    private val _restaurantState = MutableStateFlow(RestaurantState())
    val restaurantState = _restaurantState.asStateFlow()

    private val _reviewsState = MutableStateFlow(ReviewsState())
    val reviewsState = _reviewsState.asStateFlow()

    init {
        getRestarantsAndReviews()
    }

    fun setRestaurantId(restaurantId: Int) {
        savedState["restaurantId"] = restaurantId
        getRestaurantReviews()
        getRestaurant()
    }

    fun getRestaurant(){
        viewModelScope.launch {
            try{
                savedState.get<Int>("restaurantId")?.let{ restaurantId ->

                    val restaurant = api.getRestaurant(restaurantId)
                    _restaurantState.update { currentState ->
                        currentState.copy(restaurant = restaurant)
                    }

                } ?: _restaurantState.update { currentState ->
                    currentState.copy(error = "Restaurant id is null")
                } }
            catch (e: Exception){
                _restaurantState.update { currentState ->
                    currentState.copy(error = e.toString())
                }
            }finally {
                _restaurantState.update { currentState ->
                    currentState.copy(loading = false)
                }
            }
        }
    }

    fun getRestaurantReviews(){
        viewModelScope.launch {
            try{
                savedState.get<Int>("restaurantId")?.let{ restaurantId ->

                val restaurantResponse = api.getRestaurantRatings(restaurantId)
                _reviewsState.update { currentState ->
                    currentState.copy(reviews = restaurantResponse)
                }

            } ?: _reviewsState.update { currentState ->
                currentState.copy(error = "Restaurant id is null")
            } }
            catch (e: Exception){
                _reviewsState.update { currentState ->
                    currentState.copy(error = e.toString())
                }
            }finally {
                _reviewsState.update { currentState ->
                    currentState.copy(loading = false)
                }
            }
        }
    }

    fun getRestarantsAndReviews(){
        viewModelScope.launch {
            try {
                _restaurantReviewState.update { currentState ->
                    currentState.copy(loading = true, error = null)
                }
                val restaurantsWithReviews = api.getRestaurantWithReviews()
                _restaurantReviewState.update { currentState ->
                    currentState.copy(restaurantWithReview = restaurantsWithReviews)
                }
            } catch (e: Exception){
                _restaurantReviewState.update { currentState ->
                    currentState.copy(error = e.toString())
                }
            } finally {
                _restaurantReviewState.update { currentState ->
                    currentState.copy(loading = false)
                }
            }
        }
    }
}