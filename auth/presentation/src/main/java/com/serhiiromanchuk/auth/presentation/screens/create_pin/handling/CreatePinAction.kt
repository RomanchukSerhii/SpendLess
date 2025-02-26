package com.serhiiromanchuk.auth.presentation.screens.create_pin.handling

sealed interface CreatePinAction {
    data object NavigateBack : CreatePinAction
    data object NavigateNext : CreatePinAction
}