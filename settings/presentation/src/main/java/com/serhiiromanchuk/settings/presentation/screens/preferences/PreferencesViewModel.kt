package com.serhiiromanchuk.settings.presentation.screens.preferences

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.serhiiromanchuk.settings.presentation.screens.preferences.components.CurrencyCategoryItem
import com.serhiiromanchuk.settings.presentation.screens.preferences.components.DecimalSeparator
import com.serhiiromanchuk.settings.presentation.screens.preferences.components.ExpensesFormat
import com.serhiiromanchuk.settings.presentation.screens.preferences.components.ThousandsSeparator
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
        state = state.copy(expensesFormat = expensesFormat)
    }

    private fun updateCurrency(currency: CurrencyCategoryItem) {
        state = state.copy(currency = currency)
    }

    private fun updateDecimalSeparator(decimalSeparator: DecimalSeparator) {
        state = state.copy(decimalSeparator = decimalSeparator)
    }

    private fun updateThousandsSeparator(thousandsSeparator: ThousandsSeparator) {
        state = state.copy(thousandsSeparator = thousandsSeparator)
    }
}