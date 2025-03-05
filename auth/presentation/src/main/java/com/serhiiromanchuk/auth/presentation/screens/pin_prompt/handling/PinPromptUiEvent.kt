package com.serhiiromanchuk.auth.presentation.screens.pin_prompt.handling

sealed interface PinPromptUiEvent {
    data class NumberButtonClicked(val number: Int) : PinPromptUiEvent
    data object BackspaceButtonClicked : PinPromptUiEvent
    data object LogOutClicked : PinPromptUiEvent
}