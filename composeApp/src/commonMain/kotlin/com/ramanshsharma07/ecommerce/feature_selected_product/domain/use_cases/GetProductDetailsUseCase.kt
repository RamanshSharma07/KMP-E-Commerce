package com.ramanshsharma07.ecommerce.feature_selected_product.domain.use_cases

import com.ramanshsharma07.ecommerce.core.domain.DataError
import com.ramanshsharma07.ecommerce.feature_selected_product.domain.model.ProductDetails
import com.ramanshsharma07.ecommerce.feature_selected_product.domain.repository.ProductDetailsRepository
import com.ramanshsharma07.ecommerce.core.domain.Result

class GetProductDetailsUseCase(
    private val repository: ProductDetailsRepository
) {
    suspend operator fun invoke(productId: String): Result<ProductDetails, DataError> {
        return repository.getProductDetails(productId)
    }
}