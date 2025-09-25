package com.ramanshsharma07.ecommerce.feature_products.presentation.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ramanshsharma07.ecommerce.feature_products.presentation.viewmodel.ProductsEvent
import com.ramanshsharma07.ecommerce.feature_products.presentation.viewmodel.ProductsViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsScreen(
    category: String, // This will be passed during navigation
    onNavigateBack: () -> Unit,
    onNavigateToDetails: (String) -> Unit
) {
    // Koin will create a ViewModel and pass the 'category' string to its constructor
    val viewModel: ProductsViewModel = koinViewModel(
        parameters = { parametersOf(category) }
    )
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = state.title + " Products",
//                        modifier = Modifier.fillMaxWidth(),
//                        textAlign = TextAlign.Center
                        fontWeight = Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            if (state.isLoading) {
                CircularProgressIndicator()
            } else if (state.error != null) {
                Text(text = state.error!!.asString())
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2), // Creates a two-column grid
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(state.products) { product ->
                        ProductGridItem(
                            product = product,
                            onNavigateToDetails = onNavigateToDetails,
                            onAddToCartClick = {
                                viewModel.onEvent(ProductsEvent.AddToCart(product.id))
                            }
                        )
                    }
                }
            }
        }
    }
}