package com.ramanshsharma07.ecommerce.di

import com.ramanshsharma07.ecommerce.feature_cart.data.repository.FakeCartRepositoryImpl
import com.ramanshsharma07.ecommerce.feature_cart.domain.repository.CartRepository
import com.ramanshsharma07.ecommerce.feature_cart.domain.use_cases.AddProductToCartUseCase
import com.ramanshsharma07.ecommerce.feature_cart.domain.use_cases.DeleteProductFromCartUseCase
import com.ramanshsharma07.ecommerce.feature_cart.domain.use_cases.GetCartUseCase
import com.ramanshsharma07.ecommerce.feature_cart.domain.use_cases.RemoveProductFromCartUseCase
import com.ramanshsharma07.ecommerce.feature_cart.presentation.viewmodel.CartViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val cartModule = module {
    // --- Data Layer ---
    // Use 'single' for the repository because it holds the cart's state in memory.
    // We need one and only one instance of it for the whole app.
    single<CartRepository> { FakeCartRepositoryImpl() }

    // --- Domain Layer ---
    factoryOf(::GetCartUseCase)
    factoryOf(::AddProductToCartUseCase)
    factoryOf(::RemoveProductFromCartUseCase)
    factoryOf(::DeleteProductFromCartUseCase)

    // --- Presentation Layer ---
    factoryOf(::CartViewModel)
}