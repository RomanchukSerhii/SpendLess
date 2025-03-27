package com.serhiiromanchuk.settings.presentation.screens.settings.handling

sealed interface SettingsUiEvent {
    data object LogOutButtonClicked : SettingsUiEvent
}