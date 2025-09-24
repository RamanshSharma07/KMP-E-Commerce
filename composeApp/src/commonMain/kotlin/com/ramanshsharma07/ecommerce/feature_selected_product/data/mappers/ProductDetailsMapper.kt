package com.ramanshsharma07.ecommerce.feature_selected_product.data.mappers

import com.ramanshsharma07.ecommerce.feature_selected_product.data.dtos.ProductDetailsDto
import com.ramanshsharma07.ecommerce.feature_selected_product.domain.model.ProductDetails

fun ProductDetailsDto.toDomain(): ProductDetails {
    return ProductDetails(
        id = this.id,
        name = this.name,
        price = this.price,
        imageUrls = this.imageUrls,
        isFavorite = this.isFavorite,
        rating = this.rating,
        reviewCount = this.reviewCount,
        description = this.description,
        availableSizes = this.availableSizes
    )
}