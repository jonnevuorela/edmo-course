package com.example.projecttemplateexample.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projecttemplateexample.DataApi
import com.example.projecttemplateexample.NetworkChecker
import com.example.projecttemplateexample.models.ProductReviewsState
import com.example.projecttemplateexample.models.ReviewsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductReviewsViewModel @Inject constructor(
   private val api: DataApi,
   private val networkChecker: NetworkChecker,
    private val savedState: SavedStateHandle
) : ViewModel() {
    private val _productReviewsState = MutableStateFlow(ProductReviewsState())
    val productReviewsState = _productReviewsState.asStateFlow()

    private val _reviewsState = MutableStateFlow(ReviewsState())
    val reviewsState = _reviewsState

    init {
        getProductReviews()
    }

     fun getProduct(){
         viewModelScope.launch {

             if (networkChecker.isNetworkAvailable()) {
                 _reviewsState.update { currentState ->
                     currentState.copy(loading = true)
                 }

                 try {
                     savedState.get<Int>("productId")?.let { productId ->
                         val productResponse = api.getProduct(productId)
                         _reviewsState.update { currentState ->
                             currentState.copy(product = productResponse)
                         }

                     } ?: _reviewsState.update { currentState ->
                         currentState.copy(error = "Product id is null")
                     }

                 } catch (e: Exception) {
                     _reviewsState.update { currentState ->
                         currentState.copy(error = e.toString())
                     }

                 } finally {
                     _reviewsState.update { currentState ->
                         currentState.copy(loading = false)
                     }
                 }

             } else {
                 _reviewsState.update { currentState ->
                     currentState.copy(error = "Network unavailable")
                 }
             }
         }

    }

    fun setProductId(productId: Int){
        savedState["productId"] = productId
    }

    fun getProductReviews(){
        viewModelScope.launch {
            if (networkChecker.isNetworkAvailable()){

            try {
                _productReviewsState.update { currentState ->
                    currentState.copy(loading = true, error = null)
                }
                val productsWithReviews = api.getProductsWithReviews()
                _productReviewsState.update { currentState ->
                    currentState.copy(productsWithReview = productsWithReviews)
                }
            } catch(e: Exception){
                _productReviewsState.update { currentState ->
                    currentState.copy(error = e.toString())
                }
            } finally {
                _productReviewsState.update { currentState ->
                    currentState.copy(loading = false)
                }
            }

            }else {
                _productReviewsState.update { currentState ->
                currentState.copy(error = "Network unavailable")
            }
            }
        }
    }
}