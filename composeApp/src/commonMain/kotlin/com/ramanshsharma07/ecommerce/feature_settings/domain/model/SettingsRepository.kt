package com.ramanshsharma07.ecommerce.feature_settings.domain.model

import com.ramanshsharma07.ecommerce.core.domain.DataError
import com.ramanshsharma07.ecommerce.core.domain.Result
import com.ramanshsharma07.ecommerce.core.domain.EmptyResult

interface SettingsRepository {
    /** Fetches the current application settings. */
    suspend fun getAppSettings(): Result<AppSettings, DataError>

    /** Updates the user's language preference. */
    suspend fun updateLanguage(language: String): EmptyResult<DataError>

    /** Updates the user's notification preference. */
    suspend fun updateNotificationSetting(isEnabled: Boolean): EmptyResult<DataError>
}