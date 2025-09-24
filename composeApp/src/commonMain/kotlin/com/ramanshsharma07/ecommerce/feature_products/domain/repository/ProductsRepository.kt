package com.ramanshsharma07.ecommerce.feature_products.domain.repository

import com.ramanshsharma07.ecommerce.core.domain.DataError
import com.ramanshsharma07.ecommerce.core.domain.model.Product
import com.ramanshsharma07.ecommerce.core.domain.Result

interface ProductsRepository {
    /**
     * Fetches a complete list of products belonging to a specific category.
     * @param category The identifier for the category (e.g., "featured").
     */
    suspend fun getProductsByCategory(category: String): Result<List<Product>, DataError>
}