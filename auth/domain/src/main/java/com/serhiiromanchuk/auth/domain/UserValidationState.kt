package com.serhiiromanchuk.auth.domain

data class UserValidationState(
    val hasMinLength: Boolean = true,
    val isWithinMaxLength: Boolean = true,
    val hasFiveDigitPin: Boolean = true
) {
    val isValidUsername: Boolean
        get() = hasMinLength && isWithinMaxLength

    val isValidUser: Boolean
        get() = isValidUsername && hasFiveDigitPin
}
