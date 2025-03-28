package com.serhiiromanchuk.settings.presentation.screens.preferences.handling

sealed interface PreferencesAction {
    data object NavigateBack : PreferencesAction
    data object NavigateToPinPrompt : PreferencesAction
}