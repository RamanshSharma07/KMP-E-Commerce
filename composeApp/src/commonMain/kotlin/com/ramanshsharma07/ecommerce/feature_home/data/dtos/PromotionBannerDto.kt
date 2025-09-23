package com.ramanshsharma07.ecommerce.feature_home.data.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PromotionBannerDto(
    val id: String,
    val title: String,
    val subtitle: String,
    @SerialName("image_url") // Maps snake_case from JSON to camelCase
    val imageUrl: String
)
