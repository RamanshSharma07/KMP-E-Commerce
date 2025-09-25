package com.ramanshsharma07.ecommerce.feature_checkout.domain.model

data class PaymentMethod(
    val type: PaymentMethodType,
    val name: String,
    val icon: String
)
