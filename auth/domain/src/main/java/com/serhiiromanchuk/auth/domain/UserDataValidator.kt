package com.serhiiromanchuk.auth.domain

class UserDataValidator {

    fun validateUsername(username: String): UsernameValidationState {
        val hasMinLength = username.length >= MIN_USERNAME_LENGTH
        val isWithinMaxLength = username.length <= MAX_USERNAME_LENGTH

        return UsernameValidationState(
            hasMinLength = hasMinLength,
            isWithinMaxLength = isWithinMaxLength
        )
    }

    companion object {
        const val MIN_USERNAME_LENGTH = 3
        const val MAX_USERNAME_LENGTH = 14
    }
}