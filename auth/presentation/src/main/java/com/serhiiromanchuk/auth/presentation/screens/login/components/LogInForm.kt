package com.serhiiromanchuk.auth.presentation.screens.login.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.auth.presentation.R
import com.serhiiromanchuk.auth.presentation.screens.login.handling.LoginUiEvent
import com.serhiiromanchuk.auth.presentation.screens.login.handling.LoginUiState
import com.serhiiromanchuk.core.presentation.designsystem.components.AppTextField
import com.serhiiromanchuk.core.presentation.designsystem.components.FilledButton

@Composable
fun LogInForm(
    state: LoginUiState,
    onEvent: (LoginUiEvent) -> Unit,
    onLogInClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Username text field
        AppTextField(
            value = state.usernameText,
            onValueChange = { onEvent(LoginUiEvent.UsernameTextChanged(it)) },
            placeholder = {
                Text(
                    text = stringResource(R.string.username),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        )

        // Pin text field
        AppTextField(
            value = state.pinText,
            onValueChange = { onEvent(LoginUiEvent.PinTextChanged(it)) },
            placeholder = {
                Text(
                    text = stringResource(R.string.log_in),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        )

        // Log in button
        FilledButton(
            text = stringResource(R.string.log_in),
            onClick = onLogInClick,
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
        )
    }
}