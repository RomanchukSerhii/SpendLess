package com.serhiiromanchuk.auth.presentation.screens.onboarding_pref.handling

import com.serhiiromanchuk.core.presentation.designsystem.components.currency_format.CurrencyCategoryItem
import com.serhiiromanchuk.core.presentation.designsystem.components.currency_format.DecimalSeparator
import com.serhiiromanchuk.core.presentation.designsystem.components.currency_format.ExpensesFormat
import com.serhiiromanchuk.core.presentation.designsystem.components.currency_format.ThousandsSeparator

sealed interface OnboardingPrefUiEvent {
    data class ExpensesFormatClicked(val expensesFormat: ExpensesFormat) : OnboardingPrefUiEvent
    data class CurrencyClicked(val currencyCategoryItem: CurrencyCategoryItem) : OnboardingPrefUiEvent
    data class DecimalSeparatorClicked(val decimalSeparator: DecimalSeparator) : OnboardingPrefUiEvent
    data class ThousandsSeparatorClicked(val thousandsSeparator: ThousandsSeparator) : OnboardingPrefUiEvent
    data object StartButtonClicked : OnboardingPrefUiEvent
}