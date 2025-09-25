package com.ramanshsharma07.ecommerce.feature_cart.presentation.viewmodel

import com.ramanshsharma07.ecommerce.core.presentation.UiText
import com.ramanshsharma07.ecommerce.feature_cart.domain.model.Cart

data class CartState(
    val isLoading: Boolean = false,
    val cart: Cart? = null,
    val error: UiText? = null
)
