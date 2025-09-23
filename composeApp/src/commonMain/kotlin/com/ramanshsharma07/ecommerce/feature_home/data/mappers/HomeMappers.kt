package com.ramanshsharma07.ecommerce.feature_home.data.mappers

import com.ramanshsharma07.ecommerce.feature_home.data.dtos.HomePageResponseDto
import com.ramanshsharma07.ecommerce.feature_home.data.dtos.ProductDto
import com.ramanshsharma07.ecommerce.feature_home.data.dtos.ProductSectionDto
import com.ramanshsharma07.ecommerce.feature_home.data.dtos.PromotionBannerDto
import com.ramanshsharma07.ecommerce.feature_home.domain.model.Product
import com.ramanshsharma07.ecommerce.feature_home.domain.model.ProductSection
import com.ramanshsharma07.ecommerce.feature_home.domain.model.PromotionBanner

fun ProductDto.toDomain(): Product {
    return Product(
        id = this.id,
        name = this.name,
        price = this.price,
        imageUrl = this.imageUrl,
        isFavorite = this.isFavorite
    )
}

fun PromotionBannerDto.toDomain(): PromotionBanner {
    return PromotionBanner(
        id = this.id,
        title = this.title,
        subtitle = this.subtitle,
        imageUrl = this.imageUrl
    )
}

fun ProductSectionDto.toDomain(): ProductSection {
    return ProductSection(
        title = this.title,
        products = this.products.map { it.toDomain() }
    )
}

fun HomePageResponseDto.toDomain(): Pair<List<PromotionBanner>, List<ProductSection>> {
    val banners = this.banners.map { it.toDomain() }
    val sections = this.sections.map { it.toDomain() }
    return Pair(banners, sections)
}