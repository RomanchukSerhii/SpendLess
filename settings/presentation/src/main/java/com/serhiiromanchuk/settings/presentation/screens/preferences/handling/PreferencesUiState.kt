package com.serhiiromanchuk.settings.presentation.screens.preferences.handling

import com.serhiiromanchuk.settings.presentation.screens.preferences.components.CurrencyCategoryItem
import com.serhiiromanchuk.settings.presentation.screens.preferences.components.DecimalSeparator
import com.serhiiromanchuk.settings.presentation.screens.preferences.components.ExpensesFormat
import com.serhiiromanchuk.settings.presentation.screens.preferences.components.ThousandsSeparator

data class PreferencesUiState(
    val expensesFormat: ExpensesFormat = ExpensesFormat.MINUS,
    val currency: CurrencyCategoryItem = CurrencyCategoryItem.USD,
    val decimalSeparator: DecimalSeparator = DecimalSeparator.POINT,
    val thousandsSeparator: ThousandsSeparator = ThousandsSeparator.POINT
) {
    val formattingExample: String
        get() {
            val example = "${currency.symbol}10${thousandsSeparator.separator}382${decimalSeparator.separator}45"
            return when (expensesFormat) {
                ExpensesFormat.MINUS -> "-$example"
                ExpensesFormat.PARENTHESES -> "($example)"
            }
        }

    val isSaveButtonEnabled: Boolean
        get() {
            return decimalSeparator.separator != thousandsSeparator.separator
        }
}