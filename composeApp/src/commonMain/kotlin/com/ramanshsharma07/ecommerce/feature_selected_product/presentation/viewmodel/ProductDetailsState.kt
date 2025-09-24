package com.ramanshsharma07.ecommerce.feature_selected_product.presentation.viewmodel

import com.ramanshsharma07.ecommerce.core.presentation.UiText
import com.ramanshsharma07.ecommerce.feature_selected_product.domain.model.ProductDetails

data class ProductDetailsState(
    val isLoading: Boolean = false,
    val product: ProductDetails? = null,
    val selectedSize: String? = null,
    val error: UiText? = null
)