package com.serhiiromanchuk.auth.presentation.screens.pin_prompt.handling

sealed interface PinPromptAction {
    data object NavigateToTransaction : PinPromptAction
}