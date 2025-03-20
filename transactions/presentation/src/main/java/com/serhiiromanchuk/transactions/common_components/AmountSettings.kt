package com.serhiiromanchuk.transactions.common_components

import com.serhiiromanchuk.core.presentation.designsystem.components.expenses_settings.CurrencyCategoryItem
import com.serhiiromanchuk.core.presentation.designsystem.components.expenses_settings.DecimalSeparatorUi
import com.serhiiromanchuk.core.presentation.designsystem.components.expenses_settings.ExpensesFormatUi
import com.serhiiromanchuk.core.presentation.designsystem.components.expenses_settings.ThousandsSeparatorUi

data class AmountSettings(
    val expensesFormat: ExpensesFormatUi = ExpensesFormatUi.MINUS,
    val currency: CurrencyCategoryItem = CurrencyCategoryItem.USD,
    val decimalSeparator: DecimalSeparatorUi = DecimalSeparatorUi.POINT,
    val thousandsSeparator: ThousandsSeparatorUi = ThousandsSeparatorUi.COMMA,
)