package com.serhiiromanchuk.transactions.common_components

import com.serhiiromanchuk.core.presentation.ui.components.CurrencyCategoryItem
import com.serhiiromanchuk.core.presentation.ui.components.DecimalSeparatorUi
import com.serhiiromanchuk.core.presentation.ui.components.ExpensesFormatUi
import com.serhiiromanchuk.core.presentation.ui.components.ThousandsSeparatorUi

data class AmountSettings(
    val expensesFormat: ExpensesFormatUi = ExpensesFormatUi.MINUS,
    val currency: CurrencyCategoryItem = CurrencyCategoryItem.USD,
    val decimalSeparator: DecimalSeparatorUi = DecimalSeparatorUi.POINT,
    val thousandsSeparator: ThousandsSeparatorUi = ThousandsSeparatorUi.COMMA,
)