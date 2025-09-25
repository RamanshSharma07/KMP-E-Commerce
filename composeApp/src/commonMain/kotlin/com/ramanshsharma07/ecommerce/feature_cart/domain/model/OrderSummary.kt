package com.ramanshsharma07.ecommerce.feature_cart.domain.model

data class OrderSummary(
    val subtotal: Double,
    val discount: Double,
    val deliveryCharges: Double,
    val total: Double
)
