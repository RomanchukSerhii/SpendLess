package com.serhiiromanchuk.auth.presentation.screens.registration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serhiiromanchuk.auth.domain.UserDataValidator
import com.serhiiromanchuk.auth.domain.UsernameValidationState
import com.serhiiromanchuk.auth.presentation.screens.registration.create_pin.handling.CreatePinAction
import com.serhiiromanchuk.auth.presentation.screens.registration.create_pin.handling.CreatePinUiEvent
import com.serhiiromanchuk.auth.presentation.screens.registration.create_pin.handling.CreatePinUiState
import com.serhiiromanchuk.auth.presentation.screens.registration.create_pin.handling.CreatePinUiState.ScreenMode
import com.serhiiromanchuk.auth.presentation.screens.registration.create_username.handling.CreateUsernameAction
import com.serhiiromanchuk.auth.presentation.screens.registration.create_username.handling.CreateUsernameUiEvent
import com.serhiiromanchuk.auth.presentation.screens.registration.create_username.handling.CreateUsernameUiState
import com.serhiiromanchuk.auth.presentation.screens.registration.onboarding_pref.handling.OnboardingPrefUiEvent
import com.serhiiromanchuk.auth.presentation.screens.registration.onboarding_pref.handling.OnboardingPrefUiState
import com.serhiiromanchuk.core.presentation.designsystem.components.currency_format.CurrencyCategoryItem
import com.serhiiromanchuk.core.presentation.designsystem.components.currency_format.CurrencyFormatState
import com.serhiiromanchuk.core.presentation.designsystem.components.currency_format.DecimalSeparator
import com.serhiiromanchuk.core.presentation.designsystem.components.currency_format.ExpensesFormat
import com.serhiiromanchuk.core.presentation.designsystem.components.currency_format.ThousandsSeparator
import com.serhiiromanchuk.core.presentation.ui.textAsFlow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class RegistrationSharedViewModel(
    private val userDataValidator: UserDataValidator,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    var usernameState by mutableStateOf(CreateUsernameUiState())
        private set

    var pinState by mutableStateOf(CreatePinUiState())
        private set

    var onboardingPrefState by mutableStateOf(OnboardingPrefUiState())
        private set

    private val _usernameActions = Channel<CreateUsernameAction>()
    val usernameActions = _usernameActions.receiveAsFlow()

    private val _pinActions = Channel<CreatePinAction>()
    val pinActions = _pinActions.receiveAsFlow()

    init {
        usernameState.username.textAsFlow()
            .onEach(::handleUsernameInput)
            .launchIn(viewModelScope)
    }

    fun onEvent(event: CreateUsernameUiEvent) {
        when (event) {
            CreateUsernameUiEvent.NextButtonClicked -> validateUsername()
        }
    }

    fun onEvent(event: CreatePinUiEvent) {
        when (event) {
            CreatePinUiEvent.BackButtonClicked -> navigateBack()
            CreatePinUiEvent.BackspaceButtonClicked -> removePinNumber()
            is CreatePinUiEvent.NumberButtonClicked -> updatePin(event.number)
        }
    }

    fun onEvent(event: OnboardingPrefUiEvent) {
        when (event) {
            is OnboardingPrefUiEvent.CurrencyClicked -> updateCurrency(event.currencyCategoryItem)
            is OnboardingPrefUiEvent.DecimalSeparatorClicked -> updateDecimalSeparator(event.decimalSeparator)
            is OnboardingPrefUiEvent.ExpensesFormatClicked -> updateExpensesFormat(event.expensesFormat)
            is OnboardingPrefUiEvent.ThousandsSeparatorClicked -> updateThousandsSeparator(event.thousandsSeparator)
            OnboardingPrefUiEvent.StartButtonClicked -> TODO()
        }
    }

    private fun handleUsernameInput(newText: CharSequence) {
        val filteredText = newText.filter { it.isLetterOrDigit() }

        if (filteredText != newText) {
            usernameState.username.edit {
                replace(0, usernameState.username.text.length, filteredText)
            }
        }

        if (!usernameState.usernameValidationState.isValidUsername) {
            usernameState = usernameState.copy(usernameValidationState = UsernameValidationState())
        }
    }

    private fun validateUsername() {
        usernameState = usernameState.copy(
            usernameValidationState = userDataValidator.validateUsername(usernameState.username.text.toString())
        )
        if (usernameState.usernameValidationState.isValidUsername) {
            savedStateHandle[USERNAME_KEY] = usernameState.username.text.toString()
            viewModelScope.launch {
                _usernameActions.send(CreateUsernameAction.NavigateToCreatePinScreen)
            }
        }
    }

    private fun navigateBack() {
        when (pinState.screenMode) {
            ScreenMode.CreatePin -> {
                viewModelScope.launch {
                    _pinActions.send(CreatePinAction.NavigateBack)
                }
            }

            ScreenMode.RepeatPin -> {
                pinState = CreatePinUiState()
            }
        }
    }

    private fun updatePin(number: Int) {
        pinState = when (pinState.screenMode) {
            ScreenMode.CreatePin -> {
                pinState.copy(
                    createdPin = pinState.createdPin + number,
                    showError = false
                )
            }

            ScreenMode.RepeatPin -> {
                pinState.copy(
                    repeatedPin = pinState.repeatedPin + number,
                    showError = false
                )
            }
        }
        validatePin()
    }

    private fun removePinNumber() {
        pinState = when (pinState.screenMode) {
            ScreenMode.CreatePin -> pinState.copy(createdPin = pinState.createdPin.dropLast(1))
            ScreenMode.RepeatPin -> pinState.copy(repeatedPin = pinState.repeatedPin.dropLast(1))
        }
    }

    private fun toggleScreenMode() {
        pinState = when (pinState.screenMode) {
            ScreenMode.CreatePin -> pinState.copy(screenMode = ScreenMode.RepeatPin)
            ScreenMode.RepeatPin -> pinState.copy(screenMode = ScreenMode.CreatePin)
        }
    }

    private fun validatePin() {
        val isCreatedPinLengthValid = pinState.createdPin.length == 5
        val isRepeatedPinLengthValid = pinState.repeatedPin.length == 5

        when (pinState.screenMode) {
            ScreenMode.CreatePin -> if (isCreatedPinLengthValid) toggleScreenMode()
            ScreenMode.RepeatPin -> if (isRepeatedPinLengthValid) {
                if (pinState.createdPin == pinState.repeatedPin) {
                    savedStateHandle[PIN_KEY] = pinState.repeatedPin
                    viewModelScope.launch {
                        _pinActions.send(CreatePinAction.NavigateNext)
                        delay(500)
                        pinState = CreatePinUiState()
                    }
                } else {
                    pinState = pinState.copy(
                        repeatedPin = "",
                        showError = true
                    )
                }
            }
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
        onboardingPrefState = onboardingPrefState.copy(currencyFormatState = update(onboardingPrefState.currencyFormatState))
    }

    private companion object {
        const val USERNAME_KEY = "username_key"
        const val PIN_KEY = "pin_key"
    }
}