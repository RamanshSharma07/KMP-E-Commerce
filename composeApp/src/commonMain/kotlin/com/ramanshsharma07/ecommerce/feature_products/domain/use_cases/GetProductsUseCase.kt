package com.ramanshsharma07.ecommerce.feature_products.domain.use_cases

import com.ramanshsharma07.ecommerce.core.domain.DataError
import com.ramanshsharma07.ecommerce.core.domain.model.Product
import com.ramanshsharma07.ecommerce.feature_products.domain.repository.ProductsRepository
import com.ramanshsharma07.ecommerce.core.domain.Result

class GetProductsUseCase(
    private val productsRepository: ProductsRepository
) {
    suspend operator fun invoke(category: String): Result<List<Product>, DataError> {
        return productsRepository.getProductsByCategory(category)
    }
}