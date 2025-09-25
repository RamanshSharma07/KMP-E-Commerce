package com.ramanshsharma07.ecommerce.feature_settings.domain.use_cases

import com.ramanshsharma07.ecommerce.feature_settings.domain.model.SettingsRepository

class UpdateNotificationSettingUseCase(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke(isEnabled: Boolean) = repository.updateNotificationSetting(isEnabled)
}