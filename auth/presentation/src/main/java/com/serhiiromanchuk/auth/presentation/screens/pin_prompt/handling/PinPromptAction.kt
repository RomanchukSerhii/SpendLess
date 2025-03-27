package com.serhiiromanchuk.auth.presentation.screens.pin_prompt.handling

sealed interface PinPromptAction {
    data object NavigateNavigateBack : PinPromptAction
    data object NavigateToLogin : PinPromptAction
}