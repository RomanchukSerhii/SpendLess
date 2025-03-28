package com.serhiiromanchuk.auth.presentation.screens.login.handling

sealed interface LoginAction {
    data object RequestFocus : LoginAction
    data object NavigateToTransactions : LoginAction
    data object NavigateToRegistration : LoginAction
    data object NavigateToPinPrompt : LoginAction
}