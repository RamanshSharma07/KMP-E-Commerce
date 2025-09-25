package com.ramanshsharma07.ecommerce.feature_checkout.domain.repository

import com.ramanshsharma07.ecommerce.core.domain.DataError
import com.ramanshsharma07.ecommerce.feature_checkout.domain.model.CheckoutInfo
import com.ramanshsharma07.ecommerce.core.domain.Result

interface CheckoutRepository {
    /**
     * Fetches all information required to display the checkout screen,
     * including delivery info, payment methods, and the latest order summary.
     */
    suspend fun getCheckoutInfo(): Result<CheckoutInfo, DataError>
}