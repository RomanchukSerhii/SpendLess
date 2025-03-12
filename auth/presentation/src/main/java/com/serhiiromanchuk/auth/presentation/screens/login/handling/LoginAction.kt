package com.serhiiromanchuk.auth.presentation.screens.login.handling

sealed interface LoginAction {
    data object RequestFocus : LoginAction
    data object NavigateToDashboard : LoginAction
    data object NavigateToRegistration : LoginAction
}