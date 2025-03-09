package com.serhiiromanchuk.auth.presentation.screens.registration.create_username

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serhiiromanchuk.auth.domain.UserDataValidator
import com.serhiiromanchuk.auth.domain.UsernameValidationState
import com.serhiiromanchuk.auth.presentation.screens.registration.create_username.handling.CreateUsernameAction
import com.serhiiromanchuk.auth.presentation.screens.registration.create_username.handling.CreateUsernameUiEvent
import com.serhiiromanchuk.auth.presentation.screens.registration.create_username.handling.CreateUsernameUiEvent.*
import com.serhiiromanchuk.auth.presentation.screens.registration.create_username.handling.CreateUsernameUiState
import com.serhiiromanchuk.core.presentation.ui.textAsFlow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class CreateUsernameViewModel(
    private val userDataValidator: UserDataValidator
) : ViewModel() {
    var state by mutableStateOf(CreateUsernameUiState())
        private set

    private val _actions = Channel<CreateUsernameAction>()
    val actions = _actions.receiveAsFlow()

    init {
        state.username.textAsFlow()
            .onEach(::handleUsernameInput)
            .launchIn(viewModelScope)
    }

    fun onEvent(event: CreateUsernameUiEvent) {
        when (event) {
            NextButtonClicked -> validateUsername()
        }
    }

    private fun handleUsernameInput(newText: CharSequence) {
        val filteredText = newText.filter { it.isLetterOrDigit() }

        if (filteredText != newText) {
            state.username.edit {
                replace(0, state.username.text.length, filteredText)
            }
        }

        if (!state.usernameValidationState.isValidUsername) {
            state = state.copy(usernameValidationState = UsernameValidationState())
        }
    }

    private fun validateUsername() {
        state = state.copy(
            usernameValidationState = userDataValidator.validateUsername(state.username.text.toString())
        )
        if (state.usernameValidationState.isValidUsername) {
            viewModelScope.launch {
                _actions.send(CreateUsernameAction.NavigateToCreatePinScreen)
            }
        }
    }
}