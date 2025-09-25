package com.ramanshsharma07.ecommerce.feature_checkout.presentation.viewmodel

import com.ramanshsharma07.ecommerce.core.presentation.UiText
import com.ramanshsharma07.ecommerce.feature_checkout.domain.model.CheckoutInfo
import com.ramanshsharma07.ecommerce.feature_checkout.domain.model.PaymentMethodType

data class CheckoutState(
    val isLoading: Boolean = false,
    val checkoutInfo: CheckoutInfo? = null,
    val selectedPaymentMethod: PaymentMethodType = PaymentMethodType.CREDIT_CARD,
    val error: UiText? = null
)
