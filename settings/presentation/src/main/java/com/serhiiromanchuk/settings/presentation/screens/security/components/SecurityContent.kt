package com.serhiiromanchuk.settings.presentation.screens.security.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.core.presentation.designsystem.components.AppSegmentedButton
import com.serhiiromanchuk.core.presentation.designsystem.theme.SpendLessTheme
import com.serhiiromanchuk.settings.presentation.R
import com.serhiiromanchuk.core.presentation.ui.components.SettingItem
import com.serhiiromanchuk.settings.presentation.screens.security.handling.SecurityUiEvent
import com.serhiiromanchuk.settings.presentation.screens.security.handling.SecurityUiState

@Composable
fun SecurityContent(
    state: SecurityUiState,
    onEvent: (SecurityUiEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        BiometricsSettings(
            selectedOption = state.biometricsPrompt,
            onOptionClick = { onEvent(SecurityUiEvent.BiometricsPromptClicked(it)) }
        )
        SessionSettings(
            selectedOption = state.sessionExpiryDuration,
            onOptionClick = { onEvent(SecurityUiEvent.SessionExpiryClicked(it)) }
        )

        LockedOutSettings(
            selectedOption = state.lockedOutDuration,
            onOptionClick = { onEvent(SecurityUiEvent.LockedOutClicked(it)) }
        )
    }
}

@Composable
private fun BiometricsSettings(
    selectedOption: BiometricsPromptUi,
    onOptionClick: (BiometricsPromptUi) -> Unit,
    modifier: Modifier = Modifier
) {
    SettingItem(
        title = stringResource(R.string.biometrics_for_pin_prompt),
        modifier = modifier
    ) {
        AppSegmentedButton(
            segmentOptions = BiometricsPromptUi.entries,
            selectedOption = selectedOption,
            onOptionClick = { onOptionClick(it as BiometricsPromptUi) }
        )
    }
}

@Composable
private fun SessionSettings(
    selectedOption: SessionExpiryDurationUi,
    onOptionClick: (SessionExpiryDurationUi) -> Unit,
    modifier: Modifier = Modifier
) {
    SettingItem(
        title = stringResource(R.string.session_expiry_duration),
        modifier = modifier
    ) {
        AppSegmentedButton(
            segmentOptions = SessionExpiryDurationUi.entries,
            selectedOption = selectedOption,
            onOptionClick = { onOptionClick(it as SessionExpiryDurationUi) }
        )
    }
}

@Composable
private fun LockedOutSettings(
    selectedOption: LockedOutDurationUi,
    onOptionClick: (LockedOutDurationUi) -> Unit,
    modifier: Modifier = Modifier
) {
    SettingItem(
        title = stringResource(R.string.locked_out_duration),
        modifier = modifier
    ) {
        AppSegmentedButton(
            segmentOptions = LockedOutDurationUi.entries,
            selectedOption = selectedOption,
            onOptionClick = { onOptionClick(it as LockedOutDurationUi) }
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFEF7FF
)
@Composable
private fun PreviewSecurityContent() {
    SpendLessTheme {
        SecurityContent(
            state = SecurityUiState(),
            onEvent = { }
        )
    }
}