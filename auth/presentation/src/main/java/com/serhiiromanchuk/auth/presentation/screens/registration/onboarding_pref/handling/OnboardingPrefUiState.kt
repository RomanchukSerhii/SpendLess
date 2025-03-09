package com.serhiiromanchuk.auth.presentation.screens.registration.onboarding_pref.handling

import com.serhiiromanchuk.core.presentation.designsystem.components.expenses_settings.ExpensesFormatState

data class OnboardingPrefUiState(
    val expensesFormatState: ExpensesFormatState = ExpensesFormatState()
) {
    val isStartButtonEnabled: Boolean
        get() {
            val decimalSeparator = expensesFormatState.decimalSeparator.separator
            val thousandsSeparator = expensesFormatState.thousandsSeparator.separator
            return decimalSeparator != thousandsSeparator
        }
}
