package com.serhiiromanchuk.transactions.screens.create_transaction.handling

import com.serhiiromanchuk.transactions.common_components.ExpenseCategory
import com.serhiiromanchuk.transactions.common_components.RepeatingCategory
import com.serhiiromanchuk.transactions.screens.create_transaction.components.TransactionModeOptions

sealed interface CreateTransactionUiEvent {
    data object CreateTransactionSheetToggled : CreateTransactionUiEvent
    data class TransactionModeSelected(val transactionMode: TransactionModeOptions) : CreateTransactionUiEvent
    data class SpendCategorySelected(val spendCategory: ExpenseCategory) : CreateTransactionUiEvent
    data class RepeatingCategorySelected(val repeatingCategory: RepeatingCategory) : CreateTransactionUiEvent
    data object CreateButtonClicked : CreateTransactionUiEvent
}