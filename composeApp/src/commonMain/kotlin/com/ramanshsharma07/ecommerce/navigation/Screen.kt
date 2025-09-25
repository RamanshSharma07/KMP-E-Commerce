package com.ramanshsharma07.ecommerce.navigation

sealed class Screen(val route: String) {

    data object Home : Screen("home")

    data class Products(val category: String = "{category}") : Screen("products/{category}") {
        fun createRoute(category: String) = "products/$category"
    }

    data class Details(val productId: String = "{productId}") : Screen("details/{productId}") {
        fun createRoute(productId: String) = "details/$productId"
    }

    data object Cart : Screen("cart")

    data object Checkout : Screen("checkout")
}