package com.serhiiromanchuk.auth.presentation.screens.create_pin

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.auth.presentation.R
import com.serhiiromanchuk.auth.presentation.components.PinKeyboard
import com.serhiiromanchuk.auth.presentation.screens.create_pin.components.CreatePinHeader
import com.serhiiromanchuk.auth.presentation.screens.create_pin.components.PinIndicator
import com.serhiiromanchuk.auth.presentation.screens.create_pin.handling.CreatePinAction
import com.serhiiromanchuk.auth.presentation.screens.create_pin.handling.CreatePinUiEvent
import com.serhiiromanchuk.auth.presentation.screens.create_pin.handling.CreatePinUiState
import com.serhiiromanchuk.core.presentation.designsystem.components.BaseContentLayout
import com.serhiiromanchuk.core.presentation.designsystem.theme.SpendLessTheme
import com.serhiiromanchuk.core.presentation.ui.ObserveAsActions
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreatePinScreenRoot(
    navigateBack: () -> Unit,
    navigateNext: () -> Unit,
    viewModel: CreatePinViewModel = koinViewModel()
) {

    ObserveAsActions(viewModel.actions) { action ->
        when (action) {
            CreatePinAction.NavigateBack -> navigateBack()
            CreatePinAction.NavigateNext -> navigateNext()
        }
    }

    CreatePinScreen(
        state = viewModel.state,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun CreatePinScreen(
    state: CreatePinUiState,
    onEvent: (CreatePinUiEvent) -> Unit
) {
    BaseContentLayout(
        onBackPressed = { onEvent(CreatePinUiEvent.BackButtonClicked) },
        errorMessage = if (state.showError) stringResource(R.string.error_message) else null,
    ) {
        Box {
            IconButton(
                onClick = { onEvent(CreatePinUiEvent.BackButtonClicked) },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .offset(x = (-12).dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.navigate_back),
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 36.dp)
            ) {
                CreatePinHeader(screenMode = state.screenMode)

                PinIndicator(
                    enteredPin = when (state.screenMode) {
                        CreatePinUiState.ScreenMode.CreatePin -> state.createdPin
                        CreatePinUiState.ScreenMode.RepeatPin -> state.repeatedPin
                    },
                    modifier = Modifier.padding(top = 36.dp, bottom = 32.dp)
                )

                PinKeyboard(
                    onNumberClick = { onEvent(CreatePinUiEvent.NumberButtonClicked(it)) },
                    onBackspaceClick = { onEvent(CreatePinUiEvent.BackspaceButtonClicked) },
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    heightDp = 900
)
@Composable
private fun CreatePinScreenPreview() {
    SpendLessTheme {
        CreatePinScreen(
            state = CreatePinUiState(),
            onEvent = {}
        )
    }
}