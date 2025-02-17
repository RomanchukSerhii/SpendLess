package com.serhiiromanchuk.settings.presentation.screens.preferences.handling

import com.serhiiromanchuk.settings.presentation.screens.CurrencyCategoryItem
import com.serhiiromanchuk.settings.presentation.screens.DecimalSeparator
import com.serhiiromanchuk.settings.presentation.screens.ExpensesFormat
import com.serhiiromanchuk.settings.presentation.screens.ThousandsSeparator

sealed interface PreferencesUiEvent {
    data class ExpensesFormatClicked(val expensesFormat: ExpensesFormat) : PreferencesUiEvent
    data class CurrencyClicked(val currencyCategoryItem: CurrencyCategoryItem) : PreferencesUiEvent
    data class DecimalSeparatorClicked(val decimalSeparator: DecimalSeparator) : PreferencesUiEvent
    data class ThousandsSeparatorClicked(val thousandsSeparator: ThousandsSeparator) : PreferencesUiEvent
    data object SaveButtonClicked : PreferencesUiEvent
}