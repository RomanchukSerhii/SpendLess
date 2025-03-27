package com.serhiiromanchuk.settings.presentation.screens.settings.handling

sealed interface SettingsAction {
    data object NavigateToLogin : SettingsAction
}