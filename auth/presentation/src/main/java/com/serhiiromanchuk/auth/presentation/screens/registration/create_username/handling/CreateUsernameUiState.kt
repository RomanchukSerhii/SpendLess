package com.serhiiromanchuk.auth.presentation.screens.registration.create_username.handling

import androidx.compose.foundation.text.input.TextFieldState
import com.serhiiromanchuk.auth.domain.UserValidationState

data class CreateUsernameUiState(
    val username: TextFieldState = TextFieldState(),
    val userValidationState: UserValidationState = UserValidationState(),
    val isUsernameTaken: Boolean = false
) {
    val isNextButtonEnabled: Boolean
        get() {
            return username.text.isNotBlank()
        }
}
