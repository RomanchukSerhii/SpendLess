package com.serhiiromanchuk.auth.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.auth.domain.UserValidationState
import com.serhiiromanchuk.auth.presentation.R

@Composable
fun UsernameLengthError(
    validationState: UserValidationState,
    modifier: Modifier = Modifier
) {
    if (!validationState.isValidUsername) {
        Text(
            text = when  {
                !validationState.hasMinLength -> stringResource(R.string.error_min_username_length)
                !validationState.isWithinMaxLength -> stringResource(R.string.error_max_username_length)
                else -> ""
            },
            modifier = modifier.padding(top = 4.dp),
            color = MaterialTheme.colorScheme.error
        )
    }
}

@Composable
fun PinLengthError(
    validationState: UserValidationState,
    modifier: Modifier = Modifier
) {
    if (!validationState.hasFiveDigitPin) {
        Text(
            text = stringResource(R.string.error_pin_length),
            modifier = modifier.padding(top = 4.dp),
            color = MaterialTheme.colorScheme.error
        )
    }
}