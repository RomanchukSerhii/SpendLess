package com.serhiiromanchuk.settings.presentation.screens.preferences.handling

import com.serhiiromanchuk.core.presentation.ui.states.ExpensesFormatState

data class PreferencesUiState(
    val expensesFormatState: ExpensesFormatState = ExpensesFormatState()
) {
    val isSaveButtonEnabled: Boolean
        get() {
            val decimalSeparator = expensesFormatState.decimalSeparator.separator
            val thousandsSeparator = expensesFormatState.thousandsSeparator.separator
            return decimalSeparator != thousandsSeparator
        }
}