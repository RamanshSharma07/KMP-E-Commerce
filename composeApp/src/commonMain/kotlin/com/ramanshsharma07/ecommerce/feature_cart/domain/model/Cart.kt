package com.ramanshsharma07.ecommerce.feature_cart.domain.model

data class Cart(
    val items: List<CartItem>,
    val summary: OrderSummary
)
