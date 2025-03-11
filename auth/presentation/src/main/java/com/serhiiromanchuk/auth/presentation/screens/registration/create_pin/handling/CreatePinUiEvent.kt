package com.serhiiromanchuk.auth.presentation.screens.registration.create_pin.handling

sealed interface CreatePinUiEvent {
    data class NumberButtonClicked(val number: Int) : CreatePinUiEvent
    data object BackspaceButtonClicked : CreatePinUiEvent
    data object BackButtonClicked : CreatePinUiEvent
}