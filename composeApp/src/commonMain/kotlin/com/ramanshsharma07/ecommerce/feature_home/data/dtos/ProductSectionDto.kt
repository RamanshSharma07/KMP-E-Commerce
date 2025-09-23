package com.ramanshsharma07.ecommerce.feature_home.data.dtos

import kotlinx.serialization.Serializable

@Serializable
data class ProductSectionDto(
    val title: String,
    val products: List<ProductDto>
)
