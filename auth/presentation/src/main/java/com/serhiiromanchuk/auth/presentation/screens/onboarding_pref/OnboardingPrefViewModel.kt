package com.serhiiromanchuk.auth.presentation.screens.onboarding_pref

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.serhiiromanchuk.auth.presentation.screens.onboarding_pref.handling.OnboardingPrefUiEvent
import com.serhiiromanchuk.auth.presentation.screens.onboarding_pref.handling.OnboardingPrefUiEvent.*
import com.serhiiromanchuk.auth.presentation.screens.onboarding_pref.handling.OnboardingPrefUiState
import com.serhiiromanchuk.core.presentation.designsystem.components.currency_format.CurrencyCategoryItem
import com.serhiiromanchuk.core.presentation.designsystem.components.currency_format.CurrencyFormatState
import com.serhiiromanchuk.core.presentation.designsystem.components.currency_format.DecimalSeparator
import com.serhiiromanchuk.core.presentation.designsystem.components.currency_format.ExpensesFormat
import com.serhiiromanchuk.core.presentation.designsystem.components.currency_format.ThousandsSeparator

class OnboardingPrefViewModel : ViewModel() {
    var state by mutableStateOf(OnboardingPrefUiState())
        private set

    fun onEvent(event: OnboardingPrefUiEvent) {
        when (event) {
            is CurrencyClicked -> updateCurrency(event.currencyCategoryItem)
            is DecimalSeparatorClicked -> updateDecimalSeparator(event.decimalSeparator)
            is ExpensesFormatClicked -> updateExpensesFormat(event.expensesFormat)
            is ThousandsSeparatorClicked -> updateThousandsSeparator(event.thousandsSeparator)
            StartButtonClicked -> TODO()
        }
    }

    private fun updateExpensesFormat(expensesFormat: ExpensesFormat) {
        updateCurrencyFormatState { it.copy(expensesFormat = expensesFormat) }
    }

    private fun updateCurrency(currency: CurrencyCategoryItem) {
        updateCurrencyFormatState { it.copy(currency = currency) }
    }

    private fun updateDecimalSeparator(decimalSeparator: DecimalSeparator) {
        updateCurrencyFormatState { it.copy(decimalSeparator = decimalSeparator) }
    }

    private fun updateThousandsSeparator(thousandsSeparator: ThousandsSeparator) {
        updateCurrencyFormatState { it.copy(thousandsSeparator = thousandsSeparator) }
    }

    private fun updateCurrencyFormatState(
        update: (CurrencyFormatState) -> CurrencyFormatState
    ) {
        state = state.copy(currencyFormatState = update(state.currencyFormatState))
    }
}