package com.ramanshsharma07.ecommerce.di

import com.ramanshsharma07.ecommerce.feature_products.data.network.FakeProductsRemoteDataSource
import com.ramanshsharma07.ecommerce.feature_products.data.network.ProductsRemoteDataSource
import com.ramanshsharma07.ecommerce.feature_products.data.repository.ProductsRepositoryImpl
import com.ramanshsharma07.ecommerce.feature_products.domain.repository.ProductsRepository
import com.ramanshsharma07.ecommerce.feature_products.domain.use_cases.GetProductsUseCase
import com.ramanshsharma07.ecommerce.feature_products.presentation.viewmodel.ProductsViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val productsModule = module {
    // --- Data Layer ---
    factory<ProductsRemoteDataSource> { FakeProductsRemoteDataSource() }
    factory<ProductsRepository> { ProductsRepositoryImpl(get()) }

    // --- Domain Layer ---
    factoryOf(::GetProductsUseCase)

    // --- Presentation Layer ---
    // The ProductsViewModel needs a 'category' string passed at runtime.
    // 'params.get()' retrieves this parameter when the ViewModel is created.
    factory { params ->
        ProductsViewModel(
            category = params.get(),
            getProductsUseCase = get()
        )
    }
}