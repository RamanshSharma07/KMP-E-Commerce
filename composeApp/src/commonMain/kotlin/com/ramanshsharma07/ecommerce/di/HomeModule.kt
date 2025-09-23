package com.ramanshsharma07.ecommerce.di

import com.ramanshsharma07.ecommerce.feature_home.data.network.FakeHomeRemoteDataSource
import com.ramanshsharma07.ecommerce.feature_home.data.network.HomeRemoteDataSource
import com.ramanshsharma07.ecommerce.feature_home.data.repository.HomeRepositoryImpl
import com.ramanshsharma07.ecommerce.feature_home.domain.repository.HomeRepository
import com.ramanshsharma07.ecommerce.feature_home.domain.use_cases.GetHomePageDataUseCase
import com.ramanshsharma07.ecommerce.feature_home.presentation.viewmodel.HomeViewModel
import org.koin.core.module.Module
import org.koin.dsl.module


expect fun platformModule(): Module


val homeModule = module {
    // --- Data Layer ---
    // Use factory for the data source. We provide the Fake implementation here.
    single<HomeRemoteDataSource> { FakeHomeRemoteDataSource() }

    // Use factory for the repository, telling Koin to inject the HomeRemoteDataSource.
    single<HomeRepository> { HomeRepositoryImpl(get()) }

    // --- Domain Layer ---
    // Use factory for the use case.
    single { GetHomePageDataUseCase(get()) }

    // --- Presentation Layer ---
    // Koin has a special 'viewModel' builder for ViewModels.
    single { HomeViewModel(get()) }
}