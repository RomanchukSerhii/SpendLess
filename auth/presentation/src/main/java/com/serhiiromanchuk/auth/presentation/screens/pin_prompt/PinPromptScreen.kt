package com.serhiiromanchuk.auth.presentation.screens.pin_prompt

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.auth.presentation.R
import com.serhiiromanchuk.auth.presentation.components.AuthHeader
import com.serhiiromanchuk.auth.presentation.components.PinKeyboard
import com.serhiiromanchuk.auth.presentation.screens.pin_prompt.handling.PinPromptAction
import com.serhiiromanchuk.auth.presentation.screens.pin_prompt.handling.PinPromptUiEvent
import com.serhiiromanchuk.auth.presentation.screens.pin_prompt.handling.PinPromptUiState
import com.serhiiromanchuk.auth.presentation.screens.registration.create_pin.components.PinIndicator
import com.serhiiromanchuk.core.presentation.designsystem.LogoutIcon
import com.serhiiromanchuk.core.presentation.designsystem.components.ErrorIconButton
import com.serhiiromanchuk.core.presentation.designsystem.theme.SpendLessTheme
import com.serhiiromanchuk.core.presentation.ui.ObserveAsActions
import com.serhiiromanchuk.core.presentation.ui.components.BaseContentLayout
import com.serhiiromanchuk.core.presentation.ui.components.LocalSystemIconsUiController
import com.serhiiromanchuk.core.presentation.ui.components.SystemIconsUiController
import org.koin.androidx.compose.koinViewModel

@Composable
fun PinPromptScreenRoot(
    navigateBack: () -> Unit,
    navigateToLogin: () -> Unit,
    viewModel: PinPromptViewModel = koinViewModel()
) {
    ObserveAsActions(viewModel.actions) { action ->
        when (action) {
            PinPromptAction.NavigateNavigateBack -> navigateBack()
            PinPromptAction.NavigateToLogin -> navigateToLogin()
        }
    }

    PinPromptScreen(
        state = viewModel.state,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun PinPromptScreen(
    state: PinPromptUiState,
    onEvent: (PinPromptUiEvent) -> Unit
) {
    CompositionLocalProvider(
        LocalSystemIconsUiController provides SystemIconsUiController(
            isNavigationBarIconsDark = !state.showError
        )
    ) {

        BaseContentLayout(
            errorMessage = if (state.showError) stringResource(R.string.error_wrong_pin) else null
        ) {
            Box {
                // LogOutButton
                ErrorIconButton(
                    icon = LogoutIcon,
                    contentDescription = stringResource(R.string.log_out),
                    onClick = { onEvent(PinPromptUiEvent.LogOutClicked) },
                    modifier = Modifier.padding(top = 16.dp).align(Alignment.TopEnd),
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 36.dp)
                ) {
                    AuthHeader(
                        title = state.userGreeting.asString(),
                        description = state.description.asString()
                    )

                    PinIndicator(
                        enteredPin = state.enteredPin,
                        modifier = Modifier.padding(top = 36.dp, bottom = 32.dp)
                    )

                    PinKeyboard(
                        onNumberClick = { onEvent(PinPromptUiEvent.NumberButtonClicked(it)) },
                        onBackspaceClick = { onEvent(PinPromptUiEvent.BackspaceButtonClicked) },
                        modifier = Modifier.padding(horizontal = 24.dp),
                        enabled = state.isKeyboardEnabled
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PinPromptScreenPreview() {
    SpendLessTheme {
        PinPromptScreen(
            state = PinPromptUiState(),
            onEvent = {}
        )
    }
}