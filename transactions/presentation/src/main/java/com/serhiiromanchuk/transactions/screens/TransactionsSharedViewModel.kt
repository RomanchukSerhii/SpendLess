package com.serhiiromanchuk.transactions.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serhiiromanchuk.core.domain.entity.Transaction
import com.serhiiromanchuk.core.domain.repository.TransactionRepository
import com.serhiiromanchuk.core.domain.repository.UserRepository
import com.serhiiromanchuk.core.presentation.ui.mappers.toUi
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
import com.serhiiromanchuk.transactions.utils.toDomain
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class TransactionsSharedViewModel(
    private val username: String,
    private val userRepository: UserRepository,
    private val transactionRepository: TransactionRepository,
    private val amountFormatter: AmountFormatter
) : ViewModel() {
    var dashboardState by mutableStateOf(DashboardUiState())
        private set

    var createTransactionState by mutableStateOf(CreateTransactionUiState())
        private set

    private var userId: Long? = null

    init {
        viewModelScope.launch {
            userId = userRepository.getUser(username)?.id
            userId?.let { id ->
                transactionRepository.getTransactionsByUser(id).collectLatest { transactions ->
                    // TODO
                }
            }
        }
        setAmountSettings()
        observeTextFields()
    }

    fun onEvent(event: DashboardUiEvent) {
        when (event) {
            CreateTransactionSheetToggled -> toggleCreateTransactionSheet()
        }
    }

    fun onEvent(event: CreateTransactionUiEvent) {
        when (event) {
            is TransactionModeSelected -> updateTransactionMode(event.transactionMode)
            is SpendCategorySelected -> updateSpendCategory(event.spendCategory)
            is RepeatingCategorySelected -> updateRepeatingCategory(event.repeatingCategory)

            CreateButtonClicked -> createTransaction()
            CreateTransactionUiEvent.CreateTransactionSheetToggled -> toggleCreateTransactionSheet()
        }
    }

    private fun createTransaction() {
        userId?.let {
            val transactionsFields = createTransactionState.transactionFieldsState
            val title = transactionsFields.title.text.toString()
            val amount = amountFormatter.parseAmountToFloat(
                amountText = transactionsFields.amount.text,
                amountSettings = dashboardState.amountSettings
            )
            val note = if (transactionsFields.note.text.isNotBlank()) {
                transactionsFields.note.text.toString()
            } else null

            val transaction = Transaction(
                userId = it,
                title = title,
                amount = amount,
                repeatType = createTransactionState.repeatingCategory.toDomain(),
                transactionType = createTransactionState.expenseCategory.toDomain(),
                note = note
            )

            viewModelScope.launch {
                transactionRepository.upsertTransaction(transaction)
            }
        }
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

    private fun observeTextFields() {
        createTransactionState.transactionFieldsState.title.textAsFlow()
            .onEach(::handleCounterpartyInput)
            .launchIn(viewModelScope)

        createTransactionState.transactionFieldsState.amount.textAsFlow()
            .onEach(::handleAmountInput)
            .launchIn(viewModelScope)

        createTransactionState.transactionFieldsState.note.textAsFlow()
            .onEach(::handleNoteInput)
            .launchIn(viewModelScope)
    }


    private fun handleCounterpartyInput(newText: CharSequence) {
        val counterpartyState = createTransactionState.transactionFieldsState.title

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
    }

    private fun updateTransactionMode(transactionMode: TransactionModeOptions) {
        createTransactionState = createTransactionState.copy(transactionMode = transactionMode)
    }

    private fun updateSpendCategory(spendCategory: ExpenseCategory) {
        createTransactionState = createTransactionState.copy(expenseCategory = spendCategory)
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