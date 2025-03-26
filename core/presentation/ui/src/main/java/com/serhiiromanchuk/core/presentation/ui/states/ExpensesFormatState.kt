package com.serhiiromanchuk.core.presentation.ui.states

import com.serhiiromanchuk.core.presentation.ui.components.CurrencyCategoryItem
import com.serhiiromanchuk.core.presentation.ui.components.DecimalSeparatorUi
import com.serhiiromanchuk.core.presentation.ui.components.ExpensesFormatUi
import com.serhiiromanchuk.core.presentation.ui.components.ThousandsSeparatorUi

data class ExpensesFormatState(
    val expensesFormat: ExpensesFormatUi = ExpensesFormatUi.MINUS,
    val currency: CurrencyCategoryItem = CurrencyCategoryItem.USD,
    val decimalSeparator: DecimalSeparatorUi = DecimalSeparatorUi.POINT,
    val thousandsSeparator: ThousandsSeparatorUi = ThousandsSeparatorUi.COMMA
) {
    val formattingExample: String
        get() {
            val example = "${currency.symbol}10${thousandsSeparator.separator}382${decimalSeparator.separator}45"
            return when (expensesFormat) {
                ExpensesFormatUi.MINUS -> "-$example"
                ExpensesFormatUi.PARENTHESES -> "($example)"
            }
        }
}