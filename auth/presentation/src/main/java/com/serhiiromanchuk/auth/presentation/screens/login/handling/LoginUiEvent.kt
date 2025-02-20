package com.serhiiromanchuk.auth.presentation.screens.login.handling

sealed interface LoginUiEvent {
    data class UsernameTextChanged(val username: String) : LoginUiEvent
    data class PinTextChanged(val pin: String) : LoginUiEvent
    data object LogInButtonClicked : LoginUiEvent
    data object RegistrationButtonClicked : LoginUiEvent
}