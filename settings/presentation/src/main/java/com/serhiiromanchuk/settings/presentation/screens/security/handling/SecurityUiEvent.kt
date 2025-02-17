package com.serhiiromanchuk.settings.presentation.screens.security.handling

import com.serhiiromanchuk.settings.presentation.screens.BiometricsPrompt
import com.serhiiromanchuk.settings.presentation.screens.LockedOutDuration
import com.serhiiromanchuk.settings.presentation.screens.SessionExpiryDuration

sealed interface SecurityUiEvent {
    data class BiometricsPromptClicked(val biometricsPrompt: BiometricsPrompt) : SecurityUiEvent
    data class SessionExpiryClicked(val sessionExpiryDuration: SessionExpiryDuration) : SecurityUiEvent
    data class LockedOutClicked(val lockedOutDuration: LockedOutDuration) : SecurityUiEvent
    data object SaveButtonClicked : SecurityUiEvent
}