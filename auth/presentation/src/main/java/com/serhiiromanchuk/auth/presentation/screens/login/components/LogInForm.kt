package com.serhiiromanchuk.auth.presentation.screens.login.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.auth.presentation.R
import com.serhiiromanchuk.auth.presentation.components.PinLengthError
import com.serhiiromanchuk.auth.presentation.components.UsernameLengthError
import com.serhiiromanchuk.auth.presentation.screens.login.handling.LoginUiEvent
import com.serhiiromanchuk.auth.presentation.screens.login.handling.LoginUiState
import com.serhiiromanchuk.core.presentation.designsystem.components.AppTextField
import com.serhiiromanchuk.core.presentation.designsystem.components.AppFilledButton

@Composable
fun LogInForm(
    state: LoginUiState,
    onEvent: (LoginUiEvent) -> Unit,
    focusRequester: FocusRequester,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        val focusManager = LocalFocusManager.current

        // Username text field
        Column {
            AppTextField(
                value = state.username,
                onValueChange = { username ->
                    val filteredText = username.filter { it.isLetterOrDigit() }
                    onEvent(LoginUiEvent.UsernameTextChanged(filteredText))
                },
                modifier = Modifier.focusRequester(focusRequester),
                placeholder = {
                    Text(
                        text = stringResource(R.string.username),
                        style = MaterialTheme.typography.bodyLarge
                    )
                },
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )
            )

            UsernameLengthError(state.userValidationState)
        }


        // Pin text field
        Column {
            AppTextField(
                value = state.pin,
                onValueChange = { onEvent(LoginUiEvent.PinTextChanged(it)) },
                placeholder = {
                    Text(
                        text = stringResource(R.string.log_in),
                        style = MaterialTheme.typography.bodyLarge
                    )
                },
                keyboardActions = KeyboardActions(
                    onDone = {
                        onEvent(LoginUiEvent.LogInButtonClicked)
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.NumberPassword
                ),
                visualTransformation = PasswordVisualTransformation()
            )

            PinLengthError(state.userValidationState)
        }


        // Log in button
        AppFilledButton(
            text = stringResource(R.string.log_in),
            onClick = { onEvent(LoginUiEvent.LogInButtonClicked) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )
    }
}