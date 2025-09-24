package com.ramanshsharma07.ecommerce.feature_home.domain.model

import com.ramanshsharma07.ecommerce.core.domain.model.Product

data class ProductSection(
    val title: String,
    val products: List<Product>
)
