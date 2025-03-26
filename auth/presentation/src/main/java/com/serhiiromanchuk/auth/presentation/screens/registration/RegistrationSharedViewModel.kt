package com.serhiiromanchuk.auth.presentation.screens.registration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serhiiromanchuk.auth.domain.UserDataValidator
import com.serhiiromanchuk.auth.domain.UserValidationState
import com.serhiiromanchuk.auth.presentation.screens.registration.create_pin.handling.CreatePinAction
import com.serhiiromanchuk.auth.presentation.screens.registration.create_pin.handling.CreatePinUiEvent
import com.serhiiromanchuk.auth.presentation.screens.registration.create_pin.handling.CreatePinUiState
import com.serhiiromanchuk.auth.presentation.screens.registration.create_pin.handling.CreatePinUiState.ScreenMode
import com.serhiiromanchuk.auth.presentation.screens.registration.create_username.handling.CreateUsernameAction
import com.serhiiromanchuk.auth.presentation.screens.registration.create_username.handling.CreateUsernameUiEvent
import com.serhiiromanchuk.auth.presentation.screens.registration.create_username.handling.CreateUsernameUiState
import com.serhiiromanchuk.auth.presentation.screens.registration.onboarding_pref.handling.OnboardingPrefAction
import com.serhiiromanchuk.auth.presentation.screens.registration.onboarding_pref.handling.OnboardingPrefUiEvent
import com.serhiiromanchuk.auth.presentation.screens.registration.onboarding_pref.handling.OnboardingPrefUiState
import com.serhiiromanchuk.core.domain.entity.User
import com.serhiiromanchuk.core.domain.entity.UserSettings
import com.serhiiromanchuk.core.domain.repository.SessionRepository
import com.serhiiromanchuk.core.domain.repository.UserRepository
import com.serhiiromanchuk.core.presentation.ui.components.CurrencyCategoryItem
import com.serhiiromanchuk.core.presentation.ui.components.DecimalSeparatorUi
import com.serhiiromanchuk.core.presentation.ui.states.ExpensesFormatState
import com.serhiiromanchuk.core.presentation.ui.components.ExpensesFormatUi
import com.serhiiromanchuk.core.presentation.ui.components.ThousandsSeparatorUi
import com.serhiiromanchuk.core.presentation.ui.mappers.toDomain
import com.serhiiromanchuk.core.presentation.ui.textAsFlow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class RegistrationSharedViewModel(
    private val userDataValidator: UserDataValidator,
    private val savedStateHandle: SavedStateHandle,
    private val userRepository: UserRepository,
    private val sessionRepository: SessionRepository
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

    private val _onboardingPrefActions = Channel<OnboardingPrefAction>()
    val onboardingPrefAction = _onboardingPrefActions.receiveAsFlow()

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
            OnboardingPrefUiEvent.StartButtonClicked -> saveUser()
        }
    }

    private fun saveUser() {
        val username = savedStateHandle.get<String>(USERNAME_KEY) ?: throw IllegalArgumentException("User name cannot be empty")
        val pin = savedStateHandle.get<String>(PIN_KEY) ?: throw IllegalArgumentException("Pin cannot be empty")
        val expensesFormatState = onboardingPrefState.expensesFormatState
        val settings = UserSettings(
            expensesFormat = expensesFormatState.expensesFormat.toDomain(),
            currency = expensesFormatState.currency.toDomain(),
            decimalSeparator = expensesFormatState.decimalSeparator.toDomain(),
            thousandsSeparator = expensesFormatState.thousandsSeparator.toDomain()
        )

        val user = User(
            username = username,
            pin = pin,
            settings = settings
        )

        viewModelScope.launch {
            userRepository.upsertUser(user)
            sessionRepository.logIn(user.username)
            _onboardingPrefActions.send(OnboardingPrefAction.NavigateToTransactions)
        }
     }

    private fun handleUsernameInput(newText: CharSequence) {
        val filteredText = newText.filter { it.isLetterOrDigit() }

        if (filteredText != newText) {
            usernameState.username.edit {
                replace(0, usernameState.username.text.length, filteredText)
            }

            if (usernameState.isUsernameTaken) {
                usernameState = usernameState.copy(isUsernameTaken = false)
            }
        }

        if (!usernameState.userValidationState.isValidUsername) {
            usernameState = usernameState.copy(userValidationState = UserValidationState())
        }
    }

    private fun validateUsername() {
        usernameState = usernameState.copy(
            userValidationState = userDataValidator.validateUsername(usernameState.username.text.toString())
        )

        if (usernameState.userValidationState.isValidUsername) {
            viewModelScope.launch {
                val username = usernameState.username.text.toString()
                val user = userRepository.getUser(username)

                if (user != null) {
                    usernameState = usernameState.copy(isUsernameTaken = true)
                } else {
                    savedStateHandle[USERNAME_KEY] = username
                    _usernameActions.send(CreateUsernameAction.NavigateToCreatePinScreen)
                }
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

    private fun updateExpensesFormat(expensesFormat: ExpensesFormatUi) {
        updateCurrencyFormatState { it.copy(expensesFormat = expensesFormat) }
    }

    private fun updateCurrency(currency: CurrencyCategoryItem) {
        updateCurrencyFormatState { it.copy(currency = currency) }
    }

    private fun updateDecimalSeparator(decimalSeparator: DecimalSeparatorUi) {
        updateCurrencyFormatState { it.copy(decimalSeparator = decimalSeparator) }
    }

    private fun updateThousandsSeparator(thousandsSeparator: ThousandsSeparatorUi) {
        updateCurrencyFormatState { it.copy(thousandsSeparator = thousandsSeparator) }
    }

    private fun updateCurrencyFormatState(
        update: (ExpensesFormatState) -> ExpensesFormatState
    ) {
        onboardingPrefState = onboardingPrefState.copy(expensesFormatState = update(onboardingPrefState.expensesFormatState))
    }

    private companion object {
        const val USERNAME_KEY = "username_key"
        const val PIN_KEY = "pin_key"
    }
}