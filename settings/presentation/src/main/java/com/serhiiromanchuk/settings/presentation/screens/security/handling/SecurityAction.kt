package com.serhiiromanchuk.settings.presentation.screens.security.handling

sealed interface SecurityAction {
    data object NavigateBack : SecurityAction
    data object NavigateToPinPrompt : SecurityAction
}