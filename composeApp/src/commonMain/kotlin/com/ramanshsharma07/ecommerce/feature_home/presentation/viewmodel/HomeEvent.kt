package com.ramanshsharma07.ecommerce.feature_home.presentation.viewmodel

sealed interface HomeEvent {
    data class OnFavoriteClick(val productId: String) : HomeEvent
}