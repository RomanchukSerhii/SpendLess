package com.serhiiromanchuk.settings.presentation.screens.preferences.handling

import com.serhiiromanchuk.core.presentation.designsystem.components.currency_format.CurrencyFormatState

data class PreferencesUiState(
    val currencyFormatState: CurrencyFormatState = CurrencyFormatState()
) {
    val isSaveButtonEnabled: Boolean
        get() {
            val decimalSeparator = currencyFormatState.decimalSeparator.separator
            val thousandsSeparator = currencyFormatState.thousandsSeparator.separator
            return decimalSeparator != thousandsSeparator
        }
}