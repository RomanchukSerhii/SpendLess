package com.serhiiromanchuk.auth.domain

class UserDataValidator {

    fun validateUsername(username: String): UserValidationState {
        val hasMinLength = username.length >= MIN_USERNAME_LENGTH
        val isWithinMaxLength = username.length <= MAX_USERNAME_LENGTH

        return UserValidationState(
            hasMinLength = hasMinLength,
            isWithinMaxLength = isWithinMaxLength
        )
    }

    fun validatePin(pin: String): UserValidationState {
        return UserValidationState(
            hasFiveDigitPin = pin.length == PIN_REQUIRED_LENGTH
        )
    }

    fun validateUserData(username: String, pin: String): UserValidationState {
        val usernameState = validateUsername(username)
        val pinState = validatePin(pin)

        return UserValidationState(
            hasMinLength = usernameState.hasMinLength,
            isWithinMaxLength = usernameState.isWithinMaxLength,
            hasFiveDigitPin = pinState.hasFiveDigitPin
        )
    }

    companion object {
        const val MIN_USERNAME_LENGTH = 3
        const val MAX_USERNAME_LENGTH = 14
        const val PIN_REQUIRED_LENGTH = 5
    }
}