package com.example.projecttemplateexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.projecttemplateexample.ui.theme.ProjectTemplateExampleTheme
import com.example.projecttemplateexample.vm.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjectTemplateExampleTheme {
                val navController = rememberNavController()
                val scope = rememberCoroutineScope()
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                ModalNavigationDrawer(drawerContent = {
                    ModalDrawerSheet {
                        NavigationDrawerItem(label = {
                            Text("Users")
                        }, onClick = {}, selected = true)
                    }
                }, drawerState = drawerState) {
                    NavHost(navController = navController, startDestination = "userFeature") {
                        composable("creditScreen"){
                            Text("CreditScreen")
                        }
                        navigation(
                            "usersScreen",
                            route = "userFeature",
                        ){
                            composable("usersScreen"){ nav ->
                                var vm =  nav.SharedViewModel<UsersViewModel>(navController)
                                UsersScreenRoot(vm=vm, onNavigate = {
                                    navController.navigate("userScreen")
                                }, onMenuClick = {
                                    scope.launch {
                                        drawerState.open()
                                    }
                                })
                            }
                            composable("userScreen"){ nav ->
                                var vm = nav.SharedViewModel<UsersViewModel>(navController)
                                UserScreenRoot(vm=vm)
                            }
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
    val parentEntry = remember(this){
        navController.getBackStackEntry(navGraphRoute)
    }

    return hiltViewModel(parentEntry)

}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProjectTemplateExampleTheme {
        Greeting("Android")
    }
}
