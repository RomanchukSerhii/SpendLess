package com.serhiiromanchuk.core.presentation.designsystem.components.expenses_settings

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