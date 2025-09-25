package com.ramanshsharma07.ecommerce.feature_checkout.domain.use_cases

import com.ramanshsharma07.ecommerce.feature_checkout.domain.repository.CheckoutRepository

class GetCheckoutInfoUseCase(
    private val checkoutRepository: CheckoutRepository
) {
    suspend operator fun invoke() = checkoutRepository.getCheckoutInfo()
}