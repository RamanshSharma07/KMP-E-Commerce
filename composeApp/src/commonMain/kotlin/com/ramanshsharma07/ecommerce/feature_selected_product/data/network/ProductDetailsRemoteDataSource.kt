package com.ramanshsharma07.ecommerce.feature_selected_product.data.network

import com.ramanshsharma07.ecommerce.core.domain.DataError
import com.ramanshsharma07.ecommerce.feature_selected_product.data.dtos.ProductDetailsDto
import com.ramanshsharma07.ecommerce.core.domain.Result

interface ProductDetailsRemoteDataSource {
    suspend fun getProductDetails(productId: String): Result<ProductDetailsDto, DataError.Remote>
}