package com.serhiiromanchuk.auth.presentation.screens.onboarding_pref.handling

import com.serhiiromanchuk.core.presentation.designsystem.components.currency_format.CurrencyFormatState

data class OnboardingPrefUiState(
    val currencyFormatState: CurrencyFormatState = CurrencyFormatState()
) {
    val isStartButtonEnabled: Boolean
        get() {
            val decimalSeparator = currencyFormatState.decimalSeparator.separator
            val thousandsSeparator = currencyFormatState.thousandsSeparator.separator
            return decimalSeparator != thousandsSeparator
        }
}
