package com.serhiiromanchuk.transactions.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serhiiromanchuk.core.domain.repository.UserRepository
import com.serhiiromanchuk.core.presentation.designsystem.components.expenses_settings.CurrencyCategoryItem
import com.serhiiromanchuk.core.presentation.designsystem.components.expenses_settings.DecimalSeparatorUi
import com.serhiiromanchuk.core.presentation.designsystem.components.expenses_settings.ThousandsSeparatorUi
import com.serhiiromanchuk.core.presentation.ui.textAsFlow
import com.serhiiromanchuk.transactions.common_components.ExpenseCategory
import com.serhiiromanchuk.transactions.common_components.RepeatingCategory
import com.serhiiromanchuk.transactions.screens.create_transaction.components.TransactionModeOptions
import com.serhiiromanchuk.transactions.screens.create_transaction.handling.CreateTransactionUiEvent
import com.serhiiromanchuk.transactions.screens.create_transaction.handling.CreateTransactionUiEvent.CreateButtonClicked
import com.serhiiromanchuk.transactions.screens.create_transaction.handling.CreateTransactionUiEvent.NoteTextChanged
import com.serhiiromanchuk.transactions.screens.create_transaction.handling.CreateTransactionUiEvent.RepeatingCategorySelected
import com.serhiiromanchuk.transactions.screens.create_transaction.handling.CreateTransactionUiEvent.SpendCategorySelected
import com.serhiiromanchuk.transactions.screens.create_transaction.handling.CreateTransactionUiEvent.TransactionModeSelected
import com.serhiiromanchuk.transactions.screens.create_transaction.handling.CreateTransactionUiState
import com.serhiiromanchuk.transactions.screens.create_transaction.handling.CreateTransactionUiState.TransactionFieldsState
import com.serhiiromanchuk.transactions.screens.dashboard.handling.DashboardUiEvent
import com.serhiiromanchuk.transactions.screens.dashboard.handling.DashboardUiEvent.CreateTransactionSheetToggled
import com.serhiiromanchuk.transactions.screens.dashboard.handling.DashboardUiState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class TransactionsSharedViewModel(
    private val username: String,
    private val userRepository: UserRepository
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
    }

    private fun setAmountSettings() {
        viewModelScope.launch {
            val user = userRepository.getUser(username = username)
                ?: throw IllegalArgumentException("User with username $username not found")

            dashboardState = dashboardState.copy(
                amountSettings = dashboardState.amountSettings.copy(
                    currency = CurrencyCategoryItem.valueOf(user.settings.currency.name),
                    decimalSeparator = DecimalSeparatorUi.valueOf(user.settings.decimalSeparator.name),
                    thousandsSeparator = ThousandsSeparatorUi.valueOf(user.settings.thousandsSeparator.name)
                )
            )

            createTransactionState = createTransactionState.copy(
                expensesFormat = user.settings.expensesFormat
            )
        }
    }

    fun onEvent(event: CreateTransactionUiEvent) {
        when (event) {
            is TransactionModeSelected -> updateTransactionMode(event.transactionMode)
            is SpendCategorySelected -> updateSpendCategory(event.spendCategory)
            is RepeatingCategorySelected -> updateRepeatingCategory(event.repeatingCategory)

            is NoteTextChanged -> updateNoteText(event.text)

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
        val decimalSeparator = dashboardState.amountSettings.decimalSeparator.separator
        val thousandSeparator = dashboardState.amountSettings.thousandsSeparator.separator

        var filteredText = newText.filter { it.isDigit() || it == '.' || it == ',' }

        filteredText = replaceDecimalSeparator(filteredText)
        val parts = filteredText.split(decimalSeparator)

        // Formatting the integer part with thousands separator
        val integerPart = formatThousands(parts[0], thousandSeparator)

        // If there is a fractional part, limit it to two digits
        var resultText = integerPart
        if (parts.size > 1) {
            resultText += decimalSeparator + parts[1].take(2)
        }

        amountState.edit { replace(0, amountState.text.length, resultText) }
    }

    private fun replaceDecimalSeparator(text: CharSequence): CharSequence {
        return when (dashboardState.amountSettings.decimalSeparator) {
            DecimalSeparatorUi.POINT -> text.toString().replace(',', '.')
            DecimalSeparatorUi.COMMA -> text.toString().replace('.', ',')
        }
    }

    private fun formatThousands(integerPart: String, separator: String): String {
        val reversed = integerPart.reversed()
        val formatted = StringBuilder()
        for (i in reversed.indices) {
            if (i > 0 && i % 3 == 0) {
                formatted.append(separator)
            }
            formatted.append(reversed[i])
        }
        return formatted.reverse().toString()
    }

    private fun toggleCreateTransactionSheet() {
        dashboardState = dashboardState.copy(
            isCreateTransactionOpen = !dashboardState.isCreateTransactionOpen
        )
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

    private fun updateNoteText(text: String) {
        updateTransactionFieldsState {
            it.copy(note = text)
        }
    }

    private fun updateTransactionFieldsState(
        update: (TransactionFieldsState) -> TransactionFieldsState
    ) {
        createTransactionState = createTransactionState.copy(transactionFieldsState = update(createTransactionState.transactionFieldsState))
    }

    companion object {
        private const val MAX_COUNTERPARTY_LENGTH = 14
    }
}