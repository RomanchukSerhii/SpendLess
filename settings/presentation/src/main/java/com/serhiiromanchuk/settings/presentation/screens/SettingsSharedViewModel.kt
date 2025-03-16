package com.serhiiromanchuk.settings.presentation.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serhiiromanchuk.core.domain.entity.User
import com.serhiiromanchuk.core.domain.repository.UserRepository
import com.serhiiromanchuk.core.presentation.designsystem.components.expenses_settings.CurrencyCategoryItem
import com.serhiiromanchuk.core.presentation.designsystem.components.expenses_settings.DecimalSeparatorUi
import com.serhiiromanchuk.core.presentation.designsystem.components.expenses_settings.ExpensesFormatState
import com.serhiiromanchuk.core.presentation.designsystem.components.expenses_settings.ExpensesFormatUi
import com.serhiiromanchuk.core.presentation.designsystem.components.expenses_settings.ThousandsSeparatorUi
import com.serhiiromanchuk.core.presentation.ui.mappers.toDomain
import com.serhiiromanchuk.settings.presentation.screens.preferences.handling.PreferencesUiEvent
import com.serhiiromanchuk.settings.presentation.screens.preferences.handling.PreferencesUiEvent.CurrencyClicked
import com.serhiiromanchuk.settings.presentation.screens.preferences.handling.PreferencesUiEvent.DecimalSeparatorClicked
import com.serhiiromanchuk.settings.presentation.screens.preferences.handling.PreferencesUiEvent.ExpensesFormatClicked
import com.serhiiromanchuk.settings.presentation.screens.preferences.handling.PreferencesUiEvent.ThousandsSeparatorClicked
import com.serhiiromanchuk.settings.presentation.screens.preferences.handling.PreferencesUiState
import com.serhiiromanchuk.settings.presentation.screens.security.handling.SecurityUiEvent
import com.serhiiromanchuk.settings.presentation.screens.security.handling.SecurityUiEvent.BiometricsPromptClicked
import com.serhiiromanchuk.settings.presentation.screens.security.handling.SecurityUiEvent.LockedOutClicked
import com.serhiiromanchuk.settings.presentation.screens.security.handling.SecurityUiEvent.SaveButtonClicked
import com.serhiiromanchuk.settings.presentation.screens.security.handling.SecurityUiEvent.SessionExpiryClicked
import com.serhiiromanchuk.settings.presentation.screens.security.handling.SecurityUiState
import kotlinx.coroutines.launch

class SettingsSharedViewModel(
    private val username: String,
    private val userRepository: UserRepository
) : ViewModel() {
    var securityState by mutableStateOf(SecurityUiState())
        private set

    var preferencesState by mutableStateOf(PreferencesUiState())
        private set

    init {
        setUserSettings()
    }

    private fun setUserSettings() {
        viewModelScope.launch {
            val user = getUser()

            securityState = securityState.copy(
                biometricsPrompt = BiometricsPromptUi.valueOf(user.settings.biometricsPrompt.name),
                sessionExpiryDuration = SessionExpiryDurationUi.valueOf(user.settings.sessionExpiryDuration.name),
                lockedOutDuration = LockedOutDurationUi.valueOf(user.settings.lockedOutDuration.name)
            )

            updateExpensesFormatState {
                it.copy(
                    expensesFormat = ExpensesFormatUi.valueOf(user.settings.expensesFormat.name),
                    currency = CurrencyCategoryItem.valueOf(user.settings.currency.name),
                    decimalSeparator = DecimalSeparatorUi.valueOf(user.settings.decimalSeparator.name),
                    thousandsSeparator = ThousandsSeparatorUi.valueOf(user.settings.thousandsSeparator.name)
                )
            }
        }
    }

    fun onEvent(event: SecurityUiEvent) {
        when (event) {
            is BiometricsPromptClicked -> updateBiometricsPrompt(event.biometricsPrompt)
            is LockedOutClicked -> updateLockedOutDuration(event.lockedOutDuration)
            is SessionExpiryClicked -> updateSessionExpiryDuration(event.sessionExpiryDuration)
            SaveButtonClicked -> saveSecuritySettings()
        }
    }

    fun onEvent(event: PreferencesUiEvent) {
        when (event) {
            is CurrencyClicked -> updateCurrency(event.currencyCategoryItem)
            is DecimalSeparatorClicked -> updateDecimalSeparator(event.decimalSeparator)
            is ExpensesFormatClicked -> updateExpensesFormat(event.expensesFormat)
            is ThousandsSeparatorClicked -> updateThousandsSeparator(event.thousandsSeparator)
            PreferencesUiEvent.SaveButtonClicked -> savePreferencesSettings()
        }
    }

    private fun updateExpensesFormat(expensesFormat: ExpensesFormatUi) {
        updateExpensesFormatState { it.copy(expensesFormat = expensesFormat) }
    }

    private fun updateCurrency(currency: CurrencyCategoryItem) {
        updateExpensesFormatState { it.copy(currency = currency) }
    }

    private fun updateDecimalSeparator(decimalSeparator: DecimalSeparatorUi) {
        updateExpensesFormatState { it.copy(decimalSeparator = decimalSeparator) }
    }

    private fun updateThousandsSeparator(thousandsSeparator: ThousandsSeparatorUi) {
        updateExpensesFormatState { it.copy(thousandsSeparator = thousandsSeparator) }
    }

    private fun updateExpensesFormatState(
        update: (ExpensesFormatState) -> ExpensesFormatState
    ) {
        preferencesState = preferencesState.copy(
            expensesFormatState = update(preferencesState.expensesFormatState)
        )
    }

    private fun savePreferencesSettings() {
        viewModelScope.launch {
            val user = getUser()
            val expensesFormatState = preferencesState.expensesFormatState
            userRepository.upsertUser(
                user.copy(
                    settings = user.settings.copy(
                        expensesFormat = expensesFormatState.expensesFormat.toDomain(),
                        currency = expensesFormatState.currency.toDomain(),
                        decimalSeparator = expensesFormatState.decimalSeparator.toDomain(),
                        thousandsSeparator = expensesFormatState.thousandsSeparator.toDomain()
                    )
                )
            )
        }
    }

    private fun updateBiometricsPrompt(biometricsPrompt: BiometricsPromptUi) {
        securityState = securityState.copy(biometricsPrompt = biometricsPrompt)
    }

    private fun updateLockedOutDuration(lockedOutDuration: LockedOutDurationUi) {
        securityState = securityState.copy(lockedOutDuration = lockedOutDuration)
    }

    private fun updateSessionExpiryDuration(sessionExpiryDuration: SessionExpiryDurationUi) {
        securityState = securityState.copy(sessionExpiryDuration = sessionExpiryDuration)
    }

    private fun saveSecuritySettings() {

    }

    private suspend fun getUser(): User {
        return userRepository.getUser(username)
            ?: throw IllegalArgumentException("User with username $username not found")
    }
}