package com.ramanshsharma07.ecommerce.di

import com.ramanshsharma07.ecommerce.feature_selected_product.data.network.FakeProductDetailsRemoteDataSource
import com.ramanshsharma07.ecommerce.feature_selected_product.data.network.ProductDetailsRemoteDataSource
import com.ramanshsharma07.ecommerce.feature_selected_product.data.repository.ProductDetailsRepositoryImpl
import com.ramanshsharma07.ecommerce.feature_selected_product.domain.repository.ProductDetailsRepository
import com.ramanshsharma07.ecommerce.feature_selected_product.domain.use_cases.GetProductDetailsUseCase
import com.ramanshsharma07.ecommerce.feature_selected_product.presentation.viewmodel.ProductDetailsViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val productDetailsModule = module {
    // --- Data Layer ---
    factory<ProductDetailsRemoteDataSource> { FakeProductDetailsRemoteDataSource() }
    factory<ProductDetailsRepository> { ProductDetailsRepositoryImpl(get()) }

    // --- Domain Layer ---
    factoryOf(::GetProductDetailsUseCase)

    // --- Presentation Layer ---
    // The ProductDetailsViewModel needs a 'productId' string passed at runtime.
    // 'params.get()' retrieves this parameter when the ViewModel is created.
    factory { params ->
        ProductDetailsViewModel(
            productId = params.get(),
            getProductDetailsUseCase = get()
        )
    }
}