package com.ramanshsharma07.ecommerce.feature_cart.domain.repository

import com.ramanshsharma07.ecommerce.core.domain.DataError
import com.ramanshsharma07.ecommerce.feature_cart.domain.model.Cart
import com.ramanshsharma07.ecommerce.core.domain.Result

interface CartRepository {
    /** Fetches the entire cart content. */
    suspend fun getCart(): Result<Cart, DataError>

    /** Increases the quantity of a product by one. */
    suspend fun addProductToCart(productId: String): Result<Cart, DataError>

    /** Decreases the quantity of a product by one. */
    suspend fun removeProductFromCart(productId: String): Result<Cart, DataError>

    /** Deletes a product line from the cart completely. */
    suspend fun deleteProductFromCart(productId: String): Result<Cart, DataError>
}