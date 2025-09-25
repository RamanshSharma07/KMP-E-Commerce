package com.ramanshsharma07.ecommerce.feature_search.presentation.viewmodel

sealed interface SearchEvent {
    data class OnQueryChange(val query: String) : SearchEvent
    data object OnClearQuery : SearchEvent
    data class OnFavoriteClick(val productId: String) : SearchEvent
    data class OnAddToCartClick(val productId: String) : SearchEvent
}