package com.serhiiromanchuk.auth.presentation.screens.registration.handling

sealed interface RegistrationAction {
    data object NavigateToCreatePinScreen : RegistrationAction
}