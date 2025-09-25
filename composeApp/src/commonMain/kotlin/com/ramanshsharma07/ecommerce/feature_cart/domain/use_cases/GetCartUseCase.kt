package com.ramanshsharma07.ecommerce.feature_cart.domain.use_cases

import com.ramanshsharma07.ecommerce.feature_cart.domain.repository.CartRepository

class GetCartUseCase(private val repository: CartRepository) {
    suspend operator fun invoke() = repository.getCart()
}