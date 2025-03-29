package com.serhiiromanchuk.transactions.screens.dashboard.handling

sealed interface DashboardUiEvent {
    data object CreateTransactionSheetToggled : DashboardUiEvent
    data object ExportSheetToggled : DashboardUiEvent
    data object AllTransactionButtonClicked : DashboardUiEvent
    data object SettingsButtonClicked : DashboardUiEvent
}