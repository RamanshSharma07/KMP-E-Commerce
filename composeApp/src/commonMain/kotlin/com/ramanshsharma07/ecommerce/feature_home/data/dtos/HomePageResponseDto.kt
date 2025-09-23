package com.ramanshsharma07.ecommerce.feature_home.data.dtos

import kotlinx.serialization.Serializable

@Serializable
data class HomePageResponseDto(
    val banners: List<PromotionBannerDto>,
    val sections: List<ProductSectionDto>
)
