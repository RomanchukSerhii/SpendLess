package com.serhiiromanchuk.settings.presentation.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serhiiromanchuk.core.domain.entity.User
import com.serhiiromanchuk.core.domain.exception.UserNotLoggedInException
import com.serhiiromanchuk.core.domain.repository.SessionRepository
import com.serhiiromanchuk.core.domain.repository.UserRepository
import com.serhiiromanchuk.core.presentation.ui.components.CurrencyCategoryItem
import com.serhiiromanchuk.core.presentation.ui.components.DecimalSeparatorUi
import com.serhiiromanchuk.core.presentation.ui.components.ExpensesFormatUi
import com.serhiiromanchuk.core.presentation.ui.components.ThousandsSeparatorUi
import com.serhiiromanchuk.core.presentation.ui.mappers.toDomain
import com.serhiiromanchuk.core.presentation.ui.states.ExpensesFormatState
import com.serhiiromanchuk.settings.presentation.screens.preferences.handling.PreferencesAction
import com.serhiiromanchuk.settings.presentation.screens.preferences.handling.PreferencesUiEvent
import com.serhiiromanchuk.settings.presentation.screens.preferences.handling.PreferencesUiEvent.CurrencyClicked
import com.serhiiromanchuk.settings.presentation.screens.preferences.handling.PreferencesUiEvent.DecimalSeparatorClicked
import com.serhiiromanchuk.settings.presentation.screens.preferences.handling.PreferencesUiEvent.ExpensesFormatClicked
import com.serhiiromanchuk.settings.presentation.screens.preferences.handling.PreferencesUiEvent.ThousandsSeparatorClicked
import com.serhiiromanchuk.settings.presentation.screens.preferences.handling.PreferencesUiState
import com.serhiiromanchuk.settings.presentation.screens.security.components.BiometricsPromptUi
import com.serhiiromanchuk.settings.presentation.screens.security.components.LockedOutDurationUi
import com.serhiiromanchuk.settings.presentation.screens.security.components.SessionExpiryDurationUi
import com.serhiiromanchuk.settings.presentation.screens.security.components.toDomain
import com.serhiiromanchuk.settings.presentation.screens.security.components.toUi
import com.serhiiromanchuk.settings.presentation.screens.security.handling.SecurityAction
import com.serhiiromanchuk.settings.presentation.screens.security.handling.SecurityUiEvent
import com.serhiiromanchuk.settings.presentation.screens.security.handling.SecurityUiEvent.BiometricsPromptClicked
import com.serhiiromanchuk.settings.presentation.screens.security.handling.SecurityUiEvent.LockedOutClicked
import com.serhiiromanchuk.settings.presentation.screens.security.handling.SecurityUiEvent.SaveButtonClicked
import com.serhiiromanchuk.settings.presentation.screens.security.handling.SecurityUiEvent.SessionExpiryClicked
import com.serhiiromanchuk.settings.presentation.screens.security.handling.SecurityUiState
import com.serhiiromanchuk.settings.presentation.screens.settings.handling.SettingsAction
import com.serhiiromanchuk.settings.presentation.screens.settings.handling.SettingsUiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SettingsSharedViewModel(
    private val userRepository: UserRepository,
    private val sessionRepository: SessionRepository
) : ViewModel() {
    var securityState by mutableStateOf(SecurityUiState())
        private set

    var preferencesState by mutableStateOf(PreferencesUiState())
        private set

    private val _preferencesActions = Channel<PreferencesAction>()
    val preferencesActions = _preferencesActions.receiveAsFlow()

    private val _securityActions = Channel<SecurityAction>()
    val securityActions = _securityActions.receiveAsFlow()

    private val _settingsActions = Channel<SettingsAction>()
    val settingsActions = _settingsActions.receiveAsFlow()

    init {
        setUserSettings()
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

    fun onEvent(event: SettingsUiEvent) {
        when (event) {
            SettingsUiEvent.LogOutButtonClicked -> logOutUser()
        }
    }

    private fun setUserSettings() {
        viewModelScope.launch {
            val user = getUser()

            securityState = securityState.copy(
                biometricsPrompt = user.settings.biometricsPrompt.toUi(),
                sessionExpiryDuration = user.settings.sessionExpiryDuration.toUi(),
                lockedOutDuration = user.settings.lockedOutDuration.toUi()
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

    private fun logOutUser() {
        viewModelScope.launch {
            sessionRepository.logOut()
            _settingsActions.send(SettingsAction.NavigateToLogin)
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

            viewModelScope.launch {
                if (sessionRepository.isSessionExpired()) {
                    _preferencesActions.send(PreferencesAction.NavigateToPinPrompt)
                } else {
                    _preferencesActions.send(PreferencesAction.NavigateBack)
                }
            }
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
        viewModelScope.launch {
            val user = getUser()

            userRepository.upsertUser(
                user.copy(
                    settings = user.settings.copy(
                        biometricsPrompt = securityState.biometricsPrompt.toDomain(),
                        sessionExpiryDuration = securityState.sessionExpiryDuration.toDomain(),
                        lockedOutDuration = securityState.lockedOutDuration.toDomain()
                    )
                )
            )

            viewModelScope.launch {
                if (sessionRepository.isSessionExpired()) {
                    _securityActions.send(SecurityAction.NavigateToPinPrompt)
                } else {
                    sessionRepository.startSession(user.settings.sessionExpiryDuration)
                    _securityActions.send(SecurityAction.NavigateBack)
                }
            }
        }
    }

    private suspend fun getUser(): User {
        val username = sessionRepository.getLoggedInUsername() ?: throw UserNotLoggedInException()

        return userRepository.getUser(username)
            ?: throw IllegalArgumentException("User with username $username not found")
    }
}