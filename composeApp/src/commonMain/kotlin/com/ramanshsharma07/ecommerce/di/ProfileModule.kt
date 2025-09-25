package com.ramanshsharma07.ecommerce.di

import com.ramanshsharma07.ecommerce.feature_profile.data.repository.FakeProfileRepositoryImpl
import com.ramanshsharma07.ecommerce.feature_profile.domain.repository.ProfileRepository
import com.ramanshsharma07.ecommerce.feature_profile.domain.use_cases.GetUserProfileUseCase
import com.ramanshsharma07.ecommerce.feature_profile.domain.use_cases.SignOutUseCase
import com.ramanshsharma07.ecommerce.feature_profile.presentation.viewmodel.ProfileViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val profileModule = module {
    // --- Data Layer ---
    factory<ProfileRepository> { FakeProfileRepositoryImpl() }

    // --- Domain Layer ---
    factoryOf(::GetUserProfileUseCase)
    factoryOf(::SignOutUseCase)

    // --- Presentation Layer ---
    factoryOf(::ProfileViewModel)
}