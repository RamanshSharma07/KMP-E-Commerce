package com.ramanshsharma07.ecommerce.feature_products.data.network

import com.ramanshsharma07.ecommerce.core.data.dtos.ProductDto
import com.ramanshsharma07.ecommerce.core.domain.DataError
import com.ramanshsharma07.ecommerce.core.domain.Result

interface ProductsRemoteDataSource {
    suspend fun getProductsByCategory(category: String): Result<List<ProductDto>, DataError.Remote>
}