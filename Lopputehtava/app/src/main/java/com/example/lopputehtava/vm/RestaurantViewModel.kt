package com.example.lopputehtava.vm

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lopputehtava.DataApi
import com.example.lopputehtava.models.RestaurantReviewsState
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

    private val _restauranState = MutableStateFlow(ReviewsState())
    val restaurantState = _restauranState.asStateFlow()

    init {
        Log.d("tämä on testi", "init")
        getRestarantReviews()
    }

    fun setRestaurantId(restaurantId: Int) {
        savedState["restaurantId"] = restaurantId
    }

    fun getRestaurant(){
        viewModelScope.launch {
            try{
                savedState.get<Int>("restaurantId")?.let{ restaurantId ->

                val restaurantResponse = api.getRestaurant(restaurantId)
                _restauranState.update { currentState ->
                    currentState.copy(restaurant = restaurantResponse)
                }

            } ?: _restauranState.update { currentState ->
                currentState.copy(error = "Restaurant id is null")
            } }
            catch (e: Exception){
                _restauranState.update { currentState ->
                    currentState.copy(error = e.toString())
                }
            }finally {
                _restauranState.update { currentState ->
                    currentState.copy(loading = false)
                }
            }
        }
    }

    fun getRestarantReviews(){
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