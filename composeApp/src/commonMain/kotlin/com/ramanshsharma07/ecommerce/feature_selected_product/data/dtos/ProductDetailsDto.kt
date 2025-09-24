package com.ramanshsharma07.ecommerce.feature_selected_product.data.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDetailsDto(
    val id: String,
    val name: String,
    val price: Double,
    @SerialName("image_urls")
    val imageUrls: List<String>,
    @SerialName("is_favorite")
    val isFavorite: Boolean,
    val rating: Double,
    @SerialName("review_count")
    val reviewCount: Int,
    val description: String,
    @SerialName("available_sizes")
    val availableSizes: List<String>
)
