package com.serhiiromanchuk.settings.presentation.screens.preferences

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.serhiiromanchuk.core.presentation.designsystem.components.currency_format.CurrencyCategoryItem
import com.serhiiromanchuk.core.presentation.designsystem.components.currency_format.CurrencyFormatState
import com.serhiiromanchuk.core.presentation.designsystem.components.currency_format.DecimalSeparator
import com.serhiiromanchuk.core.presentation.designsystem.components.currency_format.ExpensesFormat
import com.serhiiromanchuk.core.presentation.designsystem.components.currency_format.ThousandsSeparator
import com.serhiiromanchuk.settings.presentation.screens.preferences.handling.PreferencesUiEvent
import com.serhiiromanchuk.settings.presentation.screens.preferences.handling.PreferencesUiEvent.CurrencyClicked
import com.serhiiromanchuk.settings.presentation.screens.preferences.handling.PreferencesUiEvent.DecimalSeparatorClicked
import com.serhiiromanchuk.settings.presentation.screens.preferences.handling.PreferencesUiEvent.ExpensesFormatClicked
import com.serhiiromanchuk.settings.presentation.screens.preferences.handling.PreferencesUiEvent.SaveButtonClicked
import com.serhiiromanchuk.settings.presentation.screens.preferences.handling.PreferencesUiEvent.ThousandsSeparatorClicked
import com.serhiiromanchuk.settings.presentation.screens.preferences.handling.PreferencesUiState

class PreferencesViewModel : ViewModel() {

    var state by mutableStateOf(PreferencesUiState())
        private set

    fun onEvent(event: PreferencesUiEvent) {
        when (event) {
            is CurrencyClicked -> updateCurrency(event.currencyCategoryItem)
            is DecimalSeparatorClicked -> updateDecimalSeparator(event.decimalSeparator)
            is ExpensesFormatClicked -> updateExpensesFormat(event.expensesFormat)
            is ThousandsSeparatorClicked -> updateThousandsSeparator(event.thousandsSeparator)
            SaveButtonClicked -> saveSettings()
        }
    }

    private fun saveSettings() {

    }

    private fun updateExpensesFormat(expensesFormat: ExpensesFormat) {
        updateCurrencyFormatState { it.copy(expensesFormat = expensesFormat) }
    }

    private fun updateCurrency(currency: CurrencyCategoryItem) {
        updateCurrencyFormatState { it.copy(currency = currency) }
    }

    private fun updateDecimalSeparator(decimalSeparator: DecimalSeparator) {
        updateCurrencyFormatState { it.copy(decimalSeparator = decimalSeparator) }
    }

    private fun updateThousandsSeparator(thousandsSeparator: ThousandsSeparator) {
        updateCurrencyFormatState { it.copy(thousandsSeparator = thousandsSeparator) }
    }

    private fun updateCurrencyFormatState(
        update: (CurrencyFormatState) -> CurrencyFormatState
    ) {
        state = state.copy(currencyFormatState = update(state.currencyFormatState))
    }
}