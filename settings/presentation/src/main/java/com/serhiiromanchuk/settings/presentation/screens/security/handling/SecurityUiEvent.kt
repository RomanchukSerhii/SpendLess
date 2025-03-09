package com.serhiiromanchuk.settings.presentation.screens.security.handling

import com.serhiiromanchuk.settings.presentation.screens.BiometricsPromptUi
import com.serhiiromanchuk.settings.presentation.screens.LockedOutDurationUi
import com.serhiiromanchuk.settings.presentation.screens.SessionExpiryDurationUi

sealed interface SecurityUiEvent {
    data class BiometricsPromptClicked(val biometricsPrompt: BiometricsPromptUi) : SecurityUiEvent
    data class SessionExpiryClicked(val sessionExpiryDuration: SessionExpiryDurationUi) : SecurityUiEvent
    data class LockedOutClicked(val lockedOutDuration: LockedOutDurationUi) : SecurityUiEvent
    data object SaveButtonClicked : SecurityUiEvent
}