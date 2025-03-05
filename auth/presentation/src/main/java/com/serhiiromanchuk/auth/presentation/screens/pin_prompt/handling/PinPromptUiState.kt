package com.serhiiromanchuk.auth.presentation.screens.pin_prompt.handling

import com.serhiiromanchuk.core.presentation.ui.UiText

data class PinPromptUiState(
    val title: UiText = UiText.DynamicString("Hello, rockefeller74!"),
    val description: UiText = UiText.DynamicString("Enter you PIN"),
    val enteredPin: String = "",
    val showError: Boolean = false,
    val isKeyboardEnabled: Boolean = true
)
