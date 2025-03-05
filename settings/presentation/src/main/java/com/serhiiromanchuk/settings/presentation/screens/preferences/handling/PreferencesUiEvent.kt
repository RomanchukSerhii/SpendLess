package com.serhiiromanchuk.settings.presentation.screens.preferences.handling

import com.serhiiromanchuk.core.presentation.designsystem.components.currency_format.CurrencyCategoryItem
import com.serhiiromanchuk.core.presentation.designsystem.components.currency_format.DecimalSeparator
import com.serhiiromanchuk.core.presentation.designsystem.components.currency_format.ExpensesFormat
import com.serhiiromanchuk.core.presentation.designsystem.components.currency_format.ThousandsSeparator

sealed interface PreferencesUiEvent {
    data class ExpensesFormatClicked(val expensesFormat: ExpensesFormat) : PreferencesUiEvent
    data class CurrencyClicked(val currencyCategoryItem: CurrencyCategoryItem) : PreferencesUiEvent
    data class DecimalSeparatorClicked(val decimalSeparator: DecimalSeparator) : PreferencesUiEvent
    data class ThousandsSeparatorClicked(val thousandsSeparator: ThousandsSeparator) : PreferencesUiEvent
    data object SaveButtonClicked : PreferencesUiEvent
}