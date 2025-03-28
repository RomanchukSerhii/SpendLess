package com.serhiiromanchuk.auth.presentation.screens.pin_prompt.handling

import com.serhiiromanchuk.auth.presentation.R
import com.serhiiromanchuk.core.presentation.ui.UiText

data class PinPromptUiState(
    val userGreeting: UiText = UiText.DynamicString(""),
    val description: UiText = UiText.StringResource(R.string.enter_you_pin),
    val enteredPin: String = "",
    val showError: Boolean = false,
    val currentAttempt: Int = 0,
    val isKeyboardEnabled: Boolean = true,
)
