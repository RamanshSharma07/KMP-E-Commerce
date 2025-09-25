package com.ramanshsharma07.ecommerce.feature_cart.presentation.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ramanshsharma07.ecommerce.feature_cart.presentation.viewmodel.CartEvent
import com.ramanshsharma07.ecommerce.feature_cart.presentation.viewmodel.CartViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    onNavigateBack: () -> Unit
) {
    val viewModel: CartViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cart") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { /* More options */ }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "More")
                    }
                }
            )
        },
        bottomBar = {
            Button(
                onClick = { viewModel.onEvent(CartEvent.CheckoutClicked) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(56.dp)
            ) {
                Text("Check Out")
            }
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
            } else if (state.cart != null) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(state.cart!!.items) { cartItem ->
                        CartItemCard(
                            item = cartItem,
                            onIncrease = { viewModel.onEvent(CartEvent.IncreaseQuantity(cartItem.product.id)) },
                            onDecrease = { viewModel.onEvent(CartEvent.DecreaseQuantity(cartItem.product.id)) },
                            onDelete = { viewModel.onEvent(CartEvent.DeleteItem(cartItem.product.id)) }
                        )
                    }
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                        OrderSummaryCard(summary = state.cart!!.summary)
                    }
                }
            }
        }
    }
}