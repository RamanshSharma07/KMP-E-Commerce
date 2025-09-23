package com.ramanshsharma07.ecommerce.feature_home.domain.model

data class Product(
    val id: String,
    val name: String,
    val price: Double,
    val imageUrl: String,
    val isFavorite: Boolean = false
)