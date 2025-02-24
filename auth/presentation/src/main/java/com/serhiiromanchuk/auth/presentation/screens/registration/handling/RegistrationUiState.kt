package com.serhiiromanchuk.auth.presentation.screens.registration.handling

import androidx.compose.foundation.text.input.TextFieldState
import com.serhiiromanchuk.auth.domain.UsernameValidationState

data class RegistrationUiState(
    val username: TextFieldState = TextFieldState(),
    val usernameValidationState: UsernameValidationState = UsernameValidationState()
) {
    val isNextButtonEnabled: Boolean
        get() {
            return username.text.isNotBlank()
        }
}
