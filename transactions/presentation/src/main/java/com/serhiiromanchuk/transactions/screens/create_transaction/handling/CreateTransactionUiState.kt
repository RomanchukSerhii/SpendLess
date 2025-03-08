package com.serhiiromanchuk.transactions.screens.create_transaction.handling

import com.serhiiromanchuk.transactions.common_components.ExpenseCategory
import com.serhiiromanchuk.transactions.common_components.RepeatingCategory
import com.serhiiromanchuk.transactions.screens.create_transaction.components.TransactionModeOptions

data class CreateTransactionUiState(
    val transactionMode: TransactionModeOptions = TransactionModeOptions.EXPENSE,
    val spendCategory: ExpenseCategory = ExpenseCategory.OTHER,
    val repeatingCategory: RepeatingCategory = RepeatingCategory.NOT_REPEAT,
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