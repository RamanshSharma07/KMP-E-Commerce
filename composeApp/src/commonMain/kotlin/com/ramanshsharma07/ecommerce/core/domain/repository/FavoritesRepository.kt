package com.ramanshsharma07.ecommerce.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    // A Flow that continuously emits the set of all favorite product IDs
    fun getFavoriteIds(): Flow<Set<String>>

    // A function to add or remove a product from favorites
    suspend fun toggleFavoriteStatus(productId: String)
}