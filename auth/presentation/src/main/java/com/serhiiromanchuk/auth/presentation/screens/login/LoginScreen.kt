package com.serhiiromanchuk.auth.presentation.screens.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.auth.presentation.R
import com.serhiiromanchuk.auth.presentation.screens.login.components.AuthHeader
import com.serhiiromanchuk.auth.presentation.screens.login.components.LogInForm
import com.serhiiromanchuk.auth.presentation.screens.login.handling.LoginUiEvent
import com.serhiiromanchuk.auth.presentation.screens.login.handling.LoginUiState
import com.serhiiromanchuk.core.presentation.designsystem.components.AppTextButton
import com.serhiiromanchuk.core.presentation.designsystem.components.BaseContentLayout
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreenRoot(
    onLogInClick: () -> Unit,
    onRegistrationClick: () -> Unit,
    viewModel: LoginViewModel = koinViewModel()
) {
    LoginScreen(
        state = viewModel.state,
        onEvent = viewModel::onEvent,
        onLogInClick = onLogInClick,
        onRegistrationClick = onRegistrationClick
    )
}

@Composable
private fun LoginScreen(
    state: LoginUiState,
    onEvent: (LoginUiEvent) -> Unit,
    onLogInClick: () -> Unit,
    onRegistrationClick: () -> Unit,
) {
    BaseContentLayout {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AuthHeader(
                title = stringResource(R.string.welcome_back),
                description = stringResource(R.string.enter_you_login_details),
                modifier = Modifier.padding(vertical = 36.dp)
            )

            LogInForm(
                state = state,
                onEvent = onEvent,
                onLogInClick = onLogInClick
            )

            // Registration text button
            AppTextButton(
                text = stringResource(R.string.new_to_spendless),
                onClick = onRegistrationClick,
                modifier = Modifier.padding(top = 28.dp)
            )
        }
    }
}