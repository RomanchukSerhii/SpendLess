package com.serhiiromanchuk.settings.presentation.screens.security.handling

import com.serhiiromanchuk.settings.presentation.screens.BiometricsPromptUi
import com.serhiiromanchuk.settings.presentation.screens.LockedOutDurationUi
import com.serhiiromanchuk.settings.presentation.screens.SessionExpiryDurationUi

data class SecurityUiState(
    val biometricsPrompt: BiometricsPromptUi = BiometricsPromptUi.ENABLE,
    val sessionExpiryDuration: SessionExpiryDurationUi = SessionExpiryDurationUi.FIVE_MIN,
    val lockedOutDuration: LockedOutDurationUi = LockedOutDurationUi.FIFTEEN_SEC
)