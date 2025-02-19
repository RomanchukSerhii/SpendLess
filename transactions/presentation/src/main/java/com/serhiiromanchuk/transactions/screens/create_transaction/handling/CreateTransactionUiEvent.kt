package com.serhiiromanchuk.transactions.screens.create_transaction.handling

import com.serhiiromanchuk.core.presentation.designsystem.components.select.category.RepeatingCategoryItem
import com.serhiiromanchuk.core.presentation.designsystem.components.select.category.SpendCategoryItem
import com.serhiiromanchuk.transactions.screens.create_transaction.components.TransactionModeOptions

sealed interface CreateTransactionUiEvent {
    data class CounterpartyTextChanged(val text: String) : CreateTransactionUiEvent
    data class AmountTextChanged(val text: String) : CreateTransactionUiEvent
    data class NoteTextChanged(val text: String) : CreateTransactionUiEvent
    data class TransactionModeSelected(val transactionMode: TransactionModeOptions) : CreateTransactionUiEvent
    data class SpendCategorySelected(val spendCategory: SpendCategoryItem) : CreateTransactionUiEvent
    data class RepeatingCategorySelected(val repeatingCategoryItem: RepeatingCategoryItem) : CreateTransactionUiEvent
    data object CreateButtonClicked : CreateTransactionUiEvent
}