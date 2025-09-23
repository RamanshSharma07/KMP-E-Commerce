package com.ramanshsharma07.ecommerce.feature_home.data.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    val id: String,
    val name: String,
    val price: Double,
    @SerialName("image_url")
    val imageUrl: String,
    @SerialName("is_favorite")
    val isFavorite: Boolean
)
