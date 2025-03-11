package com.serhiiromanchuk.auth.presentation.screens.registration.create_username.handling

sealed interface CreateUsernameUiEvent {
    data object NextButtonClicked : CreateUsernameUiEvent
}