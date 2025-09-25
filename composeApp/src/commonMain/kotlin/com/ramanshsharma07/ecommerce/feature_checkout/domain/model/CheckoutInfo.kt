package com.ramanshsharma07.ecommerce.feature_checkout.domain.model

import com.ramanshsharma07.ecommerce.feature_cart.domain.model.OrderSummary

data class CheckoutInfo(
    val deliveryDetails: DeliveryDetails,
    val orderSummary: OrderSummary,
    val paymentMethods: List<PaymentMethod>
)
