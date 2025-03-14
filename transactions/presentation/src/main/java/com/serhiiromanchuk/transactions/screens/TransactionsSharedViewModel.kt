package com.serhiiromanchuk.transactions.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serhiiromanchuk.core.domain.repository.UserRepository
import com.serhiiromanchuk.core.presentation.designsystem.components.expenses_settings.toUi
import com.serhiiromanchuk.core.presentation.ui.textAsFlow
import com.serhiiromanchuk.transactions.common_components.ExpenseCategory
import com.serhiiromanchuk.transactions.common_components.RepeatingCategory
import com.serhiiromanchuk.transactions.screens.create_transaction.components.TransactionModeOptions
import com.serhiiromanchuk.transactions.screens.create_transaction.handling.CreateTransactionUiEvent
import com.serhiiromanchuk.transactions.screens.create_transaction.handling.CreateTransactionUiEvent.CreateButtonClicked
import com.serhiiromanchuk.transactions.screens.create_transaction.handling.CreateTransactionUiEvent.RepeatingCategorySelected
import com.serhiiromanchuk.transactions.screens.create_transaction.handling.CreateTransactionUiEvent.SpendCategorySelected
import com.serhiiromanchuk.transactions.screens.create_transaction.handling.CreateTransactionUiEvent.TransactionModeSelected
import com.serhiiromanchuk.transactions.screens.create_transaction.handling.CreateTransactionUiState
import com.serhiiromanchuk.transactions.screens.create_transaction.handling.CreateTransactionUiState.TransactionFieldsState
import com.serhiiromanchuk.transactions.screens.dashboard.handling.DashboardUiEvent
import com.serhiiromanchuk.transactions.screens.dashboard.handling.DashboardUiEvent.CreateTransactionSheetToggled
import com.serhiiromanchuk.transactions.screens.dashboard.handling.DashboardUiState
import com.serhiiromanchuk.transactions.utils.AmountFormatter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class TransactionsSharedViewModel(
    private val username: String,
    private val userRepository: UserRepository,
    private val amountFormatter: AmountFormatter
) : ViewModel() {
    var dashboardState by mutableStateOf(DashboardUiState())
        private set

    var createTransactionState by mutableStateOf(CreateTransactionUiState())
        private set

    fun onEvent(event: DashboardUiEvent) {
        when (event) {
            CreateTransactionSheetToggled -> toggleCreateTransactionSheet()
        }
    }

    init {
        setAmountSettings()

        createTransactionState.transactionFieldsState.counterparty.textAsFlow()
            .onEach(::handleCounterpartyInput)
            .launchIn(viewModelScope)

        createTransactionState.transactionFieldsState.amount.textAsFlow()
            .onEach(::handleAmountInput)
            .launchIn(viewModelScope)

        createTransactionState.transactionFieldsState.note.textAsFlow()
            .onEach(::handleNoteInput)
            .launchIn(viewModelScope)
    }

    private fun setAmountSettings() {
        viewModelScope.launch {
            userRepository.getFlowUser(username = username).collectLatest { user ->
                user?.let {
                    dashboardState = dashboardState.copy(
                        amountSettings = dashboardState.amountSettings.copy(
                            currency = user.settings.currency.toUi(),
                            decimalSeparator = user.settings.decimalSeparator.toUi(),
                            thousandsSeparator = user.settings.thousandsSeparator.toUi()
                        )
                    )

                    createTransactionState = createTransactionState.copy(
                        expensesFormat = user.settings.expensesFormat
                    )
                }
            }
        }
    }

    fun onEvent(event: CreateTransactionUiEvent) {
        when (event) {
            is TransactionModeSelected -> updateTransactionMode(event.transactionMode)
            is SpendCategorySelected -> updateSpendCategory(event.spendCategory)
            is RepeatingCategorySelected -> updateRepeatingCategory(event.repeatingCategory)

            CreateButtonClicked -> TODO()
            CreateTransactionUiEvent.CreateTransactionSheetToggled -> toggleCreateTransactionSheet()
        }
    }

    private fun handleCounterpartyInput(newText: CharSequence) {
        val counterpartyState = createTransactionState.transactionFieldsState.counterparty

        val filteredText = newText.filter { it.isLetter() }
        val limitedText = filteredText.take(MAX_COUNTERPARTY_LENGTH)

        if (limitedText != counterpartyState.text) {
            counterpartyState.edit {
                replace(0, counterpartyState.text.length, limitedText)
            }
        }
    }

    private fun handleAmountInput(newText: CharSequence) {
        val amountState = createTransactionState.transactionFieldsState.amount
        val formatedAmount =
            amountFormatter.getFormatedAmount(newText, dashboardState.amountSettings)

        amountState.edit { replace(0, amountState.text.length, formatedAmount) }
    }

    private fun handleNoteInput(newText: CharSequence) {
        val noteState = createTransactionState.transactionFieldsState.note
        val filteredText = newText.take(100)

        noteState.edit { replace(0, noteState.text.length, filteredText) }
    }

    private fun toggleCreateTransactionSheet() {
        dashboardState = dashboardState.copy(
            isCreateTransactionOpen = !dashboardState.isCreateTransactionOpen
        )

        // Update the amount according to the settings preferences
        if (dashboardState.isCreateTransactionOpen) {
            val currentText = createTransactionState.transactionFieldsState.amount.text
            val decimalSeparator = dashboardState.amountSettings.decimalSeparator.separator
            val cleanedText =
                amountFormatter.removeOldThousandsSeparator(currentText, decimalSeparator)
            handleAmountInput(cleanedText)
        }
    }

    private fun updateTransactionMode(transactionMode: TransactionModeOptions) {
        createTransactionState = createTransactionState.copy(transactionMode = transactionMode)
    }

    private fun updateSpendCategory(spendCategory: ExpenseCategory) {
        createTransactionState = createTransactionState.copy(spendCategory = spendCategory)
    }

    private fun updateRepeatingCategory(repeatingCategory: RepeatingCategory) {
        createTransactionState = createTransactionState.copy(repeatingCategory = repeatingCategory)
    }

    private fun updateTransactionFieldsState(
        update: (TransactionFieldsState) -> TransactionFieldsState
    ) {
        createTransactionState =
            createTransactionState.copy(transactionFieldsState = update(createTransactionState.transactionFieldsState))
    }

    companion object {
        private const val MAX_COUNTERPARTY_LENGTH = 14
    }
}