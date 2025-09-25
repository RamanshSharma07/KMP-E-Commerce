package com.ramanshsharma07.ecommerce.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ramanshsharma07.ecommerce.feature_cart.presentation.view.CartScreen
import com.ramanshsharma07.ecommerce.feature_home.presentation.view.HomeScreen
import com.ramanshsharma07.ecommerce.feature_products.presentation.view.ProductsScreen
import com.ramanshsharma07.ecommerce.feature_selected_product.presentation.view.ProductDetailsScreen

@Composable
fun NavigationHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {

        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateToProducts = { category:String ->
                    navController.navigate(Screen.Products().createRoute(category))
                },
                onNavigateToDetails = { productId -> // Pass the navigation action
                    navController.navigate(Screen.Details().createRoute(productId))
                    println("Product ID: $productId")
                },
            )
        }

        composable(
            route = Screen.Products().route,
            arguments = listOf(navArgument("category") { type = NavType.StringType })
        ) { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category")
            println("Category: $category")

            if (category != null) {
                ProductsScreen(
                    category = category,
                    onNavigateToDetails = { productId -> // Pass the navigation action
                        navController.navigate(Screen.Details().createRoute(productId))
                        println("Product ID: $productId")
                    },
                    onNavigateBack = {
                        navController.popBackStack()
                    }
                )
            }
        }

        composable(
            route = Screen.Details().route,
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")
            if (productId != null) {
                ProductDetailsScreen(
                    productId = productId,
                    onNavigateBack = {
                        navController.popBackStack()
                    },
                    onNavigateToCart = {
                        navController.navigate(Screen.Cart.route)
                    }
                )
            }
        }

        composable(Screen.Cart.route) {
            CartScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }

}