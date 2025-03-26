package com.serhiiromanchuk.auth.presentation.screens.registration.onboarding_pref.handling

import com.serhiiromanchuk.core.presentation.ui.components.CurrencyCategoryItem
import com.serhiiromanchuk.core.presentation.ui.components.DecimalSeparatorUi
import com.serhiiromanchuk.core.presentation.ui.components.ExpensesFormatUi
import com.serhiiromanchuk.core.presentation.ui.components.ThousandsSeparatorUi

sealed interface OnboardingPrefUiEvent {
    data class ExpensesFormatClicked(val expensesFormat: ExpensesFormatUi) : OnboardingPrefUiEvent
    data class CurrencyClicked(val currencyCategoryItem: CurrencyCategoryItem) :
        OnboardingPrefUiEvent
    data class DecimalSeparatorClicked(val decimalSeparator: DecimalSeparatorUi) :
        OnboardingPrefUiEvent
    data class ThousandsSeparatorClicked(val thousandsSeparator: ThousandsSeparatorUi) :
        OnboardingPrefUiEvent
    data object StartButtonClicked : OnboardingPrefUiEvent
}