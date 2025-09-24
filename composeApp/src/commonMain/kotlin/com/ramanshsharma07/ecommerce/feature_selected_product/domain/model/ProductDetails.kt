package com.ramanshsharma07.ecommerce.feature_selected_product.domain.model

data class ProductDetails(
    val id: String,
    val name: String,
    val price: Double,
    val imageUrls: List<String>, // A list for multiple product images
    val isFavorite: Boolean,
    val rating: Double,
    val reviewCount: Int,
    val description: String,
    val availableSizes: List<String>
)
