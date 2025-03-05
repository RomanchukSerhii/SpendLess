package com.serhiiromanchuk.core.presentation.designsystem.components.currency_format

data class CurrencyFormatState(
    val expensesFormat: ExpensesFormat = ExpensesFormat.MINUS,
    val currency: CurrencyCategoryItem = CurrencyCategoryItem.USD,
    val decimalSeparator: DecimalSeparator = DecimalSeparator.POINT,
    val thousandsSeparator: ThousandsSeparator = ThousandsSeparator.COMMA
) {
    val formattingExample: String
        get() {
            val example = "${currency.symbol}10${thousandsSeparator.separator}382${decimalSeparator.separator}45"
            return when (expensesFormat) {
                ExpensesFormat.MINUS -> "-$example"
                ExpensesFormat.PARENTHESES -> "($example)"
            }
        }
}