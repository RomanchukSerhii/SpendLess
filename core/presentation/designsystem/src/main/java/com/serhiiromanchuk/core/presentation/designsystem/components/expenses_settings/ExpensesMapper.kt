package com.serhiiromanchuk.core.presentation.designsystem.components.expenses_settings

import com.serhiiromanchuk.core.domain.entity.Currency
import com.serhiiromanchuk.core.domain.entity.DecimalSeparator
import com.serhiiromanchuk.core.domain.entity.ExpensesFormat
import com.serhiiromanchuk.core.domain.entity.ThousandsSeparator

fun ExpensesFormat.toUi(): ExpensesFormatUi {
    return when (this) {
        ExpensesFormat.MINUS -> ExpensesFormatUi.MINUS
        ExpensesFormat.PARENTHESES -> ExpensesFormatUi.PARENTHESES
    }
}

fun Currency.toUi(): CurrencyCategoryItem {
    return when (this) {
        Currency.USD -> CurrencyCategoryItem.USD
        Currency.EUR -> CurrencyCategoryItem.EUR
        Currency.GBP -> CurrencyCategoryItem.GBP
        Currency.JPY -> CurrencyCategoryItem.JPY
        Currency.CNY -> CurrencyCategoryItem.CNY
        Currency.INR -> CurrencyCategoryItem.INR
        Currency.ZAR -> CurrencyCategoryItem.ZAR
        Currency.CAD -> CurrencyCategoryItem.CAD
        Currency.AUD -> CurrencyCategoryItem.AUD
        Currency.CHF -> CurrencyCategoryItem.CHF
    }
}

fun DecimalSeparator.toUi(): DecimalSeparatorUi {
    return when (this) {
        DecimalSeparator.POINT -> DecimalSeparatorUi.POINT
        DecimalSeparator.COMMA -> DecimalSeparatorUi.COMMA
    }
}

fun ThousandsSeparator.toUi(): ThousandsSeparatorUi {
    return when (this) {
        ThousandsSeparator.POINT -> ThousandsSeparatorUi.POINT
        ThousandsSeparator.COMMA -> ThousandsSeparatorUi.COMMA
        ThousandsSeparator.SPACE -> ThousandsSeparatorUi.SPACE
    }
}