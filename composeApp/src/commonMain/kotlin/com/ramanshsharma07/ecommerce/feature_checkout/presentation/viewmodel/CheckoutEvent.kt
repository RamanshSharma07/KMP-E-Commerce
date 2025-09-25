package com.ramanshsharma07.ecommerce.feature_checkout.presentation.viewmodel

import com.ramanshsharma07.ecommerce.feature_checkout.domain.model.PaymentMethodType

sealed interface CheckoutEvent {
    data class SelectPaymentMethod(val method: PaymentMethodType) : CheckoutEvent
    data object PlaceOrder : CheckoutEvent
}