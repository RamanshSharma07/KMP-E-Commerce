package com.ramanshsharma07.ecommerce.feature_settings.presentation.viewmodel

import com.ramanshsharma07.ecommerce.core.presentation.UiText
import com.ramanshsharma07.ecommerce.feature_profile.domain.model.UserProfile
import com.ramanshsharma07.ecommerce.feature_settings.domain.model.AppSettings

data class SettingsState(
    val isLoading: Boolean = false,
    val userProfile: UserProfile? = null,
    val appSettings: AppSettings? = null,
    val error: UiText? = null
)
