package com.serhiiromanchuk.auth.presentation.screens.registration.create_pin.handling

sealed interface CreatePinAction {
    data object NavigateBack : CreatePinAction
    data object NavigateNext : CreatePinAction
}