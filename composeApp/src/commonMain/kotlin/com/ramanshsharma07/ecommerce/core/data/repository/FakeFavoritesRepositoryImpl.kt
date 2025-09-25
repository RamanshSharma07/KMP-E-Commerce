package com.ramanshsharma07.ecommerce.core.data.repository

import com.ramanshsharma07.ecommerce.core.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FakeFavoritesRepositoryImpl : FavoritesRepository {
    private val _favoriteIds = MutableStateFlow(
        setOf("p2", "p8", "p11")
    )

    override fun getFavoriteIds(): Flow<Set<String>> {
        return _favoriteIds.asStateFlow()
    }

    override suspend fun toggleFavoriteStatus(productId: String) {
        _favoriteIds.update { currentIds ->
            if (currentIds.contains(productId)) {
                currentIds - productId
            } else {
                currentIds + productId
            }
        }
    }
}