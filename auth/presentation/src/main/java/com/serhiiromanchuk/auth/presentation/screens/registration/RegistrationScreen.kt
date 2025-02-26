package com.serhiiromanchuk.auth.presentation.screens.registration

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.auth.presentation.R
import com.serhiiromanchuk.auth.presentation.components.AuthHeader
import com.serhiiromanchuk.auth.presentation.screens.registration.components.RegistrationForm
import com.serhiiromanchuk.auth.presentation.screens.registration.handling.RegistrationAction
import com.serhiiromanchuk.auth.presentation.screens.registration.handling.RegistrationUiEvent
import com.serhiiromanchuk.auth.presentation.screens.registration.handling.RegistrationUiState
import com.serhiiromanchuk.core.presentation.designsystem.components.AppTextButton
import com.serhiiromanchuk.core.presentation.designsystem.components.BaseContentLayout
import com.serhiiromanchuk.core.presentation.designsystem.theme.SpendLessTheme
import com.serhiiromanchuk.core.presentation.ui.ObserveAsActions
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegistrationScreenRoot(
    navigateToLogIn: () -> Unit,
    navigateNext: () -> Unit,
    viewModel: RegistrationViewModel = koinViewModel()
) {
    ObserveAsActions(viewModel.actions) { action ->
        when (action) {
            RegistrationAction.NavigateToCreatePinScreen -> navigateNext()
        }
    }
    RegistrationScreen(
        state = viewModel.state,
        onEvent = viewModel::onEvent,
        onLogInClick = navigateToLogIn
    )
}

@Composable
private fun RegistrationScreen(
    state: RegistrationUiState,
    onEvent: (RegistrationUiEvent) -> Unit,
    onLogInClick: () -> Unit
) {
    BaseContentLayout {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AuthHeader(
                title = stringResource(R.string.registration_title),
                description = stringResource(R.string.registration_description),
                modifier = Modifier.padding(vertical = 36.dp)
            )

            RegistrationForm(
                state = state,
                onEvent = onEvent
            )

            // Registration text button
            AppTextButton(
                text = stringResource(R.string.already_have_an_account),
                onClick = onLogInClick,
                modifier = Modifier.padding(top = 28.dp)
            )
        }
    }
}

@Preview
@Composable
private fun RegistrationScreenPreview() {
    SpendLessTheme {
        RegistrationScreen(
            state = RegistrationUiState(),
            onEvent = {},
            onLogInClick = {}
        )
    }
}