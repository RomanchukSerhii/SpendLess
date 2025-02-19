package com.serhiiromanchuk.transactions.screens.dashboard.handling

sealed interface DashboardUiEvent {
    data object CreateTransactionSheetToggled : DashboardUiEvent
}