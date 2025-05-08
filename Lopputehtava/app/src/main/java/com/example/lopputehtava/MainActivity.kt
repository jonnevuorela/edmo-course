package com.example.lopputehtava

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.lopputehtava.ui.theme.LopputehtavaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LopputehtavaTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "restaurantFeature"
                ){
                    navigation(
                        route = "restaurantFeature",
                        startDestination = "restaurantsWithReviewScreen"
                    ) {
                        composable("restaurantsWithReviewScreen") { nav ->
                            RestaurantsWithReviewsScreenRoot(
                                onNavigate = { navController.navigate("reviewsOfRestaurantScreen")
                                })
                        }

                        composable("reviewsOfRestaurantScreen") { nav ->
                            ReviewsOfRestaurantScreenRoot(
                                id = 1,
                                navController = navController,
                                /** Koska reviews screenissä on käytössä sama composable, kuin
                                etusivun korteilla. Saa myös tämä navControllerin, joka johtaa
                                takaisin itseensä. **/
                                onNavigate = { navController.navigate("reviewsOfRestaurantScreen") }
                            )
                        }
                    }
                }
            }
        }
    }
}