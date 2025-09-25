package com.ramanshsharma07.ecommerce.feature_settings.presentation.viewmodel

sealed interface SettingsEvent {
    data class OnNotificationToggled(val isEnabled: Boolean) : SettingsEvent
}