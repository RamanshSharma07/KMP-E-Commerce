package com.ramanshsharma07.ecommerce.feature_home.presentation.viewmodel

import com.ramanshsharma07.ecommerce.core.presentation.UiText
import com.ramanshsharma07.ecommerce.feature_home.domain.model.ProductSection
import com.ramanshsharma07.ecommerce.feature_home.domain.model.PromotionBanner

data class HomeState(
    val isLoading: Boolean = false,
    val banners: List<PromotionBanner> = emptyList(),
    val productSections: List<ProductSection> = emptyList(),
    val error: UiText? = null
)