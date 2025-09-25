package com.ramanshsharma07.ecommerce.di

import com.ramanshsharma07.ecommerce.feature_settings.data.repository.FakeSettingsRepositoryImpl
import com.ramanshsharma07.ecommerce.feature_settings.domain.repository.SettingsRepository
import com.ramanshsharma07.ecommerce.feature_settings.domain.use_cases.GetAppSettingsUseCase
import com.ramanshsharma07.ecommerce.feature_settings.domain.use_cases.UpdateLanguageUseCase
import com.ramanshsharma07.ecommerce.feature_settings.domain.use_cases.UpdateNotificationSettingUseCase
import com.ramanshsharma07.ecommerce.feature_settings.presentation.viewmodel.SettingsViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val settingsModule = module {
    // --- Data Layer ---
    // Use 'single' because FakeSettingsRepositoryImpl holds the state in memory.
    single<SettingsRepository> { FakeSettingsRepositoryImpl() }

    // --- Domain Layer ---
    factoryOf(::GetAppSettingsUseCase)
    factoryOf(::UpdateLanguageUseCase)
    factoryOf(::UpdateNotificationSettingUseCase)

    // --- Presentation Layer ---
    factoryOf(::SettingsViewModel)
}