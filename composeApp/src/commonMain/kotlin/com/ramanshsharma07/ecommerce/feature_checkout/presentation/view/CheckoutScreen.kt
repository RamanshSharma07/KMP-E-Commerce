package com.ramanshsharma07.ecommerce.feature_checkout.presentation.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ramanshsharma07.ecommerce.core.presentation.purple
import com.ramanshsharma07.ecommerce.feature_cart.presentation.view.OrderSummaryCard
import com.ramanshsharma07.ecommerce.feature_checkout.presentation.viewmodel.CheckoutEvent
import com.ramanshsharma07.ecommerce.feature_checkout.presentation.viewmodel.CheckoutViewModel
import org.koin.compose.viewmodel.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(
    onNavigateBack: () -> Unit
) {
    val viewModel: CheckoutViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Check Out") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {
            Button(
                onClick = { viewModel.onEvent(CheckoutEvent.PlaceOrder) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors().copy(
                    containerColor = purple
                )
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
            } else if (state.checkoutInfo != null) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    CheckoutInfoCard(details = state.checkoutInfo!!.deliveryDetails)
                    Spacer(Modifier.weight(1f))
                    OrderSummaryCard(summary = state.checkoutInfo!!.orderSummary)
                    PaymentMethodSelector(
                        methods = state.checkoutInfo!!.paymentMethods,
                        selectedMethod = state.selectedPaymentMethod,
                        onMethodSelected = {
                            viewModel.onEvent(CheckoutEvent.SelectPaymentMethod(it))
                        }
                    )
                }
            }
        }
    }
}