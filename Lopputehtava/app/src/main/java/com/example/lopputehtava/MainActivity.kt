package com.example.lopputehtava

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.lopputehtava.ui.theme.LopputehtavaTheme
import com.example.lopputehtava.vm.RestaurantViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
                            val vm = nav.SharedViewModel<RestaurantViewModel>(navController)
                            RestaurantsWithReviewsScreenRoot(
                                viewModel = vm,
                                onNavigate = { restaurantId ->
                                    vm.setRestaurantId(restaurantId)
                                    navController.navigate("reviewsOfRestaurantScreen")
                                })
                        }

                        composable("reviewsOfRestaurantScreen") { nav ->
                            val vm = nav.SharedViewModel<RestaurantViewModel>(navController)
                            ReviewsOfRestaurantScreenRoot(
                                viewModel = vm,
                                navController = navController,
                                /** Koska reviews screenissä on käytössä sama composable, kuin
                                etusivun korteilla. Saa myös tämä navControllerin, joka johtaa
                                takaisin itseensä.
                                 edit: Jättää kutsumatta onNavigatea, jos reviewseissä.
                                 Reviews sivulta pystyi sukeltamaan aina vain syvemmälle saman
                                 ravintolan sivuille.**/
                                onNavigate = { navController.navigate("reviewsOfRestaurantScreen") }
                            )
                        }
                    }
                }
            }
        }
    }
}
@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.SharedViewModel(navController: NavController): T {

    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }

    return hiltViewModel(parentEntry)

}