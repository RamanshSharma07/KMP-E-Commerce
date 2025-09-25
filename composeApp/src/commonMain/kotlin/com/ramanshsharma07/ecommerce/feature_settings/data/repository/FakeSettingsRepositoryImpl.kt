package com.ramanshsharma07.ecommerce.feature_settings.data.repository


import com.ramanshsharma07.ecommerce.core.domain.DataError
import com.ramanshsharma07.ecommerce.core.domain.EmptyResult
import com.ramanshsharma07.ecommerce.core.domain.Result
import com.ramanshsharma07.ecommerce.feature_settings.domain.model.AppSettings
import com.ramanshsharma07.ecommerce.feature_settings.domain.repository.SettingsRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class FakeSettingsRepositoryImpl : SettingsRepository {
    private val _settings = MutableStateFlow(
        AppSettings(
            notificationsEnabled = true,
            selectedLanguage = "English"
        )
    )

    override suspend fun getAppSettings(): Result<AppSettings, DataError> {
        delay(600L) // Simulate network delay
        return Result.Success(_settings.value)
    }

    override suspend fun updateLanguage(language: String): EmptyResult<DataError> {
        delay(300L) // Simulate a quick update
        _settings.update { it.copy(selectedLanguage = language) }
        return Result.Success(Unit)
    }

    override suspend fun updateNotificationSetting(isEnabled: Boolean): EmptyResult<DataError> {
        delay(300L) // Simulate a quick update
        _settings.update { it.copy(notificationsEnabled = isEnabled) }
        return Result.Success(Unit)
    }
}