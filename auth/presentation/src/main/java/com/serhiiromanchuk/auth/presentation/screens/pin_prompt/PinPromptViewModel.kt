package com.serhiiromanchuk.auth.presentation.screens.pin_prompt

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serhiiromanchuk.auth.presentation.R
import com.serhiiromanchuk.auth.presentation.screens.pin_prompt.handling.PinPromptAction
import com.serhiiromanchuk.auth.presentation.screens.pin_prompt.handling.PinPromptUiEvent
import com.serhiiromanchuk.auth.presentation.screens.pin_prompt.handling.PinPromptUiEvent.BackspaceButtonClicked
import com.serhiiromanchuk.auth.presentation.screens.pin_prompt.handling.PinPromptUiEvent.LogOutClicked
import com.serhiiromanchuk.auth.presentation.screens.pin_prompt.handling.PinPromptUiEvent.NumberButtonClicked
import com.serhiiromanchuk.auth.presentation.screens.pin_prompt.handling.PinPromptUiState
import com.serhiiromanchuk.core.domain.entity.LockedOutDuration
import com.serhiiromanchuk.core.domain.exception.UserNotLoggedInException
import com.serhiiromanchuk.core.domain.repository.SessionRepository
import com.serhiiromanchuk.core.domain.repository.UserRepository
import com.serhiiromanchuk.core.presentation.ui.UiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.Locale

class PinPromptViewModel(
    private val userRepository: UserRepository,
    private val sessionRepository: SessionRepository
) : ViewModel() {
    var state by mutableStateOf(PinPromptUiState())
        private set

    private val _actions = Channel<PinPromptAction>()
    val actions = _actions.receiveAsFlow()

    init {
        setGreeting()
    }

    private fun setGreeting() {
        val username = getUsername()
        state = state.copy(userGreeting = UiText.StringResource(R.string.user_greeting, username))
    }

    fun onEvent(event: PinPromptUiEvent) {
        when (event) {
            BackspaceButtonClicked -> removePinNumber()
            LogOutClicked -> logOutUser()
            is NumberButtonClicked -> updatePin(event.number)
        }
    }

    private fun logOutUser() {
        viewModelScope.launch {
            sessionRepository.logOut()
            _actions.send(PinPromptAction.NavigateToLogin)
        }
    }

    private fun updatePin(number: Int) {
        state = state.copy(enteredPin = state.enteredPin + number)

        if (state.enteredPin.length == PIN_LENGTH) {
            validatePin(state.enteredPin)
        }
    }

    private fun validatePin(enteredPin: String) {
        viewModelScope.launch {
            val username = getUsername()
            val user = userRepository.getUser(username)
                ?: throw IllegalArgumentException("User with the provided username does not exist")

            if (enteredPin == user.pin) {
                sessionRepository.startSession()
                _actions.send(PinPromptAction.NavigateNavigateBack)
            } else {
                resetEnteredPin()
                showError()
                updateAttempt()

                if (state.currentAttempt == MAX_ATTEMPT) {
                    lockKeyboard(user.settings.lockedOutDuration)
                }
            }
        }
    }

    private fun removePinNumber() {
        state = state.copy(enteredPin = state.enteredPin.dropLast(1))
    }

    private fun resetEnteredPin() {
        state = state.copy(enteredPin = "")
    }

    private fun lockKeyboard(lockedOutDuration: LockedOutDuration) {
        updateAttempt(resetAttempt = true)
        disableKeyboard(lockedOutDuration)
    }

    private fun disableKeyboard(lockedOutDuration: LockedOutDuration) {
        viewModelScope.launch {
            toggleKeyboardState()
            val durationInSeconds = when (lockedOutDuration) {
                LockedOutDuration.FIFTEEN_SEC -> FIFTEEN_SECONDS
                LockedOutDuration.THIRTY_SEC -> THIRTY_SECONDS
                LockedOutDuration.ONE_MIN -> ONE_MINUTE
                LockedOutDuration.FIVE_MIN -> FIVE_MINUTES
            }

            for (i in durationInSeconds downTo 0) {
                val timerValue = formatTimeToMinuteSecond(i)
                updateDescription(R.string.try_your_pin_again, timerValue)

                if (i != 0) {
                    delay(1000)
                } else {
                    toggleKeyboardState()
                    updateDescription(R.string.enter_you_pin)
                }
            }
        }
    }

    private fun formatTimeToMinuteSecond(seconds: Int): String {
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60

        return String.format(Locale.ROOT, "%02d:%02d", minutes, remainingSeconds)
    }

    private fun toggleKeyboardState() {
        state = state.copy(isKeyboardEnabled = !state.isKeyboardEnabled)
    }

    private fun updateAttempt(resetAttempt: Boolean = false) {
        state = if (resetAttempt) {
            state.copy(currentAttempt = 0)
        } else {
            state.copy(currentAttempt = state.currentAttempt.inc())
        }
    }

    private fun updateDescription(descriptionRes: Int, vararg args: Any) {
        state = state.copy(
            description = UiText.StringResource(
                id = descriptionRes,
                args = args
            )
        )
    }

    private fun getUsername(): String {
        return sessionRepository.getLoggedInUsername() ?: throw UserNotLoggedInException()
    }

    private fun showError() {
        viewModelScope.launch {
            state = state.copy(showError = true)
            delay(2000)
            state = state.copy(showError = false)
        }
    }

    companion object {
        private const val PIN_LENGTH = 5
        private const val MAX_ATTEMPT = 3
        private const val FIFTEEN_SECONDS = 15
        private const val THIRTY_SECONDS = 30
        private const val ONE_MINUTE = 60
        private const val FIVE_MINUTES = 300
    }
}