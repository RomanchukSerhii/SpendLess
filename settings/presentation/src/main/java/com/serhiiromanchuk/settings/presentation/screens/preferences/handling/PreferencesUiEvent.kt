package com.serhiiromanchuk.settings.presentation.screens.preferences.handling

import com.serhiiromanchuk.core.presentation.ui.components.CurrencyCategoryItem
import com.serhiiromanchuk.core.presentation.ui.components.DecimalSeparatorUi
import com.serhiiromanchuk.core.presentation.ui.components.ExpensesFormatUi
import com.serhiiromanchuk.core.presentation.ui.components.ThousandsSeparatorUi

sealed interface PreferencesUiEvent {
    data class ExpensesFormatClicked(val expensesFormat: ExpensesFormatUi) : PreferencesUiEvent
    data class CurrencyClicked(val currencyCategoryItem: CurrencyCategoryItem) : PreferencesUiEvent
    data class DecimalSeparatorClicked(val decimalSeparator: DecimalSeparatorUi) : PreferencesUiEvent
    data class ThousandsSeparatorClicked(val thousandsSeparator: ThousandsSeparatorUi) : PreferencesUiEvent
    data object SaveButtonClicked : PreferencesUiEvent
}