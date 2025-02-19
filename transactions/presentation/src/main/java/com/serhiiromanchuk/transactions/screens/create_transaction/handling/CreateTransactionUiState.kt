package com.serhiiromanchuk.transactions.screens.create_transaction.handling

import com.serhiiromanchuk.core.presentation.designsystem.components.select.category.RepeatingCategoryItem
import com.serhiiromanchuk.core.presentation.designsystem.components.select.category.SpendCategoryItem
import com.serhiiromanchuk.transactions.screens.create_transaction.components.TransactionModeOptions

data class CreateTransactionUiState(
    val transactionMode: TransactionModeOptions = TransactionModeOptions.EXPENSE,
    val spendCategory: SpendCategoryItem = SpendCategoryItem.OTHER,
    val repeatingCategory: RepeatingCategoryItem = RepeatingCategoryItem.NOT_REPEAT,
    val transactionFieldsState: TransactionFieldsState = TransactionFieldsState()
) {
    val isCreateButtonEnabled: Boolean
        get() {
            return transactionFieldsState.amountText.isNotBlank()
        }
    val isExpense: Boolean
        get() {
            return transactionMode == TransactionModeOptions.EXPENSE
        }

    data class TransactionFieldsState(
        val counterpartyText: String = "",
        val amountText: String = "",
        val noteText: String = "",
    )
}