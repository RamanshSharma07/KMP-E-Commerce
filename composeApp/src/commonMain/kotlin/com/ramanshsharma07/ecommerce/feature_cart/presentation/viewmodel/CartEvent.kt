package com.ramanshsharma07.ecommerce.feature_cart.presentation.viewmodel

sealed interface CartEvent {
    data class IncreaseQuantity(val productId: String) : CartEvent
    data class DecreaseQuantity(val productId: String) : CartEvent
    data class DeleteItem(val productId: String) : CartEvent
    data object CheckoutClicked : CartEvent
}