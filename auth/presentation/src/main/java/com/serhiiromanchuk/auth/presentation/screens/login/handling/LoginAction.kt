package com.serhiiromanchuk.auth.presentation.screens.login.handling

sealed interface LoginAction {
    data object RequestFocus : LoginAction
    data class NavigateToTransactions(val username: String) : LoginAction
    data object NavigateToRegistration : LoginAction
}