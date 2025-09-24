package com.ramanshsharma07.ecommerce.feature_selected_product.presentation.viewmodel

sealed interface ProductDetailsEvent {
    data class SizeSelected(val size: String) : ProductDetailsEvent
    data object AddToCartClicked : ProductDetailsEvent
    data object FavoriteClicked : ProductDetailsEvent
}