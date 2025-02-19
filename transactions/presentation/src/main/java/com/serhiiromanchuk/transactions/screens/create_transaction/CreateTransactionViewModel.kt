package com.serhiiromanchuk.transactions.screens.create_transaction

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.serhiiromanchuk.core.presentation.designsystem.components.select.category.RepeatingCategoryItem
import com.serhiiromanchuk.core.presentation.designsystem.components.select.category.SpendCategoryItem
import com.serhiiromanchuk.transactions.screens.create_transaction.components.TransactionModeOptions
import com.serhiiromanchuk.transactions.screens.create_transaction.handling.CreateTransactionUiEvent
import com.serhiiromanchuk.transactions.screens.create_transaction.handling.CreateTransactionUiEvent.*
import com.serhiiromanchuk.transactions.screens.create_transaction.handling.CreateTransactionUiState
import com.serhiiromanchuk.transactions.screens.create_transaction.handling.CreateTransactionUiState.*

class CreateTransactionViewModel : ViewModel() {
    var state by mutableStateOf(CreateTransactionUiState())
        private set

    fun onEvent(event: CreateTransactionUiEvent) {
        when (event) {
            is TransactionModeSelected -> updateTransactionMode(event.transactionMode)
            is SpendCategorySelected -> updateSpendCategory(event.spendCategory)
            is RepeatingCategorySelected -> updateRepeatingCategory(event.repeatingCategoryItem)

            is CounterpartyTextChanged -> updateCounterpartyText(event.text)
            is AmountTextChanged -> updateAmountText(event.text)
            is NoteTextChanged -> updateNoteText(event.text)

            CreateButtonClicked -> TODO()
        }
    }

    private fun updateTransactionMode(transactionMode: TransactionModeOptions) {
        state = state.copy(transactionMode = transactionMode)
    }

    private fun updateSpendCategory(spendCategory: SpendCategoryItem) {
        state = state.copy(spendCategory = spendCategory)
    }

    private fun updateRepeatingCategory(repeatingCategory: RepeatingCategoryItem) {
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