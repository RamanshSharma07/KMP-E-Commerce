package com.ramanshsharma07.ecommerce.feature_products.presentation.viewmodel

import com.ramanshsharma07.ecommerce.core.domain.model.Product
import com.ramanshsharma07.ecommerce.core.presentation.UiText

data class ProductsState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val title: String = "Products",
    val error: UiText? = null
)
