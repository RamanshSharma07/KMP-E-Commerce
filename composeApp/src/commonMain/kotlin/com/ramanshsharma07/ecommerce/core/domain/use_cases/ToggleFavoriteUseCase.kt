package com.ramanshsharma07.ecommerce.core.domain.use_cases

import com.ramanshsharma07.ecommerce.core.domain.repository.FavoritesRepository

class ToggleFavoriteUseCase(private val repository: FavoritesRepository) {
    suspend operator fun invoke(productId: String) = repository.toggleFavoriteStatus(productId)
}