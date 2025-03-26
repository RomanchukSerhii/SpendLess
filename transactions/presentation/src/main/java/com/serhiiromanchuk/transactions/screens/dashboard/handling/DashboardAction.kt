package com.serhiiromanchuk.transactions.screens.dashboard.handling

sealed interface DashboardAction {
    data object NavigateToAllTransactions : DashboardAction
    data object NavigateToPinPrompt : DashboardAction
    data object NavigateToSettings : DashboardAction
}