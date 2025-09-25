package com.ramanshsharma07.ecommerce.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ramanshsharma07.ecommerce.feature_cart.presentation.view.CartScreen
import com.ramanshsharma07.ecommerce.feature_checkout.presentation.view.CheckoutScreen
import com.ramanshsharma07.ecommerce.feature_home.presentation.view.HomeScreen
import com.ramanshsharma07.ecommerce.feature_products.presentation.view.ProductsScreen
import com.ramanshsharma07.ecommerce.feature_profile.presentation.view.ProfileScreen
import com.ramanshsharma07.ecommerce.feature_search.presentation.view.SearchScreen
import com.ramanshsharma07.ecommerce.feature_selected_product.presentation.view.ProductDetailsScreen
import com.ramanshsharma07.ecommerce.feature_settings.presentation.view.SettingsScreen

@Composable
fun NavigationHost(
    navController: NavHostController,
    padding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {

        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateToProducts = { category:String ->
                    navController.navigate(Screen.Products().createRoute(category))
                },
                onNavigateToDetails = { productId ->
                    navController.navigate(Screen.Details().createRoute(productId))
                    println("Product ID: $productId")
                },
                onNavigateToSettings = {
                    navController.navigate(Screen.Settings.route)
                },
                onNavigateToSearch = {
                    navController.navigate(Screen.Search.route)
                },
                padding = padding
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
                    onNavigateToDetails = { productId ->
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
                },
                onNavigateToCheckout = {
                    navController.navigate(Screen.Checkout.route)
                }
            )
        }

        composable(Screen.Checkout.route) {
            CheckoutScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.Profile.route) {
            ProfileScreen(
                onNavigateToSettings = {
                    navController.navigate(Screen.Settings.route)
                }
            )
        }

        composable(Screen.Settings.route) {
            SettingsScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.Search.route) {
            SearchScreen(
                onNavigateToDetails = { productId ->
                    navController.navigate(Screen.Details().createRoute(productId))
                },
                padding = padding
            )
        }
    }

}