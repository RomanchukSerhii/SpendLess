package com.serhiiromanchuk.transactions.screens.create_transaction

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.serhiiromanchuk.transactions.common_components.ExpenseCategory
import com.serhiiromanchuk.transactions.common_components.RepeatingCategory
import com.serhiiromanchuk.transactions.screens.create_transaction.components.TransactionModeOptions
import com.serhiiromanchuk.transactions.screens.create_transaction.handling.CreateTransactionUiEvent
import com.serhiiromanchuk.transactions.screens.create_transaction.handling.CreateTransactionUiEvent.AmountTextChanged
import com.serhiiromanchuk.transactions.screens.create_transaction.handling.CreateTransactionUiEvent.CounterpartyTextChanged
import com.serhiiromanchuk.transactions.screens.create_transaction.handling.CreateTransactionUiEvent.CreateButtonClicked
import com.serhiiromanchuk.transactions.screens.create_transaction.handling.CreateTransactionUiEvent.NoteTextChanged
import com.serhiiromanchuk.transactions.screens.create_transaction.handling.CreateTransactionUiEvent.RepeatingCategorySelected
import com.serhiiromanchuk.transactions.screens.create_transaction.handling.CreateTransactionUiEvent.SpendCategorySelected
import com.serhiiromanchuk.transactions.screens.create_transaction.handling.CreateTransactionUiEvent.TransactionModeSelected
import com.serhiiromanchuk.transactions.screens.create_transaction.handling.CreateTransactionUiState
import com.serhiiromanchuk.transactions.screens.create_transaction.handling.CreateTransactionUiState.TransactionFieldsState

class CreateTransactionViewModel : ViewModel() {
    var state by mutableStateOf(CreateTransactionUiState())
        private set

    fun onEvent(event: CreateTransactionUiEvent) {
        when (event) {
            is TransactionModeSelected -> updateTransactionMode(event.transactionMode)
            is SpendCategorySelected -> updateSpendCategory(event.spendCategory)
            is RepeatingCategorySelected -> updateRepeatingCategory(event.repeatingCategory)

            is CounterpartyTextChanged -> updateCounterpartyText(event.text)
            is AmountTextChanged -> updateAmountText(event.text)
            is NoteTextChanged -> updateNoteText(event.text)

            CreateButtonClicked -> TODO()
        }
    }

    private fun updateTransactionMode(transactionMode: TransactionModeOptions) {
        state = state.copy(transactionMode = transactionMode)
    }

    private fun updateSpendCategory(spendCategory: ExpenseCategory) {
        state = state.copy(spendCategory = spendCategory)
    }

    private fun updateRepeatingCategory(repeatingCategory: RepeatingCategory) {
        state = state.copy(repeatingCategory = repeatingCategory)
    }

    private fun updateCounterpartyText(text: String) {
        updateTransactionFieldsState {
            it.copy(counterpartyText = text)
        }
    }

    private fun updateAmountText(text: String) {
        updateTransactionFieldsState {
            it.copy(amountText = text)
        }
    }

    private fun updateNoteText(text: String) {
        updateTransactionFieldsState {
            it.copy(noteText = text)
        }
    }

    private fun updateTransactionFieldsState(
        update: (TransactionFieldsState) -> TransactionFieldsState
    ) {
        state = state.copy(transactionFieldsState = update(state.transactionFieldsState))
    }
}