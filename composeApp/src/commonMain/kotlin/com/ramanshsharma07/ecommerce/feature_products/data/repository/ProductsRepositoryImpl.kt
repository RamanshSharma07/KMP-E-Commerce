package com.ramanshsharma07.ecommerce.feature_products.data.repository

import com.ramanshsharma07.ecommerce.core.domain.DataError
import com.ramanshsharma07.ecommerce.core.domain.model.Product
import com.ramanshsharma07.ecommerce.feature_products.data.network.ProductsRemoteDataSource
import com.ramanshsharma07.ecommerce.feature_products.domain.repository.ProductsRepository
import com.ramanshsharma07.ecommerce.core.domain.Result
import com.ramanshsharma07.ecommerce.core.domain.map
import com.ramanshsharma07.ecommerce.core.data.mappers.toDomain

class ProductsRepositoryImpl(
    private val remoteDataSource: ProductsRemoteDataSource
) : ProductsRepository {

    override suspend fun getProductsByCategory(category: String): Result<List<Product>, DataError> {
        return remoteDataSource.getProductsByCategory(category).map { productDtos ->
            // Map the list of DTOs to a list of Domain Models
            productDtos.map { it.toDomain() }
        }
    }
}