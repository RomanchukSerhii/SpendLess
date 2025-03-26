package com.serhiiromanchuk.core.presentation.ui.mappers

import com.serhiiromanchuk.core.domain.entity.Currency
import com.serhiiromanchuk.core.domain.entity.DecimalSeparator
import com.serhiiromanchuk.core.domain.entity.ExpensesFormat
import com.serhiiromanchuk.core.domain.entity.ThousandsSeparator
import com.serhiiromanchuk.core.presentation.ui.components.CurrencyCategoryItem
import com.serhiiromanchuk.core.presentation.ui.components.DecimalSeparatorUi
import com.serhiiromanchuk.core.presentation.ui.components.ExpensesFormatUi
import com.serhiiromanchuk.core.presentation.ui.components.ThousandsSeparatorUi

fun ExpensesFormatUi.toDomain(): ExpensesFormat {
    return when (this) {
        ExpensesFormatUi.MINUS -> ExpensesFormat.MINUS
        ExpensesFormatUi.PARENTHESES -> ExpensesFormat.PARENTHESES
    }
}

fun ExpensesFormat.toUi(): ExpensesFormatUi {
    return when (this) {
        ExpensesFormat.MINUS -> ExpensesFormatUi.MINUS
        ExpensesFormat.PARENTHESES -> ExpensesFormatUi.PARENTHESES
    }
}

fun CurrencyCategoryItem.toDomain(): Currency {
    return when (this) {
        CurrencyCategoryItem.USD -> Currency.USD
        CurrencyCategoryItem.EUR -> Currency.EUR
        CurrencyCategoryItem.GBP -> Currency.GBP
        CurrencyCategoryItem.JPY -> Currency.JPY
        CurrencyCategoryItem.CNY -> Currency.CNY
        CurrencyCategoryItem.INR -> Currency.INR
        CurrencyCategoryItem.ZAR -> Currency.ZAR
        CurrencyCategoryItem.CAD -> Currency.CAD
        CurrencyCategoryItem.AUD -> Currency.AUD
        CurrencyCategoryItem.CHF -> Currency.CHF
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

fun DecimalSeparatorUi.toDomain(): DecimalSeparator {
    return when (this) {
        DecimalSeparatorUi.POINT -> DecimalSeparator.POINT
        DecimalSeparatorUi.COMMA -> DecimalSeparator.COMMA
    }
}

fun ThousandsSeparator.toUi(): ThousandsSeparatorUi {
    return when (this) {
        ThousandsSeparator.POINT -> ThousandsSeparatorUi.POINT
        ThousandsSeparator.COMMA -> ThousandsSeparatorUi.COMMA
        ThousandsSeparator.SPACE -> ThousandsSeparatorUi.SPACE
    }
}

fun ThousandsSeparatorUi.toDomain(): ThousandsSeparator {
    return when (this) {
        ThousandsSeparatorUi.POINT -> ThousandsSeparator.POINT
        ThousandsSeparatorUi.COMMA -> ThousandsSeparator.COMMA
        ThousandsSeparatorUi.SPACE -> ThousandsSeparator.SPACE
    }
}