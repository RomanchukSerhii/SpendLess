package com.serhiiromanchuk.auth.presentation.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.serhiiromanchuk.auth.presentation.screens.login.handling.LoginUiEvent
import com.serhiiromanchuk.auth.presentation.screens.login.handling.LoginUiEvent.*
import com.serhiiromanchuk.auth.presentation.screens.login.handling.LoginUiState

class LoginViewModel : ViewModel() {

    var state by mutableStateOf(LoginUiState())
        private set

    fun onEvent(event: LoginUiEvent) {
        when (event) {
            is UsernameTextChanged -> updateUsernameText(event.username)
            is PinTextChanged -> updatePinText(event.pin)

            LogInButtonClicked -> TODO()
            RegistrationButtonClicked -> TODO()
        }
    }

    private fun updateUsernameText(username: String) {
        state = state.copy(usernameText = username)
    }

    private fun updatePinText(pin: String) {
        state = state.copy(pinText = pin)
    }
}