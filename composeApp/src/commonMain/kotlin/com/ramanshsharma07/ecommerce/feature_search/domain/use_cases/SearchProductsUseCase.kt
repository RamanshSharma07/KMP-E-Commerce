package com.ramanshsharma07.ecommerce.feature_search.domain.use_cases

import com.ramanshsharma07.ecommerce.core.domain.DataError
import com.ramanshsharma07.ecommerce.core.domain.Result
import com.ramanshsharma07.ecommerce.core.domain.model.Product
import com.ramanshsharma07.ecommerce.feature_search.domain.repository.SearchRepository

class SearchProductsUseCase(
    private val repository: SearchRepository
) {
    suspend operator fun invoke(query: String): Result<List<Product>, DataError> {
        // Business logic: if the query is blank, return an empty list immediately
        // without hitting the data source.
        if (query.isBlank()) {
            return Result.Success(emptyList())
        }
        return repository.searchProducts(query)
    }
}