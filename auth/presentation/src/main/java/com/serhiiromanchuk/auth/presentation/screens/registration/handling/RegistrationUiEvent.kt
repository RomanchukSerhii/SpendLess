package com.serhiiromanchuk.auth.presentation.screens.registration.handling

sealed interface RegistrationUiEvent {
    data object NextButtonClicked : RegistrationUiEvent
}