package com.ramanshsharma07.ecommerce.feature_selected_product.domain.repository

import com.ramanshsharma07.ecommerce.core.domain.DataError
import com.ramanshsharma07.ecommerce.feature_selected_product.domain.model.ProductDetails
import com.ramanshsharma07.ecommerce.core.domain.Result

interface ProductDetailsRepository {
    /**
     * Fetches the detailed information for a single product using its ID.
     * @param productId The unique ID of the product to fetch.
     */
    suspend fun getProductDetails(productId: String): Result<ProductDetails, DataError>
}