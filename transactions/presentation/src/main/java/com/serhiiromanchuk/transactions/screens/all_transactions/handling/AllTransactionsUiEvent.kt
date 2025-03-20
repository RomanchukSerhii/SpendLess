package com.serhiiromanchuk.transactions.screens.all_transactions.handling

sealed interface AllTransactionsUiEvent {
    data object CreateTransactionSheetToggled : AllTransactionsUiEvent
    data object ExportTransactionsSheetToggled : AllTransactionsUiEvent
}