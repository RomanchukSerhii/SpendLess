package com.serhiiromanchuk.settings.presentation.screens.security.handling

import com.serhiiromanchuk.settings.presentation.screens.BiometricsPrompt
import com.serhiiromanchuk.settings.presentation.screens.LockedOutDuration
import com.serhiiromanchuk.settings.presentation.screens.SessionExpiryDuration

data class SecurityUiState(
    val biometricsPrompt: BiometricsPrompt = BiometricsPrompt.ENABLE,
    val sessionExpiryDuration: SessionExpiryDuration = SessionExpiryDuration.FIVE_MIN,
    val lockedOutDuration: LockedOutDuration = LockedOutDuration.FIFTEEN_SEC
)