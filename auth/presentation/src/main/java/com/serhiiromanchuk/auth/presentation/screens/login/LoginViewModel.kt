package com.serhiiromanchuk.auth.presentation.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serhiiromanchuk.auth.domain.UserDataValidator
import com.serhiiromanchuk.auth.presentation.screens.login.handling.LoginAction
import com.serhiiromanchuk.auth.presentation.screens.login.handling.LoginUiEvent
import com.serhiiromanchuk.auth.presentation.screens.login.handling.LoginUiEvent.LogInButtonClicked
import com.serhiiromanchuk.auth.presentation.screens.login.handling.LoginUiEvent.PinTextChanged
import com.serhiiromanchuk.auth.presentation.screens.login.handling.LoginUiEvent.RegistrationButtonClicked
import com.serhiiromanchuk.auth.presentation.screens.login.handling.LoginUiEvent.UsernameTextChanged
import com.serhiiromanchuk.auth.presentation.screens.login.handling.LoginUiState
import com.serhiiromanchuk.core.domain.repository.SessionRepository
import com.serhiiromanchuk.core.domain.repository.UserRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val userDataValidator: UserDataValidator,
    private val userRepository: UserRepository,
    private val sessionRepository: SessionRepository
) : ViewModel() {

    var state by mutableStateOf(LoginUiState())
        private set

    private val _actions = Channel<LoginAction>()
    val actions = _actions.receiveAsFlow()

    init {
        checkUserAuthentication()
    }

    private fun checkUserAuthentication() {
        viewModelScope.launch {
            val username = sessionRepository.getLoggedInUsername()

            if (username != null) {
                if (sessionRepository.isSessionExpired()) {
                    _actions.send(LoginAction.NavigateToPinPrompt)
                } else {
                    _actions.send(LoginAction.NavigateToTransactions)
                }
            } else {
                sendAction(LoginAction.RequestFocus)
            }
        }
    }

    fun onEvent(event: LoginUiEvent) {
        when (event) {
            is UsernameTextChanged -> updateUsername(event.username)
            is PinTextChanged -> updatePin(event.pin)

            LogInButtonClicked -> validateUser()
            RegistrationButtonClicked -> sendAction(LoginAction.NavigateToRegistration)
        }
    }

    private fun validateUser() {
        state = state.copy(
            userValidationState = userDataValidator.validateUserData(
                username = state.username,
                pin = state.pin
            )
        )

        if (state.userValidationState.isValidUser) {
            viewModelScope.launch {
                val user = userRepository.getUser(username = state.username)

                if (user != null) {
                    if (state.pin == user.pin) {
                        sessionRepository.logIn(state.username)
                        sendAction(LoginAction.NavigateToTransactions)
                    } else {
                        updateError(true)
                    }
                } else {
                    updateError(true)
                }
            }
        }
    }

    private fun sendAction(action: LoginAction) {
        viewModelScope.launch { _actions.send(action) }
    }

    private fun updateUsername(username: String) {
        state = state.copy(username = username)
        if (state.showError) updateError(false)
    }

    private fun updateError(showError: Boolean) {
        state = state.copy(showError = showError)
    }

    private fun updatePin(pin: String) {
        state = state.copy(pin = pin)
    }
}