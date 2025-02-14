package com.serhiiromanchuk.settings.presentation.screens.preferences

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.serhiiromanchuk.settings.presentation.screens.preferences.handling.PreferencesUiState

class PreferencesViewModel : ViewModel() {

    var state by mutableStateOf(PreferencesUiState())
        private set
}