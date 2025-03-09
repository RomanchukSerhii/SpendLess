package com.serhiiromanchuk.auth.presentation.screens.registration.onboarding_pref.handling

import com.serhiiromanchuk.core.presentation.designsystem.components.expenses_settings.CurrencyCategoryItem
import com.serhiiromanchuk.core.presentation.designsystem.components.expenses_settings.DecimalSeparatorUi
import com.serhiiromanchuk.core.presentation.designsystem.components.expenses_settings.ExpensesFormatUi
import com.serhiiromanchuk.core.presentation.designsystem.components.expenses_settings.ThousandsSeparatorUi

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