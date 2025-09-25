package com.ramanshsharma07.ecommerce.feature_search.domain.repository

import com.ramanshsharma07.ecommerce.core.domain.DataError
import com.ramanshsharma07.ecommerce.core.domain.Result
import com.ramanshsharma07.ecommerce.core.domain.model.Product

interface SearchRepository {
    /**
     * Searches for products that match a given query.
     * @param query The text to search for.
     * @return A list of matching products.
     */
    suspend fun searchProducts(query: String): Result<List<Product>, DataError>
}