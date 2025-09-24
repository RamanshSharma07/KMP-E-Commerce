package com.ramanshsharma07.ecommerce.feature_home.data.dtos

import com.ramanshsharma07.ecommerce.core.data.dtos.ProductDto
import kotlinx.serialization.Serializable

@Serializable
data class ProductSectionDto(
    val title: String,
    val products: List<ProductDto>
)
