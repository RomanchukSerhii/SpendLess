package com.serhiiromanchuk.auth.presentation.screens.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.auth.presentation.R
import com.serhiiromanchuk.auth.presentation.components.AuthHeader
import com.serhiiromanchuk.auth.presentation.screens.login.components.LogInForm
import com.serhiiromanchuk.auth.presentation.screens.login.handling.LoginAction.*
import com.serhiiromanchuk.auth.presentation.screens.login.handling.LoginUiEvent
import com.serhiiromanchuk.auth.presentation.screens.login.handling.LoginUiState
import com.serhiiromanchuk.core.presentation.designsystem.components.AppTextButton
import com.serhiiromanchuk.core.presentation.designsystem.components.BaseContentLayout
import com.serhiiromanchuk.core.presentation.designsystem.components.LocalSystemIconsUiController
import com.serhiiromanchuk.core.presentation.designsystem.components.SystemIconsUiController
import com.serhiiromanchuk.core.presentation.ui.ObserveAsActions
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreenRoot(
    navigateToTransactions: (username: String) -> Unit,
    navigateToRegistration: () -> Unit,
    viewModel: LoginViewModel = koinViewModel()
) {
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    ObserveAsActions(viewModel.actions) { action ->
        when (action) {
            RequestFocus -> focusRequester.requestFocus()
            is NavigateToTransactions -> {
                focusManager.clearFocus()
                navigateToTransactions(action.username)
            }
            NavigateToRegistration -> {
                focusManager.clearFocus()
                navigateToRegistration()
            }
        }
    }

    LoginScreen(
        state = viewModel.state,
        onEvent = viewModel::onEvent,
        focusRequester = focusRequester
    )
}

@Composable
private fun LoginScreen(
    state: LoginUiState,
    onEvent: (LoginUiEvent) -> Unit,
    focusRequester: FocusRequester
) {
    CompositionLocalProvider (
        LocalSystemIconsUiController provides SystemIconsUiController(
            isNavigationBarIconsDark = !state.showError
        )
    ) {
        BaseContentLayout(
            errorMessage = if (state.showError) stringResource(R.string.username_or_pin_is_incorrect) else null
        ) {
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
                    focusRequester = focusRequester
                )

                // Registration text button
                AppTextButton(
                    text = stringResource(R.string.new_to_spendless),
                    onClick = { onEvent(LoginUiEvent.RegistrationButtonClicked) },
                    modifier = Modifier.padding(top = 28.dp)
                )
            }
        }
    }
}