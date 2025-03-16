package com.serhiiromanchuk.transactions.screens.create_transaction.handling

import androidx.compose.foundation.text.input.TextFieldState
import com.serhiiromanchuk.core.domain.entity.ExpensesFormat
import com.serhiiromanchuk.transactions.common_components.ExpenseCategory
import com.serhiiromanchuk.transactions.common_components.RepeatingCategory
import com.serhiiromanchuk.transactions.screens.create_transaction.components.TransactionModeOptions

data class CreateTransactionUiState(
    val transactionMode: TransactionModeOptions = TransactionModeOptions.EXPENSE,
    val expenseCategory: ExpenseCategory = ExpenseCategory.OTHER,
    val repeatingCategory: RepeatingCategory = RepeatingCategory.NOT_REPEAT,
    val transactionFieldsState: TransactionFieldsState = TransactionFieldsState(),
    val expensesFormat: ExpensesFormat = ExpensesFormat.MINUS
) {
    val isCreateButtonEnabled: Boolean
        get() {
            val hasCounterpartyMinLength = transactionFieldsState.title.text.length >= MIN_COUNTERPARTY_LENGTH
            val isAmountNotBlank = transactionFieldsState.amount.text.isNotBlank()
            return isAmountNotBlank && hasCounterpartyMinLength
        }

    val isExpense: Boolean
        get() {
            return transactionMode == TransactionModeOptions.EXPENSE
        }

    data class TransactionFieldsState(
        val title: TextFieldState = TextFieldState(),
        val amount: TextFieldState = TextFieldState(),
        val note: TextFieldState = TextFieldState(),
    )

    companion object {
        private const val MIN_COUNTERPARTY_LENGTH = 3
    }
}