package com.ramanshsharma07.ecommerce.core.domain.use_cases

import com.ramanshsharma07.ecommerce.core.domain.repository.FavoritesRepository

class GetFavoritesIdsUseCase(private val repository: FavoritesRepository) {
    operator fun invoke() = repository.getFavoriteIds()
}