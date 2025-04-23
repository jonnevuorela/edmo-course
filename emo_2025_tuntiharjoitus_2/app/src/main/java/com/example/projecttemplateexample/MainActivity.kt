package com.example.projecttemplateexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.projecttemplateexample.ui.theme.ProjectTemplateExampleTheme
import com.example.projecttemplateexample.vm.ProductReviewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjectTemplateExampleTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "productReviewsFeature"
                ){
                    navigation(
                        route = "productReviewsFeature",
                        startDestination = "productsWithReviewsScreen"
                    ){
                        composable("productsWithReviewsScreen"){ nav ->
                             val vm = nav.SharedViewModel<ProductReviewsViewModel>(navController)
                            ProductsWithReviewsScreenRoot(
                                viewmodel = vm,
                                onNavigate = { productId ->
                                    vm.setProductId(productId)
                                    navController.navigate("reviewsByProductScreen")
                                }
                            )
                        }
                        composable("reviewsByProductScreen"){ nav ->
                            val vm = nav.SharedViewModel<ProductReviewsViewModel>(navController)
                            ReviewsScreenRoot(viewmodel = vm)
                        }
                    }
                }
            }
        }
    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.SharedViewModel(navController: NavController): T {
    // route of the current navigation graph
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }

    return hiltViewModel(parentEntry)

}