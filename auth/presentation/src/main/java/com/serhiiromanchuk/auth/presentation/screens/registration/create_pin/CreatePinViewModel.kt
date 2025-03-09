package com.serhiiromanchuk.auth.presentation.screens.registration.create_pin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serhiiromanchuk.auth.presentation.screens.registration.create_pin.handling.CreatePinAction
import com.serhiiromanchuk.auth.presentation.screens.registration.create_pin.handling.CreatePinUiEvent
import com.serhiiromanchuk.auth.presentation.screens.registration.create_pin.handling.CreatePinUiEvent.*
import com.serhiiromanchuk.auth.presentation.screens.registration.create_pin.handling.CreatePinUiState
import com.serhiiromanchuk.auth.presentation.screens.registration.create_pin.handling.CreatePinUiState.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class CreatePinViewModel : ViewModel() {
    var state by mutableStateOf(CreatePinUiState())
        private set

    private val _actions = Channel<CreatePinAction>()
    val actions = _actions.receiveAsFlow()

    fun onEvent(event: CreatePinUiEvent) {
        when (event) {
            BackButtonClicked -> navigateBack()
            BackspaceButtonClicked -> removePinNumber()
            is NumberButtonClicked -> updatePin(event.number)
        }
    }

    private fun navigateBack() {
        when (state.screenMode) {
            ScreenMode.CreatePin -> {
                viewModelScope.launch {
                    _actions.send(CreatePinAction.NavigateBack)
                }
            }
            ScreenMode.RepeatPin -> {
                state = CreatePinUiState()
            }
        }
    }

    private fun updatePin(number: Int) {
        state = when (state.screenMode) {
            ScreenMode.CreatePin -> {
                state.copy(
                    createdPin = state.createdPin + number,
                    showError = false
                )
            }
            ScreenMode.RepeatPin -> {
                state.copy(
                    repeatedPin = state.repeatedPin + number,
                    showError = false
                )
            }
        }
        validatePin()
    }

    private fun removePinNumber() {
        state = when (state.screenMode) {
            ScreenMode.CreatePin -> state.copy(createdPin = state.createdPin.dropLast(1))
            ScreenMode.RepeatPin -> state.copy(repeatedPin = state.repeatedPin.dropLast(1))
        }
    }

    private fun toggleScreenMode() {
        state = when (state.screenMode) {
            ScreenMode.CreatePin -> state.copy(screenMode = ScreenMode.RepeatPin)
            ScreenMode.RepeatPin -> state.copy(screenMode = ScreenMode.CreatePin)
        }
    }

    private fun validatePin() {
        val isCreatedPinLengthValid = state.createdPin.length == 5
        val isRepeatedPinLengthValid = state.repeatedPin.length == 5

        when (state.screenMode) {
            ScreenMode.CreatePin -> if (isCreatedPinLengthValid) toggleScreenMode()
            ScreenMode.RepeatPin -> if (isRepeatedPinLengthValid) {
                if (state.createdPin == state.repeatedPin) {
                    state = CreatePinUiState()
                    viewModelScope.launch {
                        _actions.send(CreatePinAction.NavigateNext)
                    }
                } else {
                    state = state.copy(
                        repeatedPin = "",
                        showError = true
                    )
                }
            }
        }
    }
}