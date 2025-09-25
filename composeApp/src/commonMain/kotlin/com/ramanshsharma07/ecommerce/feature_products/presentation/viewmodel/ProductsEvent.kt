package com.ramanshsharma07.ecommerce.feature_products.presentation.viewmodel

interface ProductsEvent {
    data class AddToCart(val productId: String) : ProductsEvent
}