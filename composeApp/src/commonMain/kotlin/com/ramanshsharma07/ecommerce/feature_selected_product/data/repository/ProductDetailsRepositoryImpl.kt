package com.ramanshsharma07.ecommerce.feature_selected_product.data.repository

import com.ramanshsharma07.ecommerce.core.domain.DataError
import com.ramanshsharma07.ecommerce.feature_selected_product.data.network.ProductDetailsRemoteDataSource
import com.ramanshsharma07.ecommerce.feature_selected_product.domain.model.ProductDetails
import com.ramanshsharma07.ecommerce.feature_selected_product.domain.repository.ProductDetailsRepository
import com.ramanshsharma07.ecommerce.core.domain.map
import com.ramanshsharma07.ecommerce.feature_selected_product.data.mappers.toDomain
import com.ramanshsharma07.ecommerce.core.domain.Result

class ProductDetailsRepositoryImpl(
    private val remoteDataSource: ProductDetailsRemoteDataSource
) : ProductDetailsRepository {

    override suspend fun getProductDetails(productId: String): Result<ProductDetails, DataError> {
        return remoteDataSource.getProductDetails(productId).map { it.toDomain() }
    }
}