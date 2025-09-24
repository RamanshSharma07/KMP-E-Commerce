package com.ramanshsharma07.ecommerce.app

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ramanshsharma07.ecommerce.Greeting
import com.ramanshsharma07.ecommerce.feature_home.presentation.view.HomeScreen
import com.ramanshsharma07.ecommerce.feature_products.presentation.view.ProductsScreen
import com.ramanshsharma07.ecommerce.feature_selected_product.presentation.view.ProductDetailsScreen
import com.ramanshsharma07.ecommerce.navigation.NavigationHost
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import kmp_e_commerce.composeapp.generated.resources.Res
import kmp_e_commerce.composeapp.generated.resources.compose_multiplatform

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