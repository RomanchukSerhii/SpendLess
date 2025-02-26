package com.serhiiromanchuk.auth.presentation.screens.create_pin.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.serhiiromanchuk.auth.presentation.R
import com.serhiiromanchuk.auth.presentation.components.AuthHeader
import com.serhiiromanchuk.auth.presentation.screens.create_pin.handling.CreatePinUiState.ScreenMode

@Composable
internal fun CreatePinHeader(
    screenMode: ScreenMode,
    modifier: Modifier = Modifier
) {
    when (screenMode) {
        ScreenMode.CreatePin -> {
            AuthHeader(
                title = stringResource(R.string.create_pin),
                description = stringResource(R.string.create_pin_description),
                modifier = modifier
            )
        }
        ScreenMode.RepeatPin -> {
            AuthHeader(
                title = stringResource(R.string.repeat_you_pin),
                description = stringResource(R.string.repeat_pin_description),
                modifier = modifier
            )
        }
    }
}