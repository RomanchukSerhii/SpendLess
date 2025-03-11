package com.serhiiromanchuk.settings.presentation.screens.preferences.handling

import com.serhiiromanchuk.core.presentation.designsystem.components.expenses_settings.CurrencyCategoryItem
import com.serhiiromanchuk.core.presentation.designsystem.components.expenses_settings.DecimalSeparatorUi
import com.serhiiromanchuk.core.presentation.designsystem.components.expenses_settings.ExpensesFormatUi
import com.serhiiromanchuk.core.presentation.designsystem.components.expenses_settings.ThousandsSeparatorUi

sealed interface PreferencesUiEvent {
    data class ExpensesFormatClicked(val expensesFormat: ExpensesFormatUi) : PreferencesUiEvent
    data class CurrencyClicked(val currencyCategoryItem: CurrencyCategoryItem) : PreferencesUiEvent
    data class DecimalSeparatorClicked(val decimalSeparator: DecimalSeparatorUi) : PreferencesUiEvent
    data class ThousandsSeparatorClicked(val thousandsSeparator: ThousandsSeparatorUi) : PreferencesUiEvent
    data object SaveButtonClicked : PreferencesUiEvent
}