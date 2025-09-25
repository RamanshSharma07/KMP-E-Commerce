package com.ramanshsharma07.ecommerce.feature_settings.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramanshsharma07.ecommerce.core.domain.onSuccess
import com.ramanshsharma07.ecommerce.feature_profile.domain.use_cases.GetUserProfileUseCase
import com.ramanshsharma07.ecommerce.feature_settings.domain.use_cases.GetAppSettingsUseCase
import com.ramanshsharma07.ecommerce.feature_settings.domain.use_cases.UpdateNotificationSettingUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val getAppSettingsUseCase: GetAppSettingsUseCase,
    private val updateNotificationSettingUseCase: UpdateNotificationSettingUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SettingsState())
    val state = _state.asStateFlow()

    init {
        loadData()
    }

    fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.OnNotificationToggled -> {
                viewModelScope.launch {
                    updateNotificationSettingUseCase(event.isEnabled)
                    // Re-fetch settings to confirm the change
                    loadData()
                }
            }
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            // Fetch both user profile and app settings
            val profileResult = getUserProfileUseCase()
            val settingsResult = getAppSettingsUseCase()

            profileResult.onSuccess { profile ->
                _state.update { it.copy(userProfile = profile) }
            }
            settingsResult.onSuccess { settings ->
                _state.update { it.copy(appSettings = settings) }
            }

            _state.update { it.copy(isLoading = false) }
        }
    }
}