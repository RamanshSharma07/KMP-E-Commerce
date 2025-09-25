package com.ramanshsharma07.ecommerce.feature_checkout.data.repository

import com.ramanshsharma07.ecommerce.core.domain.DataError
import com.ramanshsharma07.ecommerce.core.domain.Result
import com.ramanshsharma07.ecommerce.feature_cart.domain.repository.CartRepository
import com.ramanshsharma07.ecommerce.feature_checkout.domain.model.CheckoutInfo
import com.ramanshsharma07.ecommerce.feature_checkout.domain.model.DeliveryDetails
import com.ramanshsharma07.ecommerce.feature_checkout.domain.model.PaymentMethod
import com.ramanshsharma07.ecommerce.feature_checkout.domain.model.PaymentMethodType
import com.ramanshsharma07.ecommerce.feature_checkout.domain.repository.CheckoutRepository
import kotlinx.coroutines.delay

class FakeCheckoutRepositoryImpl(
    private val cartRepository: CartRepository
) : CheckoutRepository {

    override suspend fun getCheckoutInfo(): Result<CheckoutInfo, DataError> {
        delay(500L)

        val cartResult = cartRepository.getCart()

        return when (cartResult) {
            is Result.Success -> {
                val orderSummary = cartResult.data.summary

                val deliveryDetails = DeliveryDetails(
                    address = "325 15th Eighth Avenue, NewYork",
                    deliveryTime = "6:00 pm, Wednesday 20"
                )

                val paymentMethods = listOf(
                    PaymentMethod(PaymentMethodType.PAYPAL, "Paypal", "paypal_icon"),
                    PaymentMethod(PaymentMethodType.CREDIT_CARD, "Credit Card", "credit_card_icon"),
                    PaymentMethod(PaymentMethodType.CASH, "Cash", "cash_icon")
                )

                val checkoutInfo = CheckoutInfo(
                    deliveryDetails = deliveryDetails,
                    orderSummary = orderSummary,
                    paymentMethods = paymentMethods
                )
                Result.Success(checkoutInfo)
            }
            is Result.Error -> {
                cartResult
            }
        }
    }
}