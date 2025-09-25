package com.ramanshsharma07.ecommerce.feature_home.presentation.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ramanshsharma07.ecommerce.feature_home.presentation.viewmodel.HomeEvent
import com.ramanshsharma07.ecommerce.feature_home.presentation.viewmodel.HomeViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(onNavigateToProducts: (String) -> Unit, onNavigateToDetails: (String) -> Unit) {
    val viewModel: HomeViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            HomeTopBar(userName = "John William")
        },
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
                LazyColumn(modifier = Modifier.fillMaxSize()) {

                    item {
                        Spacer(modifier = Modifier.height(16.dp))

                        SearchBar {
                            //Todo
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    // Promotions Banner
                    item {
                        PromotionsCarousel(banners = state.banners)
                    }

                    // Product Sections ("Featured", "Most Popular")
                    items(state.productSections) { section ->
                        ProductSectionRow(
                            section = section,
                            onNavigateToProducts = onNavigateToProducts,
                            onNavigateToDetails = onNavigateToDetails,
                            onFavoriteClick = { productId ->
                                viewModel.onEvent(HomeEvent.OnFavoriteClick(productId))
                            }
                        )

                        Spacer(modifier = Modifier.height(16.dp))
                        
                    }
                }
            }
        }
    }
}