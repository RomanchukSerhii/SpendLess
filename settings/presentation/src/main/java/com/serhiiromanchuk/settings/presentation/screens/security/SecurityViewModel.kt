package com.serhiiromanchuk.settings.presentation.screens.security

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.serhiiromanchuk.settings.presentation.screens.BiometricsPromptUi
import com.serhiiromanchuk.settings.presentation.screens.LockedOutDurationUi
import com.serhiiromanchuk.settings.presentation.screens.SessionExpiryDurationUi
import com.serhiiromanchuk.settings.presentation.screens.security.handling.SecurityUiEvent
import com.serhiiromanchuk.settings.presentation.screens.security.handling.SecurityUiEvent.*
import com.serhiiromanchuk.settings.presentation.screens.security.handling.SecurityUiState

class SecurityViewModel : ViewModel() {

    var state by mutableStateOf(SecurityUiState())
        private set

    fun onEvent(event: SecurityUiEvent) {
        when (event) {
            is BiometricsPromptClicked -> updateBiometricsPrompt(event.biometricsPrompt)
            is LockedOutClicked -> updateLockedOutDuration(event.lockedOutDuration)
            is SessionExpiryClicked -> updateSessionExpiryDuration(event.sessionExpiryDuration)
            SaveButtonClicked -> saveSettings()
        }
    }

    private fun saveSettings() {

    }

    private fun updateBiometricsPrompt(biometricsPrompt: BiometricsPromptUi) {
        state = state.copy(biometricsPrompt = biometricsPrompt)
    }

    private fun updateLockedOutDuration(lockedOutDuration: LockedOutDurationUi) {
        state = state.copy(lockedOutDuration = lockedOutDuration)
    }

    private fun updateSessionExpiryDuration(sessionExpiryDuration: SessionExpiryDurationUi) {
        state = state.copy(sessionExpiryDuration = sessionExpiryDuration)
    }
}