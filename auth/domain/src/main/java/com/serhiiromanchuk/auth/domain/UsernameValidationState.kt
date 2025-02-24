package com.serhiiromanchuk.auth.domain

data class UsernameValidationState(
    val hasMinLength: Boolean = true,
    val isWithinMaxLength: Boolean = true
) {
    val isValidUsername: Boolean
        get() = hasMinLength && isWithinMaxLength
}
