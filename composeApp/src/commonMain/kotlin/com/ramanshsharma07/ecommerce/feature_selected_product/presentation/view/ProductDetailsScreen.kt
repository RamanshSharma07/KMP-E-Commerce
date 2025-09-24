package com.ramanshsharma07.ecommerce.feature_selected_product.presentation.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ramanshsharma07.ecommerce.feature_selected_product.presentation.viewmodel.ProductDetailsEvent
import com.ramanshsharma07.ecommerce.feature_selected_product.presentation.viewmodel.ProductDetailsViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun ProductDetailsScreen(
    productId: String,
    onNavigateBack: () -> Unit
) {
    val viewModel: ProductDetailsViewModel = koinViewModel(
        parameters = { parametersOf(productId) }
    )
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        bottomBar = {
            ProductDetailsBottomBar(
                onBuyNowClick = { /*TODO*/ },
                onAddToCartClick = { /*TODO*/ }
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
            } else if (state.product != null) {
                val product = state.product!!
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    ProductImageCarousel(
                        imageUrls = product.imageUrls,
                        isFavorite = product.isFavorite,
                        onFavoriteClick = { /*TODO*/ },
                        onNavigateBack = onNavigateBack
                    )

                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = product.name,
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "$${product.price}",
                                style = MaterialTheme.typography.headlineSmall,
                                color = MaterialTheme.typography.headlineSmall.color,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        RatingBar(rating = product.rating, reviewCount = product.reviewCount)

                        Text("Description", style = MaterialTheme.typography.titleMedium)
                        Text(product.description, style = MaterialTheme.typography.bodyMedium)

                        Text("Size", style = MaterialTheme.typography.titleMedium)
                        SizeSelector(
                            sizes = product.availableSizes,
                            selectedSize = state.selectedSize,
                            onSizeSelected = { size ->
                                viewModel.onEvent(ProductDetailsEvent.SizeSelected(size))
                            }
                        )
                    }
                }
            }
        }
    }
}