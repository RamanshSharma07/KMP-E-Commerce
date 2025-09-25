package com.ramanshsharma07.ecommerce.feature_cart.domain.model

import com.ramanshsharma07.ecommerce.core.domain.model.Product

data class CartItem(
    val product: Product,
    val quantity: Int
)
