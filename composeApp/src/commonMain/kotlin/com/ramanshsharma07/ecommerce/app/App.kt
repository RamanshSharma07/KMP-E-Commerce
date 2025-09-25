package com.ramanshsharma07.ecommerce.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ramanshsharma07.ecommerce.core.presentation.BottomNavBar
import com.ramanshsharma07.ecommerce.navigation.NavigationHost
import com.ramanshsharma07.ecommerce.navigation.Screen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {

        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        Scaffold(
            bottomBar = {
                when (currentRoute) {
                    Screen.Home.route, Screen.Search.route, Screen.Profile.route -> {
                        BottomNavBar(navController = navController)
                    }
                }
            }
        ) { innerPadding ->
            NavigationHost(navController, padding = innerPadding)
        }
//        HomeScreen()
//        ProductsScreen("most_popular") {}
//        ProductDetailsScreen("p1"){}
    }
}