package com.ramanshsharma07.ecommerce.feature_settings.domain.use_cases

import com.ramanshsharma07.ecommerce.feature_settings.domain.repository.SettingsRepository

class GetAppSettingsUseCase(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke() = repository.getAppSettings()
}