package com.ramanshsharma07.ecommerce.core.data.mappers

import com.ramanshsharma07.ecommerce.core.data.dtos.ProductDto
import com.ramanshsharma07.ecommerce.core.domain.model.Product


fun ProductDto.toDomain(): Product {
    return Product(
        id = this.id,
        name = this.name,
        price = this.price,
        imageUrl = this.imageUrl,
        isFavorite = this.isFavorite
    )
}