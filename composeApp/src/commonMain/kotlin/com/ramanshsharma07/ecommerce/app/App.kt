package com.ramanshsharma07.ecommerce.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.ramanshsharma07.ecommerce.navigation.NavigationHost
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
//        HomeScreen()
//        ProductsScreen("most_popular") {}
        NavigationHost()
//        ProductDetailsScreen("p1"){}
    }
}