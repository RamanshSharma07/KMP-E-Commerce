package com.ramanshsharma07.ecommerce.di

import com.ramanshsharma07.ecommerce.feature_search.data.repository.FakeSearchRepositoryImpl
import com.ramanshsharma07.ecommerce.feature_search.domain.repository.SearchRepository
import com.ramanshsharma07.ecommerce.feature_search.domain.use_cases.SearchProductsUseCase
import com.ramanshsharma07.ecommerce.feature_search.presentation.viewmodel.SearchViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val searchModule = module {
    // --- Data Layer ---
    factory<SearchRepository> { FakeSearchRepositoryImpl() }

    // --- Domain Layer ---
    factoryOf(::SearchProductsUseCase)

    // --- Presentation Layer ---
    // Koin will automatically provide all the necessary use cases (Search, AddToCart, Favorites)
    factoryOf(::SearchViewModel)
}