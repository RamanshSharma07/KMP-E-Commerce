package com.ramanshsharma07.ecommerce.di

import com.ramanshsharma07.ecommerce.feature_checkout.data.repository.FakeCheckoutRepositoryImpl
import com.ramanshsharma07.ecommerce.feature_checkout.domain.repository.CheckoutRepository
import com.ramanshsharma07.ecommerce.feature_checkout.domain.use_cases.GetCheckoutInfoUseCase
import com.ramanshsharma07.ecommerce.feature_checkout.presentation.viewmodel.CheckoutViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val checkoutModule = module {
    // --- Data Layer ---
    // The CheckoutRepository needs the CartRepository, which Koin will automatically provide with get().
    factory<CheckoutRepository> { FakeCheckoutRepositoryImpl(get()) }

    // --- Domain Layer ---
    factoryOf(::GetCheckoutInfoUseCase)

    // --- Presentation Layer ---
    factoryOf(::CheckoutViewModel)
}