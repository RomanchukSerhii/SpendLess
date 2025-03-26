package com.serhiiromanchuk.settings.presentation.screens.security.handling

import com.serhiiromanchuk.settings.presentation.screens.security.components.BiometricsPromptUi
import com.serhiiromanchuk.settings.presentation.screens.security.components.LockedOutDurationUi
import com.serhiiromanchuk.settings.presentation.screens.security.components.SessionExpiryDurationUi

sealed interface SecurityUiEvent {
    data class BiometricsPromptClicked(val biometricsPrompt: BiometricsPromptUi) : SecurityUiEvent
    data class SessionExpiryClicked(val sessionExpiryDuration: SessionExpiryDurationUi) : SecurityUiEvent
    data class LockedOutClicked(val lockedOutDuration: LockedOutDurationUi) : SecurityUiEvent
    data object SaveButtonClicked : SecurityUiEvent
}