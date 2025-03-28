package com.serhiiromanchuk.settings.presentation.screens.security.handling

import com.serhiiromanchuk.settings.presentation.screens.security.components.BiometricsPromptUi
import com.serhiiromanchuk.settings.presentation.screens.security.components.LockedOutDurationUi
import com.serhiiromanchuk.settings.presentation.screens.security.components.SessionExpiryDurationUi

data class SecurityUiState(
    val biometricsPrompt: BiometricsPromptUi = BiometricsPromptUi.ENABLE,
    val sessionExpiryDuration: SessionExpiryDurationUi = SessionExpiryDurationUi.FIVE_MIN,
    val lockedOutDuration: LockedOutDurationUi = LockedOutDurationUi.FIFTEEN_SEC
)