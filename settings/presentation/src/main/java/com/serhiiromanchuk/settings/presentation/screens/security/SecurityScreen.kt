package com.serhiiromanchuk.settings.presentation.screens.security

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.core.presentation.designsystem.components.AppTopBar
import com.serhiiromanchuk.core.presentation.ui.components.BaseContentLayout
import com.serhiiromanchuk.core.presentation.designsystem.components.AppFilledButton
import com.serhiiromanchuk.core.presentation.ui.ObserveAsActions
import com.serhiiromanchuk.settings.presentation.R
import com.serhiiromanchuk.settings.presentation.screens.SettingsSharedViewModel
import com.serhiiromanchuk.settings.presentation.screens.security.components.SecurityContent
import com.serhiiromanchuk.settings.presentation.screens.security.handling.SecurityAction
import com.serhiiromanchuk.settings.presentation.screens.security.handling.SecurityUiEvent
import com.serhiiromanchuk.settings.presentation.screens.security.handling.SecurityUiState
import org.koin.androidx.compose.koinViewModel

@Composable
fun SecurityScreenRoot(
    navigateBack: () -> Unit,
    navigateToPinPrompt: () -> Unit,
    viewModel: SettingsSharedViewModel = koinViewModel()
) {
    ObserveAsActions(viewModel.securityActions) { action ->
        when (action) {
            SecurityAction.NavigateBack -> navigateBack()
            SecurityAction.NavigateToPinPrompt -> navigateToPinPrompt()
        }
    }
    BaseContentLayout(
        topBar = {
            AppTopBar(
                title = stringResource(R.string.security),
                onBackClick = navigateBack
            )
        }
    ) {
        SecurityScreen(
            state = viewModel.securityState,
            onEvent = viewModel::onEvent
        )
    }
}

@Composable
private fun SecurityScreen(
    state: SecurityUiState,
    onEvent: (SecurityUiEvent) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        SecurityContent(
            state = state,
            onEvent = onEvent
        )

        AppFilledButton(
            text = stringResource(R.string.save),
            onClick = { onEvent(SecurityUiEvent.SaveButtonClicked) },
            modifier = Modifier.fillMaxWidth()
        )
    }
}